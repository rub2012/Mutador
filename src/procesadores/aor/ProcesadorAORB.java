package procesadores.aor;

import java.util.List;

import spoon.Launcher;
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
		
		for (CtBinaryOperator<?> elemento : auxiliares){
			if (aorbsuma.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/suma");
				aorbsuma.process(elemento);
				launcher.prettyprint();
			}
			if (aorbresta.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/resta");
				aorbresta.process(elemento);
				launcher.prettyprint();
			}
			if (aorbdivision.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/division");
				aorbdivision.process(elemento);
				launcher.prettyprint();
			}
			if (aorbmultiplicacion.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/multiplicacion");
				aorbmultiplicacion.process(elemento);
				launcher.prettyprint();
			}
			if (aorbmodulo.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/modulo");
				aorbmodulo.process(elemento);
				launcher.prettyprint();
			}
			
		}
		
	}
}
