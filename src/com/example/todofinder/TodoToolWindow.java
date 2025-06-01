package com.example.todofinder;

import com.example.todofinder.models.TodoItem;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.markup.*;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TodoToolWindow {
    private JPanel content;
    private JList<TodoItem> todoList;

    public TodoToolWindow(Project project, PsiFile file) {
        content = new JPanel(new BorderLayout());
        List<TodoItem> todos = TodoScanner.scan(file);

        todoList = new JList<>(todos.toArray(new TodoItem[0]));
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        content.add(new JScrollPane(todoList), BorderLayout.CENTER);

        todoList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                TodoItem item = todoList.getSelectedValue();
                if (item != null) {
                    VirtualFile vFile = file.getVirtualFile();
                    FileEditorManager.getInstance(project).openFile(vFile, true);
                    Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
                    if (editor != null) {
                        LogicalPosition pos = new LogicalPosition(item.line, 0);
                        editor.getCaretModel().moveToLogicalPosition(pos);
                        editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
                    }
                }
            }
        });

        highlightTodos(project, file, todos);
    }

    private void highlightTodos(Project project, PsiFile file, List<TodoItem> todos) {
        Editor[] editors = EditorFactory.getInstance().getAllEditors();
        for (Editor editor : editors) {
            if (editor.getDocument().equals(PsiDocumentManager.getInstance(project).getDocument(file))) {
                MarkupModel markupModel = editor.getMarkupModel();
                for (TodoItem todo : todos) {
                    TextAttributes attr = new TextAttributes();
                    attr.setBackgroundColor(new Color(255, 255, 150));
                    markupModel.addLineHighlighter(todo.line, HighlighterLayer.ADDITIONAL_SYNTAX, attr);
                }
            }
        }
    }

    public JPanel getContent() {
        return content;
    }
}