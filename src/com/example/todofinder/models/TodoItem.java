package com.example.todofinder.models;

public class TodoItem {
    public final String text;
    public final int line;

    public TodoItem(String text, int line) {
        this.text = text;
        this.line = line;
    }

    @Override
    public String toString() {
        return "Line " + (line + 1) + ": " + text;
    }
}