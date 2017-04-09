package procesadores.ror;

import java.util.List;

import helpers.Helper;
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
		String path;
		String pathCompile = "/funcion/Funcion.java";
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (rordistinto.isToBeProcessed(elemento)){
				path = "spooned/ROR/distinto"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.NE);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (rorigual.isToBeProcessed(elemento)){
				path = "spooned/ROR/igual"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.EQ);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (rormayor.isToBeProcessed(elemento)){
				path = "spooned/ROR/mayor"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.GT);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (rormayorigual.isToBeProcessed(elemento)){
				path = "spooned/ROR/mayorigual"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.GE);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (rormenor.isToBeProcessed(elemento)){
				path = "spooned/ROR/menor"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.LT);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (rormenorigual.isToBeProcessed(elemento)){
				path = "spooned/ROR/menorigual"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.LE);
				launcher.prettyprint();
				path += pathCompile;
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
