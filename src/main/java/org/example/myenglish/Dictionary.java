package org.example.myenglish;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {
    private static HashMap<String, Word> dict = new HashMap<>();

    public Dictionary() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\MyEnglish\\src\\main\\resources\\listWord.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\t");
                if (parts.length == 4) {
                    Word word = new Word(parts[0], parts[1], parts[2], parts[3]);
                    dict.put(parts[0], word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Word> getDict() {
        return dict;
    }

    public static void saveWordToDatabase(Word word) {
        if (word == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }


        try (BufferedWriter fw = new BufferedWriter(new FileWriter("D:\\MyEnglish\\src\\main\\resources\\listWord.txt"))) {
            fw.write(word.getWord());
            fw.write("\t");
            fw.write(word.getPronunciation());
            fw.write("\t");
            fw.write(word.getType());
            fw.write("\t");
            fw.write(word.getMeaning());
            fw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveWordToDatabase() {
        try (BufferedWriter fw = new BufferedWriter(new FileWriter("D:\\MyEnglish\\src\\main\\resources\\listWord.txt"))) {
            for (Word word : dict.values()) {

                fw.write(word.getWord());
                fw.write("\t");
                fw.write(word.getPronunciation());
                fw.write("\t");
                fw.write(word.getType());
                fw.write("\t");
                fw.write(word.getMeaning());
                fw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addWord(String nameWord, String pronunciation, String typeOfWord, String meaning) {

        if (dict.containsKey(nameWord) ||
                nameWord.length()==0 ||
                pronunciation.length()==0 ||
                typeOfWord.length()==0 ||
                meaning.length()==0
        ) return false;
        Word word = new Word(nameWord, pronunciation, typeOfWord, meaning);
        dict.put(nameWord, word);
        saveWordToDatabase(word);
        return true;
    }

    public boolean updateWord(String name, String locationUpdate, String content) {
        if (content == null) return false;
        Word word = dict.get(name);
        if (locationUpdate.equals("name")) {
            word.setWord(content);

        } else if (locationUpdate.equals("pronunciation")) {
            word.setPronunciation(content);
            return true;
        } else if (locationUpdate.equals("type")) {
            word.setType(content);

        } else if (locationUpdate.equals("meaning")) {
            word.setMeaning(content);

        }
        saveWordToDatabase();
        return true;
    }

    public boolean deleteWord(String name) {
        if (!dict.containsKey(name)) return false;
        dict.remove(name);
        saveWordToDatabase();
        return true;

    }

}

