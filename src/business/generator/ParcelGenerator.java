package business.generator;

import business.BaseGenerator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * 生成Parcel类代码
 * Created by jinjingcao on 2017/6/23.
 */
public class ParcelGenerator extends BaseGenerator {
    @Override
    public PsiElement[] getElements(PsiFile file) {
        return new PsiElement[0];
    }

    @Override
    public PsiElement[] filter(PsiElement[] input, PsiFile currentPsiFile) {
        return new PsiElement[0];
    }

    @Override
    public void generate(PsiFile currentPsiFile) {

    }
}
