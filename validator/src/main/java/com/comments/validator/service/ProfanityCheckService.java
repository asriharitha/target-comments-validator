
package com.comments.validator.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfanityCheckService {
	
	static Map<String, String[]> words = new HashMap<>();
    static int largestWordLength = 0;

	public boolean checkBadWords(String comment) {
		
		Map<String, String[]> words = new HashMap<>();
	    int largestWordLength = 0;
		
		badWordsInit(words, largestWordLength);
		
		if(badWordsCount(comment, words, largestWordLength)>0) {
			return true;
		} else {
			return false;
		}
		
   	}

	private int badWordsCount(String comment, Map<String, String[]> words, int largestWordLength) {
		
		if(comment == null) {
			return 0;
		}
		
		comment = comment.replaceAll("1","i");
        comment = comment.replaceAll("!","i");
        comment = comment.replaceAll("3","e");
        comment = comment.replaceAll("4","a");
        comment = comment.replaceAll("@","a");
        comment = comment.replaceAll("5","s");
        comment = comment.replaceAll("7","t");
        comment = comment.replaceAll("0","o");
        comment = comment.replaceAll("9","g");

        ArrayList<String> badWords = new ArrayList<>();
        comment = comment.toLowerCase().replaceAll("[^a-zA-Z]", "");

        // iterate over each letter in the word
        for(int start = 0; start < comment.length(); start++) {
            // from each letter, keep going to find bad words until either the end of the sentence is reached, or the max word length is reached. 
            for(int offset = 1; offset < (comment.length()+1 - start) && offset < largestWordLength; offset++)  {
                String wordToCheck = comment.substring(start, start + offset);
                if(words.containsKey(wordToCheck)) {
                    // for example, if you want to say the word bass, that should be possible.
                    String[] ignoreCheck = words.get(wordToCheck);
                    boolean ignore = false;
                    for(int s = 0; s < ignoreCheck.length; s++ ) {
                        if(comment.contains(ignoreCheck[s])) {
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
		return badWords.size();
	}

	private void badWordsInit(Map<String, String[]> words, int largestWordLength) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
												getClass().getResourceAsStream("/badWords.csv")));
			String line = "";
			int counter = 0;
			while((line = reader.readLine()) != null) {
				counter++;
				String[] content = null;
				try {
					content = line.split(",");
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
					words.put(word.replace(" ", ""), ignore_in_combination_with_words);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			System.out.println("Loaded " + counter + " words to filter out");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}