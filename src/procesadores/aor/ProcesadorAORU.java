package procesadores.aor;

import java.util.List;

import helpers.Helper;
import main.Main;
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
		String path;
		for (CtUnaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (aorupostdec.isToBeProcessed(elemento)){
				path = "spooned/AOR/postdec"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(UnaryOperatorKind.POSTDEC);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (aorupostinc.isToBeProcessed(elemento)){
				path = "spooned/AOR/postinc"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(UnaryOperatorKind.POSTINC);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (aorupredec.isToBeProcessed(elemento)){
				path = "spooned/AOR/predec"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(UnaryOperatorKind.PREDEC);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			if (aorupreinc.isToBeProcessed(elemento)){
				path = "spooned/AOR/preinc"+indice;
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(UnaryOperatorKind.PREINC);
				launcher.prettyprint();
				Helper.compilar(path + Main.pathCompile);
				Helper.runTests("mutantes/"+ Main.mutantesTotales + Main.pathCompiled);
				elemento.setKind(aux);
			}
			indice++;
		}
		
	}
}
