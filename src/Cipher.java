import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Cipher extends Frame{
	int[] arrAlpha = new int[25]; //0 : 순서 아직 안 정해짐. 1 ~ 25 : 암호판에 들어갈 순서.
	boolean[] arrAlphaChk = new boolean[26];
	JLabel lb = new JLabel("암호화/복호화");
	RoundedButton btnEncryption = new RoundedButton("암호화");
	RoundedButton btnDecryption = new RoundedButton("복호화");
	JButton btnBack = new JButton();
	JPanel paneCipherPlate = new JPanel(new GridLayout(5, 5, 0, 0));
	JTextField[] tfAlphaList = new JTextField[25];
	JTextField tfCipherKey = new JTextField();
	JTextField tfText = new JTextField();
	JTextField tfRes = new JTextField();
	JLabel[] lbList = new JLabel[] {
		new JLabel("암호키", JLabel.RIGHT),	
		new JLabel("평문/암호문", JLabel.RIGHT),	
		new JLabel("결과", JLabel.RIGHT)
	};
	public Cipher() {

		btnEncryption.setFont(new Font("나눔고딕 ExtraBold", 0, 20));
		btnDecryption.setFont(new Font("나눔고딕 ExtraBold", 0, 20));
		btnEncryption.setBounds(500, 450, 158, 59);
		btnDecryption.setBounds(700, 450, 158, 59);
		
		lb.setFont(new Font("나눔고딕 ExtraBold", 0, 30));
		lb.setBounds(350, 10, 500, 100);
		lb.setBackground(new Color(0, 0, 0, 255));
		
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
		
		//암호판 디자인
		paneCipherPlate.setBackground(Color.white);
		for (int i = 0; i < tfAlphaList.length; i++) {
			tfAlphaList[i] = new JTextField();
			tfAlphaList[i].setHorizontalAlignment(0);
			tfAlphaList[i].setFont(new Font("나눔고딕 ExtraBold", 0, 12));
			tfAlphaList[i].setBackground(Color.white);
			tfAlphaList[i].setBorder(new LineBorder(Color.black));
			tfAlphaList[i].setEditable(false);
			paneCipherPlate.add(tfAlphaList[i]);
			tfAlphaList[i].addKeyListener(new KeyAdapter() {
				//이 기능은 공부할 때 사용(ver2)
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
						for (JTextField tf : tfAlphaList) {
							if(tf.getText().length() > 1) {
								tf.setText(Character.toString(tf.getText().charAt(0)));
							}
							tf.setText(tf.getText().toLowerCase());
							if(tf.getText().equals("q")||tf.getText().equals("z")) {
								tf.setText("q/z");
							}
						}
					}
				}
			});
		}
		
		paneCipherPlate.setBounds(50, 200, 180, 180);
		//암호판 디자인 END
		
		for (int i = 0; i < lbList.length; i++) {
			lbList[i].setFont(new Font("나눔고딕 ExtraBold", 0, 18));
			lbList[i].setBounds(240, 180+80*i, 100, 30);
			mainPane.add(lbList[i]);
		}

		tfCipherKey.setBounds(350, 180, 500, 40);
		tfText.setBounds(350, 180+80, 500, 40);
		tfRes.setBounds(350, 180+80*2, 500, 40);
		tfCipherKey.setFont(new Font("나눔고딕 ExtraBold", 0, 12));
		tfText.setFont(new Font("나눔고딕 ExtraBold", 0, 12));
		tfRes.setFont(new Font("나눔고딕 ExtraBold", 0, 12));
		tfRes.setBackground(Color.white);
		tfRes.setEditable(false);
		
		JLabel lb2 = new JLabel("암호판", JLabel.CENTER);
		lb2.setFont(new Font("나눔고딕 ExtraBold", 0, 18));
		lb2.setBounds(50, 175, 180, 20);
		
		mainPane.add(lb);
		mainPane.add(lb2);
		mainPane.add(btnBack);
		mainPane.add(paneCipherPlate);
		mainPane.add(tfCipherKey);
		mainPane.add(tfText);
		mainPane.add(tfRes);
		mainPane.add(btnDecryption);
		mainPane.add(btnEncryption);
		
		addAction();
	}	
	
	boolean check() {
		if(tfCipherKey.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "경고", JOptionPane.ERROR_MESSAGE);
			tfCipherKey.grabFocus();
			return false;
		}
		if(tfText.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "경고", JOptionPane.ERROR_MESSAGE);
			tfText.grabFocus();
			return false;
		}
		String cipher = tfCipherKey.getText().trim().replace(" ", "");
		String text = tfText.getText().trim().replace(" ", "");
		//이부분 잘 안됨
		if(cipher.matches(".*[^a-zA-Z].*")) {
			tfCipherKey.setText("");
			tfCipherKey.grabFocus();
		}
		if(text.matches("(.*)[^a-zA-Z](.*)")) {
			tfText.setText("");
			tfText.grabFocus();
		}
		if(cipher.matches(".*[^a-zA-Z].*")||text.matches("(.*)[^a-zA-Z](.*)")) {
			JOptionPane.showMessageDialog(null, "영문자만 입력해 주시길 바랍니다.", "경고", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	void createCipherPlate() {
		//암호키의 중복을 지운다.
		//asintor
		//지우고 앞에서부터 순서를 메긴다.
		//arrAlpha를 돌면서 아직 순서가 안 메겨진 곳의 순서를 메긴다.
		String cipherKey = tfCipherKey.getText();
		int arrAlphaIdx = 0;
		Arrays.fill(arrAlpha, 0);
		Arrays.fill(arrAlphaChk, false);
		
		cipherKey = cipherKey.toLowerCase(); //소문자로 바꾸기
		cipherKey = cipherKey.trim();
		cipherKey = cipherKey.replace(" ", "");
		//암호키 중복 없이 넣기
		for (int i = 0; i < cipherKey.length(); i++) {
			char alpha = cipherKey.charAt(i);
			if(arrAlpha[arrAlphaIdx] == 0&&!arrAlphaChk[(alpha-'a')]) {
				if(alpha=='z'||alpha=='q') {
					alpha = 'q';
				}
				arrAlpha[arrAlphaIdx] = (alpha-'a'+1);
				arrAlphaChk[(alpha-'a')] = true;
				tfAlphaList[arrAlphaIdx].setText(Character.toString(alpha));
				if(tfAlphaList[arrAlphaIdx].getText().equals("q")) {
					tfAlphaList[arrAlphaIdx].setText("q/z");
				}
				System.out.println();
				arrAlphaIdx++;
			}
		}
		//for end
		
		//25개까지만
		
		for (int i = 0; i < arrAlpha.length; i++) {
			if(!arrAlphaChk[i]) {
				arrAlpha[arrAlphaIdx] = i+1;
				arrAlphaChk[i] = true;
				tfAlphaList[arrAlphaIdx].setText(Character.toString((char)('a'+arrAlpha[arrAlphaIdx]-1)));
				if(tfAlphaList[arrAlphaIdx].getText().equals("q")) {
					tfAlphaList[arrAlphaIdx].setText("q/z");
				}
				arrAlphaIdx++;
			}
		}
	}
	
	void startEncryption() {
		String text = tfText.getText().trim();
		text = text.replace(" ", ""); //공백 제거
		text = text.toLowerCase(); //모두 소문자로 처리
		
		//암호화
		char[][] arrText = new char[1000000][2];//2,000,000글자

		int arrTextIdx = 0;
		int textLen = text.length();
		for (int i = 0; i < text.length();) {
			arrText[arrTextIdx][0] = text.charAt(i); i++; 
			if(i+(textLen - text.length()) == textLen && textLen%2 == 1) {
				arrText[arrTextIdx][1] = 'x';
			}else {
				arrText[arrTextIdx][1] = text.charAt(i);
			}
			i++;
			//같다면
			if(arrText[arrTextIdx][0]==arrText[arrTextIdx][1]) {
				arrText[arrTextIdx][1] = 'x';
				textLen++;
				i--;
			}
			//xx라면..?
			//xxxx가 된다..... //issue
			arrTextIdx++;
		}
		
		String changeText = "";
		for (int i = 0; i < arrTextIdx; i++) {
			changeText+=arrText[i][0];
			changeText+=arrText[i][1];
			changeText+=" ";
		}

		
		char[][] arrRes = new char[1000000][2];//2,000,000글자까지
		ArrayList<Integer> arrAlphaTemp = new ArrayList<>();
		for (int i = 0; i < arrAlpha.length; i++) {
			Arrays.stream(arrAlpha).forEach(a-> arrAlphaTemp.add(Integer.valueOf(a)));
		}
		for (int i = 0; i < arrTextIdx; i++) {
			int firstIdx = arrAlphaTemp.indexOf(arrText[i][0]-'a'+1);
			int secondIdx = arrAlphaTemp.indexOf(arrText[i][1]-'a'+1);
			int nextFIdx = -1;
			int nextSIdx = -1;
			if(firstIdx/5 == secondIdx/5 ) { //열이 같다.
				nextFIdx = (firstIdx%5+1)>=5?0:firstIdx+1;
				nextSIdx = (secondIdx%5+1)>=5?0:secondIdx+1;
			}else if(firstIdx%5 == secondIdx%5 ) { //행이 같다.
				nextFIdx = (firstIdx+5)>=25?(firstIdx%5):firstIdx+5;
				nextSIdx = (secondIdx+5)>=25?(secondIdx%5):secondIdx+5;
			}else { //대각선
				int fRow = firstIdx/5, sRow = secondIdx/5;
				int fCol = firstIdx%5, sCol = secondIdx%5;
				//열은 자기 열인데 행은 남의 행
				nextFIdx = sRow*5+fCol;
				nextSIdx = fRow*5+sCol;
			}
			arrRes[i][0] = (char)(arrAlphaTemp.get(nextFIdx)+'a'-1);
			arrRes[i][1] = (char)(arrAlphaTemp.get(nextSIdx)+'a'-1);
		}

		String res = "";
		for (int i = 0; i < arrTextIdx; i++) {
			res+=arrRes[i][0];
			res+=arrRes[i][1];
			res+=" ";
		}
		
		tfRes.setText(res);
		
		//암호화 END
	}
	
	void startDecryption() {
		String text = tfText.getText().trim();
		text = text.replace(" ", ""); //공백 제거
		text = text.toLowerCase(); //모두 소문자로 처리
		
		//복호화
		
		//arrText에 치환해서 넣음
		ArrayList<char[]> arrText = new ArrayList<>(); //arrayList사용

		//처음 문자들 세팅
		for (int i = 0; i < text.length(); i+=2) {
			char[] t = new char[2];
			t[0] = text.charAt(i);
			t[1] = text.charAt(i+1);
			arrText.add(t);
		}
		
		//arrAlphaTemp는 암호판의 영문자들이 문자-'a'+1로 저장돼있음
		ArrayList<Integer> arrAlphaTemp = new ArrayList<>();
		for (int i = 0; i < arrAlpha.length; i++) {
			Arrays.stream(arrAlpha).forEach(a-> arrAlphaTemp.add(Integer.valueOf(a)));
		}
		for (int i = 0; i < arrText.size(); i++) {
			//치환
			//암호판의 문자들은 arrAlphaTemp에 문자-'a'+1로 저장돼있음
			int firstIdx = arrAlphaTemp.indexOf(arrText.get(i)[0]-'a'+1);
			int secondIdx = arrAlphaTemp.indexOf(arrText.get(i)[1]-'a'+1);
			
			int nextFIdx = -1;
			int nextSIdx = -1;

			if(firstIdx/5 == secondIdx/5 ) { //열이 같다.
				nextFIdx = (firstIdx%5-1)<0?4:firstIdx-1;
				nextSIdx = (secondIdx%5-1)<0?4:secondIdx-1;
			}else if(firstIdx%5 == secondIdx%5 ) { //행이 같다.
				nextFIdx = (firstIdx-5)<0?(3+firstIdx%5):firstIdx-5;
				nextSIdx = (secondIdx-5)<0?(3+secondIdx%5):secondIdx-5;
			}else { //대각선
				int fRow = firstIdx/5, sRow = secondIdx/5;
				int fCol = firstIdx%5, sCol = secondIdx%5;
				//열은 자기 열인데 행은 남의 행
				nextFIdx = sRow*5+fCol;
				nextSIdx = fRow*5+sCol;
			}
			
			arrText.set(i, new char[] {
					(char)(arrAlphaTemp.get(nextFIdx)+'a'-1),
					(char)(arrAlphaTemp.get(nextSIdx)+'a'-1)
				});
		}
		
		String res = "";
		for(int i = 0; i < arrText.size(); i++) {
			if(i != arrText.size()-1 && 
					arrText.get(i)[1]== 'x' && 
					arrText.get(i)[0]==arrText.get(i+1)[0]) {
				res+=arrText.get(i)[0];
			}else {
				res+=arrText.get(i)[0];
				res+=arrText.get(i)[1];
			}
		}
		
		//마지막 x 처리, 띄어쓰기 처리 ......
		
		tfRes.setText(res);
		//복호화 END
	}
	
	void addAction() {
		btnBack.addActionListener(e->{
			dispose();
			new Main().setVisible(true);
		});
		
		btnEncryption.addActionListener(e->{
			if(check()) {
				createCipherPlate();
				startEncryption();
				
			}
			
		});
		btnDecryption.addActionListener(e->{
			if(tfText.getText().replace(" ", "").length()%2!=0) {
				JOptionPane.showMessageDialog(null, "암호문의 길이는 짝수로 이루어져 있습니다.", "경고", JOptionPane.ERROR_MESSAGE);
				tfText.grabFocus();
				return;
			}
			if(check()){
				createCipherPlate();
				startDecryption();
			}
		});
		
	}
	
	public static void main(String[] args) {
		new Cipher().setVisible(true);
	}	
}


