import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{
	
	JPanel mainPane = new JPanel();
	
	public Frame() {
		setTitle("3201");
		setSize(900, 580);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		//setUndecorated(true);
		setResizable(false);
		add(mainPane);
		mainPane.setBackground(new Color(195, 215, 255));
		mainPane.setLayout(null);
		
	}
}
