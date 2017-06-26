package business;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * 代码生成器核心接口
 * Created by jinjingcao on 2017/6/23.
 */
public interface IGenerator {
    PsiElement[] getElements(PsiFile file);
    PsiElement[] filter(PsiElement[] input, PsiFile currentPsiFile);
    void generate(PsiElement[] validElements, PsiFile currentPsiFile);
    void run();
}
