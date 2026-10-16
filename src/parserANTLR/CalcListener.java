// Generated from /Users/yaoureda/Desktop/COMPIL2/src/parserANTLR/Calc.g4 by ANTLR 4.13.2
package parserANTLR;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalcParser}.
 */
public interface CalcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link CalcParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNumber(CalcParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link CalcParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNumber(CalcParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryExp}
	 * labeled alternative in {@link CalcParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExp(CalcParser.BinaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryExp}
	 * labeled alternative in {@link CalcParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExp(CalcParser.BinaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfZero}
	 * labeled alternative in {@link CalcParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIfZero(CalcParser.IfZeroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfZero}
	 * labeled alternative in {@link CalcParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIfZero(CalcParser.IfZeroContext ctx);
}