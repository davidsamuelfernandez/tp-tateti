package Backend;

public class Juego {
	
	private Player player_x;
	private Player player_o;
	private Player player_actual;
	private Tablero tablero;
	private Integer turn;

	public Juego() {
		this.player_x = new Player("Player X","X");
		this.player_o = new Player("Player O","O");
		this.player_actual = player_x;
		this.tablero  = new Tablero();
		this.turn = 1;
	}

	public void play (Integer position_x, Integer position_y) {		
		this.tablero.insert(position_x, position_y, this.itemPlayer());
		if(!this.end()) 
			this.playerChange();	
			this.nextTurn();				
	}
	
	public String itemPlayer(){
		return this.player_actual.getItem();
	}
	
	private void playerChange() {
		if (this.player_x == this.player_actual)
			this.player_actual = this.player_o;
		else
			this.player_actual = this.player_x;
	}
	
	public boolean end() {	
		return this.tablero.hasWinner() || this.tablero.hasComplete();
	}
	
	private void nextTurn() {
		this.turn = this.turn + 1;
	}	

	public String finishMessage() {
		if(this.tablero.hasWinner())
			return "The winner of the game is " + this.player_actual.getItem();
		else
			return "Tied game !";
	}	
	
	public String viewTurn() {
		return this.turn.toString();
	}	

}


