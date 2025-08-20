package org.example.config.satoken;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.session.SaSession;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.system.SaTokenDO;
import org.example.service.system.SaTokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class SaTokenDaoImpl implements SaTokenDao {

    @Resource
    private SaTokenService saTokenService;

    @Override
    public String get(String s) {
        SaTokenDO one = saTokenService.getOne(new LambdaQueryWrapperX<SaTokenDO>().eq(SaTokenDO::getTokenKey,s));
        if (one != null) {
            long between = DateUtil.between(new Date(), one.getCreateTime(), DateUnit.SECOND);
            if (one.getTimeOut()-between>0){
                this.updateTimeout(s,one.getTimeOut()-between);
                return one.getValue();
            }else {
                this.delete(s);
                return null;
            }

        }
        return null;
    }

    @Override
    public void set(String s, String s1, long l) {
        SaTokenDO saTokenDO = new SaTokenDO();
        saTokenDO.setTokenKey(s);
        saTokenDO.setValue(s1);
        saTokenDO.setTimeOut(l);
        saTokenDO.setCreateTime(new Date());
        saTokenService.saveOrUpdate(saTokenDO,new LambdaQueryWrapperX<SaTokenDO>().eq(SaTokenDO::getTokenKey,s));
    }

    @Override
    public void update(String s, String s1) {
        saTokenService.update(new LambdaUpdateWrapper<SaTokenDO>().eq(SaTokenDO::getTokenKey,s).set(SaTokenDO::getValue, s1));
    }

    @Override
    public void delete(String s) {
        saTokenService.getBaseMapper().delete(new LambdaQueryWrapperX<SaTokenDO>().eq(SaTokenDO::getTokenKey,s));
    }

    @Override
    public long getTimeout(String s) {
        SaTokenDO one = saTokenService.getOne(new LambdaQueryWrapperX<SaTokenDO>().eq(SaTokenDO::getTokenKey,s));
        if (one != null) {
            long between = DateUtil.between(new Date(), one.getCreateTime(), DateUnit.SECOND);
            return one.getTimeOut()-between;
        }
        return -1;
    }

    @Override
    public void updateTimeout(String s, long l) {
        saTokenService.update(new LambdaUpdateWrapper<SaTokenDO>().eq(SaTokenDO::getTokenKey,s).set(SaTokenDO::getTimeOut, l));
    }

    @Override
    public Object getObject(String s) {
        SaTokenDO one = saTokenService.getOne(new LambdaQueryWrapperX<SaTokenDO>().eq(SaTokenDO::getTokenKey,s));
        if (one != null) {
            long between = DateUtil.between(new Date(), one.getCreateTime(), DateUnit.SECOND);
            if (one.getTimeOut()-between>0){
                this.updateObjectTimeout(s,one.getTimeOut()-between);
                return JSONUtil.toBean(one.getValue(), SaSession.class);
            }else {
                this.deleteObject(s);
                return null;
            }
        }
        return null;
    }

    @Override
    public void setObject(String s, Object o, long l) {
        SaTokenDO saTokenDO = new SaTokenDO();
        saTokenDO.setTokenKey(s);
        saTokenDO.setValue(JSONUtil.toJsonStr(o));
        saTokenDO.setTimeOut(l);
        saTokenDO.setCreateTime(new Date());
        saTokenService.saveOrUpdate(saTokenDO,new LambdaQueryWrapperX<SaTokenDO>().eq(SaTokenDO::getTokenKey,s));
    }

    @Override
    public void updateObject(String s, Object o) {
        saTokenService.update(new LambdaUpdateWrapper<SaTokenDO>().eq(SaTokenDO::getTokenKey,s).set(SaTokenDO::getValue, JSONUtil.toJsonStr(o)));
    }

    @Override
    public void deleteObject(String s) {
        saTokenService.getBaseMapper().delete(new LambdaQueryWrapperX<SaTokenDO>().eq(SaTokenDO::getTokenKey,s));
    }

    @Override
    public long getObjectTimeout(String s) {
        SaTokenDO one = saTokenService.getOne(new LambdaQueryWrapperX<SaTokenDO>().eq(SaTokenDO::getTokenKey,s));
        if (one != null){
            long between = DateUtil.between(new Date(), one.getCreateTime(), DateUnit.SECOND);
            return one.getTimeOut()-between;
        }
        return -1;
    }

    @Override
    public void updateObjectTimeout(String s, long l) {
        saTokenService.update(new LambdaUpdateWrapper<SaTokenDO>().eq(SaTokenDO::getTokenKey,s).set(SaTokenDO::getTimeOut, l));
    }

    /**
     * prefix – 前缀 keyword – 关键字 start – 开始处索引 size – 获取数量 (-1代表从 start 处一直取到末尾) sortType – 排序类型（true=正序，false=反序）
     * Returns:
     * 查询到的数据集合
     *
     * @param s  前缀
     * @param s1 关键字
     * @param i  开始处索引
     * @param i1 获取数量  (-1代表从 start 处一直取到末尾)
     * @param b  排序类型（true=正序，false=反序）
     * @return
     */
    @Override
    public List<String> searchData(String s, String s1, int i, int i1, boolean b) {
        List<SaTokenDO> list = saTokenService.list(new LambdaQueryWrapperX<SaTokenDO>().likeLeft(SaTokenDO::getTokenKey,s).like(SaTokenDO::getTokenKey, s1));
        if (CollectionUtil.isNotEmpty(list)) {
            return list.stream().map(SaTokenDO::getValue).collect(Collectors.toList());
        }
        return null;
    }
}
