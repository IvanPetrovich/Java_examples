package Calculator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class mainFrame extends JFrame{
	
	Calc calc;
	boolean lastKeyOperation;//флаг последнего нажатия на операцию (+-*/sqrt=)
	
	JLabel label;
	JButton buttonsDigit[];
	JButton buttonEquals;
//SUM, MUL, DIV, SUB, SQRT		
	JButton buttonSum;
	JButton buttonMul;
	JButton buttonDiv;
	JButton buttonSub;
	JButton buttonSqrt;
//сброс		
	JButton buttonC;
	JButton buttonComma;
	JButton buttonBackSpace;
	
	JTextField fieldExpression; 
	
//определяем панели
	JPanel mainPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	
//сдушатели
	BHandler bh;
	BHandlerDigits bhd;
	BHandlerOperations bho;
	BHandlerEquals bhe;
	BHandlerSqrt bhs;
	
	public mainFrame() {
		//4x4
		super("Канкулятор");
		label = new JLabel("0");
		lastKeyOperation = true;
		calc = new Calc();
		bh = new BHandler();
		bhd = new BHandlerDigits();
		bho = new BHandlerOperations();
		bhe = new BHandlerEquals();
		bhs = new BHandlerSqrt(); 
		setSize(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//10 digits
		buttonsDigit = new JButton[10];
		createButtonsDigits();
		buttonEquals = new JButton("=");
		buttonEquals.addActionListener(bhe);
//SUM, MUL, DIV, SUB, SQRT		
		buttonSum	 = new JButton("+");
		buttonSum.addActionListener(bho);
		buttonMul	 = new JButton("*");
		buttonMul.addActionListener(bho);
		buttonDiv	 = new JButton("/");
		buttonDiv.addActionListener(bho);
		buttonSub	 = new JButton("-");
		buttonSub.addActionListener(bho);
		buttonSqrt	 = new JButton("sqrt");
		buttonSqrt.addActionListener(bhs);
//сброс		
		buttonC	 = new JButton("C");
		buttonC.addActionListener(bh);
		buttonComma	 = new JButton(".");
		buttonComma.addActionListener(bhd);
		buttonBackSpace	 = new JButton("<-");
		buttonBackSpace.addActionListener(bh);
		
		fieldExpression = new JTextField("0",20);
		fieldExpression.setHorizontalAlignment(JTextField.RIGHT); // текст будет справа поля
		fieldExpression.setFont(new Font("Arial", Font.BOLD, 20));
//создаём панели
		mainPanel	 = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		topPanel	 = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(label,BorderLayout.NORTH);
		topPanel.add(fieldExpression,BorderLayout.SOUTH);
		createBottomPanel();

		Container c = getContentPane();
		mainPanel.add(topPanel,BorderLayout.NORTH);
		mainPanel.add(bottomPanel);
		c.add(mainPanel);
		
	}

//создание кнопочек
	void createBottomPanel() {
		bottomPanel	 = new JPanel();
		bottomPanel.setLayout(new GridLayout(5,4));
		
		bottomPanel.add(buttonBackSpace);
		bottomPanel.add(buttonDiv);
		bottomPanel.add(buttonMul);
		bottomPanel.add(buttonSub);
		
		bottomPanel.add(buttonsDigit[7]);
		bottomPanel.add(buttonsDigit[8]);
		bottomPanel.add(buttonsDigit[9]);
		bottomPanel.add(buttonSum);
		
		bottomPanel.add(buttonsDigit[4]);
		bottomPanel.add(buttonsDigit[5]);
		bottomPanel.add(buttonsDigit[6]);
		bottomPanel.add(buttonSqrt);
		
		bottomPanel.add(buttonsDigit[1]);
		bottomPanel.add(buttonsDigit[2]);
		bottomPanel.add(buttonsDigit[3]);
		bottomPanel.add(buttonEquals);
		
		bottomPanel.add(buttonsDigit[0]);
		bottomPanel.add(buttonsDigit[0]);
		bottomPanel.add(buttonComma);
		bottomPanel.add(buttonC);
		
	}
	
//Создание объектов кнопок с цифрами и привязка слушателей 	
	void createButtonsDigits() {
		for(int i=0;i<10;i++) {
			this.buttonsDigit[i] = new JButton(Integer.toString(i));
			this.buttonsDigit[i].addActionListener(bhd);
		}
			
	}
	
//классы слушатели
	public class BHandler implements ActionListener{
//слушатель для кнопок Бэкспейс и Сброс
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==buttonC) {//сброс
				fieldExpression.setText("0");
				calc.setCurrentOperation(null);
				calc.setOperandA(0f);
				calc.setOperandB("");
				calc.setCurrentOperation("");
			}else if(e.getSource()==buttonBackSpace) {//бэкспейс
				if(fieldExpression.getText().length()>0) 
					fieldExpression.setText(fieldExpression.getText().substring(0,fieldExpression.getText().length()-1));
				if(fieldExpression.getText().length()==0) {
					fieldExpression.setText("0");
				}
			}
			label.setText(calc.getExpression());
		}
	}

	public class BHandlerDigits implements ActionListener{
//слушатель для цифр и точки 
		@Override
		public void actionPerformed(ActionEvent e) {
			if(lastKeyOperation) {
				fieldExpression.setText("");
			}
			if(((JButton) e.getSource()).getText().equals(".")) {
				if(!fieldExpression.getText().contains(".")) {
					fieldExpression.setText(fieldExpression.getText() + ((JButton) e.getSource()).getText());
				}	
			}else {
				fieldExpression.setText(fieldExpression.getText() + ((JButton) e.getSource()).getText());
			}	

			lastKeyOperation = false;
		}
	}

	public class BHandlerOperations implements ActionListener{
//слушатель для кнопок операций
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!lastKeyOperation){
				if (calc.getCurrentOperation()==null) {
					calc.setOperandA( Double.parseDouble(fieldExpression.getText()) );
					calc.setCurrentOperation(((JButton) e.getSource()).getText());
				}else if(calc.getOperandB().equals("")){//если есть что считать
					calc.setOperandB( fieldExpression.getText() );
					calc.setOperandA(calc.calc());
					calc.setCurrentOperation(((JButton) e.getSource()).getText());
					fieldExpression.setText(String.valueOf(calc.getOperandA()));
					calc.setOperandB("");
				}else {
					calc.setOperandA(calc.calc());
					calc.setCurrentOperation(((JButton) e.getSource()).getText());
					fieldExpression.setText(String.valueOf(calc.getOperandA()));
					calc.setOperandB("");
				}
			}
			label.setText(calc.getExpression());
			calc.setCurrentOperation(((JButton) e.getSource()).getText());
			lastKeyOperation = true;
			
		}
	}

	public class BHandlerEquals implements ActionListener{
//слушатель для кнопки = 
		@Override
		public void actionPerformed(ActionEvent e) {
			if(calc.getCurrentOperation()!=null) {
				if(!lastKeyOperation) {
					calc.setOperandB( fieldExpression.getText() );
				}else if(calc.getOperandB().equals("")) {
					calc.setOperandB(String.valueOf(calc.getOperandA()));
				}
				calc.setOperandA(calc.calc());
				fieldExpression.setText(String.valueOf(calc.getOperandA()));
				lastKeyOperation = true;
				label.setText(calc.getExpression());
			}	
		}
	}
	
	public class BHandlerSqrt implements ActionListener{
//слушатель для кнопки квадратного корня
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!lastKeyOperation) {
				calc.setOperandB( String.valueOf(calc.sqrt(Double.parseDouble(fieldExpression.getText()))) );
			}else {
				calc.setOperandB( String.valueOf(calc.sqrt(Double.parseDouble(calc.getOperandB()))) );				
			}
			if(calc.getOperandA()==0) {
				calc.setCurrentOperation(((JButton) e.getSource()).getText());
				calc.setOperandA( Double.parseDouble(fieldExpression.getText()) );
			}
				
			calc.setOperandA(calc.calc());
			fieldExpression.setText(String.valueOf(calc.getOperandA()));
			lastKeyOperation = true;
			label.setText(calc.getExpression());
		}
	}
	
}
