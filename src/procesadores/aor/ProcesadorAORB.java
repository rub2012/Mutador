package procesadores.aor;

import java.util.List;

import main.Main;
import spoon.Launcher;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;

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
		String path;
		for (CtBinaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (aorbsuma.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.PLUS);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorbresta.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.MINUS);				
				//System.out.println(elemento.getPosition().getLine());//linea
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorbdivision.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.DIV);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorbmultiplicacion.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.MUL);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorbmodulo.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(BinaryOperatorKind.MOD);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
		}
		
	}
}
