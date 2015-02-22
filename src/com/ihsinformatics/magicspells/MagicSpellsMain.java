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

    public static void main(String[] args) {
	try {
	    String[] words = { "@lpha", "b3ta", "gama", "De1t@", "Eps!l0n" };
	    MagicSpellsService service = new MagicSpellsService();
	    ISpell normalize = service.getNormalize();
	    ISpell soundex = service.getSoundex();
	    ISpell spell = service.getSpellCheck();
	    for (String word : words) {
		Suggestion suggestion = normalize.getSuggestion(word);
		System.out.println(suggestion.getSuggestedWord());
	    }
	    for (String word : words) {
		Suggestion suggestion = soundex.getSuggestion(word);
		System.out.println(suggestion.getSuggestedWord());
	    }
	    for (String word : words) {
		Suggestion suggestion = spell.getSuggestion(word);
		System.out.println(suggestion.getSuggestedWord());
	    }
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
}
