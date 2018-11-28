import java.util.ArrayList;

public class state {

	boolean isStartingState; //if it is a starting state
	boolean isFinishState; // if it is a finish state
	String name;
	ArrayList<state> transitioningState = new ArrayList<state>();//To which state it's transitioning
	ArrayList<String> transitions = new ArrayList<String>(); // The transition
	

	public state(String name,boolean isStartingState,boolean isFinishState) {
		this.name = name;
		this.isStartingState = isStartingState;
		this.isFinishState = isFinishState;
	}
	
	//sets the character of the transition and the state it is transitioning to
	public void setTransition(state state,String transition) {
		transitioningState.add(state);
		transitions.add(transition);
	}
	
	public ArrayList<Integer> checkTransition(String transition) {
		ArrayList<Integer> indexes= new ArrayList<Integer>();
		for(int i=0;i<transitions.size();i++) {
			if(transition.equals(transitions.get(i)))
				indexes.add(Integer.parseInt(transitioningState.get(i).getName()));
		}
		return indexes;
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
