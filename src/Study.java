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

public class Study extends Frame{
	
	JLabel lb = new JLabel("다중 문자 치환 암호화란?");
	JLabel lbContent = new JLabel("", JLabel.CENTER);
	JButton btnBack = new JButton();
	public Study() {
		super(900, 550);
		
		lb.setFont(new Font("나눔고딕 ExtraBold", 1, 30));
		lb.setBounds(300, 30, 500, 100);
		lb.setBackground(new Color(0, 0, 0, 255));
		
		lbContent.setVerticalAlignment(JLabel.TOP);
		lbContent.setFont(new Font("나눔고딕 ExtraBold", 0, 18));
		lbContent.setText("<html>"
				+ "다중 문자 치환 암호화는 2개 이상의 문자열을 묶어 치환하는 방법입니다.<br>"
				+ " 먼저 암호키를 5 x 5 정사각현에 배열합니다<br>"
				+ "그리고......</html>");
		
		Image img;
		try {
			img = ImageIO.read(new File("./Resources/btnBack.png")).getScaledInstance(50, 50, BufferedImage.SCALE_SMOOTH);
			btnBack.setIcon(new ImageIcon(img));
			btnBack.setBorderPainted(false);
			btnBack.setContentAreaFilled(false);
			btnBack.setFocusPainted(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		btnBack.setBounds(800, 50, 50, 50);
		lbContent.setBounds(100, 150, 600, 450);
		
		mainPane.add(lb);
		mainPane.add(btnBack);
		mainPane.add(lbContent);
		
		addAction();
		
	}	
	
	void addAction() {
		btnBack.addActionListener(e->{
			dispose();
			new Main().setVisible(true);
		});
	}
	
	public static void main(String[] args) {
		new Study().setVisible(true);
	}	
}


