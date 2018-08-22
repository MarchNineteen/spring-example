package com.wyb.test.java.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private float fontHeight; // 字体高度
    private float deviation; // 误差 在字体高度的百分比之下不作为字体高度
    private float invalidDataDeviation = 0.1f; // 误差 无效数据去除百分比

    private int defaultTime = 10;
    private float defaultFontHeight = 14;
    private float defaultDeviation = 1f;

    private float minFontHeight = 20f;
    private float maxFontHeight = 150f;

    public ImgHandler(String openUrl, int time, float fontHeight, float deviation) {
        this.openUrl = openUrl;
        this.time = 0 == time ? 1 : time;
        this.fontHeight = fontHeight;
        this.deviation = deviation;
    }

    public ImgHandler(String openUrl, int time) {
        this.openUrl = openUrl;
        this.time = 0 == time ? 1 : time;
        this.fontHeight = defaultFontHeight;
        this.deviation = defaultDeviation;
    }

    public ImgHandler(String openUrl) {
        this.openUrl = openUrl;
        this.time = defaultTime;
        this.fontHeight = defaultFontHeight;
        this.deviation = defaultDeviation;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    /**
     * 得到文字行与传入标准字体高度的比例，即得到在word文档中图片的缩放比
     */
    public int getWordHeight() {
        int wordHeight = 0;
        int[] aveHeight = new int[this.time];// 图片平均高度
        BufferedImage oldBimg = null;
        try {
            oldBimg = ImageIO.read(new File(this.openUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 区域
        List<BufferedImage> list = randomCut(oldBimg, this.time);
        for (int a = 0; a < list.size(); a++) {
            BufferedImage bimg = list.get(a);
            //获取所有不为全白行的高度坐标
            int[] notWhiteRow = this.getBlackRow(bimg);
            // 计算所有连续行的行数即文字高度
            int[] height = this.getAllHeight(notWhiteRow);
//            System.out.println("第" + a + "段各个文字高度：" + Arrays.toString(height));
            //去除图片中的黑点
            int[] newArray = cutInvalidNum(height, this.fontHeight * this.deviation);
            //排序
            Arrays.sort(newArray);
            //去除数据中的无效部分
            newArray = this.cutByHeadAndEndByPercent(newArray, this.invalidDataDeviation);
//            System.out.println("第" + a + "段去掉" + this.invalidDataDeviation + "无效间隔后的所有高度数组：" + Arrays.toString(newArray));
            wordHeight = this.getAverage(newArray);
//            if (wordHeight > this.minFontHeight && wordHeight < this.maxFontHeight) {
            aveHeight[a] = wordHeight;
//            }
//            System.out.println("第" + a + "段去掉" + this.invalidDataDeviation + "无效间隔后的所有高度数组平均：" + aveHeight[a]);
        }
        //去除数据中的无效部分
        aveHeight = this.cutByHeadAndEndByPercent(aveHeight, invalidDataDeviation);
        //取平均
        wordHeight = this.getAverage(aveHeight);
//        System.out.println("横向扫描结果：" + wordHeight);
        if (wordHeight > this.minFontHeight && wordHeight < this.maxFontHeight) {
            return wordHeight;
        }
        // 执行垂直
        wordHeight = getvertical(oldBimg);
//        System.out.println("垂直扫描结果：" + wordHeight);
        if (wordHeight > this.minFontHeight && wordHeight < this.maxFontHeight) {
            return wordHeight;
        }
        return 0;
    }

    /**
     * 获取拥有黑色像素的高度坐标 沿width方向扫描
     *
     * @return
     */
    private int[] getBlackRow(BufferedImage bimg) {
        // 记录不为全白行的行码
        int[] notWhiteRow = new int[bimg.getHeight()];// 字起始行
        int[][] data = new int[bimg.getWidth()][bimg.getHeight()];
        int n = 0;
        //此方式为沿width方向扫描
        for (int y = 0; y < bimg.getHeight(); y++) {
            // 判断一整行是否为全白
            for (int x = 0; x < bimg.getWidth(); x++) {
                data[x][y] = bimg.getRGB(x, y);
                // 不是白色
                if (!(-1 == data[x][y])) {
                    notWhiteRow[n] = y;
                    n++;
                    break;
                }
            }
        }
        return notWhiteRow;
    }

    /**
     * 获取拥有黑色像素的高度坐标  沿height方向扫描
     *
     * @return
     */
    private int[] getBlackColumn(BufferedImage bimg) {
        int[] notWhiteRow;// 字起始行
        int[][] data = new int[bimg.getWidth()][bimg.getHeight()];
        // 记录不为全白行的行码
        notWhiteRow = new int[bimg.getWidth()];
        int n = 0;
        //此方式为沿height方向扫描
        for (int x = 0; x < bimg.getWidth(); x++) {
            // 判断一整行是否为全白
            for (int y = 0; y < bimg.getHeight(); y++) {
                data[x][y] = bimg.getRGB(x, y);
                // 不是白色
                if (!(-1 == data[x][y])) {
                    notWhiteRow[n] = x;
                    n++;
                    break;
                }
            }
        }
        return notWhiteRow;
    }

    /**
     * 计算所有连续行的行数即文字高度
     *
     * @return
     */
    private int[] getAllHeight(int[] notWhiteRow) {
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
        return height;
    }


    public int getvertical(BufferedImage oldBimg) {
        int wordHeight = 0;
        int[] aveHeight = new int[this.time];// 图片平均高度
        // 区域
        List<BufferedImage> list = this.horizontalCut(oldBimg, this.time);
        for (int a = 0; a < list.size(); a++) {
            BufferedImage bimg = list.get(a);
            //获取所有不为全白行的高度坐标
            int[] notWhiteColumn = this.getBlackColumn(bimg);
            // 计算所有连续行的行数即文字高度
            int[] height = this.getAllHeight(notWhiteColumn);
//            System.out.println("第" + a + "段各个文字高度：" + Arrays.toString(height));
            //去除图片中的黑点
            int[] newArray = cutInvalidNum(height, this.fontHeight * this.deviation);
            //排序
            Arrays.sort(newArray);
            //去除数据中的无效部分
            newArray = this.cutByHeadAndEndByPercent(newArray, this.invalidDataDeviation);
//            System.out.println("第" + a + "段去掉" + this.invalidDataDeviation + "无效间隔后的所有高度数组：" + Arrays.toString(newArray));
            wordHeight = this.getAverage(newArray);
            if (wordHeight > this.minFontHeight && wordHeight < this.maxFontHeight) {
                aveHeight[a] = wordHeight;
            }
//            System.out.println("第" + a + "段去掉" + this.invalidDataDeviation + "无效间隔后的所有高度数组平均：" + aveHeight[a]);
        }
        //去除数据中的无效部分
        aveHeight = this.cutByHeadAndEndByPercent(aveHeight, this.invalidDataDeviation);
        //取平均
        return this.getAverage(aveHeight);
    }

    /**
     * 按百分比去掉两头误差较大的数
     *
     * @param oldArr
     * @param t      百分比
     * @return
     */
    private int[] cutByHeadAndEndByPercent(int[] oldArr, float t) {
//        if (null == oldArr || oldArr.length <= 0) {
//            return null;
//        }
//        int zero = 0;
//        for (int i = 0; i < oldArr.length; i++) {
//            if (oldArr[i] == 0) {
//                zero++;
//            }
//        }
//        // 定义新的数组 长度是 原来旧的数组的长度减去0的个数
//        int newArr[] = new int[oldArr.length - zero];
//        int j = 0; // 新数组的索引
//        for (int i = 0; i < oldArr.length; i++) { // 遍历原来旧的数组
//            if (oldArr[i] > 0) {
//                newArr[j] = oldArr[i]; // 赋值给新的数组
//                j++;
//            }
//        }
        // todo 数据长度太小不做处理
//        if (oldArr.length < 6) {
//            return oldArr;
//        }
//        int cutLength = (int) Math.floor(newArr.length * t);
        int cutLength = Math.round(oldArr.length * t);
        int[] newArr;
        if (cutLength > 0) {
            int[] newArr1 = Arrays.copyOfRange(oldArr, cutLength, oldArr.length);
            newArr = Arrays.copyOfRange(newArr1, 0, newArr1.length - cutLength);
            return newArr;
        }
        return oldArr;
    }


    /**
     * 把一张图片垂直等分
     *
     * @param bimg
     * @param time
     * @return
     */
    private List<BufferedImage> randomCut(BufferedImage bimg, int time) {
        ArrayList list = new ArrayList();
        int width = bimg.getWidth();
        int sPerTime = width / time;
        for (int i = 0; i < time; i++) {
            BufferedImage newBuffer = bimg.getSubimage(i * sPerTime, 0, sPerTime, bimg.getHeight());
            list.add(newBuffer);
        }
        return list;
    }

    /**
     * 把一张图片横向等分
     *
     * @param bimg
     * @param time
     * @return
     */
    private List<BufferedImage> horizontalCut(BufferedImage bimg, int time) {
        ArrayList list = new ArrayList();
        int height = bimg.getHeight();
        int sPerTime = height / time;
        for (int i = 0; i < time; i++) {
            BufferedImage newBuffer = bimg.getSubimage(0, i * sPerTime, bimg.getWidth(), sPerTime);
            list.add(newBuffer);
        }
        return list;
    }

    /**
     * 去除指定误差外的无效数据
     *
     * @param oldArr
     * @param t
     * @return
     */
    private int[] cutInvalidNum(int[] oldArr, float t) {
        if (null == oldArr || oldArr.length <= 0) {
            return null;
        }
        int zero = 0;
        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i] < t) {
                zero++;
            }
        }
        // 定义新的数组 长度是 原来旧的数组的长度减去0的个数
        int newArr[] = new int[oldArr.length - zero];
        int j = 0; // 新数组的索引
        for (int i = 0; i < oldArr.length; i++) { // 遍历原来旧的数组
            if (oldArr[i] >= t) {
                newArr[j] = oldArr[i]; // 赋值给新的数组
                j++;
            }
        }
        return newArr;
    }

    /**
     * 去掉数组中为0的数值再取平均值
     *
     * @param
     */
    private int getAverage(int[] array) {
        if (null == array || array.length <= 0) {
            return 0;
        }
        int leg = 0;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (0 != array[i]) {
                sum = sum + array[i];
                leg++;
            }
        }
        if (leg == 0) {
            return 0;
        }
        return sum / leg;
    }


    private int[] cutByMiddleNum(int[] oldArr, float t) {
        if (null == oldArr || oldArr.length <= 0) {
            return null;
        }
        int median = 0;
        if ((oldArr.length % 2) == 0) {
            median = (oldArr[oldArr.length / 2] + oldArr[(oldArr.length / 2) - 1]) / 2;
        } else {
            median = oldArr[oldArr.length / 2];
        }
        int zero = 0;
        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i] < (median * (1 - t)) || oldArr[i] > (median * (1 + t))) {
                zero++;
            }
        }
        // 定义新的数组 长度是 原来旧的数组的长度减去0的个数
        int newArr[] = new int[oldArr.length - zero];
        int j = 0; // 新数组的索引
        for (int i = 0; i < oldArr.length; i++) { // 遍历原来旧的数组
            if (oldArr[i] >= median * (1 - t) && oldArr[i] <= median * (1 + t)) {
                newArr[j] = oldArr[i]; // 赋值给新的数组
                j++;
            }
        }
        Arrays.sort(newArr);
        // 去掉排序后的无效数据
        return newArr;
    }

}
