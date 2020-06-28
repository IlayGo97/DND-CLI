package UIandMain;

public class EventHandler {
	
	static EventHandler _eventhandler = null;
	
	private EventHandler()
	{
		
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
		System.out.println(s);
	}
}