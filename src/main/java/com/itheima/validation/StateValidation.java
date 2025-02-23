package com.itheima.validation;

import com.itheima.annonation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

                                        //<给哪个注解提供校验规则,校验的数据类型>
public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (value==null){
            return false;//值为空则校验不通过
        }
        if (value.equals("已发布") || value.equals("草稿")){
            return true;//校验通过
        }
        return false;
    }
}
