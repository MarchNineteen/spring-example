package com.wyb.vue.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUploadUtil {

    public static List<File> upload(String path, List<File> file) {
        String newFileNamePrefix = new SimpleDateFormat("yyyyMMdd").format(new Date());
        ArrayList list = new ArrayList();
        String name = "";
        for (File f : file) {
            //先取得文件名
            String old_name = f.getName();
            //文件路径
            File source_path = f.getParentFile();
            //文件后缀
            String suffix = old_name.substring(old_name.lastIndexOf("."));
            //判断是否是合法后缀 ,重命名文件名
            name = newFileNamePrefix + suffix;
            try {
                //拿到输入流
                FileInputStream inputStream = new FileInputStream(source_path);
                //文件目录
                File targetDir = new File(path);
                if (!targetDir.exists()) {
                    targetDir.mkdirs();
                    targetDir.mkdir();
                }
                //创建新的文件,
                File newFile = new File(targetDir, name);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                //输出流
                FileOutputStream outputStream = new FileOutputStream(newFile);
                byte[] a = new byte[100];
                while (inputStream.read(a, 0, 100) != -1) {
                    outputStream.write(a, 0, 300);
                }
                inputStream.close();
                outputStream.close();
                list.add(name);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public static String uploadOne(String path, MultipartFile file) {
        String newFileNamePrefix = String.valueOf(System.currentTimeMillis());
        String name = "";

        //先取得文件名
        String old_name = file.getOriginalFilename();
        //文件路径

        //文件后缀
        String suffix = old_name.substring(old_name.lastIndexOf("."));
        //判断是否是合法后缀 ,重命名文件名
        name = newFileNamePrefix + suffix;
        try {
            //拿到输入流
            //文件目录
            File targetDir = new File(path);
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            //创建新的文件,
            File newFile = new File(targetDir, name);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            //输出流
//                FileOutputStream outputStream = new FileOutputStream(newFile);
//                byte[] a = new byte[100];
//                while (inputStream.read(a,0,100) != -1){
//                    outputStream.write(a, 0, 300);
//                }
//                inputStream.close();
//                outputStream.close();
            file.transferTo(newFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
