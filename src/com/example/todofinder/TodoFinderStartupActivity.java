package com.example.todofinder;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.wm.ToolWindowManager;

public class TodoFinderStartupActivity implements StartupActivity {
    @Override
    public void runActivity(Project project) {
        ToolWindowManager.getInstance(project).invokeLater(() -> {
            ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Todo Finder");
            if (toolWindow != null) {
                toolWindow.show();
            }
        });
    }
}