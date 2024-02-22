package com.bdqn.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName role
 */
@TableName(value ="role")
@Data
public class Role implements Serializable {
    @TableId
    private Integer roleid;

    private String rolename;

    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
