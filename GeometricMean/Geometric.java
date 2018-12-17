import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Geometric extends JFrame{

//рассчёт
	double getGeometric(ArrayList<Integer> mas){
		double res = 1f;
		for(Integer i:mas) {
			res = res*i;
		}
//		res = Math.pow(res,1.0/mas.size());		//как вариант
		res = Math.exp(Math.log(res)/mas.size());
		return res;
	}

//конструктор	
	public Geometric(ArrayList<Integer> mas){
		super("Среднее геометрическое");
		setSize(400, 125);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container cont = getContentPane();
		cont.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
//метки
		JLabel lSource = new JLabel();
		JLabel lResult = new JLabel();

//кнопка		
		JButton bGetResult = new JButton("Calculate");

//поля ввода		
		JTextField tArg1 = new JTextField(4);
		JTextField tArg2 = new JTextField(4);
		
//работаем с кнопкой		
//курсор над кнопкой будет в виде пальца
		bGetResult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
// нажатие кнопки срабатывает при Alt+C
		bGetResult.setMnemonic('C');
//слушатель при нажатии
		bGetResult.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//коллекция, чтобы можно было вводить разные данные в поля и рассчитывать
				ArrayList<Integer> masTemp = new ArrayList<Integer>();
				masTemp.addAll(mas);
				try {
					masTemp.add(Integer.parseInt(tArg1.getText()));
					masTemp.add(Integer.parseInt(tArg2.getText()));
				}catch(Exception e){
				
				}
				double res = getGeometric(masTemp);
				lResult.setText("Среднее геометрическое: " + Double.toString(res));

			}
		});		
//слушатель при изменении кнопки	
		
		bGetResult.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
			
			//меняется шрифт	
				bGetResult.setFont(new Font("Times New Roman", 0, 10));
			//меняется общий курсор над фреймом	
				setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			}
		});

//текст метки и цвет		
		lSource.setText("Дано: " + mas.toString());
		lSource.setForeground(Color.BLUE);
		lResult.setForeground(Color.RED);
		
//добавляем объекты в контейнер		
		cont.add(lSource);
		cont.add(tArg1);
		cont.add(tArg2);
		cont.add(bGetResult);
		cont.add(lResult);
		
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> mas = new ArrayList<Integer>();
		for(String s:args) {
			mas.add(Integer.parseInt(s));
		}
		new Geometric(mas).setVisible(true);
		
	}

}
