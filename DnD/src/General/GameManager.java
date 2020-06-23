package General;
import java.util.*;
public class GameManager implements Observable{
	final ArrayList<Observer> Observers = new ArrayList<Observer>();
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
	public void getUserInput() {
		Scanner s = new Scanner(System.in);
	}
	
}