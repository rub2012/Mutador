package procesadores.ror;

import java.util.List;

import helpers.Helper;
import main.Main;
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
		String path;
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (rordistinto.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.NE);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rorigual.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.EQ);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rormayor.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.GT);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rormayorigual.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.GE);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rormenor.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.LT);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (rormenorigual.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.LE);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
		}
		
	}
}
