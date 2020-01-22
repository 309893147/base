package com.lss.oss.config;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Data
@Configuration
@ConfigurationProperties(prefix = "oss.qiniu.config")
@ApiModel(value = "七牛云配置")
public class QiniuConfig {

    @ApiModelProperty(value = "公钥")
    private String accessKey;

    @ApiModelProperty(value = "私钥")
    private String secretKey;

    @ApiModelProperty(value = "是否初始化")
    private boolean initialization=false;

    @ApiModelProperty(value = "空间名称")
    private String bucket;

    @ApiModelProperty(value = "回调地址")
    private String callbackUrl;

    @ApiModelProperty(value = "URL，域名，IPv4地址或IPv6地址")
    private String domainOfBucket;

    public QiniuConfig() {

    }

    public QiniuConfig(String accessKey, String secretKey, String bucket, String callbackUrl, String domainOfBucket) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
        this.callbackUrl = callbackUrl;
        this.domainOfBucket = domainOfBucket;
    }

}
