package enemies;

import Player.player;
import TileType.Empty;
import TileType.Unit;

public class Trap extends Enemy {
	
	int visibilityTime;
	int invisibilityTime;
	int ticksCount;
	Boolean visible;
	
	public Trap(int visibilityTime, int invisibilityTime) {
		this.visibilityTime = visibilityTime;
		this.invisibilityTime = invisibilityTime;
		ticksCount = 0;
		visible = true;
	}

	@Override
	public void Update() {
		visible = ticksCount < visibilityTime;
		if (ticksCount == visibilityTime + invisibilityTime)
			ticksCount = 0;
		else
			ticksCount++;
		if(currBoard.Range(this, currBoard.getPlayer())<=2)
			Interact(currBoard.getPlayer());
	}

	@Override
	public void Visit(Empty e) {
		//do nothing
	}

	@Override
	public void Accept(Unit u) {
		// TODO Auto-generated method stub
	}
}