package com.bdqn.pojo.vo;

import lombok.Data;

@Data
public class PortalVo {
	private String keyWords;
	private int typeid = 0;
	private int pageNum=1;
	private int pageSize=10;
}
