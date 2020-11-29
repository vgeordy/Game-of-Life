import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Cell extends JButton implements ActionListener {

	private int posX;
	private int posY;
	private boolean state;

	public Cell(int posX, int posY, boolean state) {
		super();
		this.setPosX(posX);
		this.setPosY(posY);
		this.setState(state);

	}
	public Cell() {
		super();
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
		if (e.getSource() == this) {
			this.setState(true);
			this.setBackground(Color.WHITE);
		}

	}

}
