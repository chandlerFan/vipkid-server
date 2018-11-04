package com.quxueyuan.bean.valid.validator;


import com.quxueyuan.bean.valid.annotation.NotBlank;
import com.quxueyuan.bean.valid.util.NotBlankUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {
    private boolean required = false;
    /**
     * 初始化
     */
    @Override
    public void initialize(NotBlank constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    /**
     * 校验
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return NotBlankUtil.valid(value);
        }
        return true;
    }

}
