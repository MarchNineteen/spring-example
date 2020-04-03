package com.wyb.test.spring.smallSpring.ioc.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcher丶
 */
public class PropertyValues {

    private final List<PropertyValue> values = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        // 在这里可以对参数值 pv 做一些处理，如果直接使用 List，则就不行了
        values.add(pv);
    }

    public List<PropertyValue> getValues() {
        return values;
    }
}
