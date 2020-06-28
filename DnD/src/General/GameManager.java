package General;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import Player.*;

public class GameManager implements Observable {
	
	final ArrayList<Observer> Observers = new ArrayList<Observer>();
	private player p;
	private List<List<String>> AllLevels;
	public Warrior Jonsnow =new Warrior(0, 0, "Jon Snow", 300 , 30, 4,3);
	public Warrior TheHound =  new Warrior(0,0,"The Hound",400,20,6,5);
	public Mage Meli = new Mage(0,0,"Melisandre",100,5,1,300,30,15,5,6);
	public Mage Thoros= new Mage(0,0,"Thoros of Myr",250,25,4,150,20,20,3,4);
	public Rogue Ariya =new Rogue(0,0,"Arya Stark",150,40,2,20);
	public Rogue Bronn = new Rogue(0,0,"Bronn",250,35,3,50);
	public Hunter Ygritte = new Hunter(0,0,"Ygritte",220,30,2,6);
	public GameManager(int playerType, List<String> FilePaths) throws Exception
	{
		switch(playerType)
		{
			case 1: 
				p = Jonsnow;
				break;
			case 2: 
				p = TheHound;
				break;
			case 3: 
				p = Meli;
				break;
			case 4: 
				p = Thoros;
				break;
			case 5: 	
				p = Ariya;
				break;
			case 6: 
				p = Bronn;
				break;
			case 7: 
				p = Ygritte;
				break;
			default:
				throw new Exception("Unimplemented player");
		}
		try
		{
			for(String levelPath : FilePaths)	
				AllLevels.add(Files.readAllLines(Paths.get(levelPath)));
		}
		catch (IOException e)
		{
			System.out.println("couldn't load levels: "+e.getMessage());
		}
		Board.setUp(AllLevels.get(0), p); //Loads first level
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