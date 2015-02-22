/**
 * 
 */

package com.ihsinformatics.magicspells;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.ihsinformatics.magicspells.api.ISpell;
import com.ihsinformatics.magicspells.api.MagicSpellsService;
import com.ihsinformatics.magicspells.api.Suggestion;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class MagicSpellsMain {
    static Logger log = Logger.getLogger("MagicSpellsMain");
    // Main service class to
    static MagicSpellsService service;

    public static void main(String[] args) {
	try {
	    log.debug("Initializing MagicSpell services...");
	    service = new MagicSpellsService();
	    tryNormalizeImpl();
	    trySoundexImpl();
	    trySpellImpl();
	} catch (NumberFormatException e) {
	    log.error("Trying to initialize MagicSpellService: "
		    + e.getMessage());
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    log.error("Trying to initialize MagicSpellService: "
		    + e.getMessage());
	    e.printStackTrace();
	} catch (IOException e) {
	    log.error("Trying to initialize MagicSpellService: "
		    + e.getMessage());
	    e.printStackTrace();
	}
    }

    public static void tryNormalizeImpl() {
	String[] words = { "@lpha", "b3ta", "gamma", "de1t@", "3p$!l0n" };
	ISpell normalize = service.getNormalize();
	System.out.println("\nNormalizing...");
	for (String word : words) {
	    Suggestion suggestion = normalize.getSuggestion(word);
	    System.out.println(suggestion.getSuggestedWord());
	}
    }

    public static void trySoundexImpl() {
	String[] words = { "michael", "micheal", "michelle", "michail",
		"meekhail" };
	ISpell soundex = service.getSoundex();
	System.out.println("\nSound indexing...");
	for (String word : words) {
	    Suggestion suggestion = soundex.getSuggestion(word);
	    System.out.println(suggestion.getSuggestedWord());
	}
    }

    public static void trySpellImpl() {
	String[] words = { "wierd", "definate", "wheather", "acommodate",
		"bizzare", "seperate" };
	ISpell spell = service.getSpellCheck();
	System.out.println("\nSpell check...");
	for (String word : words) {
	    Suggestion suggestion = spell.getSuggestion(word);
	    System.out.println(suggestion.getSuggestedWord());
	}
    }
}
