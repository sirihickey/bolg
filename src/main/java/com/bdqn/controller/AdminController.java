package com.bdqn.controller;

import com.bdqn.pojo.Post;
import com.bdqn.pojo.vo.PortalVo;
import com.bdqn.service.PostService;
import com.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private PostService postService;

	/**
	 * 管理员查看审核
	 */
	@PostMapping("adminNewsPage")
	public Result adminNewsPage(@RequestBody PortalVo portalVo){
		Result result = postService.adminNewsPage(portalVo);
		return  result;
	}

	/**
	 * 审核通过
	 */
	@PostMapping("adminUpdata")
	public  Result adminUpdata(@RequestBody Post post){
		Result result = postService.adminUpdata(post);
		return result;
	}

	/**
	 * 审核失败
	 */
	@PostMapping("removeAdminPostId")
	public Result removeAdminPostId(Integer postid){
		postService.removeById(postid);
		return Result.ok("删除成功");
	}


}
