package com.quxueyuan.bean.vo.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserParam implements Serializable {
	
    private Integer uid;
  
    private String name;
    
    private String pic;
    
    private Integer sex;
   
   
}
