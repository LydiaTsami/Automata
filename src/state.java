import java.util.ArrayList;

public class state {

	boolean isStartingState;
	boolean isFinishState;
	String name;
	ArrayList<Integer> transitioningTo = new ArrayList<Integer>();
	ArrayList<String> transitioning = new ArrayList<String>();
	

	public state(String name,boolean isStartingState,boolean isFinishState) {
		this.name = name;
		this.isStartingState = isStartingState;
		this.isFinishState = isFinishState;
	}
	
	public void setTransition(int state,String transition) {
		transitioningTo.add(state);
		transitioning.add(transition);
		System.out.println(this.getName() + ", " + transition + ", " +state);
	}
	
	public int checkTransition(String transition) {
		for(int i=0;i<transitioning.size();i++) {
			if(transition.equals(transitioning.get(i)))
				return i;
		}
		return -1;
	}
	
	public void print() {
		System.out.println(transitioning);
		System.out.println(transitioningTo);
	}
	
	public boolean getIsStartingState() {
		return isStartingState;
	}

	public void setStartingState(boolean isStartingState) {
		this.isStartingState = isStartingState;
	}

	public boolean getIsFinishState() {
		return isFinishState;
	}

	public void setFinishState(boolean isFinishState) {
		this.isFinishState = isFinishState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
