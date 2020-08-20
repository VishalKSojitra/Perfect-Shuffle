package vishal.shuffle.deck;

public class Shuffle {

	public static void main(String[] args) {
		Deck deck = new Deck();
		
		// In Shuffle
		System.out.println("Deck Before In Shuffle : \n" + deck.toString());
		deck.inShuffle(2);
		System.out.println("Deck After One In Shuffle : \n" + deck.toString());
		
		
		// What is the position of the first card after the 7th shuffle?
		System.out.println("First Card After 7th Shuffle : " + deck.anyCardAfterNthShuffle(deck.topCard(), 7));
		
	
		// How many times must one perform the shuffle so that the top card becomes the bottom card?
		System.out.println("Top Card Becomes Bottom Card After " + deck.anyCardToAnyPositin(0, deck.indexOfCard(deck.bottomCard())) + " Shuffles");
		
		
		// When do the first and last cards in the deck touch?
		System.out.println("Top Card Touch Bottom Card After " + deck.whenOneCradTouchToOtherCard(deck.topCard(), deck.bottomCard()) + " Shuffles");
		
		// Number of shuffle it take to get back same deck 
	    System.out.println("Number of Shuffle To Get Same Deck Back " + deck.numberOfShuffleToGetSameDeck());
	}
}
