package com.ihsinformatics.magicspells;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.ihsinformatics.magicspells.api.MagicSpellsService;
import com.ihsinformatics.magicspells.api.impl.SoundexISpellImpl;

public class SoundexSpellImplTest {
    SoundexISpellImpl spellImpl;

    @Before
    public void setUp() throws Exception {
	MagicSpellsService service = new MagicSpellsService();
	spellImpl = service.getSoundex();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testGetSuggestion() {
	assertTrue(spellImpl.getSuggestion("Hussain").equals(
		spellImpl.getSuggestion("Hossain")));
	assertTrue(spellImpl.getSuggestion("Hussain").equals(
		spellImpl.getSuggestion("Hossein")));
	assertTrue(spellImpl.getSuggestion("Husain").equals(
		spellImpl.getSuggestion("Hosein")));
    }

    @Test
    public final void testGetSuggestions() {
	// TODO
    }
}
