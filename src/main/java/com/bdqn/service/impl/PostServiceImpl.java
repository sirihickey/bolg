package com.bdqn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.pojo.Post;
import com.bdqn.pojo.vo.PortalVo;
import com.bdqn.service.PostService;
import com.bdqn.mapper.PostMapper;
import com.bdqn.utils.JwtHelper;
import com.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author yjz
* @description 针对表【post】的数据库操作Service实现
* @createDate 2024-02-12 16:14:22
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private PostMapper postMapper;

	/**
	 * 分页查询文章内容
	 * @param portalVo
	 * @return
	 */

	@Override
	public Result findNewsPage(PortalVo portalVo) {
		//Page -> 当前页数 页容量
		IPage<Map> page =new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
		postMapper.selectMyPage(page,portalVo);

		Map data =new HashMap();
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
	 *
	 * 根据id查看详情
	 */
	@Override
	public Result showPostDetail(Integer postid) {
		Map data =postMapper.queryDetailMap(postid);
		Map postMap =new HashMap();
		postMap.put("post",data);

		//修改阅读量
		Post post = new Post();
		post.setPostid((Integer) data.get("postid"));
		post.setVersion((Integer) data.get("version"));

		//阅读量+1
		post.setPostViews((Integer) data.get("postViews")+1);
		postMapper.updateById(post);

		return Result.ok(postMap);
	}

	/**
	 * 文章发布
	 * 登陆后才可以看见发布页面
	 */

	@Override
	public Result publish(Post post, String token) {
		//token查询用户id
		int userId = jwtHelper.getUserId(token).intValue();

		//数据装配
		post.setUserid(userId);
		post.setPostViews(0);
		post.setCreateTime(new Date());
		post.setUpdateTime(new Date());
		postMapper.insert(post);
		return Result.ok(null);
	}


	/**
	 * 文章修改
	 * @param post
	 * @return
	 */
	@Override
	public Result updateData(Post post) {
		Integer version = postMapper.selectById(post.getPostid()).getVersion();
		post.setVersion(version);//乐观锁
		post.setUpdateTime(new Date());

		postMapper.updateById(post);
		return Result.ok(null);
	}


	/**
	 * 管理员查看审核
	 */
	@Override
	public Result adminNewsPage(PortalVo portalVo) {
		// Page -> 当前页数，页容量
		IPage<Map> page = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
		postMapper.selectAdminPage(page,portalVo);
		Map data =new HashMap();
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
	 * 审核通过
	 */
	@Override
	public Result adminUpdata(Post post) {

		post.setStatus("APPROVED");
		postMapper.updateById(post);
		return Result.ok(null);
	}


}




