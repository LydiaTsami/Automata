import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class gui {

	private JFrame frame;
	private JTextField textField;
	private String InputFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InputFile = Paths.get(".").toAbsolutePath().normalize().toString() + "\\" +  "input.txt";
		
		frame = new JFrame();
		frame.setTitle("Automata- it1514");
		frame.setBounds(100, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPath = new JLabel("Path:");
		lblPath.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPath.setBounds(33, 59, 48, 14);
		frame.getContentPane().add(lblPath);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 12));
		textField.setBounds(33, 82, 456, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button_path = new JButton("...");
		button_path.setBounds(499, 83, 25, 20);
		frame.getContentPane().add(button_path);
		
		Button btn_next = new Button("Next");
		btn_next.setFont(new Font("Verdana", Font.PLAIN, 12));
		btn_next.setBounds(232, 119, 70, 22);
		frame.getContentPane().add(btn_next);
		
		Button btn_exit = new Button("Exit");
		btn_exit.setFont(new Font("Verdana", Font.PLAIN, 12));
		btn_exit.setBounds(454, 229, 70, 22);
		frame.getContentPane().add(btn_exit);
		
		button_path.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	 FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
		    	    dialog.setMode(FileDialog.LOAD);
		    	    dialog.setVisible(true);
		    	    String file = new File(dialog.getFile()).getAbsolutePath();
		    	    if(file!=null) {
		    	    	InputFile = file;
		    	    }
		    	    textField.setText(InputFile);
		    }
		});
		
		btn_exit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	int choice =JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(choice == JOptionPane.YES_OPTION)
					frame.dispose();
		    }
		});
		
		btn_next.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	file_reader fr;
				try {
					fr = new file_reader(InputFile);
					if(!fr.retrieve()) {
						String st = "Error on file reader";
						JOptionPane.showMessageDialog(null, st,"Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						frame.setContentPane(new InputWordPane(frame,fr));
						SwingUtilities.updateComponentTreeUI(frame);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					String error = "Please select an existing path for the .txt";
					JOptionPane.showMessageDialog(frame, error, "Warning", JOptionPane.WARNING_MESSAGE);
				}
		    }
		});
		
	    File checkfile = new File(InputFile);
		if(checkfile.exists()) {
			textField.setText(InputFile);
			textField.setEditable(false);
		}
		else {
			InputFile = null;
			textField.setText("");
			textField.setEditable(false);
		}
	}

	public JFrame getFrame() {
		return frame;
	}
}
