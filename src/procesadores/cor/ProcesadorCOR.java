package procesadores.cor;

import java.util.List;

import helpers.Helper;
import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;

public class ProcesadorCOR {
	
	private Launcher launcher;
	private List<CtBinaryOperator<?>> auxiliares;
	public ProcesadorCOR(Launcher launcher,List<CtBinaryOperator<?>> auxiliares){
		this.launcher = launcher;
		this.auxiliares = auxiliares;
	}
	
	public void run(){
		ProcesadorCORy cory = new ProcesadorCORy();
		ProcesadorCORyy coryy = new ProcesadorCORyy();
		ProcesadorCORo coro = new ProcesadorCORo();
		ProcesadorCORoo coroo = new ProcesadorCORoo();
		ProcesadorCORxor corxor = new ProcesadorCORxor();
		BinaryOperatorKind aux;
		int indice = 0;
		String path;
		String pathCompile = "/funcion/Funcion.java";
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (cory.isToBeProcessed(elemento)){
				path = "spooned/COR/y"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITAND);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (coryy.isToBeProcessed(elemento)){
				path = "spooned/COR/yy"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.AND);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (coro.isToBeProcessed(elemento)){
				path = "spooned/COR/o"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITOR);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (coroo.isToBeProcessed(elemento)){
				path = "spooned/COR/oo"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.OR);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (corxor.isToBeProcessed(elemento)){
				path = "spooned/COR/xor"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITXOR);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
