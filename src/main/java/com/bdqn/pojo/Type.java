package com.bdqn.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName type
 */
@TableName(value ="type")
@Data
public class Type implements Serializable {
    @TableId
    private Integer typeid;

    private String typename;

    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
