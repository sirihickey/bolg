package com.bdqn.service;

import com.bdqn.pojo.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.pojo.vo.PortalVo;
import com.bdqn.utils.Result;

/**
* @author yjz
* @description 针对表【post】的数据库操作Service
* @createDate 2024-02-12 16:14:22
*/
public interface PostService extends IService<Post> {


	/**
	 * 分页查询文章内容
	 * @param portalVo
	 * @return
	 */
	Result findNewsPage(PortalVo portalVo);


	/**
	 *
	 * 根据id查看详情
	 */
	Result showPostDetail(Integer postid);

	/**
	 * 文章发布
	 * 登陆后才可以看见发布页面
	 */

	Result publish(Post post, String token);

	/**
	 * 文章修改
	 * @param post
	 * @return
	 */
	Result updateData(Post post);



	/**
	 * 管理员查看审核
	 */

	Result adminNewsPage(PortalVo portalVo);

	/**
	 * 审核通过
	 */
	Result adminUpdata(Post post);
}
