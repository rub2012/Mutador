package helpers;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import main.Main;

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
			Class<?> classDataSaver = loader.loadClass(pathClase);
			Method method = classDataSaver.getMethod("getInstance");
			method.setAccessible(true);
			Object classDataSaverInstance = method.invoke(classDataSaver, null);
			Class<?> clsInstance = classDataSaverInstance.getClass();
			CustomListener listener = new CustomListener(clsInstance);
			junit.addListener(listener);
			Class<?> classTest = loader.loadClass(pathTest);
			junit.run(classTest);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void runTest(){
		for(int i = 1 ; i < Main.mutantesTotales + 1 ; i++){
			Helper.compilar(File.separator + Main.mutantesRoot + i + File.separator + Main.pathCompile, Main.mutanteBinDir);
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
}
