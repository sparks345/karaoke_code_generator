import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
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
        mSelector.setVisible(true);
    }
}
