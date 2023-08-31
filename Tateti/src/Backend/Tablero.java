package Backend;

public class Tablero {
	
	String [][] tablero;

	public Tablero() {
		this.tablero = new String[3][3];
	}
	
	public void insert (int x, int y, String item) {		
		validatePosition(x,y);
		this.tablero[x][y] = item;
	}
	
	public String getValue (int x, int y) {
		validatePosition(x,y);
		return this.tablero[x][y];
	}
	
	public boolean hasComplete(){
		try {
			boolean ret = true;
			for (int i = 0; i < this.tablero.length; i++) {
				for (int j = 0; j < this.tablero.length; j++) {
					ret = ret && this.tablero[i][j] != null ;
				}
			}
			return ret;	
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage()); 
		}
	}
	
	public boolean hasWinner() {
		
		boolean horizontal_0 = this.tablero[0][0] == this.tablero[0][1] && this.tablero[0][1] == this.tablero[0][2] && this.tablero[0][0] != null;
		boolean horizontal_1 = this.tablero[1][0] == this.tablero[1][1] && this.tablero[1][1] == this.tablero[1][2] && this.tablero[1][1] != null;
		boolean horizontal_2 = this.tablero[2][0] == this.tablero[2][1] && this.tablero[2][1] == this.tablero[2][2] && this.tablero[2][2] != null;
		
		boolean vertical_0 = tablero[0][0] == tablero[1][0] && tablero[1][0] == tablero[2][0] && tablero[0][0] != null;
		boolean vertical_1 = tablero[0][1] == tablero[1][1] && tablero[1][1] == tablero[2][1] && tablero[1][1] != null;
		boolean vertical_2 = tablero[0][2] == tablero[1][2] && tablero[1][2] == tablero[2][2] && tablero[2][2] != null;
		
		boolean diagonal_0 = tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[1][1] != null;
		boolean diagonal_1 = tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[1][1] != null;
		
		boolean diagonal_toro_0 = tablero[0][1] == tablero[1][2] && tablero[1][2] == tablero[2][0] && tablero[1][2] != null;
		boolean diagonal_toro_1 = tablero[0][1] == tablero[1][0] && tablero[1][0] == tablero[2][2] && tablero[1][0] != null;
		boolean diagonal_toro_2 = tablero[2][1] == tablero[1][2] && tablero[1][2] == tablero[0][0] && tablero[1][2] != null;
		boolean diagonal_toro_3 = tablero[2][1] == tablero[1][0] && tablero[1][0] == tablero[0][2] && tablero[1][0] != null;
		
		boolean state_horizontal 	= horizontal_0 || horizontal_1 || horizontal_2;
		boolean state_vertital 		= vertical_0 || vertical_1 || vertical_2;
		boolean state_diagonal 		= diagonal_0 || diagonal_1;
		boolean state_diagonal_toro = diagonal_toro_0 || diagonal_toro_1 || diagonal_toro_2 || diagonal_toro_3;			
		
		return state_horizontal || state_vertital || state_diagonal || state_diagonal_toro;
	}
	
	
	private void validatePosition(int x, int y) {
		if(x < 0 || x >= this.tablero.length || y < 0 || y >= this.tablero.length) {
			throw new IllegalArgumentException("El ubicación incorrecta: " + x + "-" + y);
		}
	}
}

