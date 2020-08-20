package vishal.shuffle.deck;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckTest {
	
	private Deck deck;
	
	@BeforeEach
	public void setUp() {
		deck = new Deck();
	}
	
	@Test
	void testIndexOfCard() {
		assertEquals(0, deck.indexOfCard(deck.topCard()), "Top card need to be at 0 position");
		assertEquals(51, deck.indexOfCard(deck.bottomCard()), "Bottom card need to be at 52 position");
		assertEquals(-1, deck.indexOfCard("NO"), "Position must be -1 if card not in deck");
	}

	@Test
	void testCardAt() {
		assertEquals("A of Diamonds", deck.cardAt(0), "A of Diamonds need to be at 0 position");
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.cardAt(53));
		assertTrue(exception.getMessage().equals("Enter Valid Index"));
		
	}

	@Test
	void testSize() {
		assertEquals(52, deck.size(), "Deck size need to be 52");
		Deck deck = new Deck(10);
		assertEquals(10, deck.size(), "Deck size need to be 10");
	}

	@Test
	void testAnyCardToAnyPositin() {
		assertEquals(0, deck.anyCardToAnyPositin(0, 0), "");
		assertEquals(26, deck.anyCardToAnyPositin(0, 51));
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.anyCardToAnyPositin(0, 53));
		assertTrue(exception.getMessage().equals("Enter Valid Index"));
		exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.anyCardToAnyPositin(53, 0));
		assertTrue(exception.getMessage().equals("Enter Valid Index"));
		Deck deck = new Deck(5);
		assertEquals(-1,deck.anyCardToAnyPositin(0, 4), "Must return -1");
	}

	@Test
	void testAnyCardAfterNthShuffle() {
		assertEquals(21, deck.anyCardAfterNthShuffle(deck.topCard(), 7), "Top card at 21th position after 7 shuffle");
		assertEquals(0, deck.anyCardAfterNthShuffle(deck.topCard(), 0), "Top card at 0th position after 0 shuffle");
	}

	@Test
	void testWhenOneCradTouchToOtherCard() {
		assertEquals(25,deck.whenOneCradTouchToOtherCard(deck.topCard(), deck.bottomCard()), "It take 25 shuffle to touch top and bottom card");
		assertNotEquals(-1, deck.whenOneCradTouchToOtherCard(deck.topCard(), deck.bottomCard()), "Must not be -1 shuffle to touch top and bottom card");
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.whenOneCradTouchToOtherCard("one", deck.bottomCard()));
		assertTrue(exception.getMessage().equals("Enter Valid Card"));
		exception = assertThrows(IndexOutOfBoundsException.class, () -> deck.whenOneCradTouchToOtherCard(deck.topCard(), "bottom"));
		assertTrue(exception.getMessage().equals("Enter Valid Card"));
	}

	@Test
	void testInShuffle() {
		Deck shuffledDeck = new Deck();
		shuffledDeck.inShuffle(2);
		deck.inShuffle(2);
		assertEquals(deck.toString(), shuffledDeck.toString(), "Must be same after same shuffle");
		deck.inShuffle(1);
		assertNotEquals(deck.toString(), shuffledDeck.toString(), "Must not be same after shuffle");
	}
	
	@Test
	void testNumberOfShuffleToGetSameDeck() {
		assertEquals(52, deck.numberOfShuffleToGetSameDeck(), "Must be 52 shuffle to get back same deck");
		Deck deck = new Deck(5);
		assertEquals(4, deck.numberOfShuffleToGetSameDeck(), "Must be 4 shuffle to get back same deck");
	}

}
