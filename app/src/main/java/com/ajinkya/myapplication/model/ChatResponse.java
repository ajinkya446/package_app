package com.ajinkya.myapplication.model;

public class ChatResponse {
    private String answer;
    private String answer_en;
    private String language;
    private String question;
    private String question_en;

    public ChatResponse(String answer, String answer_en, String language, String question, String question_en) {
        this.answer = answer;
        this.answer_en = answer_en;
        this.language = language;
        this.question = question;
        this.question_en = question_en;
    }
// Getter Methods

    public String getAnswer() {
        return answer;
    }

    public String getAnswer_en() {
        return answer_en;
    }

    public String getLanguage() {
        return language;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestion_en() {
        return question_en;
    }

    // Setter Methods 

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAnswer_en(String answer_en) {
        this.answer_en = answer_en;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestion_en(String question_en) {
        this.question_en = question_en;
    }
}