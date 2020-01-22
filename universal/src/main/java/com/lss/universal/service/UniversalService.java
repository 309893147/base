package com.lss.universal.service;


import com.lss.universal.config.UniversalConfig;
import com.lss.universal.util.API;
import com.lss.universal.util.UnzipUtil;
import com.lss.universal.util.UploadFileUtil;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class UniversalService {

    private UniversalConfig universalConfig;

    /**
     *
     * @param type 1-前端PC 2-管理端PC
     * @param versionNumber 版本号
     * @param username 账号
     * @param password 密码
     * @param file 文件
     * @return
     */
    public API packageAndGoOnline(Integer type, String versionNumber, String username, String password, MultipartFile file){
        if(!username.equals(universalConfig.getUsername()) || !password.equals(universalConfig.getPassword())){
            return API.e(400,"账号或密码错误");
        }
        if(type.equals(1)){
            String apppcZip = null;
            try {
                apppcZip = UploadFileUtil.savaFile(file, universalConfig.getUploadZipUrl(), "pc"+"."+versionNumber);
            } catch (IOException e) {
                e.printStackTrace();
                return API.e(400,"上传失败");
            }
            boolean unZip = UnzipUtil.unZip(apppcZip, universalConfig.getUnzipApppcUrl(), true);
            if(!unZip){
                return API.e(400,"解压失败");
            }
            return API.ok("上传成功");
        }
        if(type.equals(2)){
            String managerpcZip = null;
            try {
                managerpcZip = UploadFileUtil.savaFile(file, universalConfig.getUploadZipUrl(), "manager"+"."+versionNumber);
            } catch (IOException e) {
                e.printStackTrace();
                return API.e(400,"上传失败");
            }
            boolean unZip = UnzipUtil.unZip(managerpcZip, universalConfig.getUnzipManagerpcUrl(), true);
            if(!unZip){
                return API.e(400,"解压失败");
            }
            return API.ok("上传成功");
        }
        if(type.equals(3)){
            String appjar = null;
            try {
                appjar = UploadFileUtil.savaFile(file, universalConfig.getUploadZipUrl(), "app"+"."+versionNumber);
            } catch (IOException e) {
                e.printStackTrace();
                return API.e(400,"上传失败");
            }
            return API.ok("上传成功");
        }
        return API.e(400,"参数错误");
    }




}
