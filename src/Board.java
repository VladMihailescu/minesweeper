
public class Board {
	private Cell[][] cellsBoard;
	private int mines;
	private int rows;
	private int cols;
	private boolean finished;
	private final int dx[] = {-1,-1,-1, 0, 1, 1, 1, 0};
	private final int dy[] = {-1, 0, 1, 1, 1, 0,-1,-1};
	
	public Board(int rows, int cols, int mines) {
		this.mines = mines;
		this.cols = cols;
		this.rows = rows;
		this.finished = false;
		
		cellsBoard = new Cell[rows][cols];
		
		populateBoard();
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean value) {
		finished = value;
	}
	public Cell[][] getCells(){
		return cellsBoard;
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
				for(int k = 0; k < 8; ++k) {
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
					//System.out.print(mask[i][j]);
					cellsBoard[i][j] = new Cell(false, mask[i][j]);
				}
			}
		}
		/*
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				System.out.printf("%3d ",mask[i][j]);
			}
			System.out.print("\n");
		}
		*/
	}
}
