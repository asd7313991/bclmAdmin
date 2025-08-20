//package org.example.config.mybatisplus;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.ibatis.reflection.MetaObject;
//import org.example.config.satoken.StpSystemUtil;
//import org.example.po.BaseEntity;
//import org.example.util.web.WebFrameworkUtils;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//public class DefaultDBFieldHandler  implements MetaObjectHandler {
//
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
//            BaseEntity baseDO = (BaseEntity) metaObject.getOriginalObject();
//
//            LocalDateTime current = LocalDateTime.now();
//            // 创建时间为空，则以当前时间为插入时间
//            if (Objects.isNull(baseDO.getCreateTime())) {
//                baseDO.setCreateTime(current);
//            }
//            // 更新时间为空，则以当前时间为更新时间
//            if (Objects.isNull(baseDO.getModifyTime())) {
//                baseDO.setModifyTime(current);
//            }
//
//            String loginIdAsString = StpSystemUtil.getLoginIdAsString();
//            // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
//            if (StringUtils.isNotBlank(loginIdAsString) && Objects.isNull(baseDO.getCreator())) {
//                baseDO.setCreator(loginIdAsString);
//            }
//            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
//            if (StringUtils.isNotBlank(loginIdAsString) && Objects.isNull(baseDO.getUpdater())) {
//                baseDO.setUpdater(loginIdAsString);
//            }
//        }
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        // 更新时间为空，则以当前时间为更新时间
//        Object modifyTime = getFieldValByName("modifyTime", metaObject);
//        if (Objects.isNull(modifyTime)) {
//            setFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
//        }
//
//        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
//        Object modifier = getFieldValByName("updater", metaObject);
//        String loginIdAsString = StpSystemUtil.getLoginIdAsString();
//        if (StringUtils.isNotBlank(loginIdAsString) && Objects.isNull(modifier)) {
//            setFieldValByName("updater", loginIdAsString, metaObject);
//        }
//    }
//}
