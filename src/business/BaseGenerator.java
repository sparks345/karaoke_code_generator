package business;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * 生成器基类
 * Created by jinjingcao on 2017/6/23.
 */
public abstract class BaseGenerator implements IGenerator {

    private final PsiFile mCurrentPsiFile;

    public BaseGenerator(PsiFile file) {
        mCurrentPsiFile = file;
    }

    public void run() {

        PsiFile psiFile = getCurrentPsiFile();

        PsiElement[] elements = this.getElements(psiFile);

        PsiElement[] validElements = this.filter(elements, psiFile);

        this.generate(validElements, psiFile);
    }

    private PsiFile getCurrentPsiFile() {
        return mCurrentPsiFile;
    }
}
