package procesadores.uoi;

import java.util.List;

import main.Main;
import procesadores.aor.ProcesadorAORBsuma;
import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtTypedElement;

public class ProcesadorUOI {
	
	private Launcher launcher;
	private List<CtTypedElement<?>> auxiliares;
	public ProcesadorUOI(Launcher launcher,List<CtTypedElement<?>> auxiliares){
		this.launcher = launcher;
		this.auxiliares = auxiliares;
	}
	
	public void run(){
		ProcesadorUOInumerico uoinumerico = new ProcesadorUOInumerico();
		ProcesadorUOIbooleano uoibooleano = new ProcesadorUOIbooleano();
		CtTypedElement<?> aux;
		String path;
		for (CtTypedElement<?> elemento : auxiliares){
			aux = elemento;
			if (uoinumerico.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				CtCodeSnippetExpression<?> snippet = launcher.getFactory().Core().createCodeSnippetExpression();
				snippet.setValue("-" + elemento);
				try {
					elemento.replace(snippet);
					
					launcher.prettyprint();
					
					elemento.replace(aux);
				}catch (Exception e){
					
				}
				
			}
			if (uoibooleano.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				CtCodeSnippetExpression<?> snippet = launcher.getFactory().Core().createCodeSnippetExpression();
				snippet.setValue("!" + elemento);
				try {
					elemento.replace(snippet);
					
					launcher.prettyprint();
					
					elemento.replace(aux);
				}catch (Exception e){
					
				}
			}
		}
		
	}
}
