package com.zh.framework.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mrkin on 2017/2/14.
 */
public class FileUtil {
    private static SimpleDateFormat dirSDF=new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat fileNameSDF=new SimpleDateFormat("hhmmssSSS");
    public static String saveFile(String type, CommonsMultipartFile commonsMultipartFile, HttpServletRequest request) {
        String targetDirectory = "";
        String filename="";
        try {
            String dirDateStr=dirSDF.format(new Date());
            if (type == null || type.equals("")) {
                targetDirectory= request.getServletContext().getRealPath("/uploadFile/images/"+dirDateStr+"/");
            } else {
                targetDirectory= request.getServletContext().getRealPath("/uploadFile/images/" + type + "/"+dirDateStr+"/");
            }
            File filedir = new File(targetDirectory);
            if (!filedir.exists()) {
                System.out.println("------文件夹不存在");
                filedir.mkdirs();
            }
            filename=fileNameSDF.format(new Date())+".jpg";
            File file = new File(targetDirectory, filename);
            byte[] bytes= commonsMultipartFile.getBytes();
            OutputStream out = new FileOutputStream(file);
            out.write(bytes, 0, bytes.length);
            out.close();
            String result=targetDirectory.substring(targetDirectory.indexOf("\\uploadFile\\images\\"))+"\\"+filename;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }

}
