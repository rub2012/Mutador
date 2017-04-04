package test;

import funcion.Funcion;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestMutar extends TestCase {

		Funcion funcion;
		int valorInput;

		public TestMutar( String testName ) {
		    super( testName );
		}
		
		/**
		 * @return the suite of tests being tested
		 */
		public static Test suite() {
		    return new TestSuite( TestMutar.class );
		}
		
		public void  setUp() {
				funcion = new Funcion();
				valorInput = 2;
			
		}
		
		/**
		 * Verifico que se asignen bien los atributos en el setUp
		 */
		public void testMutante1() {
			assertEquals(valorInput + 1, funcion.suma1(valorInput));
		}
}
