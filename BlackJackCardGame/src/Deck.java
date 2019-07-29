import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;

public class Deck {

	ArrayList<Card> deck; 
	Random rand; 
	
	public Deck() {
		this.deck = new ArrayList<>();
		this.rand = new Random();
	}
	
	public void addCard(Card card) {
		
		this.deck.add(card);
	}
	
	public void removeCard(Card card) {
		
		this.deck.remove(card);
	}
	
	public void populateDeck() {
		
		int index = 1;
		for(int j=14; j>=2; j--) {
			for(int i=0; i<4; i++) {
				
				Card card = new Card(j,Suit.values()[i],new ImageIcon("pictures/" + index + ".png").getImage());
				card.setValue();
				addCard(card);
				index++;
			}
		}
	}
	
	public void shuffle() {
		
		for(int i=0; i<this.deck.size(); i++) {
			
			int rando = rand.nextInt(this.deck.size());
			Collections.swap(this.deck,i,rando);
		}
	}
	
	public ArrayList<Card> getDeck(){
		
		return this.deck;
	}
}