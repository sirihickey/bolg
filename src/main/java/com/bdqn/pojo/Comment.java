package com.bdqn.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    @TableId
    private Integer comid;

    private String comarticel;

    private Integer postid;

    private Integer userid;

    private Date comtime;

    private Integer fatherid;

    private Integer istwo;

    private Integer twoid;

    private Integer isDeleted;

    private Integer toDelete;

    @Version
    private Integer version;

    private static final long serialVersionUID = 1L;
}
