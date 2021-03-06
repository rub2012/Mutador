package procesadores.uoi;

import java.util.List;

import main.Main;
import procesadores.aor.ProcesadorAORBsuma;
import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtTypedElement;

public class ProcesadorUOI {
	
	private Launcher launcher;
	private List<CtElement> auxiliares;
	public ProcesadorUOI(Launcher launcher,List<CtElement> auxiliares){
		this.launcher = launcher;
		this.auxiliares = auxiliares;
	}
	
	public void run(){
		ProcesadorUOInumerico uoinumerico = new ProcesadorUOInumerico();
		ProcesadorUOIbooleano uoibooleano = new ProcesadorUOIbooleano();
		CtElement aux;
		String path;
		for (CtElement elemento : auxiliares){
			aux = elemento;
			if (uoinumerico.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				CtCodeSnippetExpression<?> snippet = launcher.getFactory().Core().createCodeSnippetExpression();
				snippet.setValue("-" + elemento);
			    elemento.replace(snippet);
				launcher.prettyprint();
				snippet.setValue("" + elemento);
				
			}
			else if (uoibooleano.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				CtCodeSnippetExpression<?> snippet = launcher.getFactory().Core().createCodeSnippetExpression();
				snippet.setValue("!" + elemento);
				elemento.replace(snippet);
				launcher.prettyprint();
				snippet.setValue("" + elemento);
			}
		}
		
	}
}
