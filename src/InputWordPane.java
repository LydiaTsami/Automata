import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;

public class InputWordPane extends JPanel {

	private String words="";
	private int lastState = -1;
	/**
	 * Create the panel.
	 */
	public InputWordPane(JFrame frame,file_reader fr) {
		setLayout(null);
		setBounds(100, 100, 550, 300);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice =JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(choice == JOptionPane.YES_OPTION)
					frame.dispose();
			}
		});
		btnExit.setBounds(433, 222, 89, 23);
		add(btnExit);
		
		JLabel lblPleaseTypeThe = new JLabel("Please type the word:");
		lblPleaseTypeThe.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPleaseTypeThe.setBounds(21, 11, 394, 14);
		add(lblPleaseTypeThe);
		
		JButton btn_previous = new JButton("Previous");
		btn_previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				gui window = new gui();
				window.getFrame().setVisible(true);
			}
		});
		btn_previous.setBounds(21, 222, 89, 23);
		add(btn_previous);
		
		JTextPane textPane = new JTextPane();
		JScrollPane jsp = new JScrollPane(textPane);
		jsp.setBounds(21, 39, 499, 172);
		add(jsp);
		
		for(int i=0;i<fr.words.size();i++) {
			words+= fr.words.get(i);
		}
		
		textPane.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			    int key = e.getKeyCode();

			    if (key == KeyEvent.VK_ENTER) {
			    	int currstate=-1;
			    	System.out.println("enter pressed");
			    	String lines [] = textPane.getText().toString().split("\n");
			    	System.out.println(lines[lines.length-1]);
			    	if(lines[lines.length-1].matches("[" + words + "]+")) {
			    		
			    		if(lastState==-1) {
		    				for(state state: fr.getStates()) {
		    					if(state.isStartingState)
		    						currstate = Integer.parseInt(state.getName())-1;
		    				}
		    			}
		    			else
		    				currstate=lastState;
			    		for(int i=0;i<lines[lines.length-1].length();i++) {
			    			fr.getStates().get(0).print();
			    			System.out.println("Current state: " +currstate);
			    			int index = fr.getStates().get(currstate).checkTransition(String.valueOf(lines[lines.length-1].charAt(i)));
			    			if(index!=-1)
			    				currstate = fr.getStates().get(currstate).transitioningTo.get(index)-1;
			    			System.out.println("Next state " + currstate);
			    		}
			    		if(fr.getStates().get(currstate).isFinishState)
			    			JOptionPane.showMessageDialog(frame, "This is a final state", "Error", JOptionPane.WARNING_MESSAGE);
			    		else
			    			JOptionPane.showMessageDialog(frame, "This is not a final state", "Error", JOptionPane.WARNING_MESSAGE);
			    		int choice = JOptionPane.showConfirmDialog(frame, "Do you want to input another word?");
			    		if(choice == 1) {
			    			frame.dispose();
							gui window = new gui();
							window.getFrame().setVisible(true);
			    		}
			    	}
			    	else {
			    		String error = "Acceptable characters are: " + words;
						JOptionPane.showMessageDialog(frame, error, "Error", JOptionPane.WARNING_MESSAGE);
						e.consume();
			    	}
			    }
			}
		});
		
	}
}
