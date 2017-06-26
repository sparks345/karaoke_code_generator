package business;

import com.intellij.psi.PsiElement;

/**
 * 代码生成器核心接口
 * Created by jinjingcao on 2017/6/23.
 */
public interface IGenerator {
    PsiElement[] getElements();
    PsiElement[] filter(PsiElement[] input);
    void generate(PsiElement[] validElements);
    void run();
}
