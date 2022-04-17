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
		super(900, 550);

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
		tfRes.setEnabled(false);
		
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
		if(tfCipherKey.getText().length() == 0 || tfText.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "빈칸이 존재합니다.", "경고", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		//이부분 잘 안됨
		if(!tfCipherKey.getText().matches("(.*)[a-zA-Z](.*)")) {
			tfCipherKey.setText("");
			tfCipherKey.grabFocus();
		}
		if(!tfText.getText().matches("(.*)[a-zA-Z](.*)")) {
			tfText.setText("");
			tfText.grabFocus();
		}
		if(!tfCipherKey.getText().matches("(.*)[a-zA-Z](.*)")||!tfText.getText().matches("(.*)[a-zA-Z](.*)")) {
			JOptionPane.showMessageDialog(null, "영문자만 입력해 주시길 바랍니다.", "경고", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	void startCipher() {
		String text = tfText.getText().trim();
		text = text.replace(" ", ""); //공백 제거
		text = text.toLowerCase(); //모두 소문자로 처리
		
		/*
			행끼리 : 아래
			열끼리 : 오른
			둘 다 X : 대각선
			
			중복 : x로
			q/z중에서는 더 빠른 q로
		*/
		
		//암호화/복호화
		
		/*
		 
		 //배열
		 평문을 2개씩 묶어서 배열한다.
		 묶인 2개의 영문자가 같다면 2번째 문자를 'X'로 넣고 같은 S를 다음으로 넘겨준다. (여기서 바꿔줄 문자인 X는 다른 문자로도 가능하다.)
		 마지막에 글자가 1개가 남게 되어도 'X'를 뒤에 넣어준다.
		 
		 //치환
		 (암호판은 1차원 배열.) -- 두 개의 문자가 
		 	x/5 == a/5  :  열이 같다.
		 	x%5 == a%5  :  행이 같다.
		 	위에 둘 다 아니다 : 대각선이다.
		 	
		 	열이 같은 경우 : 순서대로 자기 인덱스 + 1(+1이 5이상이면 0으로)
		 	행이 같은 경우 : 순서대로 자기 인덱스 + 5(+5가 5이상이면 (자기 인덱스%5))
		 	대각선인 경우 : 각 행과 열을 구한다. 행이 더 작으면 자기 인덱스 + 5, 행이 더 크면 자기 인덱스 - 5
		 	만약 Q/Z라면 Q로 함.
		 	(행 : 인덱스/5, 열 : 인덱스%5)
		 
		 * */
		
		char[][] arrText = new char[1000000][2];//2,000,000글자
		
		/*
		 
		 //배열
		 평문을 2개씩 묶어서 배열한다.
		 묶인 2개의 영문자가 같다면 2번째 문자를 'X'로 넣고 같은 문자를 다음으로 넘겨준다. (여기서 바꿔줄 문자인 X는 다른 문자로도 가능하다.)
		 마지막에 글자가 1개가 남게 되어도 'X'를 뒤에 넣어준다.
		*/

		int arrTextIdx = 0;
		int textLen = text.length();
		for (int i = 0; i < text.length();) {
			System.out.println(arrTextIdx);
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
		
		//출력 TEST
		String changeText = "";
		for (int i = 0; i < arrTextIdx; i++) {
			System.out.print(arrText[i][0]+""+arrText[i][1]+" ");
			changeText+=arrText[i][0];
			changeText+=arrText[i][1];
			changeText+=" ";
		}
		tfText.setText(changeText);
		//for end
		
		/*
		 //치환
		 (암호판은 1차원 배열.) -- 두 개의 문자가 
		 	x/5 == a/5  :  열이 같다.
		 	x%5 == a%5  :  행이 같다.
		 	위에 둘 다 아니다 : 대각선이다.
		 	
		 	열이 같은 경우 : 순서대로 자기 인덱스 + 1(+1이 5이상이면 0으로)
		 	행이 같은 경우 : 순서대로 자기 인덱스 + 5(+5가 25이상이면 (자기 인덱스%5))
		 	대각선인 경우 : 각 행과 구한다. ---- 더 생각
		 	 행이 더 작으면 자기 인덱스 + 5, 행이 더 크면 자기 인덱스 - 5
		 	만약 Q/Z라면 Q로 함.
		 	(행 : 인덱스/5, 열 : 인덱스%5)
		 	
		 * */
		char[][] arrRes = new char[1000000][2];//2,000,000글자까지
		ArrayList<Integer> arrAlphaTemp = new ArrayList<>();
		for (int i = 0; i < arrAlpha.length; i++) {
			Arrays.stream(arrAlpha).forEach(a-> arrAlphaTemp.add(Integer.valueOf(a)));
			//(char)(arrAlphaTemp.get(0)+'a'-1)
		}
		for (int i = 0; i < arrTextIdx; i++) {
			//arrAlpha의 문자가 있는 index를 찾는다.
			//if( arrAlpha[] )
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
		
		//암호화/복호화 END
	}
	
	void addAction() {
		btnBack.addActionListener(e->{
			System.exit(0);
//			dispose();
//			new Main().setVisible(true);
		});
		
		btnEncryption.addActionListener(e->{
			if(check()) {
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
				System.out.println(cipherKey);
				for (int i = 0; i < cipherKey.length(); i++) {
					char alpha = cipherKey.charAt(i);
					if(arrAlpha[arrAlphaIdx] == 0&&!arrAlphaChk[(alpha-'a')]) {
						arrAlpha[arrAlphaIdx] = (alpha-'a'+1);
						arrAlphaChk[(alpha-'a')] = true;
						switch(alpha) {
						case 'z' : arrAlphaChk[('q'-'a')]= true; break;
						case 'q' : arrAlphaChk[('z'-'a')]= true; break;
						}
						tfAlphaList[arrAlphaIdx].setText(Character.toString((char)('a'+arrAlpha[arrAlphaIdx]-1)));
						if(tfAlphaList[arrAlphaIdx].getText().equals("q")||
								tfAlphaList[arrAlphaIdx].getText().equals("z")) {
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
						if(i == 'q'-'a') {
							arrAlphaChk[('z'-'a')]= true;
						}else if(i == 'z'-'a') {
							arrAlphaChk[('q'-'a')]= true;
						}
						arrAlpha[arrAlphaIdx] = i+1;
						arrAlphaChk[i] = true;
						tfAlphaList[arrAlphaIdx].setText(Character.toString((char)('a'+arrAlpha[arrAlphaIdx]-1)));
						if(tfAlphaList[arrAlphaIdx].getText().equals("q")||
								tfAlphaList[arrAlphaIdx].getText().equals("z")) {
							tfAlphaList[arrAlphaIdx].setText("q/z");
						}
						arrAlphaIdx++;
					}
				}
				
				
				startCipher();
				
			}
			
		});
		btnDecryption.addActionListener(e->{
			if(check()){
				
			}
		});
		
	}
	
	public static void main(String[] args) {
		new Cipher().setVisible(true);
	}	
}


