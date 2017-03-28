package procesadores.aor;

import java.util.List;

import spoon.Launcher;
import spoon.reflect.code.CtUnaryOperator;

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
		
		for (CtUnaryOperator<?> elemento : auxiliares){
			if (aorupostdec.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/postdec");
				aorupostdec.process(elemento);
				launcher.prettyprint();
			}
			if (aorupostinc.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/postinc");
				aorupostinc.process(elemento);
				launcher.prettyprint();
			}
			if (aorupredec.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/predec");
				aorupredec.process(elemento);
				launcher.prettyprint();
			}
			if (aorupreinc.isToBeProcessed(elemento)){
				launcher.setSourceOutputDirectory("spooned/AOR/preinc");
				aorupreinc.process(elemento);
				launcher.prettyprint();
			}			
		}
		
	}
}
