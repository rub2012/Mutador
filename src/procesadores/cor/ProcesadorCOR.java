package procesadores.cor;

import java.util.List;

import spoon.Launcher;
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
		
		for (CtBinaryOperator<?> elemento : auxiliares){
			if (cory.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/y");
				cory.process(elemento);
				launcher.prettyprint();
			}
			if (coryy.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/yy");
				coryy.process(elemento);
				launcher.prettyprint();
			}
			if (coro.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/o");
				coro.process(elemento);
				launcher.prettyprint();
			}
			if (coroo.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/oo");
				coroo.process(elemento);
				launcher.prettyprint();
			}
			if (corxor.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/COR/xor");
				corxor.process(elemento);
				launcher.prettyprint();
			}
			
		}
		
	}
}
