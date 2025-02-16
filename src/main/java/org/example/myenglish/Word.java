package org.example.myenglish;

import java.util.HashMap;

public class Word {
    private String word;
    private String pronunciation;
    private String type;
    private String meaning;
    private String mistakeMeaning=null;

    public Word(String word, String pronunciation, String typeOfWord, String meaning) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.type = typeOfWord;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getType() {
        return type;
    }

    public String getMistakeMeaning() {
        return mistakeMeaning;
    }

    public void setMistakeMeaning(String mistakeMeaning) {
        this.mistakeMeaning = mistakeMeaning;
    }
}
