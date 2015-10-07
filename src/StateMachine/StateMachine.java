package StateMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StateMachine {
	
	Map<String, IState> mStates = new HashMap<String, IState>();
	IState mCurrentState = new EmptyState();
	Stack<IState> mStack = new Stack<IState>();
	
	public void update(){
		IState top = mStack.peek();
		top.update();
	}
	
	public void draw(java.awt.Graphics2D g){
		IState top = mStack.peek();
		top.draw(g);
	}
	
	public void keyPressed(int k){
		IState top = mStack.peek();
		top.KeyPressed(k);
	}
	
	public void keyReleased(int k){
		IState top = mStack.peek();
		top.KeyReleased(k);
	}
	
	public void Add(String name, IState state){
		mStates.put(name, state);
	}
	
	public void Push(String stateName){
		IState state = mStates.get(stateName);
		mStack.push(state);
	}
	
	public IState Pop(){
		return mStack.pop();
	}
	
//	public void Change(String stateName){
//		mCurrentState.OnExit();
//		mCurrentState = mStates.get(stateName);
//		mCurrentState.OnEnter();
//	}
//	
}
