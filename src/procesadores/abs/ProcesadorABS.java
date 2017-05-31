package procesadores.abs;

import java.util.List;

import main.Main;
import procesadores.aor.ProcesadorAORBsuma;
import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtExpression;

public class ProcesadorABS {
	
	private Launcher launcher;
	private List<CtBinaryOperator<?>> auxiliares;
	public ProcesadorABS(Launcher launcher,List<CtBinaryOperator<?>> auxiliares){
		this.launcher = launcher;
		this.auxiliares = auxiliares;
	}
	
	public void run(){
		ProcesadorABSpositivo aorbpositivo = new ProcesadorABSpositivo();
		CtExpression<?> aux;
		String path;
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getRightHandOperand();
			if (aorbpositivo.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				CtCodeSnippetExpression<?> snippet = launcher.getFactory().Core().createCodeSnippetExpression();
				snippet.setValue("Math.abs(" + elemento.getRightHandOperand() + ")");
				elemento.setRightHandOperand(snippet);
				
				launcher.prettyprint();
				
				elemento.setRightHandOperand(aux);
			}
		}
		
	}
}
