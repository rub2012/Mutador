package helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.Main;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.ICounter;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.instr.Instrumenter;
import org.jacoco.core.runtime.IRuntime;
import org.jacoco.core.runtime.LoggerRuntime;
import org.jacoco.core.runtime.RuntimeData;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

public class TestearMutantes {

	private static URLClassLoader loader;
	private static JUnitCore junit;
	private final String pathMutante;
	
	public TestearMutantes(String path) {
		pathMutante = path;
	}
	
	private void setearLoader() {
		try {
			File file = new File(pathMutante);
			URL classPath = file.toURI().toURL();
			URL[] urls = { classPath };
			junit = new JUnitCore();
			ClassLoader parent = junit.getClass().getClassLoader();
			loader = new URLClassLoader(urls, parent);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	
	public Map<String,HashSet<Integer>> registrarLineasPorTest(String pathTest, String pathClase) {
		Map<String,HashSet<Integer>> mapa = new HashMap<String,HashSet<Integer>>();
		try {
			List<String> metodos =  getMetodos();
			FileUtils.copyFile(new File(Main.mutanteBinDir + getPath(Main.targetclassPath)),new File(Main.mutanteBinDir + getPath(Main.targetclassPath + 2)));			
			for (String metodo : metodos) {
				setearLoader();
				IRuntime runtime = new LoggerRuntime();
				
				// The Instrumenter creates a modified version of our test target class
			    // that contains additional probes for execution data recording:			
				Instrumenter instr = new Instrumenter(runtime);
				byte[] instrumented = instr.instrument(loader.getResourceAsStream(getPath(Main.targetclassPath + 2)), Main.targetclassPath);
				//sobrescribo la clase instrumentada para que luego la cargue el classloader
				FileUtils.writeByteArrayToFile(new File(Main.mutanteBinDir + getPath(Main.targetclassPath)), instrumented);
				// Now we're ready to run our instrumented class and need to startup the
				// runtime first:
				RuntimeData data = new RuntimeData();
				runtime.startup(data);

				Request request = Request.method(loader.loadClass(Main.testclassPath), metodo);
				Result result = junit.run(request);				
				
				// At the end of test execution we collect execution data and shutdown
				// the runtime:
				ExecutionDataStore executionData = new ExecutionDataStore();
				SessionInfoStore sessionInfos = new SessionInfoStore();
				data.collect(executionData, sessionInfos, false);
				runtime.shutdown();
				
				// Together with the original class definition we can calculate coverage
				// information:
				CoverageBuilder coverageBuilder = new CoverageBuilder();
				Analyzer analyzer = new Analyzer(executionData, coverageBuilder);
				analyzer.analyzeClass(loader.getResourceAsStream(getPath(Main.targetclassPath + 2)), Main.targetclassPath);
				
				for ( IClassCoverage cc : coverageBuilder.getClasses()) {
					HashSet<Integer> set = new HashSet<Integer>();
					for (int i = cc.getFirstLine(); i <= cc.getLastLine(); i++) {						
						if(lineaCubierta(cc.getLine(i).getStatus())){
							set.add(i);
						}
					}
					if (!set.isEmpty()){
						mapa.put(metodo,set);
					}
				}				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapa;
	}
	
	public Set<Integer> runTest(HashMap<Integer,Integer> lineaXmutante,Map<String,HashSet<Integer>> lineasXtest ){
		Set<Integer> mutantesNoDetectados = new HashSet<Integer>();
		for(int i : lineaXmutante.keySet()){
			Helper.compilar(Main.mutantesRoot + i + File.separator + Main.pathCompile, Main.mutanteBinDir);
			int linea = lineaXmutante.get(i);//numero de linea mutante del mutante que estoy usando
			Set<String> metodos = new HashSet<String>();
			//Recorro para cada metodo de test fijandome si la linea mutada esta en el set del metodo del test
			for (String metodo : lineasXtest.keySet()){
				if (lineasXtest.get(metodo).contains(linea)){
					metodos.add(metodo);
				}
			}
			//Ejecuto los test necesarios unicamente del mutante y valido si pasa todos esos test
			if (pasaTodoTest(metodos)){
				mutantesNoDetectados.add(i);
			}
		}
		return mutantesNoDetectados;
	}
	
	private boolean pasaTodoTest(Set<String> metodos) {
		setearLoader();
		try {
			Class<?> test = loader.loadClass(Main.testclassPath);
			for (String metodo : metodos) {
				Request request = Request.method(test, metodo);
				Result result = junit.run(request);
				if (!result.wasSuccessful()) {
					return false;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean lineaCubierta(int status){
		return status == ICounter.FULLY_COVERED || status == ICounter.PARTLY_COVERED;
	}
	
	private String getPath(String target){
		String ret = target.replace('.', File.separatorChar) + ".class";
		return ret;		
	}
	
	private List<String> getMetodos() throws ClassNotFoundException{
		setearLoader();
		Class<?> classTest = loader.loadClass(Main.testclassPath);
		CustomListener listener = new CustomListener();
		junit.addListener(listener);
		junit.run(classTest);
		junit.removeListener(listener);
		return listener.getMetodos();
	}
}
