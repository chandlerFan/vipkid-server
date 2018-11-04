package com.quxueyuan.bean.vo.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
	
    private Integer uid;
  
    private String name;
    
    private String pic;
    
    private Integer sex;

   
}
