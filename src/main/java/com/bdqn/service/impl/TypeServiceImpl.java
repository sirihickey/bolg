package com.bdqn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.pojo.Type;
import com.bdqn.service.TypeService;
import com.bdqn.mapper.TypeMapper;
import com.bdqn.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yjz
* @description 针对表【type】的数据库操作Service实现
* @createDate 2024-02-12 16:14:22
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

	@Autowired
	private  TypeMapper typeMapper;

	@Override
	public Result findAllTypes() {

		List<Type> types =typeMapper.selectList(null);
		return Result.ok(types);
	}
}




