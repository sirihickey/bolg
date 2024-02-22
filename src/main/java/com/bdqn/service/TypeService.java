package com.bdqn.service;

import com.bdqn.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdqn.utils.Result;

/**
* @author yjz
* @description 针对表【type】的数据库操作Service
* @createDate 2024-02-12 16:14:22
*/
public interface TypeService extends IService<Type> {

	/**
	 * 分类别查询
	 * @return
	 */
	Result findAllTypes();
}
