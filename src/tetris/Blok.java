package tetris;

public class Blok {
	private int type;
	private int[] x,y;

	public Blok(int type) {
		this.setType(type);
		x = new int[4];
		y = new int[4];
		if (type == 0){
			x[0] = 1; y[0] = 0;
			x[1] = 2; y[1] = 0;
			x[2] = 3; y[2] = 0;
			x[3] = 0; y[3] = 0;
		} else if (type == 1){
			x[0] = 1; y[0] = 0;
			x[1] = 2; y[1] = 0;
			x[2] = 0; y[2] = -1;
			x[3] = 0; y[3] = 0;
		} else if (type == 2){
			x[0] = 1; y[0] = 0;
			x[1] = 2; y[1] = 0;
			x[2] = 2; y[2] = -1;
			x[3] = 0; y[3] = 0;
		} else if (type == 3){
			x[0] = 1; y[0] = 0;
			x[1] = 2; y[1] = 0;
			x[2] = 1; y[2] = -1;
			x[3] = 0; y[3] = 0;
		} else if (type == 4){
			x[0] = 1; y[0] = -1;
			x[1] = 1; y[1] = 0;
			x[2] = 0; y[2] = -1;
			x[3] = 0; y[3] = 0;
		} else if (type == 5){
			x[0] = 1; y[0] = 0;
			x[1] = 1; y[1] = -1;
			x[2] = 2; y[2] = -1;
			x[3] = 0; y[3] = 0;
		} else if (type == 6){
			x[0] = 1; y[0] = 0;
			x[1] = 1; y[1] = 1;
			x[2] = 2; y[2] = 1;
			x[3] = 0; y[3] = 0;
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getX(int type) {
		return x[type];
	}

	public int getY(int type) {
		return y[type];
	}
	
}