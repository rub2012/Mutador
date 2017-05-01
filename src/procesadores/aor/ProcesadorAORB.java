package procesadores.aor;

import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.BinaryExpression;

import helpers.Helper;
import main.Main;
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
		String path;
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (aorbsuma.isToBeProcessed(elemento)){
				path = "spooned/AOR/suma"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.PLUS);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (aorbresta.isToBeProcessed(elemento)){
				path = "spooned/AOR/resta"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.MINUS);				
				//System.out.println(elemento.getPosition().getLine());//linea
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (aorbdivision.isToBeProcessed(elemento)){
				path = "spooned/AOR/division"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.DIV);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (aorbmultiplicacion.isToBeProcessed(elemento)){
				path = "spooned/AOR/multiplicacion"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.MUL);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (aorbmodulo.isToBeProcessed(elemento)){
				path = "spooned/AOR/modulo"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.MOD);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
