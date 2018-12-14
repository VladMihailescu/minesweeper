import javax.swing.*;
//import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Game implements MouseListener, ActionListener{
	private Board board;
	private GUI gui;
	private int rows;
	private int cols;
	private int mines;
	
	public Game(int rows, int cols, int mines) {
		this.rows = rows;
		this.cols = cols;
		this.mines = mines;
		
		this.board = new Board(this.rows, this.cols, this.mines);
		
		this.gui = new GUI(this.rows, this.cols, this.mines);
		this.gui.setListeners(this);
		this.gui.updateBoard(board);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton button = (JButton)arg0.getSource();
		String aux = button.getName();
		//System.out.println(aux);
		if(aux.equals("Reset")) {
			board.reset();
			gui.updateBoard(board);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(board.isFinished()) {
			return;
		}
		JButton button = (JButton)arg0.getSource();
		String[] aux = button.getName().split(",");
		int row = Integer.parseInt(aux[0]);
		int col = Integer.parseInt(aux[1]);
		if(!board.getCells()[row][col].isHidden()) {
			return;
		}
		if(SwingUtilities.isRightMouseButton(arg0)) {
			if(board.getCells()[row][col].isFlagged()) {
				board.getCells()[row][col].setFlagged(false);
			}else {
				board.getCells()[row][col].setFlagged(true);
			}
		}else if(SwingUtilities.isLeftMouseButton(arg0)) {
			if(board.getCells()[row][col].isFlagged() == false) {
				board.updateAround(row,col);
				board.updateGameStatus();
			}
		}
		gui.updateBoard(board);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
