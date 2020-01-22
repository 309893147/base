package com.lss.oss;

import com.lss.oss.config.MinioConfig;
import com.lss.oss.config.QiniuConfig;
import com.lss.oss.service.MinioOssServiceImp;
import com.lss.oss.service.OssService;
import com.lss.oss.service.QiniuOssServiceImp;
import com.qiniu.util.Auth;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({MinioConfig.class, QiniuConfig.class})
public class OssAutoConfiguration {


    @Bean(value = "MinioOssService")
//    @ConditionalOnBean(MinioConfig.class)
    @ConditionalOnProperty(prefix = "oss.minio.config", value = "initialization",havingValue = "true")
    public OssService<MinioConfig, MinioClient> minioOssService(MinioConfig minioConfig){
        OssService<MinioConfig, MinioClient> minioOssService=new MinioOssServiceImp();
        minioOssService.setConfig(minioConfig);
        return minioOssService;
    }

    @Bean(value = "QiniuOssService")
//    @ConditionalOnBean(QiniuConfig.class)
    @ConditionalOnProperty(prefix = "oss.qiniu.config", value = "initialization",havingValue = "true")
    public OssService<QiniuConfig, Auth> qiniuOssService(QiniuConfig qiniuConfig){
        OssService<QiniuConfig, Auth> qiniuOssService=new QiniuOssServiceImp();
        qiniuOssService.setConfig(qiniuConfig);
        return qiniuOssService;
    }



}
