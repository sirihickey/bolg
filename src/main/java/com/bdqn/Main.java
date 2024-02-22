package com.bdqn;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.bdqn.mapper")
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class,args);
	}

	@Bean
	public MybatisPlusInterceptor plusInterceptor(){
		MybatisPlusInterceptor mybatisPlusInterceptor=new MybatisPlusInterceptor();
		mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//分页
		mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());//乐观锁
		mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());//防全局修改和删除

		return mybatisPlusInterceptor;
	}





}
