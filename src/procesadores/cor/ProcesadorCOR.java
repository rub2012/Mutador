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
		String path;
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (cory.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITAND);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (coryy.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.AND);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (coro.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITOR);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (coroo.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.OR);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (corxor.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.BITXOR);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
		}
		
	}
}
