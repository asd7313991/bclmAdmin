package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.config.log.TLogAspectExt;
import org.example.enums.CommonStatusEnum;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.permission.MenuConvert;
import org.example.system.db.mysql.po.system.MenuDO;
import org.example.system.system.permission.MenuService;
import org.example.system.vo.permission.vo.menu.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

import static org.example.pojo.CommonResult.success;


/**
 * The type Menu controller.
 */
@Tag(name = "管理后台 - 菜单")
@RestController
@RequestMapping("/system/menu")
@Validated
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * Create menu common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:menu:create")
    @TLogAspectExt(str = "创建菜单", moduleName = "menu", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createMenu(@Valid @RequestBody MenuCreateReqVO reqVO) {
        Long menuId = menuService.createMenu(reqVO);
        return success(menuId);
    }

    /**
     * Update menu common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update")
    @SystemCheckPermission(value = "system:menu:update")
    @TLogAspectExt(str = "修改菜单", moduleName = "menu", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updateMenu(@Valid @RequestBody MenuUpdateReqVO reqVO) {
        menuService.updateMenu(reqVO);
        return success(true);
    }

    /**
     * Delete menu common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @SystemCheckPermission(value = "system:menu:delete")
    @TLogAspectExt(str = "删除菜单", moduleName = "menu", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deleteMenu(@RequestParam("id") Long id) {
        menuService.deleteMenu(id);
        return success(true);
    }

    /**
     * Gets menu list.
     *
     * @param reqVO the req vo
     * @return the menu list
     */
    @GetMapping("/list")
    @SystemCheckPermission(value = "system:menu:query")
    @TLogAspectExt(str = "获取菜单列表", moduleName = "menu", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<List<MenuRespVO>> getMenuList(MenuListReqVO reqVO) {
        List<MenuDO> list = menuService.getMenuList(reqVO);
        list.sort(Comparator.comparing(MenuDO::getSort));
        return success(MenuConvert.INSTANCE.convertList(list));
    }

    /**
     * Gets simple menu list.
     *
     * @return the simple menu list
     */
    @GetMapping("/list-all-simple")
    @TLogAspectExt(str = "获取菜单精简信息列表", moduleName = "menu", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<List<MenuSimpleRespVO>> getSimpleMenuList() {
        // 获得菜单列表，只要开启状态的
        MenuListReqVO reqVO = new MenuListReqVO();
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        List<MenuDO> list = menuService.getMenuListByTenant(reqVO);
        // 排序后，返回给前端
        list.sort(Comparator.comparing(MenuDO::getSort));
        return success(MenuConvert.INSTANCE.convertList02(list));
    }

    /**
     * Gets menu.
     *
     * @param id the id
     * @return the menu
     */
    @GetMapping("/get")
    @SystemCheckPermission(value = "system:menu:query")
    @TLogAspectExt(str = "获取菜单信息", moduleName = "menu", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<MenuRespVO> getMenu(Long id) {
        MenuDO menu = menuService.getMenu(id);
        return success(MenuConvert.INSTANCE.convert(menu));
    }

}
