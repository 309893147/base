package com.lss.jpa.entity;

import com.lss.meta.annotation.ContentType;
import com.lss.meta.annotation.Meta;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
@Inheritance
@MappedSuperclass
public class BaseEntity implements Serializable {


    /**
     * 序列化id
     */
    private static final long serialVersionUID = 4125096758372084309L;

    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false,columnDefinition = "int(11) comment 'id自增'")
    private Integer id;


    @CreationTimestamp
    @ApiModelProperty(dataType = "java.lang.String",example = "2019-05-22 18:00:00",accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    @Column(updatable = false,columnDefinition = "datetime(0) comment '创建时间'")
    @Meta(displayName = "创建时间",type = ContentType.TIMESTAMP,searchable = true,displayInList = true)
    private Timestamp createTime;


    @UpdateTimestamp
    @ApiModelProperty(dataType = "java.lang.String",example = "2019-05-22 18:00:00",accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    @Column(columnDefinition = "datetime(0) comment '更新时间'")
    @Meta(displayName = "更新时间",type = ContentType.TIMESTAMP)
    private Timestamp updateTime;

    @ApiModelProperty(value = "0-删除 1-新建")
    @Column(insertable = false,columnDefinition = "int default 1")
    private Integer presenceStatus;


}
