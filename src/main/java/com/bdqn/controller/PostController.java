package com.bdqn.controller;

import com.bdqn.pojo.Post;
import com.bdqn.service.PostService;
import com.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("post")
@CrossOrigin
public class PostController {

	@Autowired
	private PostService postService;

	/**
	 * 文章发布
	 * 登陆后才可以看见发布页面
	 */

	@PostMapping("publish")
	public Result publish(@RequestBody Post post, @RequestHeader String token){
		Result result = postService.publish(post,token);

		return  result;
	}

	/**
	 * 文章修改数据回显
	 */

	@PostMapping("findPostByPostId")
	public Result findPostByPostId(Integer postid){
		Post post = postService.getById(postid);
		Map data = new HashMap<>();
		data.put("headline",post);
		return Result.ok(data);
	}

	/**
	 * 文章修改
	 */
	@PostMapping("update")
	public  Result update(@RequestBody Post post){
		Result result =postService.updateData(post);
		return result;
	}

	/**
	 * 文章删除
	 */

	@PostMapping("removeByPostId")
	public Result removeByPostId(Integer postid){
		postService.removeById(postid);
		return Result.ok(null);
	}


}
