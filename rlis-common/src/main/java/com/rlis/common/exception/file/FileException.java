package com.rlis.common.exception.file;

import com.rlis.common.exception.base.BaseException;

/**
 * @ClassName: FileException
 * @Description: 文件信息异常类
 * @Author tangxiaohui
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @DateTime 2020/7/10 10:10
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
