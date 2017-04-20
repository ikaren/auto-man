package org.jason.automan.plugin.ui;


import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jason.automan.ProgressListener;
import org.jason.automan.plugin.AutoManPersistConfig;
import org.jason.automan.startup.AutoManBoot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;

public class MainUI extends JDialog {
    private final AutoManPersistConfig autoManPersistConfig;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField targetPathTxt;
    private JButton targetPathSelectBtn;
    private JTextField configFileTxt;
    private JButton configFileSelectBtn;
    private JTextArea progressingTxtArea;
    private JProgressBar progressBar1;
    private JPanel panel1;
    private JScrollPane scrollPane;
    private boolean isRunning = false;

    public MainUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        targetPathSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selected = openFileChooser(new File(MainUI.this.targetPathTxt.getText()));
                if (!selected.isDirectory()) {
                    Messages.showMessageDialog("selected not a directory", "error", Messages.getErrorIcon());
                    return;
                }

                MainUI.this.targetPathTxt.setText(selected.getAbsolutePath());
            }
        });

        configFileSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selected = openFileChooser(new File(MainUI.this.configFileTxt.getText()));
                if (!selected.getAbsolutePath().endsWith(".xml")) {
                    Messages.showMessageDialog("expect .xml file", "error", Messages.getErrorIcon());
                    return;
                }

                MainUI.this.configFileTxt.setText(selected.getAbsolutePath());
            }
        });

        this.autoManPersistConfig = AutoManPersistConfig.getInstance();
        this.targetPathTxt.setText(autoManPersistConfig.getTargetPath());
        this.configFileTxt.setText(this.autoManPersistConfig.getLastSelectedConfigFilePath());

    }

    public static void main(String[] args) {
        MainUI dialog = new MainUI();
        dialog.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation((int) (screenSize.getWidth() - dialog.getWidth()) / 2, (int) (screenSize.getHeight() -
                dialog.getHeight()) / 2);
        dialog.setVisible(true);
    }

    private void onOK() {
        start();
        URL resource1 = this.getClass().getResource("/template");
        AutoManBoot.createNewProject(this.targetPathTxt.getText() + "/", this.configFileTxt.getText(),
                resource1.getFile(), new AutoManProgressListener(this.progressBar1, this.progressingTxtArea));
        this.progressingTxtArea.append(System.out.toString());
        this.autoManPersistConfig.setLastSelectedConfigFilePath(this.configFileTxt.getText());
        completed();
    }

    private void completed() {
        this.progressBar1.setValue(100);
        this.isRunning = false;
        this.buttonOK.setEnabled(true);
    }

    private void start() {
        this.progressingTxtArea.setText("...");
        this.progressBar1.setValue(0);
        this.buttonOK.setEnabled(false);
        this.isRunning = true;
    }

    private void onCancel() {
        if (isRunning) {
//            Messages.showDialog("项目正在生成,现在停止将可能会导致项目代码无法完整生成,确认停止？","确认停止",new String[]{,"CANCEL"},)
        }

        dispose();
    }

    private File openFileChooser(File currentDir) {
        JFileChooser fileChooser = new JFileChooser();
        if (null != currentDir) {
            fileChooser.setCurrentDirectory(currentDir);
        }

        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showDialog(new JLabel(), "choose");
        return fileChooser.getSelectedFile();
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
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null,
                null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel2.add(panel3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints
                .SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel3.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel3.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1,
                450), null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder("Auto Man"));
        final JLabel label1 = new JLabel();
        label1.setText("Target Path:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetPathTxt = new JTextField();
        panel1.add(targetPathTxt, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new
                Dimension(350, -1), null, 0, false));
        targetPathSelectBtn = new JButton();
        targetPathSelectBtn.setText("Select");
        panel1.add(targetPathSelectBtn, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
                .SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Config File:");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        configFileTxt = new JTextField();
        panel1.add(configFileTxt, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new
                Dimension(150, -1), null, 0, false));
        configFileSelectBtn = new JButton();
        configFileSelectBtn.setText("Select");
        panel1.add(configFileSelectBtn, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
                .SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        progressBar1 = new JProgressBar();
        panel1.add(progressBar1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null,
                null, 0, false));
        scrollPane = new JScrollPane();
        scrollPane.setAutoscrolls(true);
        scrollPane.setDoubleBuffered(true);
        panel1.add(scrollPane, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0,
                false));
        progressingTxtArea = new JTextArea();
        progressingTxtArea.setEditable(false);
        progressingTxtArea.setLineWrap(true);
        progressingTxtArea.setText("...");
        scrollPane.setViewportView(progressingTxtArea);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    class AutoManProgressListener implements ProgressListener {

        private final JProgressBar progressBar;
        private final JTextArea textArea;

        public AutoManProgressListener(JProgressBar progressBar, JTextArea textArea) {
            this.progressBar = progressBar;
            this.textArea = textArea;
        }

        @Override
        public void update(double increase) {
            int current = this.progressBar.getValue();
            if (100 < current + increase) {
                this.progressBar.setValue(100);
            } else {
                this.progressBar.setValue((int) (current + increase));
            }
        }

        @Override
        public void update(String comment) {
            this.textArea.append(comment + "\n");
        }
    }
}
