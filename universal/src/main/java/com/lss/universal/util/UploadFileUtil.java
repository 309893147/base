package com.lss.universal.util;





import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;



public class UploadFileUtil {

    public static String savaFile(MultipartFile file,String path,String fileName) throws IOException {
        File upload = new File(path);
        if(!upload.exists()){
            upload.mkdirs();
        }
        String name=file.getOriginalFilename();
        String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());
        fileName=fileName+"."+subffix;
        String realPath=path+File.separator+fileName;
        File fileMkd = new File(realPath);
        file.transferTo(fileMkd);
        return realPath;
    }



}
