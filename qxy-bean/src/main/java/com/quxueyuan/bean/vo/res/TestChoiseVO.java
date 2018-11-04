package com.quxueyuan.bean.vo.res;

import com.quxueyuan.bean.constants.Constants;
import lombok.Data;

@Data
public class TestChoiseVO {
	
	private Integer testchoiseId;
    private String  answer;
    private Integer  courseid;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer sort;
    private String question;
    private String audio;
    private String description;
    private String type= Constants.TYPE_TEST_CHOISE;
}
