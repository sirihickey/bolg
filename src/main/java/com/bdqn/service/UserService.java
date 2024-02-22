package com.bdqn.service;

import com.bdqn.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.pojo.vo.PortalVo;
import com.bdqn.utils.Result;

/**
* @author yjz
* @description 针对表【user】的数据库操作Service
* @createDate 2024-02-12 16:14:22
*/
public interface UserService extends IService<User> {

	/**
	 * 登录
	 * @param user
	 * @return
	 */
	Result login(User user);


	/**
	 * 获取用户数据
	 * @param token
	 * @return
	 */
	Result getUser(String token);

	/**
	 * 检查账号是否可用
	 * @param username
	 * @return
	 */
	Result checkUserName(String username);

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	Result regist(User user);

	/**
	 * 查询所有的用户
	 * @param portalVo
	 * @return
	 */
	Result findAllUser(PortalVo portalVo);

	/**
	 * 修改密码的方法
	 * @return
	 */
	Result modifyPwd(User user);
}
