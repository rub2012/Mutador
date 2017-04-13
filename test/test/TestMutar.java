package test;

import funcion.Funcion;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestMutar extends TestCase {

		Funcion funcion;
		int valorInput;
		boolean a,b;

		public TestMutar( String testName ) {
		    super( testName );
		}
		
		public static Test suite() {
		    return new TestSuite( TestMutar.class );
		}
		
		public void  setUp() {
				funcion = new Funcion();
				valorInput = 2;
				a = true;
				b = true;
			
		}

		public void testMutante1() {
			int resultado = funcion.suma1(valorInput);
			assertEquals(valorInput + 1, resultado);
		}
		
		public void testMutante2() {
			int resultado = funcion.suma2(valorInput);
			assertEquals(valorInput + 2, resultado);
			System.out.println("Resultado Suma 2 = " + resultado);
		}
		
		public void testMutante3() {
			boolean resultado = funcion.verdad(a, b);
			assertTrue(resultado);
		}
		
		public void testMutante4() {
			boolean resultado = funcion.siCondicional(valorInput);
			assertTrue(resultado);
		}
}
