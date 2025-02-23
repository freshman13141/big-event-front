package com.itheima.annonation;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

//自定义校验规则。模仿官方写法
@Documented//元注解 标识可以抽取到帮助文档
@Target({ ElementType.FIELD})//元注解 表示该注解可以用到哪些位置 保留属性
@Retention(RetentionPolicy.RUNTIME)//元注解 标识到哪个阶段保留 运行时
@Constraint(
        /*指定提供校验规则的类
        * validation包下的Statement类实现ConstraintValidator
        * 重新isValid方法
        * */
        validatedBy = {StateValidation.class}
)

public @interface State {
    //提供校验失败后的提示信息
    String message() default "state参数的值只能是”已发布“或者”草稿“";
    //指定分组
    Class<?>[] groups() default {};
    //负载 获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
