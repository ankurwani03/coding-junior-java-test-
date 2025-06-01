package com.example.todofinder;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.*;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import javax.swing.*;

public class TodoToolWindowFactory implements ToolWindowFactory, DumbAware {
    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        VirtualFile[] files = FileEditorManager.getInstance(project).getSelectedFiles();
        if (files.length == 0) return;

        VirtualFile file = files[0];
        if (!file.getName().endsWith(".kt")) return;

        PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
        if (psiFile == null) return;

        TodoToolWindow window = new TodoToolWindow(project, psiFile);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(window.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}