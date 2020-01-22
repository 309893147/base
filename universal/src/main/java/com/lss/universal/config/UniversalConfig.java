package com.lss.universal.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "universal.config")
@ApiModel(value = "通用配置")
public class UniversalConfig {

    @ApiModelProperty(value = "打包上线请求路径")
    private String packageAndGoOnline="/universal/packageAndGoOnline";

    @ApiModelProperty(value = "打包上线管理端PCzip文件解压地址")
    private String unzipManagerpcUrl="/home/app/manager";

    @ApiModelProperty(value = "打包上线前端PCzip文件解压地址")
    private String unzipApppcUrl="/home/app/pc";

    @ApiModelProperty(value = "打包上线zip上传地址(用于管理版本)")
    private String uploadZipUrl="/home/app/update";

    @ApiModelProperty(value = "上传账号")
    private String username = "admin";

    @ApiModelProperty(value = "上传密码")
    private String password = "cq1080";

    @ApiModelProperty(value = "是否初始化默认关闭")
    private boolean initialization=false;

    public UniversalConfig() {

    }

    public UniversalConfig(String packageAndGoOnline, String unzipManagerpcUrl, String unzipApppcUrl, String uploadZipUrl, String username, String password, boolean initialization) {
        this.packageAndGoOnline = packageAndGoOnline;
        this.unzipManagerpcUrl = unzipManagerpcUrl;
        this.unzipApppcUrl = unzipApppcUrl;
        this.uploadZipUrl = uploadZipUrl;
        this.username = username;
        this.password = password;
        this.initialization = initialization;
    }
}
