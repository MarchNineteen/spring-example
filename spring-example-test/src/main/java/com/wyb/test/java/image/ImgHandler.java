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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangyb
 * @version $$ Revision: 1.0 $$, $$ Date: 2018/8/9 18:54 $$
 */
public class ImgHandler {

    private String openUrl; // 原始图片打开路径
    private int time; // 图片均分为几段
//    String saveUrl; // 新图保存路径
//    String saveName; // 新图名称
//    String suffix; // 新图类型 仅仅支持gif,jpg,png

    public ImgHandler(String openUrl, int time) {
        this.openUrl = openUrl;
        this.time = time;
//        this.saveName = saveName;
//        this.saveUrl = saveUrl;
//        this.suffix = suffix;
    }


    /**
     * 得到文字行与图片高度的比例
     */
    public void getBili() {
//        float bili = 0f;
        int[] notWhiteRow;// 字起始行
        int[] aveHeight = new int[this.time];// 图片平均高度
        try {
            System.out.println(this.openUrl);
            BufferedImage oldBimg = ImageIO.read(new File(this.openUrl));
            // 随机截取宽度 为20px的区域
            List<BufferedImage> list = randomCut(oldBimg, this.time);
            for (int a = 0; a < list.size(); a++) {
                BufferedImage bimg = list.get(a);
                int[][] data = new int[bimg.getWidth()][bimg.getHeight()];
                // 记录不为全白行的行码
                notWhiteRow = new int[bimg.getHeight()];
                int n = 0;
                //此方式为沿width方向扫描
                for (int y = 0; y < bimg.getHeight(); y++) {
                    // 判断一整行是否为全白
                    for (int x = 0; x < bimg.getWidth(); x++) {
                        data[x][y] = bimg.getRGB(x, y);
                        // 不是白色
                        if (!("ffffffff".equals(String.format("%x", data[x][y])))) {
                            notWhiteRow[n] = y;
                            n++;
                            break;
                        }

                    }
                }
                System.out.println("第" + a + "段所有字元素：" + Arrays.toString(notWhiteRow));
                // 计算所有连续行的行数 即文字高度
                int k = 0;
                int[] height = new int[notWhiteRow.length];
                int temp = 0;
                //遍历row
                for (int i = 0; i < notWhiteRow.length; i++) {
                    if (i != notWhiteRow.length - 1 && !((notWhiteRow[i + 1] - notWhiteRow[i]) == 1)) {
                        if (k == 0) {
                            height[k] = notWhiteRow[i] - notWhiteRow[0];
                        } else {
                            height[k] = notWhiteRow[i] - temp;
                        }
                        temp = notWhiteRow[i + 1];
                        k++;
                    }
                }
                System.out.println("第" + a + "段各个文字高度：" + Arrays.toString(height));
                //去掉无效间隔
                int[] newArray = cutNum(height, 10);
                System.out.println("第" + a + "段去掉无效间隔后的所有高度数组：" + Arrays.toString(newArray));
                Arrays.parallelPrefix(newArray, (x, y) -> (x + y));
                int pingJunJianGe = newArray[newArray.length - 1] / newArray.length;
                System.out.println("第" + a + "段图片平均文字高度：" + pingJunJianGe+ "\n");
                aveHeight[a] = pingJunJianGe;
//                DecimalFormat df = new DecimalFormat("0.00");
//                System.out.println(df.format((float) pingJunJianGe / bimg.getHeight()));
//                bili = (float) (pingJunJianGe / (bimg.getHeight()));
            }
            //计算平均高度
            Arrays.parallelPrefix(aveHeight, (x, y) -> (x + y));
            System.out.println("图片的平均文字高度：" + aveHeight[aveHeight.length - 1] / aveHeight.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return bili;
    }

    private List<BufferedImage> randomCut(BufferedImage bimg, int time) {
        ArrayList list = new ArrayList();
        int width = bimg.getWidth();
        int sPerTime = width / time;
        for (int i = 0; i < time; i++) {
            BufferedImage newBuffer = bimg.getSubimage(i * sPerTime, 0, sPerTime, bimg.getHeight());
//            int[][] data = new int[newBuffer.getWidth()][bimg.getHeight()];
            list.add(newBuffer);
        }
        return list;
    }

    private int[] cutNum(int[] oldArr, int t) {
        int zero = 0;
        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i] < t) {
                zero++;
            }
        }
        int newArr[] = new int[oldArr.length - zero]; // 定义新的数组 长度是 原来旧的数组的长度减去0的个数
        int j = 0; // 新数组的索引
        for (int i = 0; i < oldArr.length; i++) { // 遍历原来旧的数组
            if (oldArr[i] > 10) { // 假如不等于0
                newArr[j] = oldArr[i]; // 赋值给新的数组
                j++;
            }
        }
        Arrays.sort(newArr);
        //计算标准差
        return newArr;
    }

    //方差s^2=[(x1-x)^2 +...(xn-x)^2]/n
    public static int Variance(int[] x) {
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++) {//求和
            sum += x[i];
        }
        double dAve = sum / m;//求平均值
        double dVar = 0;
        for (int i = 0; i < m; i++) {//求方差
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        return (int) (dVar / m);
    }

    public static void getData(String path) {
        int n1 = 0;
        int n2 = 0;
        try {
            BufferedImage bimg = ImageIO.read(new File(path));
            int[][] data = new int[bimg.getWidth()][bimg.getHeight()];
            //方式一：通过getRGB()方式获得像素矩阵
            //此方式为沿Height方向扫描
            for (int i = 0; i < bimg.getWidth(); i++) {
                for (int j = 0; j < bimg.getHeight(); j++) {
                    data[i][j] = bimg.getRGB(i, j);
                    //输出一列数据比对
//                    if(i==0) {
//                        System.out.printf("%x\t\n", data[i][j]);
//                    }
                    if ("ffffffff".equals(String.format("%x", data[i][j]))) {
                        n1++;
                    }
                    n2++;
                }
            }
            DecimalFormat df = new DecimalFormat("0.00");
            System.out.println(df.format((float) n1 / (bimg.getWidth() * bimg.getHeight())));
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        ImgHandler imgHandler =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\0a0f8f26cd304b69b484aae22757c54b_1533647014561.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler1 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\5c170e9b625741c291225fc2bb55d9e2_1533716280051.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler2 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\5f769f6dd37e459b8c76c7a23b0d09c6_1533647064137.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler3 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\88e03f52b35e42ba8767d1ad19eb7246_1533716316500.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler4 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\886c23ac0a8b47d2a588f421bff00c3d_1533731116100.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler5 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\1807d6a623044e619f5d460c442e28a6_1533647100679.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler6 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\d5ba262635ae4d7580b5683e4a7e37d6_1533730849394.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler7 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\dc94999f7b284900b7503c4f9b294fad_1533647147746.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler8 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\e576bb95dde2490eac8667be10fca9fd_1533647188895.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler9 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\e81275f3eda549d0a3fb8bc93977174a_1533716075354.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler10 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\e700501add4a4c24a247c1d994f2d86e_1533113680523.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler11 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\f4c404e52a454b3f91c159b68efea779_1533731019799.jpg", 5);
//        ImgHandler imgHandler12 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\1.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler13 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\2.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler14 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\3.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler15 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\4.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
//        ImgHandler imgHandler16 =
//                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\外网1.jpg", "C:\\Users\\user\\Desktop\\错题图片\\111", "11", "jpg");
        ImgHandler imgHandler17 =
                new ImgHandler("C:\\Users\\user\\Desktop\\错题图片\\外网2.jpg", 5);
//        imgHandler.getBili();
//        imgHandler1.getBili();
//        imgHandler2.getBili();
//        imgHandler3.getBili();
//        imgHandler4.getBili();
//        imgHandler5.getBili();
//        imgHandler6.getBili();
//        imgHandler7.getBili();
//        imgHandler8.getBili();
//        imgHandler9.getBili();
//        imgHandler10.getBili();
//        imgHandler11.getBili();
//        imgHandler12.getBili();
//        imgHandler13.getBili();
//        imgHandler14.getBili();
//        imgHandler15.getBili();
//        imgHandler16.getBili();
        imgHandler17.getBili();
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\avatar.jpg");
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\0a0f8f26cd304b69b484aae22757c54b_1533647014561.jpg");
        //纯黑
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\1533814105(1).jpg");
        //纯白
//        ImgHandler.getData("C:\\Users\\user\\Desktop\\错题图片\\1533815053(1).jpg");
    }
}
