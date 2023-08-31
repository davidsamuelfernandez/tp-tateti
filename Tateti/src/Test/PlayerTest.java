package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Backend.Player;

class PlayerTest {

	@Test
	public void CrearPlayerTest() {
		Player player = new Player("test","x");
		assertEquals(Player.class,player.getClass());
	}

}
