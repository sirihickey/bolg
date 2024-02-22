package com.bdqn.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.pojo.User;
import com.bdqn.pojo.vo.PortalVo;
import com.bdqn.service.UserService;
import com.bdqn.mapper.UserMapper;
import com.bdqn.utils.JwtHelper;
import com.bdqn.utils.MD5Util;
import com.bdqn.utils.Result;
import com.bdqn.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author yjz
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-02-12 16:14:22
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JwtHelper jwtHelper;
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@Override
	public Result login(User user) {
		// 根据账号查询数据库
		LambdaQueryWrapper<User> lambdaQueryWrapper =new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
		User loginUser = userMapper.selectOne(lambdaQueryWrapper);

		if (loginUser==null){
			return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
		}
		// 对比密码

		if (!StringUtils.isEmpty(user.getPassword())
				&& MD5Util.encrypt(user.getPassword()).equals(loginUser.getPassword())){
			//登录成功
			//根据用户id生成token
			String token = jwtHelper.createToken(Long.valueOf(loginUser.getUserid()));
			//将token封装到result返回
			Map data = new HashMap<>();
			data.put("token",token);
			return Result.ok(data);
		}
		//密码错误
		return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
	}

	/**
	 * 获取用户数据
	 * @param token
	 * @return
	 */
	@Override
	public Result getUser(String token) {
		//判断是否在有效期
		boolean expiration = jwtHelper.isExpiration(token); //expiration true表示为失效（过期），false表示有效
		if (expiration) {
			//表示失效，未登录
			return Result.build(null,ResultCodeEnum.NOTLOGIN);
		}
		int userId = jwtHelper.getUserId(token).intValue();
		User user = userMapper.selectById(userId);
		user.setPassword("");

		Map data=new HashMap();
		data.put("loginUser",user);

		return Result.ok(data);
	}

	/**
	 * 检查账号是否可用
	 * @param username
	 * @return
	 */
	@Override
	public Result checkUserName(String username) {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getUsername,username);
		Long count = userMapper.selectCount(lambdaQueryWrapper);

		//如果count为0证明账号还没有被注册
		if (count==0){
			return Result.ok(null);
		}
		return Result.build(null,ResultCodeEnum.USERNAME_USED);
	}

	/**
	 * 注册
	 * @param user
	 * @return
	 */
	@Override
	public Result regist(User user) {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
		Long count = userMapper.selectCount(lambdaQueryWrapper);

		//如果count为0证明账号还没有被注册
		if (count>0){
			return  Result.build(null,ResultCodeEnum.USERNAME_USED);
		}
		user.setPassword(MD5Util.encrypt(user.getPassword()));
		user.setRegistertime(new Date());
		userMapper.insert(user);
		return Result.ok(null);
	}

	/**
	 * 查询所有用户
	 * @param portalVo
	 * @return
	 */
	@Override
	public Result findAllUser(PortalVo portalVo) {
		// Page -> 当前页数 页容量
		IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
		userMapper.selectAllUser(page,portalVo);
		Map data = new HashMap();
		data.put("pageData",page.getRecords());
		data.put("pageNum",page.getCurrent());
		data.put("pageSize",page.getSize());
		data.put("totalPage",page.getPages());
		data.put("totalSize",page.getTotal());
		Map pageInfo = new HashMap();
		pageInfo.put("pageInfo",data);
		return Result.ok(pageInfo);
	}

	/**
	 * 修改密码的方法
	 * @return
	 */

	@Override
	public Result modifyPwd(User user) {
		Integer version = userMapper.selectById(user.getUserid()).getVersion();
		user.setVersion(version);
		user.setPassword(MD5Util.encrypt(user.getPassword()));
		userMapper.updateById(user);
		return Result.ok(null);
	}

}




