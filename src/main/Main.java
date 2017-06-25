package main;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import helpers.Helper;
import helpers.TestearMutantes;
import procesadores.abs.ProcesadorABS;
import procesadores.aor.ProcesadorAORB;
import procesadores.aor.ProcesadorAORU;
import procesadores.cor.ProcesadorCOR;
import procesadores.ror.ProcesadorROR;
import procesadores.uoi.ProcesadorUOI;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

public class Main {
	
	public static String testclassPath,mutantesRoot,mutanteBinDir,pathTestSource,pathFuncSource,targetclassPath;
	public static int mutantesTotales,mutantesPass;
	public static HashMap<Integer,Integer> lineaMutante;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		mutantesRoot = "mutantes"+ File.separator +"source" + File.separator;
		mutanteBinDir = "mutantes" + File.separator + "bin" + File.separator; // .class del test y mutante a testear
		pathFuncSource = args[0]; // Fuente de la clase objetivo *.java para compilar
		pathTestSource = args[1]; // Fuente del test *.java para compilar
		targetclassPath = pathFuncSource.replace(File.separator, ".").replace(".java", ""); // class name loader de la clase objetivo
		testclassPath = pathTestSource.replace(File.separator, ".").replace(".java", ""); // class name loader del test		
		lineaMutante = new HashMap<Integer,Integer>();
		File mutantes = new File(mutantesRoot);
		Helper.limpiarDirectorio(mutantes);
		Launcher launcher = new Launcher();
		launcher.addInputResource(pathFuncSource);
		TypeFilter<CtBinaryOperator<?>> expresionB = new TypeFilter<CtBinaryOperator<?>>(CtBinaryOperator.class);
		TypeFilter<CtUnaryOperator<?>> expresionU = new TypeFilter<CtUnaryOperator<?>>(CtUnaryOperator.class);
		Filter<CtElement> expresionUOI = new Filter<CtElement>() {

			@Override
			public boolean matches(CtElement element) {
				return element instanceof CtUnaryOperator ||
						element instanceof CtVariableRead ||
						element instanceof CtLiteral ;
			}
		};
		launcher.run();
		CtModel modelo = launcher.getModel();
		List<CtBinaryOperator<?>> elementosB;
		List<CtUnaryOperator<?>> elementosU;
		List<CtElement> elementosUOI;
		List<CtBinaryOperator<?>> auxiliaresB;
		List<CtUnaryOperator<?>> auxiliaresU;
		elementosB = modelo.getElements(expresionB);
		elementosU = modelo.getElements(expresionU);
		elementosUOI = modelo.getElements(expresionUOI);
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
		
		ProcesadorABS abs = new ProcesadorABS(launcher, elementosB);
		abs.run();
		
		ProcesadorUOI uoi = new ProcesadorUOI(launcher, elementosUOI);
		//uoi.run();
		
		Helper.compilar(Main.pathFuncSource, Main.mutanteBinDir);
		Helper.compilar(Main.pathTestSource, Main.mutanteBinDir);
		TestearMutantes mut = new TestearMutantes(Main.mutanteBinDir);
		Map<String,HashSet<Integer>> lineasXmutante = mut.registrarLineasPorTest();
		Set<Integer> s = mut.runTest(lineaMutante, lineasXmutante); // Devuelve un set que contiene los mutantes que pasan todos los test
		System.out.println(s.size() +" de " + mutantesTotales + " Mutantes pasaron todos los test.");
		System.out.println();
		for (Integer mutante : s) {
			System.out.println("El mutante ubicado en: '~"+ File.separator + mutantesRoot + mutante + "' pasa todos los test y su número de linea mutada es: " + lineaMutante.get(mutante));
		}

	}

}
