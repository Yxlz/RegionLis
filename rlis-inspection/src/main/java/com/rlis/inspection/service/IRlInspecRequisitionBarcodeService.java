package com.rlis.inspection.service;

import com.rlis.inspection.domain.RlInspecRequisitionBarcode;

import java.util.List;

/**
 * @Description: 条码标服务
 * @Author: tangxiaohui
 * @CreateDate: 2020/7/25 0025 17:46
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @Version: 1.0
 */
public interface IRlInspecRequisitionBarcodeService {
    /**
     * @return:  java.util.List<com.rlis.inspection.domain.RlInspecRequisitionBarcode>
     * @description: 通过申请单ID查询条码
     * @Param requisitionId:
     * @date: 2020/7/25 0025 17:47
     */
    List<RlInspecRequisitionBarcode> getBarcodesByRequisitionId(String requisitionId);
}
