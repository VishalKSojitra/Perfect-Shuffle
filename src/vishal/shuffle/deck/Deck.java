package vishal.shuffle.deck;

import java.util.Arrays;


public class Deck {
	public String[] deckOfCards;
	final String[] suits = { "Diamonds", "Clubs", "Hearts", "Spades"};
    final String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	
    // Constructor which initializes deck with 52 cards
	public Deck() {
		int index = 0;
		deckOfCards = new String[52];
	    for(String suit : suits) {
	    	for(String rank : ranks) {
	    		deckOfCards[index++] = rank + " of " + suit;
	    	}
	    }
	}
	
	// Constructor which initializes deck with given size
	public Deck(int i) {
		deckOfCards = new String[i];
		for(int index = 0; index < i; index++) {
			deckOfCards[index] = String.valueOf(index);
		}
	}
	
	// Return top card in deck
	public String topCard() {
		return deckOfCards[0];
	}
	
	// Return bottom card in deck
	public String bottomCard() {
		return deckOfCards[this.size()-1];
	}
	
	// Return size of deck
	public int size() {
		return deckOfCards.length;
	}
	
	// Return index of given card in the given deck
	public int indexOfCard(String card) {
		for(int i = 0; i < this.size(); i++) {
			if(deckOfCards[i].equals(card)) {
				return i; 
			}
		}
		return -1;
	}
	
	//Return card at given index in the given deck
	public String cardAt(int index) {
		if(index <= this.size()) {
			return deckOfCards[index];
		}
		throw new IndexOutOfBoundsException("Enter Valid Index");
	}
	
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < this.size(); i++) {
			str.append(deckOfCards[i] + "\n");
		}
		return str.toString();
	}
	
	// Return number of shuffle it took to reach a card to the given position 
	public int anyCardToAnyPositin(int fromPosition, int toPosition) {
		if(fromPosition < this.size() && toPosition < this.size()) {
		String card = cardAt(fromPosition); 
		int index = 0;
		int maxNumberOfShuffleToGetSameDeck = this.numberOfShuffleToGetSameDeck();
		while(indexOfCard(card) != toPosition) {
			inShuffle(1);
			index++;
			if(index > maxNumberOfShuffleToGetSameDeck) {
				return -1;
			}
		}
		return index;
		}
		throw new IndexOutOfBoundsException("Enter Valid Index");
	}
	
	// return index of card after nth number of shuffle
	public int anyCardAfterNthShuffle(String card, int numberOfShuffle) {
		inShuffle(numberOfShuffle);
		return indexOfCard(card);
	}
	
	// Return after how many shuffle given to cards touch each other
	public int whenOneCradTouchToOtherCard(String firstCard, String SecondCard) {
		if(this.indexOfCard(firstCard) != -1 && this.indexOfCard(SecondCard) != -1) {
		int index = 0;
		int maxNumberOfShuffleToGetSameDeck = this.numberOfShuffleToGetSameDeck();
		while(Math.abs((indexOfCard(firstCard) - indexOfCard(SecondCard))) != 1) {
			inShuffle(1);
			index++;
			if(index > maxNumberOfShuffleToGetSameDeck) {
				return -1;
			}
		}
		return index;
		}
		throw new IndexOutOfBoundsException("Enter Valid Card");
	}
	
	// Return number of shuffle it took to get back same deck as given
	public int numberOfShuffleToGetSameDeck() {
		String[] unShuffledDeck = this.deckOfCards.clone();
		int count = 0;
		while(true) {
			if(count != 0 && Arrays.equals(unShuffledDeck, this.deckOfCards)) {
				return count;
			}
			this.inShuffle(1);
			count++;
		}
	}
	
	
	// Perform In Shuffle
	public void inShuffle(int numberOfShuffle) {
		int lengthOfDeck = deckOfCards.length;
		String[] topHalfOfDeck = new String[lengthOfDeck / 2];
		String[] bottomHalfOfDeck = new String[lengthOfDeck - lengthOfDeck / 2];
		int i;
		while(numberOfShuffle > 0) {
			for(i = 0; i < lengthOfDeck / 2; i++) {
				topHalfOfDeck[i] = deckOfCards[i];
			}
			for(int j = 0; i < lengthOfDeck; i++, j++) {
				bottomHalfOfDeck[j] = deckOfCards[i];
			}
			
			for(i = 0; i < topHalfOfDeck.length; i++) {
				deckOfCards[i * 2 + 1] = topHalfOfDeck[i];
			}
			for(i = 0; i < bottomHalfOfDeck.length; i++) {
				deckOfCards[i * 2] = bottomHalfOfDeck[i];
			}
			numberOfShuffle--;
		}
	}
}
