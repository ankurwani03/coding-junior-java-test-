package com.example.todofinder;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;

import com.example.todofinder.models.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class TodoScanner {
    public static List<TodoItem> scan(PsiFile psiFile) {
        List<TodoItem> todos = new ArrayList<>();
        psiFile.accept(new PsiRecursiveElementVisitor() {
            @Override
            public void visitComment(PsiComment comment) {
                String text = comment.getText();
                if (text.contains("TODO")) {
                    Document doc = PsiDocumentManager.getInstance(psiFile.getProject()).getDocument(psiFile);
                    if (doc != null) {
                        int line = doc.getLineNumber(comment.getTextOffset());
                        todos.add(new TodoItem(text.trim(), line));
                    }
                }
            }
        });
        return todos;
    }
}