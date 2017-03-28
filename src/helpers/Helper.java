package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtStatement;
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
}
