package by.sva.xogame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // ловит нажатия на кнопки
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Основная логика
public class XOGame implements ActionListener {
	
	JFrame frame = new JFrame(); // главное окно
	JPanel infoPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JLabel text = new JLabel();
	JButton[] buttons = new JButton[9];
	
	boolean firstPlayer;
	
	XOGame(){
		// настройки главного окна
		frame.setSize(800, 900); // размер окна
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // при закрытии окна (крестик) -> выход и освобождение памяти
		frame.getContentPane().setBackground(new Color(70,70,70)); // Задать звет фона RGB
		frame.setLayout(new BorderLayout()); // менеджер компановки объектов на слое
		frame.setVisible(true);
		
		// настройки JLabel
		text.setBackground(new Color(30,30,30)); // цвет фона
		text.setForeground(new Color(30,200,10)); // цвет шрифта
		text.setFont(new Font("Ink Free", Font.BOLD, 50)); // задать шрифт, ЖИРНЫЙ, размер шрифта
		text.setHorizontalAlignment(JLabel.CENTER); // выравнивание текста по центру
		text.setText("X - 0 Game"); // задать текст
		text.setOpaque(true); // уставнока прозрачности
		
		// настройка Panel
		infoPanel.setLayout(new BorderLayout()); // менеджер компановки объектов на слое
		infoPanel.setBounds(0, 0, 800, 200); // размеры (координаты верхнего левого угла, ширина, высота
		infoPanel.add(text); // добавление объекта на панель
		
		// настройка Panel
		buttonPanel.setLayout(new GridLayout(3,3)); // менеджер компановки объектов на слое (таблица 3х3)
		buttonPanel.setBackground(new Color(200,200,200)); // цвет фона
		
		// настройка кнопок
		for(int i=0; i<9; i++) {
			buttons[i] = new JButton(); // создать кнопку
			buttonPanel.add(buttons[i]); // добавить кнопку на панель
			buttons[i].setFont(new Font("MVBoli", Font.BOLD, 100)); // установить шрифт
			buttons[i].setFocusable(false); // после нажатия не оставаться нажатой
			buttons[i].addActionListener(this); // задать слушатель нажатия. this - т.к. есть имплементированный метод
		}
		
		frame.add(infoPanel, BorderLayout.NORTH); // добавить панель в главное окно, прижать к верхнему краю
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
					check(); // проверка
				} else {
					buttons[i].setForeground(new Color(0,0,200));
					buttons[i].setText("0");
					buttons[i].setEnabled(false);
					firstPlayer = true;
					text.setText("X turn");
					check(); // проверка
				}
			}
		}
	}
	
	
	// проверка совпадения
	public void check() {
		// горизонтали
		if (buttons[0].getText() == "X" && buttons[1].getText() == "X" && buttons[2].getText() == "X") {
			xWins(0,1,2);
		}
		
		if (buttons[3].getText() == "X" && buttons[3].getText() == "X" && buttons[5].getText() == "X") {
			xWins(3,4,5);
		}
		
		if (buttons[6].getText() == "X" && buttons[7].getText() == "X" && buttons[8].getText() == "X") {
			xWins(6,7,8);
		}
		
		// вертикали
		if (buttons[0].getText() == "X" && buttons[3].getText() == "X" && buttons[6].getText() == "X") {
			xWins(0,3,6);
		}
		
		if (buttons[1].getText() == "X" && buttons[4].getText() == "X" && buttons[7].getText() == "X") {
			xWins(1,4,7);
		}
		
		if (buttons[2].getText() == "X" && buttons[5].getText() == "X" && buttons[8].getText() == "X") {
			xWins(2,5,8);
		}
		
		// диагонали
		if (buttons[0].getText() == "X" && buttons[4].getText() == "X" && buttons[8].getText() == "X") {
			xWins(0,4,8);
		}
		
		if (buttons[2].getText() == "X" && buttons[4].getText() == "X" && buttons[6].getText() == "X") {
			xWins(2,4,6);
		}
		
		
		// горизонтали
		if (buttons[0].getText() == "0" && buttons[1].getText() == "0" && buttons[2].getText() == "0") {
			oWins(0,1,2);
		}
		if (buttons[3].getText() == "0" && buttons[3].getText() == "0" && buttons[5].getText() == "0") {
			oWins(3,4,5);
		}
		
		if (buttons[6].getText() == "0" && buttons[7].getText() == "0" && buttons[8].getText() == "0") {
			oWins(6,7,8);
		}
		
		// вертикали
		if (buttons[0].getText() == "0" && buttons[3].getText() == "0" && buttons[6].getText() == "0") {
			oWins(0,3,6);
		}
		
		if (buttons[1].getText() == "0" && buttons[4].getText() == "0" && buttons[7].getText() == "0") {
			oWins(1,4,7);
		}
		
		if (buttons[2].getText() == "0" && buttons[5].getText() == "0" && buttons[8].getText() == "0") {
			oWins(2,5,8);
		}
		
		// диагонали
		if (buttons[0].getText() == "0" && buttons[4].getText() == "0" && buttons[8].getText() == "0") {
			oWins(0,4,8);
		}
		
		if (buttons[2].getText() == "0" && buttons[4].getText() == "0" && buttons[6].getText() == "0") {
			oWins(2,4,6);
		}		
		
	}
	
	// смена цветов кнопок на линии в случае победы X
	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(Color.CYAN);
		buttons[b].setBackground(Color.CYAN);
		buttons[c].setBackground(Color.CYAN);
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false); // деактивировать кнопку
		}
		
		text.setText("X WON");
	}
	
	// смена цветов кнопок на линии в случае победы O
	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(Color.CYAN);
		buttons[b].setBackground(Color.CYAN);
		buttons[c].setBackground(Color.CYAN);
		
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false); // деактивировать кнопку
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
