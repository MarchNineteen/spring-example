package com.wyb.test.spring.smallSpring.ioc.xml;

import com.wyb.test.spring.smallSpring.ioc.BeanDefinitionReader;
import com.wyb.test.spring.smallSpring.ioc.bean.BeanDefinition;
import com.wyb.test.spring.smallSpring.ioc.bean.BeanReference;
import com.wyb.test.spring.smallSpring.ioc.bean.PropertyValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcher丶
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;

    public XmlBeanDefinitionReader() {
        registry = new HashMap<>();
    }

    @Override
    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        // 1.加载xml文件
        Element root = loadXml(location);

        // 2.获取标签
        parseBeanDefinitions(root);

        // 3 解析标签 读取属性

        // 4 创建beanDefinition对象 属性赋值

        // 5 遍历标签下的标签 属性赋值

        // 6 存入map
    }

    private Element loadXml(String location) throws Exception {
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document document = docBuilder.parse(inputStream);
        Element root = document.getDocumentElement();

        return root;
    }

    private void parseBeanDefinitions(Element root) {
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseBeanDefinition(ele);
            }
        }
    }

    private void parseBeanDefinition(Element element) {
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        parseProperty(element.getElementsByTagName("property"), beanDefinition);
        registry.put(name, beanDefinition);
    }

    private void parseProperty(NodeList propertyNodes, BeanDefinition beanDefinition) {
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node propertyNode = propertyNodes.item(i);
            if (propertyNode instanceof Element) {
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                // 不直接实例化 使用时实例化
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("ref config error");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
