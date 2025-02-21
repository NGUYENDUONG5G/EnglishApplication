package org.example.myenglish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LearningApp {
    private Dictionary dictionary = new Dictionary();
    private List<Word> mistakeMemory = new ArrayList<>();
    private HashMap<String, Boolean> usedWord = new HashMap<>();
    private Random random = new Random();

    public LearningApp() {
    }

    public String wordProvider() {
        if(usedWord.keySet().size()==dictionary.getDictWord().size()) return null;
        if (dictionary.getDictWord().isEmpty()) return null;

        List<String> keys = new ArrayList<>(dictionary.getDictMean().keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));
        while (usedWord.containsKey(randomKey)) {
            randomKey = keys.get(random.nextInt(keys.size()));
        }
        usedWord.put(randomKey,true);

        return randomKey;
    }
    public boolean enterWord(String name,String content){
        if(content.isEmpty()) return false;
        Word word=dictionary.getDictMean().get(name);

        if(!word.getWord().equals(content)) {
            word.setMistakeMeaning(content);
            mistakeMemory.add(word);
        }
        return true;
    }

    public List<Word> getMistakeMemory() {
        return mistakeMemory;
    }
}
