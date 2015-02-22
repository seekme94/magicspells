/**
 * This class is an implementation of Soundex algorithm, which converts words with different spellings into a common form
 */

package com.ihsinformatics.magicspells.api.impl;

import org.apache.log4j.Logger;
import com.ihsinformatics.magicspells.api.ISpell;
import com.ihsinformatics.magicspells.api.Suggestion;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class SoundexISpellImpl implements ISpell {
    static Logger log = Logger.getLogger("SoundexISpellImpl");

    public SoundexISpellImpl() {
    }

    /**
     * Apply Sound Expression algorithm and return transformed string
     * 
     * @param word
     * @return
     */
    @Override
    public Suggestion getSuggestion(String word) {
	log.debug("Converting " + word + " to sound index.");
	Suggestion suggestion = new Suggestion();
	String temp = word.substring(1);
	String correctedWord = temp.replaceAll("[aeiouyAEIOUY](?!\\b)", "");
	correctedWord = correctedWord.replaceAll("[bfpvBFPV](?!\\b)", "1");
	correctedWord = correctedWord.replaceAll("[cgjkqsxzCGJKQSXZ](?!\\b)",
		"2");
	correctedWord = correctedWord.replaceAll("[dtDT](?!\\b)", "3");
	correctedWord = correctedWord.replaceAll("[lL](?!\\b)", "4");
	correctedWord = correctedWord.replaceAll("[mnMN](?!\\b)", "5");
	correctedWord = correctedWord.replaceAll("[rR](?!\\b)", "5");
	suggestion.setSuggestedWord(word.substring(0, 1) + correctedWord);
	suggestion.setDistance(1);
	return suggestion;
    }

    /**
     * Equivalent to <code>getSuggestions (String word)</code>
     */
    @Override
    public Suggestion[] getSuggestions(String word) {
	return new Suggestion[] { getSuggestion(word) };
    }
}
