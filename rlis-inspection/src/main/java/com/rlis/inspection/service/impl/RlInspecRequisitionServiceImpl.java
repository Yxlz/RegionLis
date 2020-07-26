package com.rlis.inspection.service.impl;

import com.rlis.common.constant.CenterOrgConstants;
import com.rlis.common.core.text.Convert;
import com.rlis.common.exception.barcode.GetLisBarCodeFailureException;
import com.rlis.common.utils.Arith;
import com.rlis.common.utils.DateUtils;
import com.rlis.common.utils.StringUtils;
import com.rlis.common.utils.UUIDGenerator;
import com.rlis.common.utils.http.HttpUtils;
import com.rlis.common.utils.xml.XmlUtil;
import com.rlis.inspection.domain.RlInspecRequisition;
import com.rlis.inspection.domain.RlInspecRequisitionBarcode;
import com.rlis.inspection.domain.RlInspecRequisitionItems;
import com.rlis.inspection.mapper.RlInspecRequisitionBarcodeMapper;
import com.rlis.inspection.mapper.RlInspecRequisitionMapper;
import com.rlis.inspection.model.barcode.*;
import com.rlis.inspection.service.IRlInspecRequisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 检验申请Service业务层处理
 *
 * @author tangxiaohui
 * @date 2020-07-23
 */
@Service
public class RlInspecRequisitionServiceImpl implements IRlInspecRequisitionService {

    private static final Logger logger = LoggerFactory.getLogger(RlInspecRequisitionServiceImpl.class);

    @Resource
    private RlInspecRequisitionMapper rlInspecRequisitionMapper;

    @Resource
    private RlInspecRequisitionBarcodeMapper rlInspecRequisitionBarcodeMapper;

    /**
     * 查询检验申请
     *
     * @param id 检验申请ID
     * @return 检验申请
     */
    @Override
    public RlInspecRequisition selectRlInspecRequisitionById(String id) {
        return rlInspecRequisitionMapper.selectRlInspecRequisitionById(id);
    }

    /**
     * 查询检验申请列表
     *
     * @param rlInspecRequisition 检验申请
     * @return 检验申请
     */
    @Override
    public List<RlInspecRequisition> selectRlInspecRequisitionList(RlInspecRequisition rlInspecRequisition) {
        return rlInspecRequisitionMapper.selectRlInspecRequisitionList(rlInspecRequisition);
    }

    /**
     * 新增检验申请
     *
     * @param rlInspecRequisition 检验申请
     * @return 结果
     */
    @Transactional
    @Override
    public int insertRlInspecRequisition(RlInspecRequisition rlInspecRequisition) {
        rlInspecRequisition.setServiceOrgCode(CenterOrgConstants.centerOrgId);
        rlInspecRequisition.setServiceOrgName(CenterOrgConstants.centerOrgName);
        List<RlInspecRequisitionItems> items = rlInspecRequisition.getRlInspecRequisitionItemsList();
        BigDecimal amount = new BigDecimal(0.00);
        for (RlInspecRequisitionItems item : items) {
            amount = Arith.add(amount, item.getItemPrice());
        }
        rlInspecRequisition.setAppAmount(amount);
        int rows = rlInspecRequisitionMapper.insertRlInspecRequisition(rlInspecRequisition);
        insertRlInspecRequisitionItems(rlInspecRequisition);
        return rows;
    }

    /**
     * 修改检验申请
     *
     * @param rlInspecRequisition 检验申请
     * @return 结果
     */
    @Transactional
    @Override
    public int updateRlInspecRequisition(RlInspecRequisition rlInspecRequisition) {
        List<RlInspecRequisitionItems> items = rlInspecRequisition.getRlInspecRequisitionItemsList();
        BigDecimal amount = new BigDecimal(0.00);
        for (RlInspecRequisitionItems item : items) {
            amount = Arith.add(amount, item.getItemPrice());
        }
        rlInspecRequisition.setAppAmount(amount);
        rlInspecRequisitionMapper.deleteRlInspecRequisitionItemsByRequisitionId(rlInspecRequisition.getId());
        insertRlInspecRequisitionItems(rlInspecRequisition);
        return rlInspecRequisitionMapper.updateRlInspecRequisition(rlInspecRequisition);
    }

