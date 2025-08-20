package org.example.service.system.dict;


import org.example.po.system.DictTypeDO;
import org.example.pojo.PageResult;
import org.example.vo.system.dict.vo.DictDataVO;
import org.example.vo.system.dict.vo.DictReq;
import org.example.vo.system.dict.vo.type.DictTypeCreateReqVO;
import org.example.vo.system.dict.vo.type.DictTypeExportReqVO;
import org.example.vo.system.dict.vo.type.DictTypePageReqVO;
import org.example.vo.system.dict.vo.type.DictTypeUpdateReqVO;

import java.util.List;

/**
 * 字典类型 Service 接口
 *
 * @author 后台源码
 */
public interface DictTypeService {

    /**
     * 创建字典类型
     *
     * @param reqVO 字典类型信息
     * @return 字典类型编号
     */
    Long createDictType(DictTypeCreateReqVO reqVO);

    /**
     * 更新字典类型
     *
     * @param reqVO 字典类型信息
     */
    void updateDictType(DictTypeUpdateReqVO reqVO);

    /**
     * 删除字典类型
     *
     * @param id 字典类型编号
     */
    void deleteDictType(Long id);

    /**
     * 获得字典类型分页列表
     *
     * @param reqVO 分页请求
     * @return 字典类型分页列表
     */
    PageResult<DictTypeDO> getDictTypePage(DictTypePageReqVO reqVO);

    /**
     * 获得字典类型列表
     *
     * @param reqVO 列表请求
     * @return 字典类型列表
     */
    List<DictTypeDO> getDictTypeList(DictTypeExportReqVO reqVO);

    /**
     * 获得字典类型详情
     *
     * @param id 字典类型编号
     * @return 字典类型
     */
    DictTypeDO getDictType(Long id);

    /**
     * 获得字典类型详情
     *
     * @param type 字典类型
     * @return 字典类型详情
     */
    DictTypeDO getDictType(String type);

    /**
     * 获得全部字典类型列表
     *
     * @return 字典类型列表
     */
    List<DictTypeDO> getDictTypeList();


    List<DictDataVO> getDictListByType(DictReq dictReq);
}
