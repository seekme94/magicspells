package com.ihsinformatics.magicspells;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ihsinformatics.magicspells.api.MagicSpellsService;
import com.ihsinformatics.magicspells.api.NormalizeImpl;

public class NormalizeImplTest {

    NormalizeImpl normalizeImpl;

    @Before
    public void setUp() throws Exception {
	MagicSpellsService service = new MagicSpellsService();
	normalizeImpl = service.getNormalize();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testGetSuggestion() {
	assertTrue(normalizeImpl.getSuggestion("alph@").getSuggestedWord()
		.equals("alpha"));
	assertTrue(normalizeImpl.getSuggestion("83t@").getSuggestedWord()
		.equals("beta"));
	assertTrue(normalizeImpl.getSuggestion("g@mm@").getSuggestedWord()
		.equals("gamma"));
	assertTrue(normalizeImpl.getSuggestion("d31t@").getSuggestedWord()
		.equals("delta"));
	assertTrue(normalizeImpl.getSuggestion("3p$!10n").getSuggestedWord()
		.equals("epsilon"));
    }

    @Test
    public final void testGetSuggestions() {
	// Not applicable
    }

    @Test
    public final void testRemoveNonAlpha() {
	assertTrue(normalizeImpl.removeNonAlpha("3p$!10n").equals("pn"));
    }

    @Test
    public final void testRemoveNonAlphanumeric() {
	assertTrue(normalizeImpl.removeNonAlphanumeric("3p$!10n").equals("3p10n"));
    }
}
