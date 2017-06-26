package business.generator;

import business.BaseGenerator;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.RunResult;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.psi.*;
import com.intellij.ui.awt.RelativePoint;

import java.awt.*;
import java.util.ArrayList;

/**
 * 生成Parcel类代码
 * Created by jinjingcao on 2017/6/23.
 */
public class ParcelGenerator extends BaseGenerator {
    private final PsiClass mClazz;
    private final PsiElementFactory mFactory;

    public ParcelGenerator(PsiFile file) {
        super(file);

        //mClazz = JavaPsiFacade.getInstance(getProject()).findClass(getCurrentPsiFile().getName(), getSearchScope());
        if (!(getCurrentPsiFile() instanceof PsiJavaFile)) {
            throw new IllegalArgumentException("current psi file not a JAVA file.");
        }

        PsiClass[] tmpClasses = ((PsiJavaFile) getCurrentPsiFile()).getClasses();
        if (tmpClasses.length <= 0) {
            throw new IllegalArgumentException("current psi file not have a CLASS define.");
        }

        mClazz = tmpClasses[0];

        mFactory = JavaPsiFacade.getInstance(getProject()).getElementFactory();

    }

    @Override
    public PsiElement[] getElements() {
        return mClazz != null ? mClazz.getFields() : new PsiElement[0];
    }

    @Override
    public PsiElement[] filter(PsiElement[] input) {
        ArrayList<PsiElement> retList = new ArrayList<>();
        for (PsiElement elem : input) {
            if (elem instanceof PsiField) {
                retList.add(elem);
            }
        }
        return retList.toArray(new PsiElement[0]);
    }

    @Override
    public void generate(PsiElement[] validElements) {

        RunResult result = new WriteCommandAction.Simple(getProject(),
                getCurrentPsiFile()) {
            @Override
            protected void run() throws Throwable {
                for (PsiElement elem : validElements) {
                    if (elem instanceof PsiField) {
                        PsiField field = (PsiField) elem;
                        // 判断是否有方法已存在
                        if (mClazz.findMethodsByName("set" + field.getName(), false).length <= 0) {
                            PsiMethod method = mFactory
                                    .createMethodFromText("public void set" + field.getName() + "() {}", mClazz);
                            mClazz.add(method);
                        }
                    }
                }
            }
        }.execute();

        RelativePoint rp = new RelativePoint(new Point(300, 300));
        if (result.hasException()) {
            JBPopupFactory.getInstance().createMessage(result.getThrowable().getMessage()).show(rp);
        } else {
            JBPopupFactory.getInstance().createMessage("generated OKAY.").show(rp);
        }
    }
}
