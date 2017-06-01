package main;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.runner.JUnitCore;

import helpers.CustomListener;
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
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.declaration.CtTypedElement;
import spoon.reflect.visitor.filter.TypeFilter;

public class Main {
	
	public static String pathCompile,testclassPath,mutantesRoot,mutanteBinDir,pathTestSource,pathFuncSource,targetclassPath;
	public static int mutantesTotales,mutantesPass;
	public static HashMap<Integer,Integer> lineaMutante;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		mutantesRoot = "mutantes"+ File.separator +"source" + File.separator;
		mutanteBinDir = "mutantes" + File.separator + "bin" + File.separator; // .class del test y mutante a testear
		pathCompile = "funcion" + File.separator + "Funcion.java";
		pathTestSource = "origen"+ File.separator +"test"+ File.separator +"MutarTest.java";
		pathFuncSource = "origen"+ File.separator + pathCompile;
		testclassPath = "test.MutarTest";
		targetclassPath = "funcion.Funcion";
		lineaMutante = new HashMap<Integer,Integer>();
		File mutantes = new File(mutantesRoot);
		Helper.limpiarDirectorio(mutantes);
		File log = new File("Log.txt");
		Helper.limpiarDirectorio(log);
		Launcher launcher = new Launcher();
		launcher.addInputResource(pathFuncSource);
		TypeFilter<CtBinaryOperator<?>> expresionB = new TypeFilter<CtBinaryOperator<?>>(CtBinaryOperator.class);
		TypeFilter<CtUnaryOperator<?>> expresionU = new TypeFilter<CtUnaryOperator<?>>(CtUnaryOperator.class);
		TypeFilter<CtTypedElement<?>> expresionUOI = new TypeFilter<CtTypedElement<?>>(CtTypedElement.class);
		launcher.run();
		CtModel modelo = launcher.getModel();
		List<CtBinaryOperator<?>> elementosB;
		List<CtUnaryOperator<?>> elementosU;
		List<CtTypedElement<?>> elementosUOI;
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
		
		//ProcesadorUOI uoi = new ProcesadorUOI(launcher, elementosUOI);
		//uoi.run();
		
		Helper.compilar(Main.pathFuncSource, Main.mutanteBinDir);
		Helper.compilar(Main.pathTestSource, Main.mutanteBinDir);
		TestearMutantes mut = new TestearMutantes(Main.mutanteBinDir);
		Map<String,HashSet<Integer>> lineasXmutante = mut.registrarLineasPorTest(Main.testclassPath,"funcion.Funcion");
		Set<Integer> s = mut.runTest(lineaMutante, lineasXmutante); // Devuelve un set que contiene los mutantes que pasan todos los test
		System.out.println(s.size());
		//Helper.registrarMutante("Mutantes totales procesados: " + mutantesTotales + " - Mutantes que pasan todos los test: " + mutantesPass );
	}

}
