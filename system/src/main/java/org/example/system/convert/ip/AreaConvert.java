package org.example.system.convert.ip;

import org.example.enums.Area;
import org.example.enums.AreaTypeEnum;
import org.example.system.vo.ip.vo.AppAreaNodeRespVO;
import org.example.system.vo.ip.vo.AreaNodeRespVO;
import org.example.system.vo.ip.vo.AreaNodeSimpleRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 * The interface Area convert.
 */
@Mapper
public interface AreaConvert {

    /**
     * The constant INSTANCE.
     */
    AreaConvert INSTANCE = Mappers.getMapper(AreaConvert.class);

    /**
     * Convert list list.
     *
     * @param list the list
     * @return the list
     */
    List<AreaNodeRespVO> convertList(List<Area> list);

    /**
     * Convert list 2 list.
     *
     * @param list the list
     * @return the list
     */
    List<AreaNodeSimpleRespVO> convertList2(List<Area> list);

    /**
     * Convert area node simple resp vo.
     *
     * @param area the area
     * @return the area node simple resp vo
     */
    @Mapping(source = "type", target = "leaf")
    AreaNodeSimpleRespVO convert(Area area);

    /**
     * Convert area type boolean.
     *
     * @param type the type
     * @return the boolean
     */
    default Boolean convertAreaType(Integer type) {
        return Objects.equals(AreaTypeEnum.DISTRICT.getType(), type);
    }

    /**
     * Convert list 3 list.
     *
     * @param list the list
     * @return the list
     */
    List<AppAreaNodeRespVO> convertList3(List<Area> list);

}
