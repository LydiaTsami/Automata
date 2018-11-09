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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

public class gui {

	private JFrame frame;
	private JTextField textField;
	private String InputFile;
	private JFileChooser fc;

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
		fc = new JFileChooser();
		InputFile = Paths.get(".").toAbsolutePath().normalize().toString() + "\\" +  "input.txt";
		System.out.println(InputFile);
		
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
		    	file_reader fr =new file_reader(InputFile);
				if(!fr.retrieve()) {
					String st = "Error on file reader";
					JOptionPane.showMessageDialog(null, st);
				}
				else {
					frame.setContentPane(new InputWordPane(frame,fr));
					SwingUtilities.updateComponentTreeUI(frame);
				}
		    }
		});
		
		File checkfile = new File(InputFile);
		if(checkfile.exists()) {
			textField.setText(InputFile);
			textField.setEditable(false);
		}
		else {
			textField.setText("");
			textField.setEditable(false);
		}
	}

	public JFrame getFrame() {
		return frame;
	}
}
