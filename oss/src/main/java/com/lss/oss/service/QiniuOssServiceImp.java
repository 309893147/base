package com.lss.oss.service;

import com.lss.oss.config.QiniuConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author longpeng
 * Qiniu OSS
 */
public class QiniuOssServiceImp implements OssService<QiniuConfig, Auth>{


    private QiniuConfig qiniuConfig;

    private Auth auth;

    @Override
    public QiniuConfig getConfig() {
        return qiniuConfig;
    }

    @Override
    public Auth getClient() {
        return auth;
    }

    @Override
    public void setConfig(QiniuConfig config) {
        qiniuConfig=config;
        auth=Auth.create(qiniuConfig.getAccessKey(),qiniuConfig.getSecretKey());
    }

    @Override
    public String uploadFileToken(String fileName, Integer expiry) {
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", qiniuConfig.getCallbackUrl());
        putPolicy.put("callbackBody", "{\"qiniuKey\":\"$(key)\",\"etag\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize),\"mimeType\":$(mimeType)}");
        putPolicy.put("callbackBodyType", "application/json");
        long expireSeconds = (long)expiry;
        String upToken = auth.uploadToken(qiniuConfig.getBucket(), null, expireSeconds, putPolicy);
        return upToken;
    }

    @Override
    public void uploadFile(String fileName, MultipartFile file) {
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);
        String upToken = auth.uploadToken(qiniuConfig.getBucket());
        try{
            Response response = uploadManager.put(file.getInputStream(),fileName,upToken,null, null);
        }catch (QiniuException ex){
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getFileUrl(String fileName, Integer expiry) {
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String publicUrl = String.format("%s/%s", qiniuConfig.getDomainOfBucket(), encodedFileName);
        String finalUrl = auth.privateDownloadUrl(publicUrl, (long)expiry);
        return finalUrl;
    }
}
