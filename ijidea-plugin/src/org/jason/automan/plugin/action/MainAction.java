package org.jason.automan.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jason.automan.plugin.ui.MainUI;

/**
 * Created by Jason.Xia on 17/4/18.
 */
public class MainAction extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        MainUI.main(null);
    }
}
