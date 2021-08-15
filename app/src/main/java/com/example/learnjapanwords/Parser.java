package com.example.learnjapanwords;

public class Parser {

    private String text = "Заполните блок";
    private boolean result;
    private String errorMessage = "0";


    private boolean isEmpty(String word) {
        return word.equals("");
    }

    private boolean isRussia(String word) {
        result = false;
        if (isEmpty(word)) {
            errorMessage = text;
            return result;
        }
        for (char ch : word.toCharArray()) {
            if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.CYRILLIC) {
                result = true;
            } else {
                errorMessage = "В русском присутствуют другие символы";
                result = false;
            }
        }
        return result;
    }

    private boolean isJapan(String word) {
        result = false;
        for (char ch: word.toCharArray()) {
            if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.HIRAGANA || Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.KATAKANA) {
                result = true;
            } else {
                errorMessage = "В японском присутствуют другие символы";
                result = false;
            }
        }
        return result;
    }

    public boolean checkWords(String russia, String japan) {
        if(!isRussia(russia)) {
            return false;
        }
        if (!isJapan(japan)) {
            return false;
        }
        return true;
    }

//    public boolean checkSingleString(String text) {
//        if (!isRussia(text))
//            return false;
//        if (!isJapan(text))
//            return false;
//        return true;
//    }

    public String getErrorMessage() {
        return errorMessage;
    }


}
