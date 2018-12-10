
public class Cell {
	private boolean hidden;
	private boolean mine;
	private boolean flag;
	private int value;
	
	public Cell(boolean mine, int value) {
		this.hidden = true;
		this.mine = mine;
		this.flag = false;
		this.value = value;
	}
	public boolean isHidden() {
		return hidden;
	}
	public boolean isFlagged() {
		return flag;
	}
	public boolean isMine() {
		return mine;
	}
	public int getValue() {
		return value;
	}
	public void setHidden(boolean value) {
		hidden = value;
	}
	public void setFlagged(boolean value) {
		if(hidden == true) {
			flag = value;
		}
	}
}
