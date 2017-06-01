package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;

public class Helper {
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
	
	public static void compilar(String path,String directorio){		
		try {
			Path currentRelativePath = Paths.get("");
			String currentPath = currentRelativePath.toAbsolutePath().toString() + File.separator + directorio;
			File f = new File(currentPath);
			f.mkdirs();			
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
			String[] options = new String[] { "-classpath", "origen" + File.pathSeparator + System.getProperty("java.class.path") , "-d", currentPath };
			File[] javaFiles = new File[] { new File(currentRelativePath.toAbsolutePath().toString() + File.separator + path) };
			CompilationTask compilationTask = compiler.getTask(null, null, null, Arrays.asList(options), null,sjfm.getJavaFileObjects(javaFiles));
			compilationTask.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
