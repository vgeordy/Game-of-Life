import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;

public class Cell extends JButton implements ActionListener {

	
	
	private int cellNum;
	private boolean state;
	private boolean nextState;
	private int count = 0; 
	
	public Cell(int cellNum, boolean state) {
		super();
		this.setCellNum(cellNum);
		this.setState(state);
		this.addActionListener(this);
		setBorderPainted(false);
		setFocusPainted(false);
		paint();
	}
	
	public Cell(int cNum) {
		super();
		setCellNum(cNum);
		this.addActionListener(this);
		setBorderPainted(false);
		setFocusPainted(false);
		setBackground(Color.BLACK);
	}
	public int getCellNum() {
		
		return this.cellNum;
	}
	
	public void setCellNum(int cellN) {
		this.cellNum = cellN;
		
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean getNextState() {
		return nextState;
	}

	public void setNextState(boolean nextState) {
		this.nextState = nextState;
	}

	public boolean isAlive() {
		if (getState() == true) {
			return true;
		}

		return false;
	}

	public boolean isDead(){
		if (getState() == false){
			return true;
		}else {
			return false;
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		count++;   //look into how count should be implemented.
		if (count % 2 != 0) {
			this.setBackground(Color.WHITE);
			this.setState(true);
		}else {
			this.setBackground(Color.BLACK);
			this.setState(false);
		}

	}
	
	public void paint() {
		
		if (getState() == true) {
			setBackground(Color.WHITE);
		}else {
			setBackground(Color.BLACK);
		}
	}
	
	public void resetCell() {
		setState(false);
		setNextState(false);
		setBackground(Color.BLACK);
	}

}
