# 1080 Java 基础库

### 如何使用这些库

配置maven的settings.xml文件，加入以下配置
```xml
<profiles>
 <profile>
  <id>dev</id>
  <repositories>
   <repository>
     <id>cq1080</id>
     <url>http://nexus.cq1080.com/repository/teneighty/</url>
     <releases>
        <enabled>true</enabled>
     </releases>
     <snapshots>
        <enabled>true</enabled>
     </snapshots>
   </repository>
 </repositories>
 </profile>
 </profiles>
 <activeProfiles>
   <activeProfile>dev</activeProfile>
 </activeProfiles>
```

### [meta](meta)

一个用来获取类元信息，方便前端表格展示的工具

### [oss](oss)

OSS第三方集成，目前暂时只支持Minio OSS、七牛云 OSS

### [config](config)

项目通用配置工具

### [universal](universal)

项目快速打包上线工具