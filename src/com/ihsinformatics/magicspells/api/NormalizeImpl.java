/**
 * This class normalizes strings into their close-to dictionary form. For example, (ha1k80@rd will convert into chalkboard
 */

package com.ihsinformatics.magicspells.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.ihsinformatics.magicspells.util.FileUtil;
import com.ihsinformatics.magicspells.util.RegexUtil;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class NormalizeImpl implements ISpell {
    public static Map<Character, Character> charMap;

    public NormalizeImpl(Properties props) {
	String mapFilePath = props.getProperty("character_map_file");
	FileUtil fileUtil = new FileUtil();
	String[] pairs = fileUtil.getLines(mapFilePath);
	charMap = new HashMap<Character, Character>();
	for (String pair : pairs) {
	    String[] split = pair.split("=");
	    if (split.length != 2)
		continue;
	    String character = split[0];
	    String mappedCharacter = split[1];
	    charMap.put(character.charAt(0), mappedCharacter.charAt(0));
	}
    }

    /**
     * Equivalent to <code>getSuggestions (String word)</code>
     */
    @Override
    public Suggestion getSuggestion(String word) {
	int change = 0;
	char[] wordArr = word.toCharArray();
	for (int i = 0; i < wordArr.length; i++) {
	    char ch = wordArr[i];
	    if (charMap.containsKey(ch)) {
		wordArr[i] = charMap.get(ch);
		change++;
	    }
	}
	Suggestion suggestion = new Suggestion(new String(wordArr), change
		/ word.length());
	return suggestion;
    }

    /**
     * Equivalent to <code>getSuggestion (String word)</code>
     */
    @Override
    public Suggestion[] getSuggestions(String word) {
	return new Suggestion[] { getSuggestion(word) };
    }

    /**
     * Returns clean string after removing special characters and digits
     * 
     * @param word
     * @return
     */
    public String removeNonAlpha(String word) {
	return word.replaceAll(RegexUtil.alphaPattern, "");
    }

    /**
     * Returns clean string after removing special characters
     * 
     * @param word
     * @return
     */
    public String removeNonAlphanumeric(String word) {
	return word.replaceAll(RegexUtil.alphaNumPattern, "");
    }
}
