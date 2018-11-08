import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class file_reader {
	ArrayList<String> words;
	ArrayList<state> states;
	
	public file_reader(String path) {
		words = new ArrayList<String>();
		states = new ArrayList<state>();
		words.add("@");
		retrieve(path);
	}
	
	public void retrieve(String path) {
		FileReader fileReader;
		try {
			fileReader = new FileReader(path);
			BufferedReader bf = new BufferedReader(fileReader);
			int noStates = Integer.valueOf(bf.readLine());//1st line
			int start = Integer.valueOf(bf.readLine());//2nd line
			int noEndStates = Integer.valueOf(bf.readLine());//3rd line
			String temp = bf.readLine();//4th line
			String end[] = temp.split(" ");
			int noTransitions = Integer.valueOf(bf.readLine()); //5th line
			
			
			
			for(int i=0;i<noStates;i++) {
				boolean isstart=false;
				boolean isend=false;
				if(i==(start-1))
					isstart=true;
				for(int j=0;j<end.length;j++) {
					if(i==(Integer.valueOf(end[j])-1))
						isend=true;
				}
				state s = new state(Integer.toString(i),isstart,isend);
				states.add(s);
			}
			
			System.out.println(noStates);
			System.out.println(start);
			System.out.println(noEndStates);
			for(int k=0;k<states.size();k++) {
				System.out.println(states.get(k).getName() + " , "+ states.get(k).getIsStartingState() + " , " + states.get(k).getIsFinishState());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
