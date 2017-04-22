package org.jason.automan.plugin.ui;


import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jason.automan.ProgressListener;
import org.jason.automan.parser.ActionParams;
import org.jason.automan.plugin.AutoManPersistConfig;
import org.jason.automan.startup.AutoManBoot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainUI extends JDialog {
    private final AutoManPersistConfig autoManPersistConfig;
    private final Project currentProject;
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
    private JTabbedPane tabbedPane1;
    private JTextField curProjectPathTxt;
    private JTextField tableConfigPathTxt;
    private JButton tableConfigSelectBtn;
    private JTextArea addTbaleProgressTxt;
    private JTextField projectNameTxt;
    private JTextPane tipsProjectNameAndTextPane;
    private boolean isRunning = false;

    //    public static void main(String[] args) {
//        MainUI dialog = new MainUI();
//        dialog.pack();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        dialog.setLocation((int) (screenSize.getWidth() - dialog.getWidth()) / 2, (int) (screenSize.getHeight() -
//                dialog.getHeight()) / 2);
//        dialog.setVisible(true);
//    }

    public MainUI(Project project) {
        this.currentProject = project;
        String basePath = currentProject.getBasePath();
        this.curProjectPathTxt.setText(basePath);
        assert basePath != null;
        int i = basePath.lastIndexOf("/");
        if (i != -1) {
            String[] split = basePath.substring(i).split("-");
            this.projectNameTxt.setText(split[split.length - 1]);
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(tabbedPane1.getSelectedIndex());
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

        tableConfigSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File selected = openFileChooser(new File(MainUI.this.tableConfigPathTxt.getText()));
                if (!selected.getAbsolutePath().endsWith(".xml")) {
                    Messages.showMessageDialog("expect .xml file", "error", Messages.getErrorIcon());
                    return;
                }

                MainUI.this.tableConfigPathTxt.setText(selected.getAbsolutePath());
            }
        });

        this.autoManPersistConfig = AutoManPersistConfig.getInstance();
        this.targetPathTxt.setText(autoManPersistConfig.getTargetPath());
        this.configFileTxt.setText(this.autoManPersistConfig.getLastSelectedConfigFilePath());
    }

    private void onOK(int tabIndex) {
        switch (tabIndex) {
            case 0:
                createNew();
                break;
            case 1:
                addTable();
                break;
        }
    }

    private void createNew() {
        start(0);
        URL resPath = this.getClass().getResource("/template");
        ActionParams params = new ActionParams(this.targetPathTxt.getText() + "/", this.configFileTxt.getText()
                , resPath.getFile(), new AutoManProgressListener(this.progressBar1, this.progressingTxtArea));
        AutoManBoot.create(ActionParams.Action.CREATE_PROJECT, params);
        this.autoManPersistConfig.setLastSelectedConfigFilePath(this.configFileTxt.getText());
        completed(0);
    }

    private void addTable() {
        start(1);
        URL resPath = this.getClass().getResource("/template");
        ActionParams params = new ActionParams(this.curProjectPathTxt.getText() + "/", this.tableConfigPathTxt.getText()
                , resPath.getFile(), new AutoManProgressListener(null, this.addTbaleProgressTxt));
        Map<String, String> extra = new HashMap<>();
        extra.put("projectName", this.projectNameTxt.getText());
        params.setExtra(extra);
        AutoManBoot.create(ActionParams.Action.ADD_NEW_DOMAIN, params);
        completed(1);
    }

    private void completed(int tabIndex) {
        this.isRunning = false;
        this.buttonOK.setEnabled(true);
        switch (tabIndex) {
            case 0:
                this.progressBar1.setValue(100);
                break;
        }
    }

    private void start(int tabIndex) {
        switch (tabIndex) {
            case 0:
                this.progressingTxtArea.setText("...");
                this.progressBar1.setValue(0);
                break;
            case 1:
                this.addTbaleProgressTxt.setText("...");
                break;
        }

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
        contentPane.setPreferredSize(new Dimension(579, 450));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null,
                null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, true));
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
        tabbedPane1 = new JTabbedPane();
        contentPane.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0,
                false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Create New", panel4);
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints
                .SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder("Auto Man"));
        final JLabel label1 = new JLabel();
        label1.setText("Target Path:");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        targetPathTxt = new JTextField();
        panel1.add(targetPathTxt, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new
                Dimension(150, -1), null, 0, false));
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
        progressingTxtArea.setPreferredSize(new Dimension(533, 450));
        progressingTxtArea.setText("...");
        scrollPane.setViewportView(progressingTxtArea);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setPreferredSize(new Dimension(-1, -1));
        tabbedPane1.addTab("Add Table", panel5);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setName("");
        panel5.add(panel6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints
                .SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 300), null, 0,
                false));
        panel6.setBorder(BorderFactory.createTitledBorder("Auto Man"));
        final JLabel label3 = new JLabel();
        label3.setText("Current Project:");
        panel6.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        curProjectPathTxt = new JTextField();
        curProjectPathTxt.setEditable(false);
        panel6.add(curProjectPathTxt, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new
                Dimension(150, -1), null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.add(panel7, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints
                .SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel7.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints
                .FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0,
                false));
        addTbaleProgressTxt = new JTextArea();
        addTbaleProgressTxt.setDoubleBuffered(true);
        addTbaleProgressTxt.setEditable(false);
        addTbaleProgressTxt.setLineWrap(true);
        addTbaleProgressTxt.setPreferredSize(new Dimension(533, 400));
        addTbaleProgressTxt.setText("...");
        scrollPane1.setViewportView(addTbaleProgressTxt);
        final JLabel label4 = new JLabel();
        label4.setText("Table Config:");
        panel6.add(label4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tableConfigPathTxt = new JTextField();
        panel6.add(tableConfigPathTxt, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new
                Dimension(150, -1), null, 0, false));
        tableConfigSelectBtn = new JButton();
        tableConfigSelectBtn.setText("Select");
        panel6.add(tableConfigSelectBtn, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
                .SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Project Name:");
        panel6.add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        projectNameTxt = new JTextField();
        panel6.add(projectNameTxt, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints
                .FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new
                Dimension(150, -1), null, 0, false));
        tipsProjectNameAndTextPane = new JTextPane();
        tipsProjectNameAndTextPane.setEditable(false);
        tipsProjectNameAndTextPane.setEnabled(true);
        tipsProjectNameAndTextPane.setText("tips:\n[project name] and [package name] must same as <project " +
                "project-name=\"...\" package-name=\"...\"> when you create the project in your [Table Config] xml " +
                "file.\n    you should correct this values to right if not match.");
        panel6.add(tipsProjectNameAndTextPane, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints
                .SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
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

        AutoManProgressListener(JProgressBar progressBar, JTextArea textArea) {
            this.progressBar = progressBar;
            this.textArea = textArea;
        }

        @Override
        public void update(double increase) {
            if (null == progressBar) {
                return;
            }

            int current = this.progressBar.getValue();
            if (100 < current + increase) {
                this.progressBar.setValue(100);
            } else {
                this.progressBar.setValue((int) (current + increase));
            }
        }

        @Override
        public void update(String comment) {
            if (null == textArea) {
                return;
            }

            this.textArea.append(comment + "\n");
        }
    }
}
