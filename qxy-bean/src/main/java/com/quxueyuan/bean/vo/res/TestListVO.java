package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.util.List;

@Data
public class TestListVO {

	private List<TestChoiseVO> choiseList;
	private List<TestReciteVO> reciteList;
}
