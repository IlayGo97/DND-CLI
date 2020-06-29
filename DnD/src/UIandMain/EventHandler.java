package UIandMain;
import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Queue;

public class EventHandler {
	
	static EventHandler _eventhandler = null;
	public Queue<String> events;
	
	private EventHandler()
	{
		events = new LinkedList<String>();
	}
	
	public static EventHandler GetInstance()
	{
		if(_eventhandler == null)
		{
			_eventhandler = new EventHandler();
		}
		return _eventhandler; 
	}
	
	public void HandleEvent(String s)
	{
		events.add(s);
	}
	
	public void HandleAllEvents()
	{
		while(!events.isEmpty())
		{
			System.out.println(events.remove());
		}
	}
}