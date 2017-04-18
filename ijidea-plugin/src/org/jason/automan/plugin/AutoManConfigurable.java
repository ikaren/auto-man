package org.jason.automan.plugin;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jason.automan.plugin.bean.ConfigBean;
import org.jason.automan.plugin.ui.SettingUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by Jason.Xia on 17/4/18.
 */
public class AutoManConfigurable implements Configurable {
    private final AutoManPersistConfig autoManPersistConfig;
    private SettingUI settingUI;

    public AutoManConfigurable() {
        this.autoManPersistConfig = AutoManPersistConfig.getInstance();
    }

//    @NotNull
//    @Override
//    public String getId() {
//        return "automan.setting";
//    }
//
//    @Nullable
//    @Override
//    public Runnable enableSearch(String s) {
//        return null;
//    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Auto Man Setting";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "help topic";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        ConfigBean configBean = new ConfigBean();

        String targetPath = autoManPersistConfig.getTargetPath();
        if (null == targetPath) {
            targetPath = "~/AutoManProject/";
        }

        configBean.setTargetPath(targetPath);
        settingUI = new SettingUI(configBean);
        return settingUI.getRootComponentWithInit();
    }

    @Override
    public boolean isModified() {
        return !this.settingUI.getTargetPathTxt().getText().equals(autoManPersistConfig.getTargetPath());
    }

    @Override
    public void apply() throws ConfigurationException {
//        ConfigBean configBean = new ConfigBean();
//        configBean.setTargetPath(this.settingUI.getTargetPathTxt().getText());
        autoManPersistConfig.setTargetPath(this.settingUI.getTargetPathTxt().getText());
    }

    @Override
    public void reset() {
        settingUI.getTargetPathTxt().setText("~/AutoManProject/");
    }

    @Override
    public void disposeUIResources() {
        this.settingUI = null;
    }
}
