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
            targetPath = "/";
        }

        configBean.setTargetPath(targetPath);
        settingUI = new SettingUI(configBean);
        return settingUI.$$$getRootComponent$$$();
    }

    @Override
    public boolean isModified() {
        return !this.settingUI.getTargetPathTxt().getText().equals(autoManPersistConfig.getTargetPath());
    }

    @Override
    public void apply() throws ConfigurationException {
        autoManPersistConfig.setTargetPath(this.settingUI.getTargetPathTxt().getText());
    }

    @Override
    public void reset() {
        if (!autoManPersistConfig.getTargetPath().equals(settingUI.getTargetPathTxt().getText())) {
            settingUI.getTargetPathTxt().setText(autoManPersistConfig.getTargetPath());
        }
    }

    @Override
    public void disposeUIResources() {
        this.settingUI = null;
    }
}
