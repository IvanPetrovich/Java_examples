package ProjectCars;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * Создать меню с выбором марки автомобиля.
 * При выборе марки автомобиля 
 * должно появляться подменю 
 * с выбором моделей данной марки. 
 * После выбора модели 
 * вывести информацию о выбранном автомобиле.
 */
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class RunCars extends JFrame{
	JTextArea label;
	ArrayList<Car> cars;
	
	public RunCars() {
		ActionListener listener = new Listener(); 
		
		fillCars();//заполняем авто
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		label = new JTextArea("Car info");
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.add(label);
		
		
		JMenuBar jmb = new JMenuBar();
		JMenu menuCar = new JMenu("Cars");
		int carCount=0;
		for(Car car:cars) {//цикл по всем записям
			int i=0;//индекс меню бренда
			int countItems = menuCar.getItemCount();
			boolean brandAvailable = false; 
			//находим бренд в меню	
				for(i=0;i<countItems;i++) {
					if(menuCar.getItem(i).getLabel().equals(car.brand)) {
						brandAvailable = true;
						break;
					}
				}
				if (!brandAvailable) {//если не нашли бренд, добавляем в меню
					menuCar.add(new JMenu(car.brand));
					i = countItems;
				}
				
			JMenuItem jmItemModel = new JMenuItem(car.model);
			//устанавливаем имя как "car"+ индекс в коллнекции, чтобы потом получить информацию
			jmItemModel.setName("car"+carCount);
			jmItemModel.addActionListener(listener);
			menuCar.getItem(i).add(jmItemModel);	
			carCount++;
		}
		
		menuCar.addSeparator();
		JMenuItem itemExit = new JMenuItem("Exit");
		itemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuCar.add(itemExit);
		
		jmb.add(menuCar);
		setJMenuBar(jmb);
		pack();

	}
	
	public void fillCars() {
		
		cars = new ArrayList<Car>(); 
		cars.add(new Car("Audi","Q7", 2011, 1600));
		cars.add(new Car("Kia","Sportage", 2018, 1555));
		cars.add(new Car("Audi","A6", 2011, 1150));
		cars.add(new Car("Hyundai","Santa fe", 2018, 2204));
		cars.add(new Car("Audi","A8", 1998, 340));
		
		cars.add(new Car("Hyundai","Solaris", 2016, 680));
		cars.add(new Car("Hyundai","Tucson", 2006, 620));
		
		cars.add(new Car("Kia","Rio", 2013, 560));
		cars.add(new Car("Audi","A4", 2008, 550));
		cars.add(new Car("Kia","Sorento", 2018, 2120));
		
	}
	
	public static void main(String[] args) {
		
		new RunCars().setVisible(true);
		
	}

	public class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//получаем номер элемента в коллекции
			int item = Integer.parseInt(((JMenuItem)e.getSource()).getName().replaceAll("car", ""));
			
			label.setText(cars.get(item).showInfo());
			pack();//размер окна, чтобы вошли все компоненты
		}
		
	}
}
