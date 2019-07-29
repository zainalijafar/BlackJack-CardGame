import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI extends JPanel implements Runnable,ActionListener{

	private static final long serialVersionUID = 1L;
	private static final int Width = 600; 
	private static final int Height = 600;
	
	private Deck deck;
	private Player player; 
	private Player dealer;
	
	private boolean playerWins = false; 
	private boolean dealerWins = false;
	private boolean dealerBusted = false; 
	private boolean dealerBlackJack = false;
	private boolean gameOver = false;
	
	private boolean showCards = false;
	
	JFrame Jframe = new JFrame();
	
	public GUI() {
		
		Jframe.setTitle("Card Game");
		
		setPreferredSize(new Dimension(Width,Height));
		JButton hit = new JButton("HIT");
		JButton stand = new JButton("STAND");

		hit.setBounds(450,200,80,80);
		stand.setBounds(450,300,80,80);
		
		Jframe.add(hit);
		Jframe.add(stand);
		
		JLabel player = new JLabel("Player");
		JLabel dealer = new JLabel("Dealer");
		player.setBounds(150,30,100,100);
		player.setForeground(Color.RED);
		player.setFont(new Font("Arial", Font.BOLD, 20));
		Jframe.add(player);
		
		dealer.setBounds(150,320,100,100);
		dealer.setForeground(Color.RED);
		dealer.setFont(new Font("Arial", Font.BOLD, 20));
		Jframe.add(dealer);
		
		start();
		Jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Jframe.add(this);
		hit.addActionListener(this);
		stand.addActionListener(this);
		
		Jframe.pack();
		Jframe.setVisible(true);
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLUE);
		g.fillRect(0,0,Width,Height);
		
		for(int i=0; i<this.player.showPlayerCards().size(); i++) {
			
			Card card = this.player.showPlayerCards().get(i);
			Image image = card.getImage();
			g.drawImage(image,100+i*80,100,this);
		}
		
		for(int i=0; i<this.dealer.showPlayerCards().size(); i++) {
			
			if(!showCards && i == 1) {
				Image image = new ImageIcon("pictures/b2fv.png").getImage();
				g.drawImage(image,100+i*80,400,this);
			}else {
				Card card = this.dealer.showPlayerCards().get(i);
				Image image = card.getImage();
				g.drawImage(image,100+i*80,400,this);				
			}
		}
		
		if(gameOver) {
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.drawString("Player Busted ",180,300);
		}
		
		if(playerWins) {
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.drawString("Player Wins ",180,300);
		}
		
		if(dealerWins) {
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.drawString("Dealer Wins ",180,300);
		}
		
		if(dealerBlackJack) {
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.drawString("Dealer has blackJack ",180,300);
		}
		
		if(dealerBusted) {
			
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(new Color(1.0f, 0.0f, 0.0f));
			g.drawString("Dealer Busted ",180,300);
		}
	}
	
	public void start() {
		
		this.player = new Player();
		this.dealer = new Player();
		
		this.deck = new Deck();
		this.deck.populateDeck();
		this.deck.shuffle();
		
		for(int i=0; i<2; i++) {
			this.player.addCard(this.deck.getDeck().get(i));
			this.deck.removeCard(this.deck.getDeck().get(i));
		}
		
		for(int i=0; i<2; i++) {
			
			this.dealer.addCard(this.deck.getDeck().get(i));
			this.deck.removeCard(this.deck.getDeck().get(i));
		}
		System.out.println(this.player.showPlayerCards());
		System.out.println(this.dealer.showPlayerCards()); 
	}
	
	public void stand() {
		
		int dealerHandValue = 0;
		
		showCards = true;
		Jframe.repaint();
		
		while(dealerHandValue<17) {
			
			this.dealer.addCard(this.deck.getDeck().get(0));
			this.deck.removeCard(this.deck.getDeck().get(0));
			System.out.println(this.dealer.showPlayerCards());
			dealerHandValue = this.dealer.handValue();
			System.out.println("Dealer Hand Value : " + dealerHandValue);
			Jframe.repaint();
		}
		
		if(this.dealer.handValue() == 21) {	
			dealerBlackJack = true;
			dealerWins = true;
			System.out.println("21");
			Jframe.repaint();
		}
		
		if(this.dealer.handValue() > 21) {
			dealerBusted = true;
			System.out.println("Over 21");
			Jframe.repaint();
		}
		
		if(this.dealer.handValue()>this.player.handValue() && !dealerBusted && !dealerWins) {
			dealerWins = true;	
			System.out.println("Dealer Wins ");
			Jframe.repaint();
			
		}else if (this.dealer.handValue()<this.player.handValue() && !dealerBusted && !dealerWins) {
			playerWins = true;
			System.out.println("Player Wins");
			Jframe.repaint();
		}
	}
	
	public void playerHit() {
		
		this.player.addCard(this.deck.getDeck().get(0));
		this.deck.removeCard(this.deck.getDeck().get(0));
		Jframe.repaint();
		
		if(this.player.handValue()>21) {
			System.out.println("Hand Value is : " + this.player.handValue());
			gameOver = true;
			Jframe.repaint();
		}
	}
	
	public void run() {
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getActionCommand() == "HIT") {
			System.out.println("Hit");
			playerHit();
			System.out.println(this.player.showPlayerCards());
		
		}else if (ae.getActionCommand() == "STAND") {
			System.out.println("Stand");
			stand();
		}
	}
}