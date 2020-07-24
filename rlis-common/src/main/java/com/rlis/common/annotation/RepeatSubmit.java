package com.rlis.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: RepeatSubmit
 * @Description: 自定义注解防止表单重复提交
 * @Author tangxiaohui
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @DateTime 2020/7/10 10:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit
{

}