package com.example.common;


public class Abbreviation {

    public static String abbreviation(String input) {
        StringBuilder result = new StringBuilder();
        String[] words = input.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(word.charAt(0));
            }
        }
        return result.toString().toUpperCase();
    }
}