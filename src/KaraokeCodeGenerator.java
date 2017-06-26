import com.intellij.lang.PsiBuilderUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import ui.GeneratorSelector;

/**
 * 菜单入口
 * Created by jinjingcao on 2017/6/23.
 */
public class KaraokeCodeGenerator extends AnAction {

    private GeneratorSelector mSelector;

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        if (mSelector == null) {
            mSelector = new GeneratorSelector();
        }
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        PsiFile currentPsiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        mSelector.setContextPsiFile(currentPsiFile);
        mSelector.setVisible(true);
        mSelector.setSize(500, 500);
        mSelector.requestFocus();
    }
}
