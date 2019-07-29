import java.awt.Image;

public class Card {

	private int value; 
	private Suit suit;
	private String stringValue;
	private Image image;
	
	public Card(int value,Suit suit,Image image) {
		
		this.value = value; 
		this.suit = suit;
		this.image = image;
	}
	
	public void setValue() {
		
		if(this.value == 11) {
			stringValue = "Jack";
		}else if (this.value == 12) {
			stringValue = "Queen";
		}else if (this.value == 13) {
			stringValue = "King";
		}else if (this.value == 14) {
			stringValue = "Ace";
		}else {
			stringValue = Integer.toString(this.value);
		}
	}
	
	public int cardValue() {
		
		int cardValue = 0;
		
		if(this.value == 11 || this.value == 12 || this.value == 13) {
			
			cardValue = 10;
		}else if(this.value == 14) {
			cardValue = 11;
		}else {
			cardValue = this.value;
		}
		return cardValue;
	}
	
	public Image getImage() {
		
		return this.image;
	}
	
	public String getValue() {
		
		return stringValue;
	}
	
	public String toString() {
		
		return this.stringValue + " of " + this.suit.toString() +"\n";
	}
}