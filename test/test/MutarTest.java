package test;

import Interfaces.IFuncion;
import funcion.Funcion;
import helpers.Helper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MutarTest {

		//IFuncion funcion;
		Funcion funcion;
		int valorInput;
		boolean a,b;		
		
		
		public MutarTest() {
		}
		
		@Before
		public void  setUp() {
				funcion = new Funcion();
				valorInput = 2;
				a = true;
				b = true;
			
		}
		
		
		@Test
		public void testMutante1() {
			int resultado = funcion.suma1(valorInput);
			assertEquals(valorInput + 1, resultado);
		}
		
		@Test
		public void testMutante2() {
			int resultado = funcion.suma2(valorInput);
			assertEquals(valorInput + 2, resultado);
		}
		
		@Test
		public void testMutante3() {
			boolean resultado = funcion.verdad(a, b);
			assertTrue(resultado);
		}
		
		@Test
		public void testMutante4() {
			boolean resultado = funcion.siCondicional(valorInput);
			assertTrue(resultado);
		}
}
