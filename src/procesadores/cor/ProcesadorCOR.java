package procesadores.cor;

import java.util.List;

import helpers.Helper;
import main.Main;
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
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (cory.isToBeProcessed(elemento)){
				path = "spooned/COR/y"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITAND);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (coryy.isToBeProcessed(elemento)){
				path = "spooned/COR/yy"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.AND);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (coro.isToBeProcessed(elemento)){
				path = "spooned/COR/o"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITOR);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (coroo.isToBeProcessed(elemento)){
				path = "spooned/COR/oo"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.OR);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (corxor.isToBeProcessed(elemento)){
				path = "spooned/COR/xor"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITXOR);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
