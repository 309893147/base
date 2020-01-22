package com.lss.universal.util;

import lombok.Data;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Data
public class UnzipUtil {
    public static boolean unZip(String srcPath ,String destPath,boolean includeZipFileName){
        try {
            ZipInputStream Zin=new ZipInputStream(new FileInputStream(
                    srcPath));
            BufferedInputStream Bin=new BufferedInputStream(Zin);
            if(includeZipFileName){
                String zipFileName = srcPath.substring(srcPath.lastIndexOf(File.separator));
                zipFileName = zipFileName.substring(0, zipFileName.indexOf("."));
                destPath += zipFileName;
            }
            File Fout=null;
            ZipEntry entry;
            try {
                while((entry = Zin.getNextEntry())!=null){
                    if(entry.isDirectory()){
                        continue;
                    }
                    Fout=new File(destPath,entry.getName());
                    if(!Fout.exists()){
                        (new File(Fout.getParent())).mkdirs();
                    }
                    FileOutputStream out=new FileOutputStream(Fout);
                    BufferedOutputStream Bout=new BufferedOutputStream(out);
                    int b;
                    while((b=Bin.read())!=-1){
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                }
                Bin.close();
                Zin.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
