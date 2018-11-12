import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class file_reader {
	private ArrayList<String> words;
	private ArrayList<state> states;
	private String path;
	
	public file_reader(String path) {
		words = new ArrayList<String>();
		states = new ArrayList<state>();
		words.add("@");
		this.path = path;
	}
	
	public boolean retrieve() {
		FileReader fileReader;
		try {
			fileReader = new FileReader(path);
			BufferedReader bf = new BufferedReader(fileReader);
			int noStates = Integer.valueOf(bf.readLine());//1st line (no of states)
			int start = Integer.valueOf(bf.readLine());//2nd line (start state)
			bf.readLine();//3rd line (no of end states)
			String temp = bf.readLine();//4th line
			String end[] = temp.split(" ");// end states
			
			for(int i=0;i<noStates;i++) {
				boolean isstart=false;
				boolean isend=false;
				if(i==(start-1))
					isstart=true;
				for(int j=0;j<end.length;j++) {
					if(i==(Integer.valueOf(end[j])-1))
						isend=true;
				}
				state s = new state(Integer.toString(i+1),isstart,isend);
				states.add(s);
			}
			
			int noTransitions = Integer.valueOf(bf.readLine()); //5th line (no of transitions)
			for(int j=0;j<noTransitions;j++) {// rest of the lines
				String tempTrans = bf.readLine();
				String transition[] = tempTrans.split(" ");//0 is starting state, 1 is the symbol and 2 the final state of the transition
				if(!words.contains(transition[1]))
					words.add(transition[1]);
				for(state state: states) {
					if(state.getName().equals(transition[0])) {
						state.setTransition(states.get(Integer.parseInt(transition[2])-1), transition[1]);
					}		
				}
			}
			bf.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<state> getStates() {
		return states;
	}
	
	public ArrayList<String> getWords() {
		return words;
	}

}
