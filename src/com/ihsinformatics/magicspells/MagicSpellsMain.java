/**
 * 
 */

package com.ihsinformatics.magicspells;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.ihsinformatics.magicspells.api.MagicSpellsService;
import com.ihsinformatics.magicspells.api.Suggestion;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class MagicSpellsMain
{
	static Logger				log = Logger.getLogger ("MagicSpellsMain");
	public static final String	INDEX_DIRECTORY		= "res/index";
	public static final String	DICTIONARY_FILE		= "res/dict_en.txt";
	public static final int		SUGGESTION_LIMIT	= 5;

	public static void main (String[] args)
	{
		try
		{
			MagicSpellsService service = new MagicSpellsService ();
			Suggestion suggestion = service.getSoundex ().getSuggestion ("Owais Ahmed Hussain");
			System.out.println (suggestion.getSuggestedWord ());
		}
		catch (NumberFormatException e)
		{
			log.error ("Trying to initialize MagicSpellService: " + e.getMessage ());
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			log.error ("Trying to initialize MagicSpellService: " + e.getMessage ());
			e.printStackTrace();
		}
		catch (IOException e)
		{
			log.error ("Trying to initialize MagicSpellService: " + e.getMessage ());
			e.printStackTrace();
		}
	}
}
