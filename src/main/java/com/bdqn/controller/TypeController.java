package com.bdqn.controller;

import com.bdqn.pojo.vo.PortalVo;
import com.bdqn.service.PostService;
import com.bdqn.service.TypeService;
import com.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("type")
@CrossOrigin
public class TypeController {

	@Autowired
	private PostService postService;

	@Autowired
	private TypeService typeService;

	/**
	 * 分类别查询
	 * @return
	 */
	@GetMapping("/findAllTypes")
	public Result findAllTypes(){
		Result result = typeService.findAllTypes();
		return  result;
	}

	/**
	 * 分页查询文章内容
	 * @param portalVo
	 * @return
	 */
	@PostMapping("findNewsPage")
	public Result findNewsPage(@RequestBody PortalVo portalVo){
		Result result=postService.findNewsPage(portalVo);
		return  result;
	}

	/**
	 *
	 * 根据id查看详情
	 */
	@PostMapping("showPostDetail")
	public Result showPostDetail(Integer postid){
		Result result = postService.showPostDetail(postid);
		return  result;
	}

}
