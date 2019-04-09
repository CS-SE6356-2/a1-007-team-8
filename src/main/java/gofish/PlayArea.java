package myPackage;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class PlayArea 
{
	private ArrayList<Card> playArea;
	
	PlayArea()
	{
		this.playArea = new ArrayList<Card>();
	}
	
	
	public ArrayList<Card> getCards()
	{
		
	}
	
	public Card removeCard()
	{
		Card removedCard;
		removedCard = playArea.get(0);
		playArea.remove(0);
		
		return removeCard;
	}
	
	public Card removeCard(int index)
	{
		Card removedCard;
		removedCard = playArea.get(index);
		playArea.remove(index);
		
		return removedCard;
	}
	
	public void addCard(Card c)
	{
		playArea.add(playArea.size()-1, c);
	}
	
	public void draw(Frame f)
	{
		
	}
}
