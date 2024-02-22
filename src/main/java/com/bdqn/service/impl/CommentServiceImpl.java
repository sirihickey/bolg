package com.bdqn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.mapper.PostMapper;
import com.bdqn.pojo.Comment;
import com.bdqn.service.CommentService;
import com.bdqn.mapper.CommentMapper;
import com.bdqn.utils.Result;
import com.bdqn.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yjz
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-02-13 14:54:52
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private PostMapper postMapper;
	/**
	 * 根据id查询所有评论
	 * @param postid
	 * @return
	 */
	@Override
	public Result findAllComment(Integer postid) {
		List<Comment> comments = commentMapper.selectAllComment(postid);
		return Result.ok(comments);
	}

	/**
	 * 查询一级评论
	 * @param postid
	 * @return
	 */
	@Override
	public Result findAllFather(Integer postid) {
		List<Comment> comments = commentMapper.selectFatherComment(postid);
		return Result.ok(comments);
	}

	/**
	 * 查询二级评论
	 * @param postid
	 * @return
	 */
	@Override
	public Result findAllTwo(Integer postid) {
		List<Comment> comments = commentMapper.selectTwoComment(postid);
		return Result.ok(comments);
	}



}




