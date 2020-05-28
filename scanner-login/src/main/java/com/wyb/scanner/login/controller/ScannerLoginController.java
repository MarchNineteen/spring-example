package com.wyb.scanner.login.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.wyb.scanner.login.service.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Marcher丶
 */
@Controller
public class ScannerLoginController {

    public static final String KEY_PRE = "qr_key:";
    public static final String NO_USE = "NO_USE";
    public static final String PRE_USE = "PRE_USE";
    public static final String USE = "USE";

    @Resource
    CacheService redisService;

    /**
     * 请求生成二维码
     */
    @GetMapping("/index")
    public String index(ModelMap map) throws IOException {
        UUID uuid = UUID.randomUUID();
        map.put("uuid", uuid.toString());
        return "qrcode";
    }

    /**
     * 请求生成二维码
     */
    @GetMapping("/getQrCode")
    public void generateQrCode(String uuid , HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        assert response != null;
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getOutputStream();

        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");

        //通过id查询数据设置二维码内容
        String text = "{\"id\":\"" + uuid + "\"}";

        // 把id存入redis
        redisService.putCache(KEY_PRE + uuid, NO_USE, 300);

        BufferedImage bufferedImage = QREncode(text);

        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    @GetMapping("/checkQrCode")
    @ResponseBody
    public String checkQrCode(String uuid) throws IOException {
        String status = (String) redisService.getCache(KEY_PRE + uuid);
        if (null == status) {
            return "NO_VALID";
        }
        if (PRE_USE.equals(status)) {
            return PRE_USE;
        }
        if (USE.equals(status)) {
            return USE;
        }
        return NO_USE;
    }


    /******** app  *******/
    @GetMapping("/app/scan")
    @ResponseBody
    public String scan(String uuid) {
        String status = (String) redisService.getCache(uuid);
        if (null == status) {
            return "NO_VALID";
        }
        if (NO_USE.equals(status)) {
            redisService.putCache(KEY_PRE + uuid, USE);
            return NO_USE;
        }
        if (PRE_USE.equals(status)) {
            return PRE_USE;
        }
        if (USE.equals(status)) {
            return USE;
        }
        // 生成preToken
        return "";
    }

    public void getToken(String preToken) {
        // 生成token

    }

    /**
     * 生成二维码
     */
    public static BufferedImage QREncode(String content) {
        int width = 250; // 图像宽度
        int height = 250; // 图像高度
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //内容编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置二维码边的空度，非负数
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = null;
        BufferedImage bufferedImage = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        //无logo
        return bufferedImage;

        //带logo，System.getProperty("user.dir")是项目工程根路径
//        assert bufferedImage != null;
//        return LogoMatrix(bufferedImage,new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\logo.png"));
    }


}
