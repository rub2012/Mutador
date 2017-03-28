package procesadores.ror;

import java.util.List;

import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
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
		BinaryOperatorKind aux;
		int indice = 0;
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (rordistinto.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/distinto"+indice);
				//rordistinto.process(elemento);
				elemento.setKind(BinaryOperatorKind.NE);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rorigual.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/igual"+indice);
				//rorigual.process(elemento);
				elemento.setKind(BinaryOperatorKind.EQ);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rormayor.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/mayor"+indice);
				//rormayor.process(elemento);
				elemento.setKind(BinaryOperatorKind.GT);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rormayorigual.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/mayorigual"+indice);
				//rormayorigual.process(elemento);
				elemento.setKind(BinaryOperatorKind.GE);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rormenor.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/menor"+indice);
				//rormenor.process(elemento);
				elemento.setKind(BinaryOperatorKind.LT);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rormenorigual.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/ROR/menorigual"+indice);
				//rormenorigual.process(elemento);
				elemento.setKind(BinaryOperatorKind.LE);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
