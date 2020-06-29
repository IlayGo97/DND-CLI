package General;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import Player.*;
import UIandMain.EventHandler;
import enemies.Enemy;

public class GameManager implements Observable {
	
	final ArrayList<Observer> Observers = new ArrayList<Observer>();
	public player p;
	private List<List<String>> AllLevels;
	public static Warrior Jonsnow = new Warrior(0, 0, "Jon Snow", 300 , 30, 4,3);
	public static Warrior TheHound =  new Warrior(0,0,"The Hound",400,20,6,5);
	public static Mage Meli = new Mage(0,0,"Melisandre",100,5,1,300,30,15,5,6);
	public static Mage Thoros = new Mage(0,0,"Thoros of Myr",250,25,4,150,20,20,3,4);
	public static Rogue Ariya = new Rogue(0,0,"Arya Stark",150,40,2,20);
	public static Rogue Bronn = new Rogue(0,0,"Bronn",250,35,3,50);
	public static Hunter Ygritte = new Hunter(0,0,"Ygritte",220,1000000,2,100000000);
	public static List<player> playerList = new ArrayList<player>();
	public boolean GameOngoing;
	int currLevel;
	
	public GameManager(int playerType, List<String> FilePaths) throws Exception
	{
		AllLevels = new ArrayList<List<String>>();
		try
		{
			p = playerList.get(playerType-1);
		}
		catch(Exception e)
		{
			throw new Exception("enter a valid number");
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
		currLevel = 0;
		Board.setUp(AllLevels.get(0), p); // Loads first level
		GameOngoing = true;
		p.ReloadBoard();
		addObserver(p);
		for(Enemy e : Board.GetInstance().EnemyList)
		{
			addObserver(e);
			e.ReloadBoard();
		}
	}
	
	public static List<player> getPlayerList()
	{
		if(playerList.size()==0)
		{
			playerList.add(Jonsnow);
			playerList.add(TheHound);
			playerList.add(Meli);
			playerList.add(Thoros);
			playerList.add(Ariya);
			playerList.add(Bronn);
			playerList.add(Ygritte);
		}
		return playerList;
	}
	
	@Override
	public void addObserver(Observer o) {
		Observers.add(o);
	}
	
	@Override
	public void notifyObservers() { // game tick
		for(Observer o: Observers)
		{
			o.Update();
		}
		GameOngoing = p.healthPool.current != 0;
		if(Board.GetInstance().EnemyList.isEmpty()) // load next level
		{
			AdvanceLevel();
		}
	}
	
	public void AdvanceLevel() {
		currLevel++;
		try
		{
			Board.setUp(AllLevels.get(currLevel), p);
			p.ReloadBoard();
			for(Enemy e : Board.GetInstance().EnemyList)
			{
				addObserver(e);
				e.ReloadBoard();
			}
		}
		catch (Exception e) //if no more levels
		{
			EventHandler.GetInstance().HandleAllEvents();
			GameOngoing = false;
			EventHandler.GetInstance().HandleEvent("You Won!");
		}
	}
	
	public void getUserInput(char input) {
		if(!GameOngoing)
			return;
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