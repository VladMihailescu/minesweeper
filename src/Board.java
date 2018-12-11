
public class Board {
	private Cell[][] cellsBoard;
	private int mines;
	private int rows;
	private int cols;
	private boolean finished;
	private boolean win;
	private final int dx[] = {-1,-1,-1, 0, 1, 1, 1, 0};
	private final int dy[] = {-1, 0, 1, 1, 1, 0,-1,-1};
	private final int directions = 8;
	
	public Board(int rows, int cols, int mines) {
		this.mines = mines;
		this.cols = cols;
		this.rows = rows;
		this.finished = false;
		this.win = false;
		
		cellsBoard = new Cell[rows][cols];
		
		populateBoard();
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean value) {
		finished = value;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean value) {
		win = value;
	}
	public Cell[][] getCells(){
		return cellsBoard;
	}
	public void reset() {
		this.finished = false;
		this.win = false;
		
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				cellsBoard[i][j].reset();
			}
		}
		
		populateBoard();
	}
	private void FloodFill(int row, int col) {
		if(!(row < rows && row >= 0 && col < cols && col >= 0))
			return;
		if(!cellsBoard[row][col].isHidden()) 
			return;
		cellsBoard[row][col].setHidden(false);
		if(!cellsBoard[row][col].isMine() && cellsBoard[row][col].getValue() == 0) {
			for(int i = 0; i < directions; ++i) {
				int vx = dx[i] + row;
				int vy = dy[i] + col;
				FloodFill(vx,vy);
			}
		}
	}
	public void updateAround(int row, int col) {
		FloodFill(row,col);
	}
	public void updateGameStatus() {
		int totalHidden = 0;
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				if(cellsBoard[i][j].isMine() && !cellsBoard[i][j].isHidden()) {
					win = false;
					finished = true;
					for(int k = 0; k < rows; ++k) {
						for(int l = 0; l < cols; ++l) {
							cellsBoard[k][l].setHidden(false);
						}
					}
					return;
				}
				if(cellsBoard[i][j].isHidden()) {
					totalHidden++;
				}
			}
		}
		if(totalHidden == this.mines) {
			win = true;
			finished = true;
			for(int k = 0; k < rows; ++k) {
				for(int l = 0; l < cols; ++l) {
					cellsBoard[k][l].setHidden(false);
				}
			}
			return;
		}
	}
	private void populateBoard() {
		int[][] mask = new int[rows][cols];
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				mask[i][j] = 0;
			}
		}
		for(int i = 0; i < mines; ++i) {
			int row = (int)Math.floor(Math.random() * rows);
			int col = (int)Math.floor(Math.random() * cols);
			while(mask[row][col] == 1) {
				row = (int)Math.floor(Math.random() * rows);
				col = (int)Math.floor(Math.random() * cols);
			}
			mask[row][col] = -1;
		}
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				if(mask[i][j] == -1)
					continue;
				for(int k = 0; k < directions; ++k) {
					int vx = i + dx[k];
					int vy = j + dy[k];
					if(vx < rows && vx >= 0 && vy < cols && vy >= 0 && mask[vx][vy] == -1) {
						mask[i][j] ++;
					}
				}
			}
		}
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				if(mask[i][j] == -1) {
					cellsBoard[i][j] = new Cell(true, 0);
				} else {
					cellsBoard[i][j] = new Cell(false, mask[i][j]);
				}
			}
		}
	}
}
