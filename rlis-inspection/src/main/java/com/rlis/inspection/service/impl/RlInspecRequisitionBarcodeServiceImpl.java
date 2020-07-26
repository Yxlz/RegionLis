package com.rlis.inspection.service.impl;

import com.rlis.inspection.domain.RlInspecRequisitionBarcode;
import com.rlis.inspection.mapper.RlInspecRequisitionBarcodeMapper;
import com.rlis.inspection.service.IRlInspecRequisitionBarcodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 条码标业务处理层
 * @Author: tangxiaohui
 * @CreateDate: 2020/7/25 0025 17:48
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @Version: 1.0
 */
@Service
public class RlInspecRequisitionBarcodeServiceImpl implements IRlInspecRequisitionBarcodeService {

    @Resource
    RlInspecRequisitionBarcodeMapper barcodeMapper;

    /**
     * @param requisitionId
     * @return: java.util.List<com.rlis.inspection.domain.RlInspecRequisitionBarcode>
     * @description: 通过申请单ID查询条码
     * @Param requisitionId:
     * @date: 2020/7/25 0025 17:47
     */
    @Override
    public List<RlInspecRequisitionBarcode> getBarcodesByRequisitionId(String requisitionId) {
        return barcodeMapper.selectByRequisitionId(requisitionId);
    }
}
