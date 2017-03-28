package procesadores.aor;

import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.BinaryExpression;

import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;

public class ProcesadorAORB {
	
	private Launcher launcher;
	private List<CtBinaryOperator<?>> auxiliares;
	public ProcesadorAORB(Launcher launcher,List<CtBinaryOperator<?>> auxiliares){
		this.launcher = launcher;
		this.auxiliares = auxiliares;
	}
	
	public void run(){
		ProcesadorAORBsuma aorbsuma = new ProcesadorAORBsuma();
		ProcesadorAORBresta aorbresta = new ProcesadorAORBresta();
		ProcesadorAORBdivision aorbdivision = new ProcesadorAORBdivision();
		ProcesadorAORBmultiplicacion aorbmultiplicacion = new ProcesadorAORBmultiplicacion();
		ProcesadorAORBmodulo aorbmodulo = new ProcesadorAORBmodulo();
		BinaryOperatorKind aux;
		int indice = 0;
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (aorbsuma.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/suma"+indice);
				//aorbsuma.process(elemento.clone());
				elemento.setKind(BinaryOperatorKind.PLUS);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorbresta.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/resta"+indice);
				//aorbresta.process(elemento.clone());
				elemento.setKind(BinaryOperatorKind.MINUS);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorbdivision.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/division"+indice);
				//aorbdivision.process(elemento.clone());
				elemento.setKind(BinaryOperatorKind.DIV);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorbmultiplicacion.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/multiplicacion"+indice);
				//aorbmultiplicacion.process(elemento.clone());
				elemento.setKind(BinaryOperatorKind.MUL);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorbmodulo.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/modulo"+indice);
				//aorbmodulo.process(elemento.clone());
				elemento.setKind(BinaryOperatorKind.MOD);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
