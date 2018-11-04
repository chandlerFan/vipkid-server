package com.quxueyuan.bean.vo.res;

import com.quxueyuan.bean.constants.Constants;
import lombok.Data;

@Data
public class TestReciteVO {
	
	private Integer testreciteId;
    private String answer;
    private Integer courseid;
    private Integer sort;
    private String question;
    private String audioQuestion;
    private String description;
    private String type= Constants.TYPE_TEST_RECITE;

    //
    private String recordingAdd;
	
}
