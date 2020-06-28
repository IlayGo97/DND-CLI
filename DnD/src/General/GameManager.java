package General;
import java.util.*;
import Player.player;

public class GameManager implements Observable {
	
	final ArrayList<Observer> Observers = new ArrayList<Observer>();
	private player p;
	
	@Override
	public void addObserver(Observer o) {
		Observers.add(o);
	}
	
	@Override
	public void notifyObservers() {
		for(Observer o: Observers)
		{
			o.Update();
		}
	}
	
	public void getUserInput(char input) {
		switch(input)
		{
			case 'a':
				p.InteractLeft();
				break;
			case 's':
				p.InteractDown();
				break;
			case 'd':
				p.InteractRight();
				break;
			case 'w':
				p.InteractUp();
				break;
			case 'e':
				p.SpecialAbility();
				break;
			case 'q':
				break;
			default: 
				return; //error message
		}
		notifyObservers();
	}
}