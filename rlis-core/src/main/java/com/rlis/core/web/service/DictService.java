package com.rlis.core.web.service;

import com.rlis.system.domain.RlSysDictData;
import com.rlis.system.service.IRlSysDictDataService;
import com.rlis.system.service.IRlSysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DictService
 * @Description: i首创 html调用 thymeleaf 实现字典读取
 * @Author tangxiaohui
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @DateTime 2020/7/10 10:38
 */
@Service("dict")
public class DictService
{
    @Autowired
    private IRlSysDictTypeService dictTypeService;

    @Autowired
    private IRlSysDictDataService dictDataService;

    /**
     * 根据字典类型查询字典数据信息
     * 
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<RlSysDictData> getType(String dictType)
    {
        return dictTypeService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue)
    {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }
}
