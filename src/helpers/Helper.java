package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import Interfaces.IFuncion;
import main.Main;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;
import test.TestMutar;

public class Helper {
	private static IFuncion funcion;
	public static boolean MatchAORbinary(CtElement elemento, BinaryOperatorKind tipo){
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.PLUS,
																			BinaryOperatorKind.MINUS,
																			BinaryOperatorKind.MUL,
																			BinaryOperatorKind.DIV,
																			BinaryOperatorKind.MOD));
		try
		{
			CtBinaryOperator op = (CtBinaryOperator)elemento;
			return operadoresBin.contains(op.getKind()) && op.getKind() != tipo;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static boolean MatchAORunary(CtElement elemento, UnaryOperatorKind tipo){
		Set<UnaryOperatorKind> operadoresUn = new HashSet<>(Arrays.asList(UnaryOperatorKind.POSTDEC,
																			UnaryOperatorKind.POSTINC,
																			UnaryOperatorKind.PREDEC,
																			UnaryOperatorKind.PREINC));
		try
		{
			CtUnaryOperator op = (CtUnaryOperator)elemento;
			return operadoresUn.contains(op.getKind()) && op.getKind() != tipo;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static boolean MatchCORbinary(CtElement elemento, BinaryOperatorKind tipo){
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.AND,
																			BinaryOperatorKind.OR,
																			BinaryOperatorKind.BITAND,
																			BinaryOperatorKind.BITOR,
																			BinaryOperatorKind.BITXOR));
		try
		{
			CtBinaryOperator op = (CtBinaryOperator)elemento;
			return operadoresBin.contains(op.getKind()) && op.getKind() != tipo;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static boolean MatchROR(CtElement elemento, BinaryOperatorKind tipo){
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.LE,
																			BinaryOperatorKind.LT,
																			BinaryOperatorKind.GE,
																			BinaryOperatorKind.GT,
																			BinaryOperatorKind.EQ,
																			BinaryOperatorKind.NE));
		try
		{
			CtBinaryOperator op = (CtBinaryOperator)elemento;
			return operadoresBin.contains(op.getKind()) && op.getKind() != tipo;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static List<CtBinaryOperator<?>> getAORBinarios(List<CtBinaryOperator<?>> elementos){
		List<CtBinaryOperator<?>> aorBinarios = new ArrayList<CtBinaryOperator<?>>();
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.PLUS,
				BinaryOperatorKind.MINUS,
				BinaryOperatorKind.MUL,
				BinaryOperatorKind.DIV,
				BinaryOperatorKind.MOD));
		
		for (CtBinaryOperator<?> elemento : elementos) {
			if (operadoresBin.contains(elemento.getKind())){
				aorBinarios.add(elemento);
			}
		}
		return aorBinarios;
	}
	
	public static List<CtUnaryOperator<?>> getAORUnarios(List<CtUnaryOperator<?>> elementos){
		List<CtUnaryOperator<?>> aorUnarios = new ArrayList<CtUnaryOperator<?>>();
		Set<UnaryOperatorKind> operadoresUn = new HashSet<>(Arrays.asList(UnaryOperatorKind.POSTDEC,
				UnaryOperatorKind.POSTINC,
				UnaryOperatorKind.PREDEC,
				UnaryOperatorKind.PREINC));
		
		for (CtUnaryOperator<?> elemento : elementos) {
			if (operadoresUn.contains(elemento.getKind())){
				aorUnarios.add(elemento);
			}
		}
		return aorUnarios;
	}
	
	public static List<CtBinaryOperator<?>> getCOR(List<CtBinaryOperator<?>> elementos){
		List<CtBinaryOperator<?>> cor = new ArrayList<CtBinaryOperator<?>>();
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.AND,
				BinaryOperatorKind.OR,
				BinaryOperatorKind.BITAND,
				BinaryOperatorKind.BITOR,
				BinaryOperatorKind.BITXOR));
		
		for (CtBinaryOperator<?> elemento : elementos) {
			if (operadoresBin.contains(elemento.getKind())){
				cor.add(elemento);
			}
		}
		return cor;
	}
	
	public static List<CtBinaryOperator<?>> getROR(List<CtBinaryOperator<?>> elementos){
		List<CtBinaryOperator<?>> ror = new ArrayList<CtBinaryOperator<?>>();
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.LE,
				BinaryOperatorKind.LT,
				BinaryOperatorKind.GE,
				BinaryOperatorKind.GT,
				BinaryOperatorKind.EQ,
				BinaryOperatorKind.NE));
		
		for (CtBinaryOperator<?> elemento : elementos) {
			if (operadoresBin.contains(elemento.getKind())){
				ror.add(elemento);
			}
		}
		return ror;
	}
	
	public static void compilar(String path){
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, path);
	}
	
	public static void limpiarDirectorio(File directory){
		if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(null!=files){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                	limpiarDirectorio(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	        directory.delete();
	    }
	}
	
	public static void runTests(String pathCompiled){
		File file = new File(pathCompiled);
		Helper.setInstancia(file,Main.classPath);
		Main.mutantesTotales++;
	    Result result = JUnitCore.runClasses(TestMutar.class);
	    if (result.getFailureCount() == 0){
	    	Main.mutantesPass++;
	    	registrarMutante(pathCompiled);
	    }
//	    for (Failure failure : result.getFailures()){
//	        System.out.println(failure.toString());
//	    }
	}
	
	public static IFuncion getInstancia(){
		return funcion;
	}
	
	public static void setInstancia(File file, String classPath){
		MutadorClassLoader loader = new MutadorClassLoader(file.getAbsoluteFile(),classPath);
		try {
			funcion =  (IFuncion) loader.loadClass(classPath).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void registrarMutante(String path){
		Writer output;
		try {
			output = new BufferedWriter(new FileWriter("Log.txt",true));
			output.write(path + System.getProperty("line.separator"));
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
	}
}
