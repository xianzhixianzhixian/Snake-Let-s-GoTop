package Game;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameMenu extends JMenuBar{

	
	private static final long serialVersionUID = 1L;
	private JMenu mapMenu;
	private JMenuItem mapItem1,mapItem2,mapItem3;



	public JMenu getMapMenu() {
		return mapMenu;
	}



	public JMenuItem getMapItem1() {
		return mapItem1;
	}



	public JMenuItem getMapItem2() {
		return mapItem2;
	}



	public JMenuItem getMapItem3() {
		return mapItem3;
	}




	public GameMenu() {
		
		mapMenu = new JMenu("MAP");
		add(mapMenu);
		
		
		mapItem1 = new JMenuItem("Map1");
		mapItem2 = new JMenuItem("Map2");
		mapItem3 = new JMenuItem("Map3");
		
		mapMenu.add(mapItem1);
		mapMenu.add(mapItem2);
		mapMenu.add(mapItem3);
	}
	
}
