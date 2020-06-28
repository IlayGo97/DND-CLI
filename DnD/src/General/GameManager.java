package General;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import Player.player;

public class GameManager implements Observable {
	
	final ArrayList<Observer> Observers = new ArrayList<Observer>();
	private player p;
	private List<List<String>> AllLevels;
	
	public GameManager(int playerType, List<String> FilePaths)
	{
		try
		{
			for(String levelPath : FilePaths)	
				AllLevels.add(Files.readAllLines(Paths.get(levelPath)));
		}
		catch (IOException e)
		{
			System.out.println("couldn't load levels: "+e.getMessage());
		}
		//p = new player (); TODO
		Board.setUp(AllLevels.get(0), p);
	}
	
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