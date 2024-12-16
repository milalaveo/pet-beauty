package com.example.petbeauty.command;

public class Router {
    private String page = "index.jsp";
    private Type type = Type.REDIRECT;

    public enum Type {
        FORWARD, REDIRECT
    }

    public Router() {}

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }

    public boolean isRedirect() {
        return this.type == Type.REDIRECT;
    }
}