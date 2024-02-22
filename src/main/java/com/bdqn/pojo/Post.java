package com.bdqn.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    @TableId
    private Integer postid;

    private String title;

    private String article;

    private Integer postViews;

    private Date createTime;

    private Date updateTime;

    private Integer typeid;

    private Integer userid;

    private String status;

    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
