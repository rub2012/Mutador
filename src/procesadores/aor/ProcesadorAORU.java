package procesadores.aor;

import java.util.List;

import helpers.Helper;
import spoon.Launcher;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.visitor.PrettyPrinter;

public class ProcesadorAORU {
	
	private Launcher launcher;
	private List<CtUnaryOperator<?>> auxiliares;
	public ProcesadorAORU(Launcher launcher,List<CtUnaryOperator<?>> auxiliares){
		this.launcher = launcher;
		this.auxiliares = auxiliares;
	}
	
	public void run(){
		ProcesadorAORUpostdec aorupostdec = new ProcesadorAORUpostdec();
		ProcesadorAORUpostinc aorupostinc = new ProcesadorAORUpostinc();
		ProcesadorAORUpredec aorupredec = new ProcesadorAORUpredec();
		ProcesadorAORUpreinc aorupreinc = new ProcesadorAORUpreinc();
		UnaryOperatorKind aux;
		int indice = 0;
		for (CtUnaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (aorupostdec.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/postdec"+indice);
				//aorupostdec.process(elemento);
				elemento.setKind(UnaryOperatorKind.POSTDEC);
				launcher.prettyprint();
				String path = launcher.getModelBuilder().getSourceOutputDirectory() + "/funcion/Funcion.java";
				Helper.compilar(path);
				elemento.setKind(aux);
			}
			if (aorupostinc.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/postinc"+indice);
				//aorupostinc.process(elemento);
				elemento.setKind(UnaryOperatorKind.POSTINC);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorupredec.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/predec"+indice);
				//aorupredec.process(elemento);
				elemento.setKind(UnaryOperatorKind.PREDEC);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorupreinc.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/preinc"+indice);
				//aorupreinc.process(elemento);
				elemento.setKind(UnaryOperatorKind.PREINC);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
