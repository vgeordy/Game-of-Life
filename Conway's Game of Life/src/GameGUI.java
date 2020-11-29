import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameGUI extends JFrame{

	
	private Cell[] cellBoard = new Cell[9];
	
	
	
	private JPanel panel = new JPanel();
	
	
	public GameGUI() {
		
	
		super("Conway's Game of Life");
		
		setSize(800,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		panel.setLayout(new GridLayout(3,3));
		
		for(int i=0;i<9;i++){
			cellBoard[i] = new Cell();
			panel.add(cellBoard[i]);

		}
		add(panel);

		setVisible(true);
		
	}
	public static void main(String[] args) {
		
		new GameGUI();

	}
	

	
	
	
	
}
