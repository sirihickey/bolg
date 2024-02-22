package com.bdqn.controller;

import com.bdqn.pojo.User;
import com.bdqn.pojo.vo.PortalVo;
import com.bdqn.service.UserService;
import com.bdqn.utils.JwtHelper;
import com.bdqn.utils.Result;
import com.bdqn.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtHelper jwtHelper;

	//登录
	@PostMapping("login")
	public Result login(@RequestBody User user){
		Result result = userService.login(user);
		return result;
	}



	//发布文章的时候检查是否登录
	@GetMapping("checkLogin")
	public Result checkLogin(@RequestHeader String token){
		boolean expiration = jwtHelper.isExpiration(token);
		if (expiration) {
			//已经过期
			return  Result.build(null, ResultCodeEnum.NOTLOGIN);
		}
		return Result.ok(null);
	}

	/**
	 * 获取用户数据
	 * @param token
	 * @return
	 */

	@GetMapping("getUser")
	public Result getUser(@RequestHeader String token){
		 Result result = userService.getUser(token);
		 return  result;
	}

	/**
	 * 注册判断
	 */

	@PostMapping("checkUserName")
	public Result checkUserName(String username){
		Result result = userService.checkUserName(username);
		return  result;
	}

	/**
	 * 注册
	 */
	@PostMapping("regist")
	public  Result regist(@RequestBody User user){
		Result result = userService.regist(user);
		return result;
	}

	/**
	 * 查询所有用户
	 */
	@PostMapping("findAllUser")
	public Result findAllUser(@RequestBody PortalVo portalVo){
		Result result = userService.findAllUser(portalVo);
		return result;
	}

	@PostMapping("modifyPwd")
	public Result modifyPwd(@RequestBody User user){
		Result result = userService.modifyPwd(user);
		return result;

	}
}
