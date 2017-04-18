package org.jason.automan.plugin;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Jason.Xia on 17/4/18.
 */
@State(name = "autoManPersistConfig", storages = {@Storage(file = "autoManPersistConfig.xml")})
public class AutoManPersistConfig implements PersistentStateComponent<AutoManPersistConfig> {
    private String targetPath;

    public static AutoManPersistConfig getInstance() {
        return ServiceManager.getService(AutoManPersistConfig.class);
    }

    @Nullable
    @Override
    public AutoManPersistConfig getState() {
        return this;
    }

    @Override
    public void loadState(AutoManPersistConfig autoManPersistConfig) {
        XmlSerializerUtil.copyBean(autoManPersistConfig, this);
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
