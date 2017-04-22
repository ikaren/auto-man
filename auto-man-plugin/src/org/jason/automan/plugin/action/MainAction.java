package org.jason.automan.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.project.Project;
import org.jason.automan.plugin.ui.MainUI;

import java.awt.*;

/**
 * Created by Jason.Xia on 17/4/18.
 */
public class MainAction extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {

//        MainUI.main(null);
        ;
        MainUI dialog = new MainUI((Project) e.getDataContext().getData(DataConstants.PROJECT));
        dialog.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation((int) (screenSize.getWidth() - dialog.getWidth()) / 2, (int) (screenSize.getHeight() -
                dialog.getHeight()) / 2);
        dialog.setVisible(true);
    }
}
