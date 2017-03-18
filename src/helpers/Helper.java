package helpers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;

public class Helper {
	
	public static boolean MatchAORbinary(CtElement elemento){
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.PLUS,
																			BinaryOperatorKind.MINUS,
																			BinaryOperatorKind.MUL,
																			BinaryOperatorKind.DIV,
																			BinaryOperatorKind.MOD));
		try
		{
			CtBinaryOperator op = (CtBinaryOperator)elemento;
			return operadoresBin.contains(op);
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static boolean MatchAORunary(CtElement elemento){
		Set<UnaryOperatorKind> operadoresUn = new HashSet<>(Arrays.asList(UnaryOperatorKind.POSTDEC,
																			UnaryOperatorKind.POSTINC,
																			UnaryOperatorKind.PREDEC,
																			UnaryOperatorKind.PREINC));
		try
		{
			CtUnaryOperator op = (CtUnaryOperator)elemento;
			return operadoresUn.contains(op);
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static boolean MatchCORbinary(CtElement elemento){
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.AND,
																			BinaryOperatorKind.OR,
																			BinaryOperatorKind.BITAND,
																			BinaryOperatorKind.BITOR,
																			BinaryOperatorKind.BITXOR));
		try
		{
			CtBinaryOperator op = (CtBinaryOperator)elemento;
			return operadoresBin.contains(op);
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static boolean MatchROR(CtElement elemento){
		Set<BinaryOperatorKind> operadoresBin = new HashSet<>(Arrays.asList(BinaryOperatorKind.LE,
																			BinaryOperatorKind.LT,
																			BinaryOperatorKind.GE,
																			BinaryOperatorKind.GT,
																			BinaryOperatorKind.EQ,
																			BinaryOperatorKind.NE));
		try
		{
			CtBinaryOperator op = (CtBinaryOperator)elemento;
			return operadoresBin.contains(op);
		}
		catch (Exception e) 
		{
			return false;
		}
	}
}
