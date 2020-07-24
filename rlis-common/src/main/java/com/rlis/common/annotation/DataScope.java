package com.rlis.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: DataScope
 * @Description: 数据权限过滤注解
 * @Author tangxiaohui
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @DateTime 2020/7/10 9:59
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 机构表的别名
     */
    String orgAlias() default "";

    /**
     * 用户表的别名
     */
    String userAlias() default "";

}
