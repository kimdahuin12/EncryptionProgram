import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends Frame{
	
	JLabel lb = new JLabel("암호화 공부 프로그램");
	RoundedButton btnClose = new RoundedButton("프로그램 종료");
	RoundedButton btnStudy = new RoundedButton("암호화 공부하기");
	RoundedButton btnCipher = new RoundedButton("암호화/복호화");
	public Main() {
		super(900, 550);
		
		lb.setFont(new Font("나눔고딕 ExtraBold", 0, 30));
		lb.setBounds(300, 100, 500, 100);
		lb.setBackground(new Color(0, 0, 0, 255));
		
		btnClose.setFont(new Font("나눔고딕 ExtraBold", 0, 20));
		btnStudy.setFont(new Font("나눔고딕 ExtraBold", 0, 20));
		btnCipher.setFont(new Font("나눔고딕 ExtraBold", 0, 20));
		

		int startX = 150;
		int y = 300;
		btnStudy.setBounds(startX, y, 188, 89);
		btnCipher.setBounds(startX+200, y, 188, 89);
		btnClose.setBounds(startX+400, y, 188, 89);
		
		mainPane.add(lb);
		mainPane.add(btnClose);
		mainPane.add(btnStudy);
		mainPane.add(btnCipher);
		
		addAction();
		
	}	
	
	void addAction() {
		btnClose.addActionListener(e->{
			System.exit(0);
		});
		
		btnStudy.addActionListener(e->{
			dispose();
			new Study().setVisible(true);
		});
		
		btnCipher.addActionListener(e->{
			dispose();
			new Cipher().setVisible(true);
		});
		
	}
	
	public static void main(String[] args) {
		new Main().setVisible(true);
	}	
}


