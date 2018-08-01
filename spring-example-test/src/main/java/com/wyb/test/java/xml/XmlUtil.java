package com.wyb.test.java.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * xml
 * @author Kunzite
 */
public class XmlUtil {

    /**
     * String 类型的xml 转化成 map
     * @param xml
     * @return
     */
    public static Map<String,String> transferToObject(String xml){
        Map<String,String> hashMap = new HashMap<>();
        try {
            Document document = DocumentHelper.parseText(xml);
            //得到xml根元素
            Element root = document.getRootElement();
//            List<Element> sonRoot = root.elements();

//            Element body = root.element("Request");
            List<Element> sonRoot = root.elements();
            for (Element element :sonRoot){
                hashMap.put(element.getName(),element.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public static String transToXml(HashMap<String,String> map){
        Document document = DocumentHelper.createDocument();
        Element body  = document.addElement("Request");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            body.addElement(entry.getKey()).setText(entry.getValue());
        }
       return body.asXML();
    }

    public static void addCDATA(){
        //二级XML
        Document second = DocumentHelper.createDocument();

        Element secondBody = second.addElement("Response");
        secondBody.addElement("TradeCode").setText("1101");
        String secondXml = secondBody.asXML();

        //一级XML
        Document document = DocumentHelper.createDocument();
        Element body = document.addElement("Request");
        body.addElement("data").setText("22");
        body.addElement("BankTradeInfo").add(DocumentHelper.createCDATA(secondXml));;//添加二级xml的CDATA
        System.out.println(body.asXML());

    }

    public static void main(String[] args) {
        addCDATA();
    }



}

////二级XML
//        Document second = DocumentHelper.createDocument();
//
//        Element secondBody = second.addElement("Response");
//        secondBody.addElement("TradeCode").setText("1101");
//        secondBody.addElement("BankCode").setText("1101");
//        secondBody.addElement("BankDate").setText("1101");
//        secondBody.addElement("BankTradeNo").setText("1101");
//        secondBody.addElement("ResultCode").setText("1101");
//        secondBody.addElement("ResultContent").setText("1101");
//        secondBody.addElement("PayCardNo").setText("1101");
//        secondBody.addElement("BankAccDate").setText("1101");
//        secondBody.addElement("RevTranFlag").setText("1101");
//        secondBody.addElement("PatientID").setText("1101");
//        secondBody.addElement("PayAmt").setText("1101");
//        secondBody.addElement("OrgHISTradeNo").setText("1101");
//        secondBody.addElement("OrgPaySeqNo").setText("1101");
//        String secondXml = secondBody.asXML();
//
//        //一级XML
//        Document document = DocumentHelper.createDocument();
//        Element body = document.addElement("Request");
//        body.addElement("HospitalId").setText(HospitalId);
//        body.addElement("ScheduleItemCode").setText(scheduleItemCode);
//        body.addElement("ExtUserID").setText(extUserID);
//        body.addElement("PatientCard").setText(patientCard);
//        body.addElement("TradeCode").setText("1101");
//        //卡类别处理
//        if (StringUtils.equals(cardType, "12")) {
//            cardType =  "01";
//        } else if (StringUtils.equals(cardType,"2")){
//            cardType =  "02";
//        } else if (StringUtils.equals(cardType,"10")){
//            cardType =  "04";
//        } else {
//            cardType =  "05";
//        }
//        body.addElement("CardType").setText(cardType);
//        body.addElement("PayModeCode").setText(cardType);
//        body.addElement("QueueNo").setText(queueNo);
//        body.addElement("BankTradeInfo").add(DocumentHelper.createCDATA(secondXml));//添加二级xml的CDATA
//
//        logger.error("flowId=" + flowId);
//        return result.value(body.asXML());