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
		
		lb.setFont(new Font("나눔고딕 ExtraBold", 1, 30));
		lb.setBounds(300, 30, 500, 100);
		lb.setBackground(new Color(0, 0, 0, 255));
		
		lbContent.setVerticalAlignment(JLabel.TOP);
		lbContent.setHorizontalAlignment(JLabel.CENTER);
		lbContent.setFont(new Font("나눔고딕 ExtraBold", 0, 15));
		lbContent.setText("<html>"
				+ "다중 문자 치환 암호화는 2개 이상의 문자열을 묶어 치환하는 방법입니다.<br>"
				+ "암호키를 5 x 5 정사각형에 배열하고 한 번에 두글자씩 암호화합니다.<br>"
				+ "<br>"
				+ "먼저 암호키의 중복 문자열을 제거하고 5 X 5 정사각형에 배열한 후, 나머지 알파벳을 차례대로 넣습니다.<br>"
				+ "25개의 칸밖에 없기 때문에 영문에서 많이 나타나지 않는 Q와 Z를 같은 같에 넣도록 합니다.(다른 영문자로도 가능합니다.)<br>"
				+ "암호화를 하기 위해 평문의 띄어쓰기를 없애고 2개씩 묶어줍니다.<br>"
				+ "묶여있는 문자열이 SS와 같이 연속으로 나타난다면 문자열의 중간에 X를 넣습니다.(SS -> SX)<br>"
				+ "마지막에 홀수 글자가 남을 경우에도 X를 넣어줍니다.<br>"
				+ "<br>"
				+ "이제 묶여있는 2개의 문자를 치환해줍니다.<br>"
				+ "2개의 문자가 암호판(5 X 5 정사각형)에서 서로 다른 행과 다른 열에 존재하면 서로의 행 위치와 자신의 열 위치에 있는 문자가 암호문자입니다.<br>"
				+ "2개의 문자가 암호판에서 서로 같은 열에 있다면 암호문자는 각 문자의 아래쪽에 있는 문자입니다. 만약 아래쪽에 문자가 없다면 맨 위의 문자로 치환됩니다.<br>"
				+ "2개의 문자가 암호판에서 서로 같은 행에 있다면 암호문자는 각 문자의 오른쪽에 있는 문자입니다. 만약 오른쪽에 문자가 없다면 맨 왼쪽의 문자로 치환됩니다.<br>"
				+ "<br>"
				+ "암호화/복호화를 직접 해보고 프로그램으로 답을 맞춰보세요!<br>"
				+ "</html>");
		
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
		lbContent.setBounds(100, 140, 600, 450);
		
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


