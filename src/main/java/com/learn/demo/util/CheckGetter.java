package com.learn.demo.util;

/**
 * @author sxy
 * @version 1.0
 * @className CheckGetter
 * @date 2020/11/3 14:39
 */

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.SOURCE)
public @interface CheckGetter {
}