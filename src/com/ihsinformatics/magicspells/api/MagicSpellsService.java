/**
 * This class provides spell checking services
 */
package com.ihsinformatics.magicspells.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.ihsinformatics.magicspells.api.impl.SoundexISpellImpl;
import com.ihsinformatics.magicspells.api.impl.SpellImpl;

public class MagicSpellsService
{
	static Logger				log = Logger.getLogger ("MagicSpellsService");
	private static Properties	props	= new Properties ();
	private SpellImpl			spellCheck;
	private SoundexISpellImpl	soundex;

	public MagicSpellsService () throws FileNotFoundException, IOException, NumberFormatException
	{
		log.debug ("Loading properties...");
		getProps ().load (new FileInputStream ("magicspells.properties"));
		log.debug ("Initializing spelling services...");
		setSpellCheck (new SpellImpl (props));
		setSoundex (new SoundexISpellImpl ());
		Integer.parseInt (getProps ().getProperty ("max_suggestions", "3"));
	}

	public Properties getProps ()
	{
		return props;
	}

	public SpellImpl getSpellCheck ()
	{
		return spellCheck;
	}

	public void setSpellCheck (SpellImpl spellCheck)
	{
		this.spellCheck = spellCheck;
	}

	public SoundexISpellImpl getSoundex ()
	{
		return soundex;
	}

	public void setSoundex (SoundexISpellImpl soundex)
	{
		this.soundex = soundex;
	}
}