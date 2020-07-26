package com.rlis.inspection.mapper;

import com.rlis.inspection.domain.RlInspecRequisitionBarcode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RlInspecRequisitionBarcodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(RlInspecRequisitionBarcode record);

    int insertSelective(RlInspecRequisitionBarcode record);

    RlInspecRequisitionBarcode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RlInspecRequisitionBarcode record);

    int updateByPrimaryKey(RlInspecRequisitionBarcode record);

    List<RlInspecRequisitionBarcode> selectByRequisitionId(@Param("requisitionId") String requisitionId);
}