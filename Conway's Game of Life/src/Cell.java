import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Cell extends JButton implements ActionListener {

	private int posX;
	private int posY;
	private boolean state;
	private int count;

	public Cell(int posX, int posY, boolean state) {
		super();
		this.setPosX(posX);
		this.setPosY(posY);
		this.setState(state);
		this.addActionListener(this);
		setBorderPainted(false);
		setFocusPainted(false);
		setBackground(Color.BLACK);
	}
	public Cell() {
		super();
		this.addActionListener(this);
		setBorderPainted(false);
		setFocusPainted(false);
		setBackground(Color.BLACK);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
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
		if (count % 2 == 0) {
			this.setBackground(Color.BLACK);
		}else {
			this.setBackground(Color.WHITE);
		}

	}
	
	
	
	

}
