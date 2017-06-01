package main;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.lf5.util.ResourceUtils;
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
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.CtVariableRead;
import spoon.reflect.code.CtVariableWrite;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtTypedElement;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;

public class Main {
	
	public static String pathCompile,testclassPath,mutantesRoot,mutanteBinDir,pathTestSource,pathFuncSource,targetclassPath;
	public static int mutantesTotales,mutantesPass;
	public static HashMap<Integer,Integer> lineaMutante;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		mutantesRoot = "mutantes"+ File.separator +"source" + File.separator;
		mutanteBinDir = "mutantes" + File.separator + "bin" + File.separator; // .class del test y mutante a testear
		pathCompile = "Funcion.java";
		pathTestSource = "origen"+ File.separator +"MutarTest.java";
		pathFuncSource = "origen"+ File.separator + pathCompile;
		testclassPath = "MutarTest";
		targetclassPath = "Funcion";
		lineaMutante = new HashMap<Integer,Integer>();
		File ss1 = new File("origen");
		List<File> af = new ArrayList<File>();
		List<File> ass = Helper.listarFiles(ss1,af);
		String ff = ass.get(0).toString().replace("origen"+ File.separator , "").replace(File.separator, ".").replace(".java", "");
		
		//jj[0].toURI()
		//ResourceUtils.getRelat;
		File mutantes = new File(mutantesRoot);
		Helper.limpiarDirectorio(mutantes);
		File log = new File("Log.txt");
		Helper.limpiarDirectorio(log);
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
		uoi.run();
		
		Helper.compilar(Main.pathFuncSource, Main.mutanteBinDir);
		Helper.compilar(Main.pathTestSource, Main.mutanteBinDir);
		TestearMutantes mut = new TestearMutantes(Main.mutanteBinDir);
		Map<String,HashSet<Integer>> lineasXmutante = mut.registrarLineasPorTest(Main.testclassPath,"funcion.Funcion");
		Set<Integer> s = mut.runTest(lineaMutante, lineasXmutante); // Devuelve un set que contiene los mutantes que pasan todos los test
		System.out.println(s.size());
		//Helper.registrarMutante("Mutantes totales procesados: " + mutantesTotales + " - Mutantes que pasan todos los test: " + mutantesPass );
	}

}
