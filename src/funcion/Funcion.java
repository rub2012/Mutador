package funcion;

import Interfaces.IFuncion;

public class Funcion implements IFuncion {

	//Crear metodos para cubrir todos los Casos de Mutacion y que se puedan generar todos los mutantes necesarios
	//Tambien crear cada mutante en una carpeta distinta
	
	@Override
	public int suma2(int a){
		return a + 2;
	}
	
	@Override
	public int suma1(int a){
		return ++a;
	}
	
	@Override
	public boolean verdad(boolean a, boolean b){
		if (a && b){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean siCondicional(int a){
		if (a > 1){
			return true;
		}
		return false;
	}
	
}
