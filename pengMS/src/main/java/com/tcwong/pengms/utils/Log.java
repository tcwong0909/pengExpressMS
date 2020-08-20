package com.tcwong.pengms.utils;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
     String behavior() default "";
     LogdicType fkTypeid();
}
