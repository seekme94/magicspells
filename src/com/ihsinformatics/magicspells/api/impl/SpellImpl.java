/**
 * 
 */
package com.ihsinformatics.magicspells.api.impl;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import com.ihsinformatics.magicspells.api.ISpell;
import com.ihsinformatics.magicspells.api.Suggestion;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public final class SpellImpl implements ISpell
{
	int				maxSuggestions;
	Directory		directory;
	Dictionary		dictionary;
	SpellChecker	spellchecker;

	public SpellImpl (Properties props) throws IOException
	{
		directory = FSDirectory.open (new File (props.getProperty ("index_directory", "res/index")));
		dictionary = new PlainTextDictionary (new File (props.getProperty ("dictionary_path", "res/dict_en.txt")));
		spellchecker = new SpellChecker (directory);
		StandardAnalyzer analyzer = new StandardAnalyzer (Version.LUCENE_36);
		IndexWriterConfig config = new IndexWriterConfig (Version.LUCENE_36, analyzer);
		spellchecker.clearIndex ();
		spellchecker.indexDictionary (dictionary, config, true);
		spellchecker.setAccuracy (Float.parseFloat (props.getProperty ("default_accuracy", "0.75")));
	}

	@Override
	public Suggestion getSuggestion (String word)
	{
		return getSuggestions (word)[0];
	}

	@Override
	public Suggestion[] getSuggestions (String word)
	{
		Suggestion[] suggestions = null;
		try
		{
			String[] alternates = spellchecker.suggestSimilar (word, getMaxSuggestions ());
			suggestions = new Suggestion[alternates.length];
			for (int i=0; i< alternates.length; i++)
				suggestions[i] = new Suggestion (alternates[i], getMaxSuggestions ());
			spellchecker.close ();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return suggestions;
	}

	public int getMaxSuggestions ()
	{
		return maxSuggestions;
	}

	public void setMaxSuggestions (int maxSuggestions)
	{
		this.maxSuggestions = maxSuggestions;
	}
}
