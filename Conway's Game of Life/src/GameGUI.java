import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class GameGUI extends JFrame{

	private int size;
	int sizeSqrt;
	private Cell[] cellBoard;
	
	
	
	
	private JPanel boardPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton start = new JButton("Step");
	private JToggleButton run = new JToggleButton("Run");
	
	public GameGUI(int sz) {
		
		
		super("Conway's Game of Life");
		setSizeBoard(sz);
		setSize(800,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cellBoard = new Cell[this.size];
		setLayout(new BorderLayout());
		boardPanel.setBackground(Color.ORANGE);
		sizeSqrt = (int)Math.sqrt(this.size);
		boardPanel.setLayout(new GridLayout(sizeSqrt,sizeSqrt,1,1));
		boardPanel.setPreferredSize(new Dimension(800,800));
		buttonPanel.setPreferredSize(new Dimension(100,100));
		initBoard();
		
		add(boardPanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
		buttonPanel.add(start);
		buttonPanel.add(run);
		
		setVisible(true);
		pack();
		
	}
	public void setSizeBoard(int s){
		if (Math.sqrt(s) % 1 == 0) {
		this.size = s;
		}else {
			throw new IllegalArgumentException("Enter a perfect square");
		}
	}
	
	public void initBoard() {
		for(int i=0;i<this.size;i++){
			cellBoard[i] = new Cell();
			boardPanel.add(cellBoard[i]);

		}
	}
			
	

	
			
	

	public static void main(String[] args) {
		
		new GameGUI(100);
	
	}
	
	

	
	
	
	
}
