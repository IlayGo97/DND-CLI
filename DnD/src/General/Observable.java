package General;
import java.util.ArrayList;
import java.util.List;

public interface Observable {
	void addObserver(Observer o);
	void notifyObservers();
}
