package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Backend.Juego;

class JuegoTest {

	@Test
	public void CrearJuegoTest() {
		Juego juego = new Juego();
		assertEquals(Juego.class, juego.getClass());
	}
	
	
	@Test
	public void TurnoInicialTest() {
		Juego juego = new Juego();		
		assertEquals("1",juego.viewTurn());		
	}
	
	@Test
	public void PlayerInicialTest() {
		Juego juego = new Juego();		
		assertEquals("X",juego.itemPlayer());		
	}
	
	@Test
	public void JugarYCambiarJugadorTest() {
		Juego juego = new Juego();		
		juego.play(0,0);		
		assertEquals("O", juego.itemPlayer());
	}
	
	@Test
	public void JugarYAvanzarTurnoTest() {
		Juego juego = new Juego();		
		juego.play(0,0);		
		assertEquals("2", juego.viewTurn());
	}
	@Test
	public void JugarYNoTerminarTest() {
		Juego juego = new Juego();		
		juego.play(0,0);		
		assertFalse(juego.end());
	}	
	
	@Test
	public void GanadorXTest() {
		Juego juego = new Juego();		
		juego.play(0,0);
		juego.play(2,2);
		juego.play(0,1);
		juego.play(1,1);
		juego.play(0,2);				
		assertTrue(juego.end());
		assertEquals("The winner of the game is X", juego.finishMessage());
	}
	
	@Test
	public void GanadorOTest() {
		Juego juego = new Juego();		
		juego.play(2,2);
		juego.play(0,0);
		juego.play(0,2);
		juego.play(0,1);
		juego.play(1,1);
		juego.play(0,2);				
		assertTrue(juego.end());
		assertEquals("The winner of the game is O", juego.finishMessage());
	}

}
