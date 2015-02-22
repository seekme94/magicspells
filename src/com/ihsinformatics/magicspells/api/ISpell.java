/**
 * This interface is implemented by Spelling correction classes
 */
package com.ihsinformatics.magicspells.api;

public interface ISpell {
    Suggestion getSuggestion(String word);

    Suggestion[] getSuggestions(String word);
}
