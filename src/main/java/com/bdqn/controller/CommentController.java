package com.bdqn.controller;



import com.bdqn.service.CommentService;
import com.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
@CrossOrigin

public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * 根据文章id查询所有评论
	 * @param postid
	 * @return
	 */
	@GetMapping("findAllComment")
	public Result findAllComment(Integer postid){
		Result result = commentService.findAllComment(postid);
		return  result;
	}

	/**
	 * 查询所有的一级评论
	 */
	@GetMapping("findAllFather")
	public  Result findAllFather(Integer postid){
		Result result = commentService.findAllFather(postid);
		return  result;
	}

	/**
	 * 查询所有二级评论
	 */

	@GetMapping("findAllTwo")
	public  Result findAllTwo(Integer postid){
		Result result = commentService.findAllTwo(postid);
		return  result;
	}



}
