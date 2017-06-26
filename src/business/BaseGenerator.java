package business;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.GlobalSearchScope;

/**
 * 生成器基类
 * Created by jinjingcao on 2017/6/23.
 */
public abstract class BaseGenerator implements IGenerator {

    private final PsiFile mCurrentPsiFile;
    private final GlobalSearchScope mSearchScope;
    private final Project mProject;

    public BaseGenerator(PsiFile file) {
        mCurrentPsiFile = file;

        mSearchScope = GlobalSearchScope.fileScope(mCurrentPsiFile);
        mProject = mCurrentPsiFile.getProject();
    }



    public void run() {

        PsiFile psiFile = getCurrentPsiFile();

        PsiElement[] elements = this.getElements();

        PsiElement[] validElements = this.filter(elements);

        this.generate(validElements);
    }


    public GlobalSearchScope getSearchScope() {
        return mSearchScope;
    }

    public Project getProject() {
        return mProject;
    }

    public PsiFile getCurrentPsiFile() {
        return mCurrentPsiFile;
    }
}
