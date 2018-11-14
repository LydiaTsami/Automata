import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class InputWordPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String words="";
	private ArrayList<Integer> currstate= new ArrayList<Integer>();// current state
	private ArrayList<ArrayList<Integer>> prevstates= new ArrayList<ArrayList<Integer>>();// previous states
	private int startingState; //starting state
	private JTextField textFieldState;
	private boolean emptyPressed = false;
	private Set<Integer> indexes = new LinkedHashSet<Integer>();
	
	
	public InputWordPane(JFrame frame,file_reader fr) {
		setLayout(null);
		setBounds(100, 100, 550, 310);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice =JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(choice == JOptionPane.YES_OPTION)
					frame.dispose();
			}
		});
		btnExit.setBounds(429, 231, 89, 23);
		add(btnExit);
		
		JLabel lblPleaseTypeThe = new JLabel("Please type the word:");
		lblPleaseTypeThe.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPleaseTypeThe.setBounds(21, 11, 394, 14);
		add(lblPleaseTypeThe);
		
		JLabel lblAlphabet = new JLabel("New label");
		lblAlphabet.setBounds(208, 12, 310, 14);
		add(lblAlphabet);
		lblAlphabet.setHorizontalAlignment(SwingConstants.RIGHT);
		String alphabetText = "Acceptable words are: ";
		
		JButton btn_previous = new JButton("Previous");
		btn_previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				gui window = new gui();
				window.getFrame().setVisible(true);
			}
		});
		btn_previous.setBounds(21, 231, 89, 23);
		add(btn_previous);
		
		JTextPane textPane = new JTextPane();
		JScrollPane jsp = new JScrollPane(textPane);
		jsp.setBounds(19, 29, 499, 160);
		add(jsp);
		
		//gets the words from file reader class
		for(int i=0;i<fr.getWords().size();i++) {
			words+= fr.getWords().get(i);
			alphabetText += fr.getWords().get(i) + " ";
		}
		lblAlphabet.setText(alphabetText);
		
		//finds the starting state
		for(state state: fr.getStates()) {
			if(state.isStartingState)
				startingState = Integer.parseInt(state.getName());
		}
		
		
		currstate.add(startingState);
		textFieldState = new JTextField();
		textFieldState.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldState.setBounds(21, 200, 497, 20);
		add(textFieldState);
		textFieldState.setColumns(10);
		textFieldState.setEditable(false);
		textFieldState.setText(print());
		
		textPane.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    int key = e.getKeyCode();
			    String keypressed = String.valueOf(e.getKeyChar());
			    
			    //checks if the pressed key is in 'words' or shift
			    if(keypressed.matches("[" + words + "]+") || key == KeyEvent.VK_SHIFT) {
			    		//if @ is pressedwhich is the empty word, current state is set to -
			    		if(keypressed.equals("@")) {
			    			emptyPressed=true;
			    			textFieldState.setText("Current state is: -");
			    		}
			    		if (!emptyPressed) {
			    			//gets the index of the state we are transiotioning to
		    				prevstates.add(new ArrayList<Integer>());
			    			for(Integer curr: currstate) {
			    				indexes.addAll(fr.getStates().get(curr-1).checkTransition(keypressed));
			    				prevstates.get(prevstates.size()-1).add(curr);//hard copy of currstate ArrayList
			    			}
			    			currstate.clear();//clears the current states list
							for(Integer index: indexes) {
								if (index != -1) {
									currstate.add(index);// adds the new indexes to the current states if it's not -1
								} 
							}
							textFieldState.setText(print());
							indexes.clear();
						}
			    } 
			    //if the enter key is pressed
			    else if (key == KeyEvent.VK_ENTER) {
			    	boolean finish=false;
			    	for(Integer st: currstate) { //checks all the currents states to find if any of them is a finish state
			    		if(fr.getStates().get(st-1).isFinishState)
			    			finish = true;
			    	}
		    		if(finish)
		    			JOptionPane.showMessageDialog(frame, "This is a final state");
		    		else
		    			JOptionPane.showMessageDialog(frame, "This is not a final state");
		    		int choice = JOptionPane.showConfirmDialog(frame, "Do you want to input another word?","Message",JOptionPane.YES_NO_OPTION);
		    		if(choice == 1) {
		    			frame.dispose();
						gui window = new gui();
						window.getFrame().setVisible(true);
		    		}
		    		else if(choice == 0) {
		    			currstate.clear();
		    			indexes.clear();
		    			currstate.add(startingState);
		    			textFieldState.setText(print());
		    			emptyPressed = false;
		    		}
			    }
			    else if(key == KeyEvent.VK_BACK_SPACE) {
			    	textPane.setText(""+textPane.getText().substring(0, textPane.getText().length()));
			    	if(!prevstates.isEmpty()) {
			    		currstate.clear();
				    	currstate.addAll(prevstates.get(prevstates.size()-1));
				    	prevstates.remove(prevstates.size()-1);
			    	}
			    	textFieldState.setText(print());
			    	}
			    //if anything else is pressed
			    else {
		    		String error = "Acceptable characters are: " + words;
					JOptionPane.showMessageDialog(frame, error, "Error", JOptionPane.WARNING_MESSAGE);
					e.consume();
		    	}
			}
		});
		
	}
	
	public String print() {
		String print="Current state is: ";
		for(Integer curr: currstate) {
			print += curr + "  ";
		}
		return print;
	}
}
