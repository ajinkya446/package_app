package com.ajinkya.myapplication;

public class ChatParams {
    public String language;
    public String question;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ChatParams(String language, String question) {
        this.language = language;
        this.question = question;
    }
}
