package procesadores.cor;

import java.util.List;

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
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (cory.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/y"+indice);
				//cory.process(elemento);
				elemento.setKind(BinaryOperatorKind.BITAND);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (coryy.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/yy"+indice);
				//coryy.process(elemento);
				elemento.setKind(BinaryOperatorKind.AND);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (coro.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/o"+indice);
				//coro.process(elemento);
				elemento.setKind(BinaryOperatorKind.BITOR);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (coroo.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/oo"+indice);
				//coroo.process(elemento);
				elemento.setKind(BinaryOperatorKind.OR);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (corxor.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/xor"+indice);
				//corxor.process(elemento);
				elemento.setKind(BinaryOperatorKind.BITXOR);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
