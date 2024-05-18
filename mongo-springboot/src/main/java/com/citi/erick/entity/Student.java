package com.citi.erick.entity;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*以实体类为表名*/
@Data
@Document
public class Student implements Serializable {

    private static final long serialVersionUID = -3258839839160856613L;

    /*映射数据库的主键*/
    @Id
    private String id;

    private String name;
    private int age;
    private List<String> hobby;
    private boolean status;
    private Date createTime;
    private Date updateTime;
}
