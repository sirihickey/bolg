package com.bdqn.service;

import com.bdqn.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.utils.Result;

/**
* @author yjz
* @description 针对表【comment】的数据库操作Service
* @createDate 2024-02-13 14:54:52
*/
public interface CommentService extends IService<Comment> {


	/**
	 * 根据文章id查询所有评论
	 * @param postid
	 * @return
	 */
	Result findAllComment(Integer postid);

	/**
	 * 查询一级评论
	 * @param postid
	 * @return
	 */
	Result findAllFather(Integer postid);

	/**
	 * 查询二级评论
	 * @param postid
	 * @return
	 */
	Result findAllTwo(Integer postid);



}
