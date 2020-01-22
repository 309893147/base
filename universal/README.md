# 快速打包上传到服务器并解压

## usage 用法

### 添加maven依赖
```xml
<dependency>
    <groupId>com.cq1080</groupId>
    <artifactId>universal</artifactId>
    <version>${revision}</version>
</dependency>
```
### 项目中使用
#### 配置文件
```properties
properties文件
# false关闭 true开启
universal.config.initialization=false
# 打包上线请求路径  无法改动改了也没有用
universal.config.package-and-go-online=/universal/packageAndGoOnline
# 打包上线zip,jar上传地址(用于管理版本)
universal.config.upload-zip-url=/home/app/update
# 管理端PCzip文件解压地址
universal.config.unzip-managerpc-url=/home/app/manager
# 前端PCzip文件解压地址
universal.config.unzip-apppc-url=/home/app/pc
# 上传账号
universal.config.password=admin
# 上传密码
universal.config.username=cq1080
# 显示的均是默认值 除了请求路径 其他均可以修改
```
#### 项目实践
启动项目直接访问项目的 /universal/uploadHtml 路径就可以上传文件了（注意：zip文件不能有目录)
