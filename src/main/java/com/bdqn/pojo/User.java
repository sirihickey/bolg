package com.bdqn.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    @TableId
    private Integer userid;

    private String username;

    private String password;

    private String nickname;

    private Date registertime;

    private Integer roleid;

    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
