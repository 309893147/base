# OSS第三方集成

## usage 用法

### 添加maven依赖
```xml
<dependency>
    <groupId>com.cq1080</groupId>
    <artifactId>oss</artifactId>
    <version>${revision}</version>
</dependency>
```

### 项目中使用
#### 配置文件
##### 1.Minio OSS
```properties
properties文件
oss.minio.config.initialization=true
oss.minio.config.access-key=公钥
oss.minio.config.secret-key=密钥
oss.minio.config.bucket-name=空间名称
oss.minio.config.endpoint=域名，IPv4地址或IPv6地址
```
##### 2.Qiniu
```properties
properties文件
oss.qiniu.config.initialization=true
oss.qiniu.config.access-key=公钥
oss.qiniu.config.secret-key=密钥
oss.qiniu.config.bucket-name=空间名称
oss.qiniu.config.domain-of-bucket=域名，IPv4地址或IPv6地址
oss.qiniu.config.callback-url=回调地址
```
#### 项目实践
##### （一） 注入
###### 1.Minio OSS
```java
import com.cq1080.oss.config.MinioConfig;
import io.minio.MinioClient;
import javax.annotation.Resource;
public class demo{
    @Resource(name = "MinioOssService")
    private OssService<MinioConfig,MinioClient> MinioOssService;
}
```
###### 2.七牛云 OSS
```java
import com.cq1080.oss.config.QiniuConfig;
import com.qiniu.util.Auth;
import javax.annotation.Resource;
public class demo{
    @Resource(name = "QiniuOssService")
    private OssService<QiniuConfig,Auth> MinioOssService;
}
```
回调接收对应实体类及接收地址示例（只要接收就好，不需要返回）
```java
public class demo{
    @ApiModelProperty(value = "获得文件保存在空间中的资源名")
    private String qiniuKey;

    @ApiModelProperty(value = "文件上传成功后的HTTPETag")
    private String etag;

    @ApiModelProperty(value = "获得上传的目标空间名")
    private String bucket;

    @ApiModelProperty(value = "资源尺寸,单位为字节")
    private String fsize;

    @ApiModelProperty(value = "资源类型")
    private String mimeType;
}

@PostMapping("callback")
@ApiOperation(value = "获取七牛云回调")
public void qiniuCallback(@RequestBody demo fileDetail){
    universalService.insertFileDetail(fileDetail);
}
```
##### （二）内置方法
###### 1.修改配置并重新初始化
```java
    /**
     * 修改配置并初始化
     * @param config
     */
    void setConfig(T config);
```
###### 2.获取配置
```java
    /**
     * 基本配置
     * @return
     */
    T getConfig();
```
###### 3.获取原始客户端(可以根据对应的第三方官方文档扩展)
```java
    /**
     * oss客户端
     * @return
     */
    K getClient();
```
###### 4.获取前端上传文件所需token
```java
    /**
     * 前端上传文件所需token
     * @param fileName 文件名称
     * @param expiry token过期时间
     * @return
     */
    String uploadFileToken(String fileName, Integer expiry);
```
###### 5.服务器文件直传
```java
    /**
     * 客户端上传文件
     * @param fileName 文件名称
     * @param file 文件
     */
    void uploadFile(String fileName, MultipartFile file);
```
###### 6.空间内文件访问路径
```java
    /**
     * 空间内文件访问路径
     * @param fileName 文件名称
     * @param expiry 访问路径过期时间
     * @return
     */
    String getFileUrl(String fileName, Integer expiry);
```
