package com.example.learnjapanwords;

public class Words {

    private String translate;
    private String japan;
    private String word;


    public Words(String translate, String japan) {
        this.translate = translate;
        this.japan = japan;
    }

    public String getWord() {
        return this.word;
    }

    public String getFlip() {
        return "Перевод";
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate(){
        return this.translate;
    }

    public String getJapan(){
        return this.japan;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public void setJapan(String japan) {
        this.japan = japan;
    }

}
