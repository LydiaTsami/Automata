
public class state {

	boolean isStartingState;
	boolean isFinishState;
	String name;

	public state(String name,boolean isStartingState,boolean isFinishState) {
		this.name = name;
		this.isStartingState = isStartingState;
		this.isFinishState = isFinishState;
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
