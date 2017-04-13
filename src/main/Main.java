package main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import helpers.Helper;
import procesadores.aor.ProcesadorAORB;
import procesadores.aor.ProcesadorAORU;
import procesadores.cor.ProcesadorCOR;
import procesadores.ror.ProcesadorROR;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.visitor.filter.TypeFilter;
import test.TestMutar;

public class Main {

	public static void main(String[] args) {
		File carpeta = new File("spooned");
		Helper.limpiarDirectorio(carpeta);
		Launcher launcher = new Launcher();
		launcher.addInputResource("src/funcion/Funcion.java");
		TypeFilter<CtBinaryOperator<?>> expresionB = new TypeFilter<CtBinaryOperator<?>>(CtBinaryOperator.class);
		TypeFilter<CtUnaryOperator<?>> expresionU = new TypeFilter<CtUnaryOperator<?>>(CtUnaryOperator.class);
		launcher.run();
		CtModel modelo = launcher.getModel();
		List<CtBinaryOperator<?>> elementosB;
		List<CtUnaryOperator<?>> elementosU;
		List<CtBinaryOperator<?>> auxiliaresB;
		List<CtUnaryOperator<?>> auxiliaresU;
		elementosB = modelo.getElements(expresionB);
		elementosU = modelo.getElements(expresionU);
		//Filtro AOR binarios
		auxiliaresB = Helper.getAORBinarios(elementosB);
		ProcesadorAORB aorb = new ProcesadorAORB(launcher,auxiliaresB);
		aorb.run();
		//Filtro AOR unarios
		auxiliaresU = Helper.getAORUnarios(elementosU);
		ProcesadorAORU aoru = new ProcesadorAORU(launcher,auxiliaresU);
		aoru.run();
		//Filtro COR
		auxiliaresB = Helper.getCOR(elementosB);
		ProcesadorCOR cor = new ProcesadorCOR(launcher,auxiliaresB);
		cor.run();
		//Filtro ROR
		auxiliaresB = Helper.getROR(elementosB);
		ProcesadorROR ror = new ProcesadorROR(launcher,auxiliaresB);
		ror.run();
		
		Helper.runTests(TestMutar.class);
		String a = "spooned/AOR/resta0/funcion/Funcion.class";
		URI s = new File(a).toURI();
		try {
			s.toURL().toString();
			Helper.cambiarContexto(s.toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//s.get
		
		Helper.runTests(TestMutar.class);
	}

}
