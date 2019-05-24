package com.comments.validator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfanityCheckService {
	
	static Map<String, String[]> words = new HashMap<>();
    static int largestWordLength = 0;

	public boolean checkBadWords(String comment) {
		
        int counter = 0;
        while(comment!= null) {
            counter++;
            String[] content = null;
            try {
                content = comment.split(",");
                if(content.length == 0) {
                    continue;
                }
                String word = content[0];
                String[] ignore_in_combination_with_words = new String[]{};
                if(content.length > 1) {
                    ignore_in_combination_with_words = content[1].split("_");
                }

                if(word.length() > largestWordLength) {
                    largestWordLength = word.length();
                }
                words.put(word.replaceAll(" ", ""), ignore_in_combination_with_words);

            } catch(Exception e) {
                e.printStackTrace();
            }

        }
        
		ArrayList<String> badWords = badWordsFound(comment,words);
        if(badWords.size() > 0) {
            return false;
        }
        return true;
	}
	
        public static ArrayList<String> badWordsFound(String input, Map<String,String[]> words) {
        if(input == null) {
            return new ArrayList<>();
        }

        input = input.replaceAll("1","i");
        input = input.replaceAll("!","i");
        input = input.replaceAll("3","e");
        input = input.replaceAll("4","a");
        input = input.replaceAll("@","a");
        input = input.replaceAll("5","s");
        input = input.replaceAll("7","t");
        input = input.replaceAll("0","o");
        input = input.replaceAll("9","g");

        ArrayList<String> badWords = new ArrayList<>();
        input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");

        // iterate over each letter in the word
        for(int start = 0; start < input.length(); start++) {
            // from each letter, keep going to find bad words until either the end of the sentence is reached, or the max word length is reached. 
            for(int offset = 1; offset < (input.length()+1 - start) && offset < largestWordLength; offset++)  {
                String wordToCheck = input.substring(start, start + offset);
                if(words.containsKey(wordToCheck)) {
                    // for example, if you want to say the word bass, that should be possible.
                    String[] ignoreCheck = words.get(wordToCheck);
                    boolean ignore = false;
                    for(int s = 0; s < ignoreCheck.length; s++ ) {
                        if(input.contains(ignoreCheck[s])) {
                            ignore = true;
                            break;
                        }
                    }
                    if(!ignore) {
                        badWords.add(wordToCheck);
                    }
                }
            }
        }
        return badWords;
    }
}