package Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Backend.Tablero;

class TableroTest {
	
	@Test
	public void CrearTableroTest() {
		Tablero tablero = new Tablero();		
		assertEquals(Tablero.class,tablero.getClass());
	}
	
	@Test
	public void TableroInicialTest() {
		Tablero tablero = new Tablero();
		
		String[] tablero_esperado = { null,null,null,
									  null,null,null,
									  null,null,null};
		
		String[] tablero_recibido = { tablero.getValue(0,0), tablero.getValue(0,1),tablero.getValue(0,2),
									  tablero.getValue(1,0), tablero.getValue(1,1),tablero.getValue(1,2),
									  tablero.getValue(2,0), tablero.getValue(2,1),tablero.getValue(2,2)};
		
		assertArrayEquals(tablero_esperado, tablero_recibido);
	}
	

	@Test
	public void InsertarFueraDeRangoTest() {
		Tablero tablero = new Tablero();	
		assertThrows(IllegalArgumentException.class, () -> { 
				tablero.insert(0, 3, "x"); 
			} 
		); 
	}
	@Test
	public void InsertarRangoNegativoTest() {
		Tablero tablero = new Tablero();	
		assertThrows(IllegalArgumentException.class, () -> { 
			tablero.insert(-1, 1, "x"); 
			} 
		); 
	}
	
	@Test
	public void InsertarOkTest() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		
		String[] tablero_esperado = { "x",null,null,
									  null,null,null,
									  null,null,null};

		String[] tablero_recibido = { tablero.getValue(0,0), tablero.getValue(0,1),tablero.getValue(0,2),
									  tablero.getValue(1,0), tablero.getValue(1,1),tablero.getValue(1,2),
									  tablero.getValue(2,0), tablero.getValue(2,1),tablero.getValue(2,2)};
		
		assertArrayEquals(tablero_esperado, tablero_recibido);
	}
	
	@Test
	public void InsertarMultipleTest() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(1, 2, "x");
		tablero.insert(1, 0, "o");
		tablero.insert(2, 2, "o");
		
		String[] tablero_esperado = { "x",null,null,
									  "o",null,"x",
									  null,null,"o"};
		
		String[] tablero_recibido = { tablero.getValue(0,0), tablero.getValue(0,1),tablero.getValue(0,2),
							     	  tablero.getValue(1,0), tablero.getValue(1,1),tablero.getValue(1,2),
									  tablero.getValue(2,0), tablero.getValue(2,1),tablero.getValue(2,2)};
							
		assertArrayEquals(tablero_esperado, tablero_recibido);
	}
	
	@Test
	public void InsertarDosVecesEnMismaCeldaIgualValorTest() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(0, 0, "x");
		
		String[] tablero_esperado = { "x",null,null,
									  null,null,null,
									  null,null,null};

		String[] tablero_recibido = { tablero.getValue(0,0), tablero.getValue(0,1),tablero.getValue(0,2),
						  tablero.getValue(1,0), tablero.getValue(1,1),tablero.getValue(1,2),
						  tablero.getValue(2,0), tablero.getValue(2,1),tablero.getValue(2,2)};

		assertArrayEquals(tablero_esperado, tablero_recibido);
	}
	
	@Test
	public void InsertarDosVecesEnMismaCeldaDiferenteValorTest() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(0, 0, "o");
		
		String[] tablero_esperado = { "o",null,null,
									  null,null,null,
									  null,null,null};
		
		String[] tablero_recibido = { tablero.getValue(0,0), tablero.getValue(0,1),tablero.getValue(0,2),
									  tablero.getValue(1,0), tablero.getValue(1,1),tablero.getValue(1,2),
									  tablero.getValue(2,0), tablero.getValue(2,1),tablero.getValue(2,2)};
								
		assertArrayEquals(tablero_esperado, tablero_recibido);
	}
	
	@Test
	public void TableroNoCompletoTest() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(1, 1, "o");
		tablero.insert(2, 2, "o");
		
		assertFalse(tablero.hasComplete());
	}
	
	@Test
	public void TableroCompletoTest() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(0, 1, "x");
		tablero.insert(0, 2, "x");
		tablero.insert(1, 0, "x");
		tablero.insert(1, 1, "x");
		tablero.insert(1, 2, "x");
		tablero.insert(2, 0, "o");
		tablero.insert(2, 1, "o");
		tablero.insert(2, 2, "o");
		
		assertTrue(tablero.hasComplete());
	}
	
	@Test
	public void SinGanador_1_Test() {
		Tablero tablero = new Tablero();	
			
		assertFalse(tablero.hasWinner());
	}
	
	@Test
	public void SinGanador_2_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 1, "o");
		tablero.insert(1, 1, "o");
		tablero.insert(2, 0, "o");
		
		assertFalse(tablero.hasWinner());
	}
	
	@Test
	public void SinGanador_3_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(1, 0, "o");
		tablero.insert(2, 0, "x");
		
		assertFalse(tablero.hasWinner());
	}
	
	@Test
	public void SinGanador_4_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 1, "x");
		tablero.insert(1, 0, "x");
		tablero.insert(0, 2, "x");
		
		assertFalse(tablero.hasWinner());
	}
	@Test
	public void GanadorHorizontal_1_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(0, 1, "x");
		tablero.insert(0, 2, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorHorizontal_2_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(1, 0, "x");
		tablero.insert(1, 1, "x");
		tablero.insert(1, 2, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorHorizontal_3_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(2, 0, "x");
		tablero.insert(2, 1, "x");
		tablero.insert(2, 2, "x");
		
		assertTrue(tablero.hasWinner());
	}
	
	@Test
	public void GanadorVertital_1_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(1, 0, "x");
		tablero.insert(2, 0, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorVertital_2_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 1, "x");
		tablero.insert(1, 1, "x");
		tablero.insert(2, 1, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorVertital_3_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 2, "x");
		tablero.insert(1, 2, "x");
		tablero.insert(2, 2, "x");
		
		assertTrue(tablero.hasWinner());
	}
	
	@Test
	public void GanadorDiagonal_1_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(1, 1, "x");
		tablero.insert(2, 2, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorDiagonal_2_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 2, "x");
		tablero.insert(1, 1, "x");
		tablero.insert(2, 0, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorDiagonalToro_1_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 1, "x");
		tablero.insert(1, 2, "x");
		tablero.insert(2, 0, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorDiagonalToro_2_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 1, "x");
		tablero.insert(1, 0, "x");
		tablero.insert(2, 2, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorDiagonalToro_3_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(0, 0, "x");
		tablero.insert(1, 2, "x");
		tablero.insert(2, 1, "x");
		
		assertTrue(tablero.hasWinner());
	}
	@Test
	public void GanadorDiagonalToro_4_Test() {
		Tablero tablero = new Tablero();	
		
		tablero.insert(2, 1, "x");
		tablero.insert(1, 0, "x");
		tablero.insert(0, 2, "x");
		
		assertTrue(tablero.hasWinner());
	}
	
	

}
