package com.wyb.test.design.behavioral.memento;

/**
 * @author Kunzite
 * 备忘录（Memento）: 在不违反封装的情况下获得对象的内部状态，从而在需要时可以将对象恢复到最初状态。
 * Class Diagram
 * Originator：原始对象
 * Caretaker：负责保存好备忘录
 * Menento：备忘录，存储原始对象的的状态。备忘录实际上有两个接口，一个是提供给 Caretaker 的窄接口：它只能将备忘录传递给其它对象；一个是提供给 Originator 的宽接口，
 * 允许它访问到先前状态所需的所有数据。理想情况是只允许 Originator 访问本备忘录的内部状态。
 *
 * 计算器接口依赖了备忘录 拥有一个创建一个元数据的备忘录 和 获取备忘录
 */
public class Client {

    public static void main(String[] args) {
        Calculator cal = new CalculatorImp();

        cal.setFirstNumber(1);
        cal.setSecondNumber(2);
        System.out.println(cal.getCalculationResult());

        // 备份数据
        PreviousCalculationToCareTaker memento = cal.backupLastCalculation();

        cal.setFirstNumber(3);
        cal.setSecondNumber(4);

        System.out.println(cal.getCalculationResult());

        cal.restorePreviousCalculation(memento);

        System.out.println(cal.getCalculationResult());
    }
}
