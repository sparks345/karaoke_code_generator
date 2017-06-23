package business;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.javadoc.PsiDocComment;

/**
 * 代码生成器核心接口
 * Created by jinjingcao on 2017/6/23.
 */
public interface IGenerator {
    PsiElement[] getElements(PsiFile file);
    PsiElement[] filter(PsiElement[] input, PsiFile currentPsiFile);
    void generate(PsiFile currentPsiFile);
}
