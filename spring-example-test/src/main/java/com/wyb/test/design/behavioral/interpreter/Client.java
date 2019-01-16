package com.wyb.test.design.behavioral.interpreter;

/**
 * @author Kunzite
 * 解释器:为语言创建解释器，通常由语言的语法和语法分析来定义。
 * <p>
 * TerminalExpression：终结符表达式，每个终结符都需要一个 TerminalExpression。
 * Context：上下文，包含解释器之外的一些全局信息。
 * </p>
 * <p>
 * 以下是一个规则检验器实现，具有 and 和 or 规则，通过规则可以构建一颗解析树，用来检验一个文本是否满足解析树定义的规则。
 * 例如一颗解析树为 D And (A Or (B C))，文本 "D A" 满足该解析树定义的规则。
 * 这里的 Context 指的是 String。
 * </p>
 * JDK
 * java.util.Pattern
 * java.text.Normalizer
 * All subclasses of java.text.Format
 * javax.el.ELResolver
 */
public class Client {

    /**
     * 构建解析树
     */
    public static Expression buildTree() {
        // Literal
        Expression terminal1 = new TerminalExpression("A");
        Expression terminal2 = new TerminalExpression("B");
        Expression terminal3 = new TerminalExpression("C");
        Expression terminal4 = new TerminalExpression("D");
        // B C
        Expression alternation1 = new OrExpression(terminal2, terminal3);
        // A or (B C)
        Expression alternation2 = new OrExpression(terminal1, alternation1);
        // D AND (A OR (B C))
        Expression alternation3 = new AndExpression(terminal4, alternation2);
        return alternation3;
    }

    public static void main(String[] args) {
        Expression define = buildTree();
        String context1 = "D A";
        String context2 = "B C";
        System.out.println(define.interpret(context1));
        System.out.println(define.interpret(context2));
    }
}
