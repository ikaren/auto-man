package org.jason.automan.plugin.ui;

import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jason.automan.plugin.bean.ConfigBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jason.Xia on 17/4/18.
 */
public class SettingUI {
    public JPanel panelMain;
    private JTextField targetPathTxt;
    private JButton selectBtn;
    private ConfigBean configBean;

    public SettingUI(ConfigBean configBean) {
        this.configBean = configBean;
        this.selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Messages.showMessageDialog("clicked this button", "title", Messages.getInformationIcon());
            }
        });
    }


    public JTextField getTargetPathTxt() {
        return targetPathTxt;
    }

    public void setTargetPathTxt(JTextField targetPathTxt) {
        this.targetPathTxt = targetPathTxt;
    }

    public JComponent getRootComponentWithInit() {
        init(this.configBean);
        return panelMain;
    }

    public void init(ConfigBean configBean) {
        this.targetPathTxt.setText(configBean.getTargetPath());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Target Path");
        panelMain.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetPathTxt = new JTextField();
        targetPathTxt.setText("");
        panelMain.add(targetPathTxt, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new
                Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        selectBtn = new JButton();
        selectBtn.setText("select");
        panelMain.add(selectBtn, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }
}
