import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Cell extends JButton implements ActionListener {

	private int cellNum;
	private boolean state;
	private int count;

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

	public boolean isAlive() {
		if (state) {
			return true;
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
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
	
	
	
	

}
