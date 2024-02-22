package com.bdqn.mapper;

import com.bdqn.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author yjz
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2024-02-13 14:54:52
* @Entity com.bdqn.pojo.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {

	/**
	 * 查询全部的评论
	 * @param postid
	 * @return
	 */
	List<Comment> selectAllComment(Integer postid);

	/**
	 * 查询一级评论
	 * @param postid
	 * @return
	 */
	List<Comment> selectFatherComment(Integer postid);

	/**
	 * 查询二级评论
	 * @param postid
	 * @return
	 */
	List<Comment> selectTwoComment(Integer postid);
}




