package org.example.player.controller;


import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.player.config.log.PlayerLogConvert;
import org.example.pojo.CommonResult;
import org.example.service.notice.NoticeService;
import org.example.service.system.dict.DictTypeService;
import org.example.vo.system.dict.vo.DictDataVO;
import org.example.vo.system.dict.vo.DictReq;
import org.example.vo.system.notice.NoticeRespVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.example.enums.DictTypeConstants.*;
import static org.example.exception.util.ServiceExceptionUtil.exception;

@Slf4j
@RestController
@RequestMapping("/player/common")
public class CommonController {


    //    @Value("${project.upload.fileSubfix:'.png'}")
//    private String fiileSubfix;
    @Resource
    private DictTypeService dictTypeService;
    @Resource
    private NoticeService noticeService;
    private final List<String> QA_TYPES = Arrays.asList(QA_DEFAULT_TYPE, QA_DEPOSIT_TYPE, QA_WITHDRAW_TYPE, QA_PASSWORD_TYPE);
    private final List<String> CUSTOMER_CONTACTS = Collections.singletonList(CUSTOMER_CONTACT);

    /**
     * 获取系统级别基础数据配置
     *
     * @param dictReq
     * @return
     */
    @TLogAspectExt(str = "获得系统级别常量", moduleName = "Common", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getDictData")
    public CommonResult<List<DictDataVO>> getDictData(@RequestBody DictReq dictReq) {
        List<DictDataVO> dictDataVOS = dictTypeService.getDictListByType(dictReq);
        return CommonResult.success(dictDataVOS);
    }


    /**
     * 获取系统公告
     */
    @TLogAspectExt(str = "获得系统公告", moduleName = "Common", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getSystemNotice")
    public CommonResult<List<NoticeRespVO>> getSystemNotice() {
        List<NoticeRespVO> noticeRespVOS = noticeService.getNoticeList();
        return CommonResult.success(noticeRespVOS);
    }

    /**
     * 获取qa列表
     *
     * @param dictReq
     * @return
     */
    @TLogAspectExt(str = "获取qa列表", moduleName = "Common", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getQAList")
    public CommonResult<List<DictDataVO>> getQAList(@RequestBody DictReq dictReq) {
        if (CollectionUtil.isEmpty(dictReq.getDictCodes()) || !new HashSet<>(QA_TYPES).containsAll(dictReq.getDictCodes())) {
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }
        List<DictDataVO> dictDataVOS = dictTypeService.getDictListByType(dictReq);
        return CommonResult.success(dictDataVOS);
    }

    /**
     * 获取客服联系方式 客服联系方式使用；分别是qq;skype;telegram 例如：111,112;222;333,逗号表示多个相同类型账号
     *
     * @param dictReq
     * @return
     */
    @TLogAspectExt(str = "获取客服联系方式", moduleName = "Common", type = OperateTypeEnum.GET, convert = PlayerLogConvert.class)
    @PostMapping("/getCustomerContact")
    public CommonResult<List<DictDataVO>> getCustomerContact(@RequestBody DictReq dictReq) {
        if (CollectionUtil.isEmpty(dictReq.getDictCodes()) || !new HashSet<>(CUSTOMER_CONTACTS).containsAll(dictReq.getDictCodes())) {
            throw exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }
        List<DictDataVO> dictDataVOS = dictTypeService.getDictListByType(dictReq);
        return CommonResult.success(dictDataVOS);
    }


}
