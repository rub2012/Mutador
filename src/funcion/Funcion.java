package funcion;

public class Funcion {

	//Crear metodos para cubrir todos los Casos de Mutacion y que se puedan generar todos los mutantes necesarios
	//Tambien crear cada mutante en una carpeta distinta
	public int suma2(int a){
		return a + 2 + 4;
	}
	
	public int suma1(int a){
		return a++;
	}
	
	public boolean verdad(boolean a, boolean b){
		if (a && b){
			return true;
		}
		return false;
	}
	
	public boolean siCondicional(int a){
		if (a > 1){
			return true;
		}
		return false;
	}
	
}
