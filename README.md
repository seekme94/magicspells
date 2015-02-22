# MagicSpells
A simple API for Spell checking

## Features
- Logging and custom properties
- Converts strings into respective index by sound using _Soundex_ algorithm
- Suggests words closest to a given string using dictionary
- Replaces special characters and digits with closest alphabets, for example, *a1ph@* will be converted to *alpha*

## Known issues
- Compatible only with English dictionary
- Finds punctuation marks seriously offensive

## Pre-requisites
The API has been developed and tested on Java 1.7. However, it should be compatible on 1.6 too. The dependency libraries include:
- *log4j-1.2.15* for logging
- *lucene-core-3.6.0.jar* and *lucene-spellchecker-3.6.0.jar* for spell check


## Usage
Running examples are provided in MagicSpellsMain. Snippets are to help write boilerplate code:

### Normalization snippet
NormalizeImpl class provides functions to remove funky letters from text. This might be used as a pre-processor before spell check.

```
public static void main(String[] args) {
	try {
		MagicSpellsService service = new MagicSpellsService();
		String[] words = { "@lpha", "b3ta", "gamma", "de1t@", "3p$!l0n" };
		ISpell normalize = service.getNormalize();
		System.out.println("\nNormalizing...");
		for (String word : words) {
		    Suggestion suggestion = normalize.getSuggestion(word);
		    System.out.println(suggestion.getSuggestedWord());
		}
		catch (IOException e) {
	    log.error("Trying to initialize MagicSpellService: "
		    + e.getMessage());
	    e.printStackTrace();
	}
}
```

### Soundex snippet
SoundexImpl class provides indexing methods for English text. The output of a word is a code, which is consistent for multiple pronunciations of the same word.

```
public static void main(String[] args) {
	try {
		MagicSpellsService service = new MagicSpellsService();
		String[] words = { "michael", "micheal", "michelle", "michail", "meekhail" };
		ISpell soundex = service.getSoundex();
		System.out.println("\nSound indexing...");
		for (String word : words) {
		    Suggestion suggestion = soundex.getSuggestion(word);
		    System.out.println(suggestion.getSuggestedWord());
		}
		catch (IOException e) {
	    log.error("Trying to initialize MagicSpellService: "
		    + e.getMessage());
	    e.printStackTrace();
	}
}
```

### Spell check snippet
SpellImpl class uses Lucene library to check for spelling mistakes. The res/dict_en.txt is the dictionary 

```
public static void main(String[] args) {
	try {
		MagicSpellsService service = new MagicSpellsService();
		String[] words = { "wierd", "definate", "wheather", "acommodate",
			"bizzare", "seperate" };
		ISpell spell = service.getSpellCheck();
		System.out.println("\nSpell check...");
		for (String word : words) {
		    Suggestion suggestion = spell.getSuggestion(word);
		    System.out.println(suggestion.getSuggestedWord());
		}
		catch (IOException e) {
	    log.error("Trying to initialize MagicSpellService: "
		    + e.getMessage());
	    e.printStackTrace();
	}
}
```