package UIandMain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import General.Board;
import General.GameManager;
import Player.player;

public class Main {
	public static void main(String[] args)
	{
		EventHandler eh = EventHandler.GetInstance();
		System.out.println("Select player:");
		int c = 1;
		for (player p : GameManager.getPlayerList())
		{
			System.out.println(c+". "+p.Describe());
			c++;
		}
		Scanner s = new Scanner(System.in);
		int Selection = s.nextInt();
		System.out.println("You have selected:");
		System.out.println(GameManager.getPlayerList().get(Selection-1).name);
		GameManager gm = null;
		try
		{
			List<String> LevelFiles = Files.list(Paths.get(args[0])).sorted().map(Path::toString).collect(Collectors.toList());
			gm = new GameManager(Selection, LevelFiles);
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Board.GetInstance());
		Scanner inputScanner  = new Scanner(System.in);
		while(gm.GameOngoing)
		{
			gm.getUserInput(inputScanner.next().charAt(0));
			System.out.println(Board.GetInstance());
			System.out.println(gm.p.Describe());
			eh.HandleAllEvents();
		}
	}
}
