/* 
 * @(#)ImgHandler    Created on 2018/8/9
 * Copyright (c) 2018 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.wyb.test.java.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author wangyb
 * @version $$ Revision: 1.0 $$, $$ Date: 2018/8/9 18:54 $$
 */
public class ImgHandler {

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
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\avatar.jpg");
        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\0a0f8f26cd304b69b484aae22757c54b_1533647014561.jpg");
        //纯黑
        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\1533814105(1).jpg");
        //纯白
        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\1533815053(1).jpg");
    }
}
