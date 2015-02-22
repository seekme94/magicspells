/**
 * This class is implementation ISpell and provides spell check function. The class uses Levenshtein distance to compute closeness between given string and dictionary to suggest closest matches
 */

package com.ihsinformatics.magicspells.api.impl;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
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
public final class SpellImpl implements ISpell {
    static Logger log = Logger.getLogger("SpellImpl");
    int maxSuggestions;
    Directory directory;
    Dictionary dictionary;
    SpellChecker spellChecker;

    /**
     * Constructor. Initializes directory for indexing and dictionary
     * 
     * @param props
     *            properties file Object
     * @throws IOException
     *             when dictionary or directory path is/are invalid or
     *             inaccessible
     */
    public SpellImpl(Properties props) throws IOException {
	directory = FSDirectory.open(new File(props.getProperty(
		"index_directory", "res/index")));
	dictionary = new PlainTextDictionary(new File(props.getProperty(
		"dictionary_path", "res/dict_en.txt")));
	spellChecker = new SpellChecker(directory);
	StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
	IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36,
		analyzer);
	spellChecker.clearIndex();
	spellChecker.indexDictionary(dictionary, config, true);
	spellChecker.setAccuracy(Float.parseFloat(props.getProperty(
		"default_accuracy", "0.75")));
	setMaxSuggestions(Integer.parseInt(props.getProperty("max_suggestions",
		"5")));
    }

    public void shutDown() {
	try {
	    spellChecker.close();
	} catch (IOException e) {
	    log.error(e.getMessage());
	    e.printStackTrace();
	}
    }

    @Override
    public Suggestion getSuggestion(String word) {
	return getSuggestions(word)[0];
    }

    @Override
    public Suggestion[] getSuggestions(String word) {
	return getSuggestions(word, getMaxSuggestions());
    }

    public Suggestion[] getSuggestions(String word, int num) {
	if ("".equals(word))
	    return new Suggestion[] { new Suggestion("", 0) };
	Suggestion[] suggestions = null;
	try {
	    String[] alternates = spellChecker.suggestSimilar(word,
		    getMaxSuggestions());
	    suggestions = new Suggestion[alternates.length];
	    for (int i = 0; i < alternates.length; i++)
		suggestions[i] = new Suggestion(alternates[i], spellChecker
			.getStringDistance().getDistance(word, alternates[i]));
	} catch (IOException e) {
	    log.error(e.getMessage());
	}
	return suggestions;
    }

    public int getMaxSuggestions() {
	return maxSuggestions;
    }

    public void setMaxSuggestions(int maxSuggestions) {
	this.maxSuggestions = maxSuggestions;
    }

    /**
     * Breaks the text into
     * 
     * @param text
     * @return
     */
    public String[] tokenize(String text) {
	String[] tokens = text.split("[,.;:?! ]");
	return tokens;
    }

    public String iAmFeelingLucky(String text) {
	// TODO
	return text;
    }

    /**
     * Returns benchmark result of spelling suggestions from given sample and
     * respective correct text. Helps you determine how well the API functions
     * on your text
     * 
     * @param sampleText
     *            some text including spelling mistakes
     * @param correctText
     *            correctly spelled version of provided sample text
     * @return accuracy of correctness between 0 and 1
     */
    public float benchmark(String sampleText, String correctText) {
	float accuracy = 0;
	String suggestedText = iAmFeelingLucky(sampleText);
	int len1 = suggestedText.length();
	int len2 = correctText.length();
	if (suggestedText.equals(correctText))
	    return accuracy;
	// Tokenize strings and compute distance of each term
	// TODO:

	return accuracy;
    }
}
