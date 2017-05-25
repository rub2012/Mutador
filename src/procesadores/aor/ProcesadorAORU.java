package procesadores.aor;

import java.util.List;

import main.Main;
import spoon.Launcher;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;

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
		String path;
		for (CtUnaryOperator<?> elemento : auxiliares){
			aux = elemento.getKind();
			if (aorupostdec.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(UnaryOperatorKind.POSTDEC);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorupostinc.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(UnaryOperatorKind.POSTINC);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorupredec.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(UnaryOperatorKind.PREDEC);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
			if (aorupreinc.isToBeProcessed(elemento)){
				Main.mutantesTotales++;
				path = Main.mutantesRoot+Main.mutantesTotales;
				Main.lineaMutante.put(Main.mutantesTotales, elemento.getPosition().getLine());
				launcher.setSourceOutputDirectory(path);
				elemento.setKind(UnaryOperatorKind.PREINC);
				launcher.prettyprint();
				elemento.setKind(aux);
			}
		}
		
	}
}
