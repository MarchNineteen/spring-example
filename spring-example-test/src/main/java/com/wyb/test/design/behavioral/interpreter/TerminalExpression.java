package com.wyb.test.design.behavioral.interpreter;

import java.util.StringTokenizer;

/**
 * @author Kunzite
 */
public class TerminalExpression extends Expression {

    private String literal = null;

    public TerminalExpression(String literal) {
        this.literal = literal;
    }

    @Override
    public boolean interpret(String str) {
        // StringTokenizer是一个用来分隔String的应用类 java默认的分隔符是“空格”、“制表符(‘\t’)”、“换行符(‘\n’)”、“回车符(‘\r’)”
        StringTokenizer st = new StringTokenizer(str);
        // 返回是否还有分隔符 同hasMoreElements()
        while (st.hasMoreTokens()) {
            String test = st.nextToken();
            if (test.equals(literal)) {
                return true;
            }
        }
        return false;
    }
}
