import java.util.ArrayList;

public class Player {

	private ArrayList<Card>playerDeck;
	
	public Player() {
		this.playerDeck = new ArrayList<>();
	}
	
	public void addCard(Card card) {
		
		this.playerDeck.add(card);
	}
	
	public int handValue() {
		
		int total = 0;
		
		for(int i=0; i<this.playerDeck.size(); i++) {
			
			total = total + this.playerDeck.get(i).cardValue();
		}
		return total;
	}
	
	public ArrayList<Card> showPlayerCards() {
		
		return this.playerDeck;
	}
}