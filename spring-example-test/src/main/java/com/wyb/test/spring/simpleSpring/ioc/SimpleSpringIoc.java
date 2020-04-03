package com.wyb.test.spring.simpleSpring.ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Marcher丶
 * 读取配置文件 遍历标签
 * 获取标签的值 加载对应的类 并创建对象
 * 遍历标签 获取属性值 填充bean中
 * 加入ioc
 */
public class SimpleSpringIoc {
    private ConcurrentHashMap<String, Object> ioc = new ConcurrentHashMap<>(16);


    public static void main(String[] args) throws Exception {
        String url = Objects.requireNonNull(SimpleSpringIoc.class.getClassLoader().getResource("miniSpring.xml")).getFile();
        SimpleSpringIoc ioc = new SimpleSpringIoc(url);
        MiniBean miniBean = (MiniBean) ioc.getBean("miniBean");
        System.out.println(miniBean.getName());
        System.out.println(miniBean.getPassword());
        MiniBean miniBean1 = (MiniBean) ioc.getBean("miniBean1");
        System.out.println(miniBean1.getMiniBean().getName());
        System.out.println(miniBean1.getMiniBean().getPassword());
    }

    public SimpleSpringIoc(String resourceUrl) {
        try {
            loadXml(resourceUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String clazzName) throws Exception {
        Object obj = ioc.get(clazzName);
        // ref有先后引用的问题
        if (null == obj) {
            throw new Exception("bean not exsit");
        }
        return obj;
    }

    /**
     * 加载xml 读取配置文件 遍历标签
     */
    private void loadXml(String resourceUrl) throws Exception {
        InputStream is = new FileInputStream(resourceUrl);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        Document doc = db.parse(is);
        Element root = doc.getDocumentElement();
        // 获取beans节点
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");
                if (null == className || "".equals(className)) {
                    continue;
                }
                Class clazz = Class.forName(className);
                Object obj = clazz.newInstance();
                // 遍历property
                NodeList propertyNodes = ((Element) node).getElementsByTagName("property");

                for (int j = 0; j < propertyNodes.getLength(); j++) {
                    Element propertyNode = (Element) propertyNodes.item(j);
                    String name = propertyNode.getAttribute("name");
                    String value = propertyNode.getAttribute("value");
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);

                    if (null != value && !"".equals(value)) {
                        field.set(obj, value);
                    }
                    else {
                        String ref = propertyNode.getAttribute("ref");
                        if (null != ref && !"".equals(ref)) {
                            field.set(obj, getBean(ref));
                        }
                    }

                }
                registerBean(id, obj);
            }
        }

    }

    private void registerBean(String id, Object obj) {
        ioc.put(id, obj);
    }

}
