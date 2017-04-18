package org.jason.automan.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

/**
 * Created by Jason.Xia on 17/4/18.
 */
public class ProjectAutoMan extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Messages.showMessageDialog("test clicked", "test title", Messages.getInformationIcon());
    }
}