    /**
     * 提交检验申请信息（打院内条码）
     *
     * @param rlInspecRequisition 检验申请
     */
    @Override
    public void commitRequisition(RlInspecRequisition rlInspecRequisition) throws Exception {
        Request request = parseRequest(rlInspecRequisition);

        String requestXml = XmlUtil.convertBeanToXml(request, true);
        requestXml = requestXml.replace("Dob", "DOB");
        logger.info("向杏和lis发起获取条码调用xml=" + requestXml);
        Response response;
        String url = "http://172.16.140.21:8090/Strutservice/getLisPrintData.action?xml=requestXML";
        requestXml = URLEncoder.encode(requestXml, "GBK");
        url = url.replace("requestXML", requestXml);
        String responseXml = HttpUtils.sendPost(url, requestXml);
        logger.info("杏和lis返回xml=" + responseXml);
        if (StringUtils.isEmpty(responseXml)) {
            throw new GetLisBarCodeFailureException("杏和接口訪問失敗");
        }
        response = XmlUtil.convertXmlToBean(Response.class, responseXml.substring(responseXml.indexOf(">") + 1));
        if (!response.isSuccess()) {
            throw new GetLisBarCodeFailureException(response.getErrorMsg());
        }
        RlInspecRequisitionBarcode rlInspecRequisitionBarcode;
        for (String barcode : response.getSpecimenCode()) {
            rlInspecRequisitionBarcode = analysisBarCode(rlInspecRequisition, barcode);
            rlInspecRequisitionBarcodeMapper.insertSelective(rlInspecRequisitionBarcode);
        }
    }

    /**
     * 删除检验申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRlInspecRequisitionByIds(String ids) {
        rlInspecRequisitionMapper.deleteRlInspecRequisitionItemsByRequisitionIds(Convert.toStrArray(ids));
        return rlInspecRequisitionMapper.deleteRlInspecRequisitionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除检验申请信息
     *
     * @param id 检验申请ID
     * @return 结果
     */
    @Override
    public int deleteRlInspecRequisitionById(String id) {
        rlInspecRequisitionMapper.deleteRlInspecRequisitionItemsByRequisitionId(id);
        return rlInspecRequisitionMapper.deleteRlInspecRequisitionById(id);
    }

    /**
     * 新增检验申请项目信息
     *
     * @param rlInspecRequisition 检验申请对象
     */
    public void insertRlInspecRequisitionItems(RlInspecRequisition rlInspecRequisition) {
        List<RlInspecRequisitionItems> rlInspecRequisitionItemsList = rlInspecRequisition.getRlInspecRequisitionItemsList();
        String id = rlInspecRequisition.getId();
        if (StringUtils.isNotNull(rlInspecRequisitionItemsList)) {
            List<RlInspecRequisitionItems> list = new ArrayList<>();
            for (RlInspecRequisitionItems rlInspecRequisitionItems : rlInspecRequisitionItemsList) {
                rlInspecRequisitionItems.setId(UUID.randomUUID().toString().replace("-", ""));
                rlInspecRequisitionItems.setRequisitionId(id);
                list.add(rlInspecRequisitionItems);
            }
            if (list.size() > 0) {
                rlInspecRequisitionMapper.batchRlInspecRequisitionItems(list);
            }
        }
    }

