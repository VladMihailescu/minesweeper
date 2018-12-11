import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
	private JButton[][] buttonsBoard;
	private int rows;
	private int cols;
	private int mines;
	
	private JLabel winLabel;
	private JLabel loseLabel;
	private JLabel mineLabel;
	private int SCREEN_WIDTH = 550;
	private int SCREEN_HEIGHT = 520;
	
	private Icon mineIcon;
	private Icon flagIcon;
	private Icon tileIcon;
	private Icon[] valueIcon;
	
	public GUI(int rows, int cols, int mines) {
		this.rows = rows;
		this.cols = cols;
		this.mines = mines;		
		buttonsBoard = new JButton[this.rows][this.cols];
		valueIcon = new Icon[10];
		
		super.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		super.setTitle("Minesweeper");
		super.setLocation(0,0);
		
		JPanel buttonsInit;
		buttonsInit = new JPanel();
		buttonsInit.setLayout(new GridLayout(rows, cols, 0, 0));
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				buttonsBoard[i][j] = new JButton("");
				buttonsBoard[i][j].setName(Integer.toString(i) + "," + Integer.toString(j));
				
				buttonsInit.add(buttonsBoard[i][j]);
			}
		}

		JPanel menuInit = new JPanel();
		menuInit.setLayout(new BoxLayout(menuInit,BoxLayout.PAGE_AXIS));
		this.mineLabel = new JLabel("  0  ", SwingConstants.CENTER);
		mineLabel.setText("  " + Integer.toString(this.mines) + "  ");
		mineLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		this.winLabel = new JLabel("Win!", SwingConstants.CENTER);
		winLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		this.loseLabel = new JLabel("Lose!", SwingConstants.CENTER);
		loseLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		JLabel mineImage = new JLabel("", SwingConstants.CENTER);
		mineImage.setIcon(new ImageIcon(getClass().getResource("/media/mine.png")));
		menuInit.add(mineImage);
		menuInit.add(mineLabel);
		menuInit.add(winLabel, BorderLayout.CENTER);
		menuInit.add(loseLabel, BorderLayout.CENTER);
		winLabel.setVisible(false);
		loseLabel.setVisible(false);
		
		super.add(buttonsInit, BorderLayout.CENTER);
		super.add(menuInit, BorderLayout.EAST);
		super.setVisible(true);
		setIcons();
	}
	public void setListeners(Game game) {
		for(int i = 0; i < this.rows; ++i) {
			for(int j = 0; j < this.cols; ++j) {
				buttonsBoard[i][j].addMouseListener(game);
			}
		}
	}
	public void updateBoard(Board board) {
		if(board.isFinished() == true) {
			if(board.isWin() == true) {
				winLabel.setVisible(true);
				loseLabel.setVisible(false);
			}else {
				winLabel.setVisible(false);
				loseLabel.setVisible(true);
			}
		}else {
			winLabel.setVisible(false);
			loseLabel.setVisible(false);
		}
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				if(board.getCells()[i][j].isHidden()) {
					if(board.getCells()[i][j].isFlagged()) {
						buttonsBoard[i][j].setIcon(flagIcon);
					}else {
						buttonsBoard[i][j].setIcon(tileIcon);
					}
				} else {
					if(board.getCells()[i][j].isMine()) {
						buttonsBoard[i][j].setIcon(mineIcon);
					} else {
						buttonsBoard[i][j].setIcon(valueIcon[board.getCells()[i][j].getValue()]);
					}
				}
			}
		}
	}
	private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
	}    
	public void setIcons() {
		int buttonWidth = buttonsBoard[0][0].getWidth();
		int buttonHeight = buttonsBoard[0][0].getHeight();
		ImageIcon aux = new ImageIcon(getClass().getResource("/media/mine.png"));                
		mineIcon = resizeIcon(aux, buttonWidth, buttonHeight);        
		aux = new ImageIcon(getClass().getResource("/media/tile.png"));                
		tileIcon = resizeIcon(aux, buttonWidth, buttonHeight);   
		aux = new ImageIcon(getClass().getResource("/media/flag.png"));                
		flagIcon = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/0.png"));                
		valueIcon[0] = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/1.png"));                
		valueIcon[1] = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/2.png"));                
		valueIcon[2] = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/3.png"));                
		valueIcon[3] = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/4.png"));                
		valueIcon[4] = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/5.png")); 
		valueIcon[5] = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/6.png"));                
		valueIcon[6] = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/7.png"));                
		valueIcon[7] = resizeIcon(aux, buttonWidth, buttonHeight);  
		aux = new ImageIcon(getClass().getResource("/media/8.png"));                
		valueIcon[8] = resizeIcon(aux, buttonWidth, buttonHeight);  
	}
}
