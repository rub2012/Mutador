package helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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
	
	public void registrarTestPorMutante(String pathTest, String pathClase) {
		setearLoader();
		try {
//			Class<?> classDataSaver = loader.loadClass(pathClase);
//			Method method = classDataSaver.getMethod("getInstance");
//			method.setAccessible(true);
//			Object classDataSaverInstance = method.invoke(classDataSaver, null);
//			Class<?> clsInstance = classDataSaverInstance.getClass();
			
			
			// For instrumentation and runtime we need a IRuntime instance
			// to collect execution data:
			final IRuntime runtime = new LoggerRuntime();

			// The Instrumenter creates a modified version of our test target class
			// that contains additional probes for execution data recording:
			
			FileUtils.copyFile(new File(Main.mutanteBinDir + getPath(Main.targetclassPath)),new File(Main.mutanteBinDir + getPath(Main.targetclassPath + 2)));			
			
			final Instrumenter instr = new Instrumenter(runtime);
			final byte[] instrumented = instr.instrument(loader.getResourceAsStream(getPath(Main.targetclassPath + 2)), Main.targetclassPath);
			
			//sobrescribo la clase instrumentada para que luego la cargue el classloader
			FileUtils.writeByteArrayToFile(new File(Main.mutanteBinDir + getPath(Main.targetclassPath)), instrumented);
			
			// Now we're ready to run our instrumented class and need to startup the
			// runtime first:
			final RuntimeData data = new RuntimeData();
			runtime.startup(data);

			CustomListener listener = new CustomListener(null);
			junit.addListener(listener);
			Class<?> classTest = loader.loadClass(Main.testclassPath);
			Result resultado = junit.run(classTest);
			System.out.println("Fallas totales: " + resultado.getFailureCount());
			
			
			// At the end of test execution we collect execution data and shutdown
			// the runtime:
			final ExecutionDataStore executionData = new ExecutionDataStore();
			final SessionInfoStore sessionInfos = new SessionInfoStore();
			data.collect(executionData, sessionInfos, false);
			runtime.shutdown();
			
			// Together with the original class definition we can calculate coverage
			// information:
			final CoverageBuilder coverageBuilder = new CoverageBuilder();
			final Analyzer analyzer = new Analyzer(executionData, coverageBuilder);
			analyzer.analyzeClass(loader.getResourceAsStream(getPath(Main.targetclassPath + 2)), Main.targetclassPath);
			
			for (final IClassCoverage cc : coverageBuilder.getClasses()) {
				System.out.println("Coverage of class " + cc.getName());

				for (int i = cc.getFirstLine(); i <= cc.getLastLine(); i++) {
					if(lineaCubierta(cc.getLine(i).getStatus())){
						System.out.println("Linea cubierta: " + i);
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
	}
	
	public void runTest(){
		for(int i = 1 ; i < Main.mutantesTotales + 1 ; i++){
			Helper.compilar(Main.mutantesRoot + i + File.separator + Main.pathCompile, Main.mutanteBinDir);
			setearLoader();
			try {
				Class<?> test = loader.loadClass(Main.testclassPath);
				Result resultado = junit.run(test);
				System.out.println(resultado.getFailureCount());
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean lineaCubierta(int status){
		return status == ICounter.FULLY_COVERED || status == ICounter.PARTLY_COVERED;
	}
	
	private String getPath(String target){
		String ret = target.replace('.', File.separatorChar) + ".class";
		return ret;		
	}
}
