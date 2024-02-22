package com.bdqn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.pojo.Role;
import com.bdqn.service.RoleService;
import com.bdqn.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author yjz
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-02-12 16:14:22
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