    /**
     * 将数据库类型检验申请转换成webservice参数型申请bean
     *
     * @param rlInspecRequisition
     * @return
     */
    private Request parseRequest(RlInspecRequisition rlInspecRequisition) {
        if (CollectionUtils.isEmpty(rlInspecRequisition.getRlInspecRequisitionItemsList())) {
            throw new IllegalArgumentException("检验项目不能为空.");
        }
        Request request = new Request();

        request.setId(rlInspecRequisition.getPatientIdno());
        request.setPatName(rlInspecRequisition.getPatientName());
        request.setPatNo(rlInspecRequisition.getPatientId());
        request.setRowId(rlInspecRequisition.getPatientId());
        request.setSex(rlInspecRequisition.getPatientSex());
        request.setTelOffice(rlInspecRequisition.getPatientPhone());
        request.setDOB(DateUtils.getDateOfBirth(rlInspecRequisition.getPatientIdno()));
        request.setBirthDay(rlInspecRequisition.getPatientAge());
        request.setWorkUnit(rlInspecRequisition.getAppOrgName());
        //送检医院+电话
        request.setAddress(rlInspecRequisition.getAppOrgName() + "," + rlInspecRequisition.getPatientPhone());

        EpisodeInfo episodeInfo = new EpisodeInfo();
        Episode episode = new Episode();

        // episode.setEpisodeID(episodeID);// ???????
        // episode.setAdmDate(admDate);// 病人出生日期
        // episode.setAdmTime(admTime);// 病人出生时间

        //episode.setAdmLocCode(rlInspecRequisition.getAppOrganName());
        // episode.setAdmType(admType);// 病人类别
        //episode.setBed(rlInspecRequisition.getBedCode());// 病人床号
        episode.setEpisodeID(rlInspecRequisition.getPatientId());//病人唯一标识号

        List<OrdItem> items = new ArrayList<>();
        OrdItem oi;
        for (RlInspecRequisitionItems req : rlInspecRequisition.getRlInspecRequisitionItemsList()) {
            oi = new OrdItem();
            oi.setArcimCode(req.getItemCode());
            oi.setOrdId(rlInspecRequisition.getAppNo());// 检验项目医嘱ID
            oi.setSampleHospID(rlInspecRequisition.getAppOrgName());//送检机构
            items.add(oi);
        }

        episode.setOrders(items);
        //开单医生
        episode.setDoctorCode(rlInspecRequisition.getAppDocName());
        //诊断
        Diagnosis dia = new Diagnosis();
        List<Diagnosis> diaList = new ArrayList<>();
        dia.setDiagDesc(rlInspecRequisition.getPatientDiagnosis());
        diaList.add(dia);
        episode.setDiagnosis(diaList);

        episodeInfo.setEpisode(episode);
        request.setEpisodeInfo(episodeInfo);

        return request;
    }

    /**
     * 将webservice 返回条码数据装备成数据库型条码bean
     *
     * @param request
     * @param barcode
     * @return
     */
    private RlInspecRequisitionBarcode analysisBarCode(RlInspecRequisition request, String barcode) {
        String[] bcArr = barcode.split("\\|");
        if (bcArr.length != 11) {
            throw new IllegalArgumentException("杏和lis ws 返回<BarCode />节点错误，通过[|]拆分后数据不够11个.");
        }

        String[] libInfo = bcArr[1].split("\\^");

        RlInspecRequisitionBarcode lisBc = new RlInspecRequisitionBarcode();
        if (libInfo[8] == null || "".equals(libInfo[8])) {
            lisBc.setExcuteSection("异常");
        } else {
            lisBc.setExcuteSection(libInfo[8]);// 执行科室
        }
        lisBc.setId(UUIDGenerator.getUUID());
        lisBc.setRequisitionId(request.getId());
        lisBc.setItems(bcArr[3]);// 检验项目
        lisBc.setPatientCode(libInfo[2]);// 住院或门诊号
        lisBc.setPatientId(request.getPatientId());// 病人号
        lisBc.setPatientAge(request.getPatientAge());
        lisBc.setPatientName(request.getPatientName());
        lisBc.setPatientSex(request.getPatientSex());
        lisBc.setBarcode(bcArr[0]); // 条码号
        lisBc.setPrintTime(bcArr[9].substring(3));// 条码打印时间
        if (bcArr[7] != null && !bcArr[7].equals("")) {
            lisBc.setReportPlace(bcArr[7]);// 取报告地点
        }
        if (bcArr[4] != null && !bcArr[4].equals("")) {
            lisBc.setSampleCollectDate(bcArr[4]);//采样时间
        }
        if ((bcArr[5] != null && !bcArr[5].equals("")) || (bcArr[6] != null && !bcArr[6].equals(""))) {
            lisBc.setReportTime(bcArr[5] + "," + bcArr[6]);//取报告时间
        }
        if (bcArr[2] != null && !bcArr[2].equals("")) {
            lisBc.setSampleCollectAdvice(bcArr[2]);// 采样地点
        }
        return lisBc;
    }
}
