package org.jason.automan.plugin.component;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Jason.Xia on 17/4/23.
 */
public class AppComponent implements ApplicationComponent {
    public AppComponent() {
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "AppComponent";
    }
}
