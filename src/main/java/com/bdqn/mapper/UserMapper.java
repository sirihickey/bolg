package com.bdqn.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdqn.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bdqn.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author yjz
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-02-12 16:14:22
* @Entity com.bdqn.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

	IPage<Map> selectAllUser(IPage<Map> page, @Param("portalVo") PortalVo portalVo);
}




