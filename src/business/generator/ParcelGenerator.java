package business.generator;

import business.BaseGenerator;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiMethodImpl;
import com.intellij.psi.search.GlobalSearchScope;

import java.util.ArrayList;

/**
 * 生成Parcel类代码
 * Created by jinjingcao on 2017/6/23.
 */
public class ParcelGenerator extends BaseGenerator {
    public ParcelGenerator(PsiFile file) {
        super(file);
    }

    @Override
    public PsiElement[] getElements(PsiFile file) {
        return file.getChildren();
    }

    @Override
    public PsiElement[] filter(PsiElement[] input, PsiFile currentPsiFile) {
        ArrayList<PsiElement> retList = new ArrayList<>();
        for (PsiElement elem : input) {
            if (elem instanceof PsiField) {
                retList.add(elem);
            }
        }
        return retList.toArray(new PsiElement[0]);
    }

    @Override
    public void generate(PsiElement[] validElements, PsiFile currentPsiFile) {
        GlobalSearchScope searchScope = GlobalSearchScope.fileScope(currentPsiFile);
        Project project = currentPsiFile.getProject();
        PsiClass clazz = JavaPsiFacade.getInstance(project).findClass(currentPsiFile.getName(), searchScope);
        PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();

        for (PsiElement elem : validElements) {
            if (elem instanceof PsiField) {
                PsiField field = (PsiField) elem;
                PsiMethod method = factory.createMethodFromText("public void set" + field.getName(), clazz);
                currentPsiFile.add(method);
            }
        }

//        currentPsiFile.

    }
}
