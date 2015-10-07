package StateMachine;

import java.awt.Graphics2D;

public interface IState {
	public void update();
	public void draw(Graphics2D g);
	public void KeyPressed(int k);
	public void KeyReleased(int k);
	public void OnEnter();
	public void OnExit();
}
