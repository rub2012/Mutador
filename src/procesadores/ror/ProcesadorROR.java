package procesadores.ror;

import java.util.List;

import spoon.Launcher;
import spoon.reflect.code.CtBinaryOperator;

public class ProcesadorROR {
	
	private Launcher launcher;
	private List<CtBinaryOperator<?>> auxiliares;
	public ProcesadorROR(Launcher launcher,List<CtBinaryOperator<?>> auxiliares){
		this.launcher = launcher;
		this.auxiliares = auxiliares;
	}
	
	public void run(){
		ProcesadorRORdistinto rordistinto = new ProcesadorRORdistinto();
		ProcesadorRORigual rorigual = new ProcesadorRORigual();
		ProcesadorRORmayor rormayor = new ProcesadorRORmayor();
		ProcesadorRORmayorigual rormayorigual = new ProcesadorRORmayorigual();
		ProcesadorRORmenor rormenor = new ProcesadorRORmenor();
		ProcesadorRORmenorigual rormenorigual = new ProcesadorRORmenorigual();
		
		for (CtBinaryOperator<?> elemento : auxiliares){
			if (rordistinto.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/distinto");
				rordistinto.process(elemento);
				launcher.prettyprint();
			}
			if (rorigual.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/igual");
				rorigual.process(elemento);
				launcher.prettyprint();
			}
			if (rormayor.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/mayor");
				rormayor.process(elemento);
				launcher.prettyprint();
			}
			if (rormayorigual.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/mayorigual");
				rormayorigual.process(elemento);
				launcher.prettyprint();
			}
			if (rormenor.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/menor");
				rormenor.process(elemento);
				launcher.prettyprint();
			}
			if (rormenorigual.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/menorigual");
				rormenorigual.process(elemento);
				launcher.prettyprint();
			}
			
		}
		
	}
}
