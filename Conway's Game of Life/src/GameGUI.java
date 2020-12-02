import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class GameGUI extends JFrame{

	private int size;
	int sizeSqrt;
	private Cell[] cellBoard;
	private int numOfNeighborsAlive;
	private int gameLogicCounter;
	
	
	private JPanel boardPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton start = new JButton("Start");
	private JToggleButton run = new JToggleButton("Run");
	
	public GameGUI(int sz) {
		
		
		super("Conway's Game of Life");
		setBoardSize(sz);
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
		start.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
					gameLogic();
					
				}
		});
		add(boardPanel,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);
		buttonPanel.add(start);
		buttonPanel.add(run);
		
		setVisible(true);
		pack();
		
	}
	public void setBoardSize(int s){
		if (Math.sqrt(s) % 1 == 0) {
		this.size = s;
		}else {
			throw new IllegalArgumentException("Enter a perfect square");
		}
	}
	
	public void initBoard() {
		for(int i=0;i<this.cellBoard.length;i++){
			cellBoard[i] = new Cell(i);
			boardPanel.add(cellBoard[i]);

		}
	}
	
	public  void updateBoard() {
		JPanel newboardPanel = new JPanel();
		newboardPanel.setBackground(Color.ORANGE);
		newboardPanel.setLayout(new GridLayout(sizeSqrt,sizeSqrt,1,1));
		newboardPanel.setPreferredSize(new Dimension(800,800));
		Cell[] updatedBoard = new Cell[this.size];
		for(int i=0;i<this.cellBoard.length;i++){
			updatedBoard[i] = new Cell(i,cellBoard[i].getNextState());
			newboardPanel.add(updatedBoard[i]);
			
		}
		add(newboardPanel,BorderLayout.CENTER);
		pack();
	}
	
	
	public void gameLogic() {
		/*
		 *Rules: 
	1. Any live cell with fewer than two live neighbors dies, as if by underpopulation.
	2. Any live cell with two or three live neighbors lives on to the next generation.
	3. Any live cell with more than three live neighbors dies, as if by overpopulation.
	4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
		 */
		
		//1
		
		
/* TODO
 *  you have to set the state of the next generation, currently its setting the current state  to whatever fits the logic.
 *  
 */
		for(int j=0;j<this.size;j++) {
			for(int i=0;i<cellBoard.length;i++) {
			isNeighborAlive(i);
			//1. Any live cell with fewer than two live neighbors dies, as if by underpopulation.
			if (cellBoard[i].isAlive() && this.numOfNeighborsAlive < 2) {
				cellBoard[i].setNextState(false);
				
			//Any live cell with two or three live neighbors lives on to the next generation.
			}if (cellBoard[i].isAlive() && (this.numOfNeighborsAlive == 2 || this.numOfNeighborsAlive == 3)) {
				cellBoard[i].setNextState(true);
				
			// Any live cell with more than three live neighbors dies, as if by overpopulation.
			}if(cellBoard[i].isAlive() && this.numOfNeighborsAlive > 3) {
				cellBoard[i].setNextState(false);
				
			}if(!cellBoard[i].isAlive() && this.numOfNeighborsAlive == 3 ) {
				cellBoard[i].setNextState(true);
				
			}
		
		}
	}
		
		updateBoard();
	}
	
	
	public boolean isNeighborAlive(int index) {
		
		this.numOfNeighborsAlive = 0;
		if(index >= 0 && index < cellBoard.length) {
			if (index +1<cellBoard.length) {
				if(cellBoard[index+1].isAlive()) {
					this.numOfNeighborsAlive++;
				}
			}if (index-1 >=0) {
				if(cellBoard[index-1].isAlive()) {
				this.numOfNeighborsAlive++;
				}
			}if (index+this.sizeSqrt<cellBoard.length) {
				if(cellBoard[index+this.sizeSqrt].isAlive()) {
				this.numOfNeighborsAlive++;
				}
			}if (index-this.sizeSqrt >=0) {
				if(cellBoard[index-this.sizeSqrt].isAlive()) {
				this.numOfNeighborsAlive++;
				}
			}if (index-this.sizeSqrt-1 >=0) {
				if(cellBoard[index-this.sizeSqrt-1].isAlive()) {
				this.numOfNeighborsAlive++;
				}	
			}if (index-this.sizeSqrt+1 >=0) {
				if(cellBoard[index-this.sizeSqrt+1].isAlive()) {
				this.numOfNeighborsAlive++;
				}
				
			}if (index+this.sizeSqrt-1<cellBoard.length) {
				if (cellBoard[index+this.sizeSqrt-1].isAlive()) {
				this.numOfNeighborsAlive++;
				}
			}if (index+this.sizeSqrt+1<cellBoard.length) {
				if (cellBoard[index+this.sizeSqrt+1].isAlive()) {
				this.numOfNeighborsAlive++;
				}
			}
		}
		
		if (this.numOfNeighborsAlive > 0) {
			return true;
		}
		
		return false;
	}
	

	
			
	

	public static void main(String[] args) {
		
		new GameGUI(100);
	
	}
	
	

	
	
	
	
}
