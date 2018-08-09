/*
 * @(#)ImgHandler    Created on 2018/8/9
 * Copyright (c) 2018 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.test.java.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author wangyb
 * @version $$ Revision: 1.0 $$, $$ Date: 2018/8/9 18:54 $$
 */
public class ImgHandler {

    String openUrl; // 原始图片打开路径
    String saveUrl; // 新图保存路径
    String saveName; // 新图名称
    String suffix; // 新图类型 仅仅支持gif,jpg,png

    public ImgHandler(String openUrl, String saveUrl, String saveName,
                     String suffix) {
        this.openUrl = openUrl;
        this.saveName = saveName;
        this.saveUrl = saveUrl;
        this.suffix = suffix;
    }

    /**
     * 图片缩放.
     *
     * @param width
     *            须要的宽度
     * @param height
     *            须要的高度
     * @throws Exception
     */
    public void zoom(int width, int height) throws Exception {
        double sx = 0.0;
        double sy = 0.0;

        File file = new File(openUrl);
        if (!file.isFile()) {
            throw new Exception("ImageDeal>>>" + file + " 不是一个图片文件!");
        }
        BufferedImage bi = ImageIO.read(file); // 读取该图片
        // 计算x轴y轴缩放比例--如需等比例缩放，在调用之前确保參数width和height是等比例变化的
        sx = (double) width / bi.getWidth();
        sy = (double) height / bi.getHeight();

        AffineTransformOp op = new AffineTransformOp(
                AffineTransform.getScaleInstance(sx, sy), null);
        File sf = new File(saveUrl, saveName + "." + suffix);
        Image zoomImage = op.filter(bi, null);
        try {
            ImageIO.write((BufferedImage) zoomImage, suffix, sf); // 保存图片
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 得到文字行与图片高度的比例
     */
    public float getBili(){
        float bili = 0f;
        int[] startRow;// 字起始行
        int endRow = 0;// 字结束行
        try{
            BufferedImage bimg = ImageIO.read(new File(this.openUrl));
            startRow = new int[bimg.getWidth()];
            int [][]data = new int[bimg.getHeight()][bimg.getWidth()];
            //方式一：通过getRGB()方式获得像素矩阵
            //此方式为沿width方向扫描
            for(int i=0;i<bimg.getHeight();i++){
                // 判断一整行是否为全白
                int n = 0;
                for(int j=0;j<bimg.getWidth();j++){
                    data[i][j]=bimg.getRGB(i,j);
                    // 不是白色
                    if (!("ffffffff".equals(String.format("%x",data[i][j])))){
                        break;
                    }
                    else if (j==(bimg.getWidth()-1)){
                        startRow[n] = i;
                        n++;
                    }

                }
            }
            //遍历row
//            for (int i=0;i<startRow.length;i++) {
//
//            }
            DecimalFormat df=new DecimalFormat("0.00");
            System.out.println(df.format((float)(startRow[1]-startRow[0])/(bimg.getWidth())));
            bili = (float)(startRow[1]-startRow[0])/(bimg.getWidth());
        }catch (IOException e){
            e.printStackTrace();
        }
        return bili;
    }

    public static void getData(String path){
        int n1=0;
        int n2=0;
        try{
            BufferedImage bimg = ImageIO.read(new File(path));
            int [][] data = new int[bimg.getWidth()][bimg.getHeight()];
            //方式一：通过getRGB()方式获得像素矩阵
            //此方式为沿Height方向扫描
            for(int i=0;i<bimg.getWidth();i++){
                for(int j=0;j<bimg.getHeight();j++){
                    data[i][j]=bimg.getRGB(i,j);
                    //输出一列数据比对
//                    if(i==0) {
//                        System.out.printf("%x\t\n", data[i][j]);
//                    }
                    if ("ffffffff".equals(String.format("%x",data[i][j]))){
                        n1++;
                    }
                    n2++;
                }
            }
            DecimalFormat df=new DecimalFormat("0.00");
            System.out.println(df.format((float)n1/(bimg.getWidth()*bimg.getHeight())));
//            Raster raster = bimg.getData();
//            System.out.println("");
//            int [] temp = new int[raster.getWidth()*raster.getHeight()*raster.getNumBands()];
//            //方式二：通过getPixels()方式获得像素矩阵
//            //此方式为沿Width方向扫描
//            int [] pixels  = raster.getPixels(0,0,raster.getWidth(),raster.getHeight(),temp);
//            for (int i=0;i<pixels.length;) {
//                //输出一列数据比对
//                if((i%raster.getWidth()*raster.getNumBands())==0) {
//                    System.out.printf("ff%x%x%x\t", pixels[i], pixels[i + 1], pixels[i + 2]);
//                }
//                i+=3;
//            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String [] args){
        ImgHandler imgHandler =
                new ImgHandler("C:\\Users\\wyb\\Desktop\\测试\\1.jpg","C:\\Users\\wyb\\Desktop\\测试","11","jpg");

        imgHandler.getBili();
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\avatar.jpg");
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\0a0f8f26cd304b69b484aae22757c54b_1533647014561.jpg");
        //纯黑
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\1533814105(1).jpg");
        //纯白
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\1533815053(1).jpg");
    }
}
