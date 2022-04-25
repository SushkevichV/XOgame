package by.sva.xogame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // ����� ������� �� ������
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// �������� ������
public class XOGame implements ActionListener {
	
	JFrame frame = new JFrame(); // ������� ����
	JPanel infoPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JLabel text = new JLabel();
	JButton[] buttons = new JButton[9];
	
	boolean firstPlayer;
	
	XOGame(){
		// ��������� �������� ����
		frame.setSize(800, 900); // ������ ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ��� �������� ���� (�������) -> ����� � ������������ ������
		frame.getContentPane().setBackground(new Color(70,70,70)); // ������ ���� ���� RGB
		frame.setLayout(new BorderLayout()); // �������� ���������� �������� �� ����
		frame.setVisible(true);
		
		// ��������� JLabel
		text.setBackground(new Color(30,30,30)); // ���� ����
		text.setForeground(new Color(30,200,10)); // ���� ������
		text.setFont(new Font("Ink Free", Font.BOLD, 50)); // ������ �����, ������, ������ ������
		text.setHorizontalAlignment(JLabel.CENTER); // ������������ ������ �� ������
		text.setText("X - 0 Game"); // ������ �����
		text.setOpaque(true); // ��������� ������������
		
		// ��������� Panel
		infoPanel.setLayout(new BorderLayout()); // �������� ���������� �������� �� ����
		infoPanel.setBounds(0, 0, 800, 200); // ������� (���������� �������� ������ ����, ������, ������
		infoPanel.add(text); // ���������� ������� �� ������
		
		// ��������� Panel
		buttonPanel.setLayout(new GridLayout(3,3)); // �������� ���������� �������� �� ���� (������� 3�3)
		buttonPanel.setBackground(new Color(200,200,200)); // ���� ����
		
		// ��������� ������
		for(int i=0; i<9; i++) {
			buttons[i] = new JButton(); // ������� ������
			buttonPanel.add(buttons[i]); // �������� ������ �� ������
			buttons[i].setFont(new Font("MVBoli", Font.BOLD, 100)); // ���������� �����
			buttons[i].setFocusable(false); // ����� ������� �� ���������� �������
			buttons[i].addActionListener(this); // ������ ��������� �������. this - �.�. ���� ������������������ �����
		}
		
		frame.add(infoPanel, BorderLayout.NORTH); // �������� ������ � ������� ����, ������� � �������� ����
		frame.add(buttonPanel);
		
		firstPlayer = setFirstPlayer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i<9; i++) {
			if(e.getSource() == buttons[i]) {
				if(firstPlayer) {
					buttons[i].setForeground(new Color(200,110,0));
					buttons[i].setText("X");
					buttons[i].setEnabled(false);
					firstPlayer = false;
					text.setText("0 turn");
					check(); // ��������
				} else {
					buttons[i].setForeground(new Color(0,0,200));
					buttons[i].setText("0");
					buttons[i].setEnabled(false);
					firstPlayer = true;
					text.setText("X turn");
					check(); // ��������
				}
			}
		}
	}
	
	
	// �������� ����������
	public void check() {
		// �����������
		if (buttons[0].getText() == "X" && buttons[1].getText() == "X" && buttons[2].getText() == "X") {
			xWins(0,1,2);
		}
		
		if (buttons[3].getText() == "X" && buttons[3].getText() == "X" && buttons[5].getText() == "X") {
			xWins(3,4,5);
		}
		
		if (buttons[6].getText() == "X" && buttons[7].getText() == "X" && buttons[8].getText() == "X") {
			xWins(6,7,8);
		}
		
		// ���������
		if (buttons[0].getText() == "X" && buttons[3].getText() == "X" && buttons[6].getText() == "X") {
			xWins(0,3,6);
		}
		
		if (buttons[1].getText() == "X" && buttons[4].getText() == "X" && buttons[7].getText() == "X") {
			xWins(1,4,7);
		}
		
		if (buttons[2].getText() == "X" && buttons[5].getText() == "X" && buttons[8].getText() == "X") {
			xWins(2,5,8);
		}
		
		// ���������
		if (buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "X") {
			xWins(0,4,8);
		}
		
		if (buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "X") {
			xWins(2,4,6);
		}
		
		
		// �����������
		if (buttons[0].getText() == "0" && buttons[1].getText() == "0" && buttons[2].getText() == "0") {
			oWins(0,1,2);
		}
		if (buttons[3].getText() == "0" && buttons[3].getText() == "0" && buttons[5].getText() == "0") {
			oWins(3,4,5);
		}
		
		if (buttons[6].getText() == "0" && buttons[7].getText() == "0" && buttons[8].getText() == "0") {
			oWins(6,7,8);
		}
		
		// ���������
		if (buttons[0].getText() == "0" && buttons[3].getText() == "0" && buttons[6].getText() == "0") {
			oWins(0,3,6);
		}
		
		if (buttons[1].getText() == "0" && buttons[4].getText() == "0" && buttons[7].getText() == "0") {
			oWins(1,4,7);
		}
		
		if (buttons[2].getText() == "0" && buttons[5].getText() == "0" && buttons[8].getText() == "0") {
			oWins(2,5,8);
		}
		
		// ���������
		if (buttons[0].getText() == "0" && buttons[4].getText() == "0" && buttons[8].getText() == "0") {
			oWins(0,4,8);
		}
		
		if (buttons[2].getText() == "0" && buttons[4].getText() == "0" && buttons[6].getText() == "0") {
			oWins(2,4,6);
		}		
		
	}
	
	// ����� ������ ������ �� ����� � ������ ������ X
	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(Color.CYAN);
		buttons[b].setBackground(Color.CYAN);
		buttons[c].setBackground(Color.CYAN);
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false); // �������������� ������
		}
		
		text.setText("X WON");
	}
	
	// ����� ������ ������ �� ����� � ������ ������ O
	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(Color.CYAN);
		buttons[b].setBackground(Color.CYAN);
		buttons[c].setBackground(Color.CYAN);
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false); // �������������� ������
		}
		
		text.setText("O WON");
	}
	
	public boolean setFirstPlayer() {
		Random random = new Random();
		if(random.nextInt(2) == 0) {
			text.setText("First turn 0");
			return false;
		} else {
			text.setText("First turn X");
			return true;
		}
	}

}
