package com.itheima.annonation;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {StateValidation.class}
)

public @interface State {
    String message() default "state参数的值只能是”已发布“或者”草稿“";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
