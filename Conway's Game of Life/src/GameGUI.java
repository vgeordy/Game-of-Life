import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;

public class GameGUI extends JFrame implements ActionListener {

	private int size;
	int sizeSqrt;
	private Cell[] cellBoard;
	private int numOfNeighborsAlive;
	private boolean running;

	private JPanel boardPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton startButton = new JButton("Step");
	private JButton resetButton = new JButton("reset");
	private JButton randomizeButton = new JButton("Randomize");
	private JToggleButton runButton = new JToggleButton("Run");


	public GameGUI(int sz) {

		super("Conway's Game of Life");
		setBoardSize(sz);
		setSize(800, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cellBoard = new Cell[this.size];
		setLayout(new BorderLayout());
		boardPanel.setBackground(Color.RED);
		sizeSqrt = (int) Math.sqrt(this.size);
		boardPanel.setLayout(new GridLayout(sizeSqrt, sizeSqrt, 1, 1));
		boardPanel.setPreferredSize(new Dimension(800, 800));
		buttonPanel.setPreferredSize(new Dimension(100, 100));
		initBoard();
		randomizeBoard();

		startButton.addActionListener(this);
		runButton.addActionListener(this);
		resetButton.addActionListener(this);
		randomizeButton.addActionListener(this);
		add(boardPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.add(startButton);
		buttonPanel.add(runButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(randomizeButton);
		setVisible(true);
		pack();

	}

	public void setBoardSize(int s) {
		if (Math.sqrt(s) % 1 == 0) {
			this.size = s;
		} else {
			throw new IllegalArgumentException("Enter a perfect square");
		}
	}

	public void initBoard() {
		for (int i = 0; i < this.cellBoard.length; i++) {
			cellBoard[i] = new Cell(i);
			boardPanel.add(cellBoard[i]);

		}
	}

	public void reset() {
		for (int i = 0; i < cellBoard.length; i++) {
			cellBoard[i].reset();

		}

	}

	public void randomizeBoard() {
		for (int i = 0; i < cellBoard.length; i++) {
			cellBoard[i].setState(Math.random() < 0.3 ? true : false);
			cellBoard[i].paint();
		}
	}

	public void updateBoard() {
		for (int i = 0; i < cellBoard.length; i++) {
			cellBoard[i].setState(cellBoard[i].getNextState());
			cellBoard[i].paint();
		}
		revalidate();
		repaint();
		pack();
	}

	/*
	 * public void updateBoard() {
	 * 
	 * for(int i=0;i<cellBoard.length;i++){ boardPanel.remove(cellBoard[i]);
	 * cellBoard[i] = new Cell(i,cellBoard[i].getNextState());
	 * boardPanel.add(cellBoard[i]);
	 * 
	 * }
	 * 
	 * revalidate(); repaint(); pack();
	 * 
	 * }
	 * 
	 */

	public void gameLogic() {
		/*
		 * Rules: 1. Any live cell with fewer than two live neighbors dies, as if by
		 * underpopulation. 2. Any live cell with two or three live neighbors lives on
		 * to the next generation. 3. Any live cell with more than three live neighbors
		 * dies, as if by overpopulation. 4. Any dead cell with exactly three live
		 * neighbors becomes a live cell, as if by reproduction.
		 */

		// 1

		for (int i = 0; i < cellBoard.length; i++) {
			isNeighborAlive(i);
			// 1. Any live cell with fewer than two live neighbors dies, as if by
			// underpopulation.
			if (cellBoard[i].isAlive() && this.numOfNeighborsAlive < 2) {
				cellBoard[i].setNextState(false);

				// Any live cell with two or three live neighbors lives on to the next
				// generation.
			}
			if (cellBoard[i].isAlive() && (this.numOfNeighborsAlive == 2 || this.numOfNeighborsAlive == 3)) {
				cellBoard[i].setNextState(true);

				// Any live cell with more than three live neighbors dies, as if by
				// overpopulation.
			}
			if (cellBoard[i].isAlive() && this.numOfNeighborsAlive > 3) {
				cellBoard[i].setNextState(false);

			}
			if (!cellBoard[i].isAlive() && this.numOfNeighborsAlive == 3) {
				cellBoard[i].setNextState(true);

			}

		}

	}

	public boolean isNeighborAlive(int index) {

		this.numOfNeighborsAlive = 0;
		if (index >= 0 && index < cellBoard.length) {
			if (index + 1 < cellBoard.length) {
				if (cellBoard[index + 1].isAlive()) {
					this.numOfNeighborsAlive++;
				}
			}
			if (index - 1 >= 0) {
				if (cellBoard[index - 1].isAlive()) {
					this.numOfNeighborsAlive++;
				}
			}
			if (index + this.sizeSqrt < cellBoard.length) {
				if (cellBoard[index + this.sizeSqrt].isAlive()) {
					this.numOfNeighborsAlive++;
				}
			}
			if (index - this.sizeSqrt >= 0) {
				if (cellBoard[index - this.sizeSqrt].isAlive()) {
					this.numOfNeighborsAlive++;
				}
			}
			if (index - this.sizeSqrt - 1 >= 0) {
				if (cellBoard[index - this.sizeSqrt - 1].isAlive()) {
					this.numOfNeighborsAlive++;
				}
			}
			if (index - this.sizeSqrt + 1 >= 0) {
				if (cellBoard[index - this.sizeSqrt + 1].isAlive()) {
					this.numOfNeighborsAlive++;
				}

			}
			if (index + this.sizeSqrt - 1 < cellBoard.length) {
				if (cellBoard[index + this.sizeSqrt - 1].isAlive()) {
					this.numOfNeighborsAlive++;
				}
			}
			if (index + this.sizeSqrt + 1 < cellBoard.length) {
				if (cellBoard[index + this.sizeSqrt + 1].isAlive()) {
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

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GameGUI(3600);

			}

		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object thing = e.getSource();

		if (thing == startButton) {
			gameLogic();
			updateBoard();
		}

		if (thing == runButton) {
			if (runButton.isSelected()){
				running = true;
				runButton.setText("Stop");
				backgroundRun();
			}else {
				running = false;
				runButton.setText("Run");
			}
			
		}
		

		if (thing == resetButton){
			this.reset();
		}

		if (thing == randomizeButton){
			this.randomizeBoard();
		}

	}
	public void backgroundRun(){
		SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				while(running){
					gameLogic();
					updateBoard();
					Thread.sleep(150);
				}
				if (startButton.isSelected()){
					gameLogic();
					updateBoard();
				}

				
				return null;
			}
			
		};
		worker.execute();
	}
	
	

	
	
	
	
}
