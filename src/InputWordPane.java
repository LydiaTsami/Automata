import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	/**
	 * Create the panel.
	 */
	public InputWordPane(JFrame frame) {
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
			}
		});
		btn_previous.setBounds(21, 222, 89, 23);
		add(btn_previous);
		
		JTextPane textPane = new JTextPane();
		JScrollPane jsp = new JScrollPane(textPane);
		jsp.setBounds(21, 39, 499, 172);
		add(jsp);
		
//		int condition = JComponent.WHEN_FOCUSED;
//		InputMap iMap = textPane.getInputMap(condition);
//		ActionMap aMap = textPane.getActionMap();
//		String enter = "enter";
//		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
//		aMap.put(enter, new AbstractAction() {
//		   @Override
//		   public void actionPerformed(ActionEvent arg0) {
//			   textPane.getDocument().putProperty(DefaultEditorKit.EndOfLineStringProperty, "\r\n");
//		   }
//		});
		
	}
}
