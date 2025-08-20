package org.example.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.config.log.TLogAspectExt;
import org.example.enums.CommonStatusEnum;
import org.example.excel.core.util.ExcelUtils;
import org.example.operatelog.core.enums.OperateTypeEnum;
import org.example.pojo.CommonResult;
import org.example.pojo.PageResult;
import org.example.system.config.log.SystemTLogConvert;
import org.example.system.config.satoken.SystemCheckPermission;
import org.example.system.convert.dept.PostConvert;
import org.example.system.db.mysql.po.system.PostDO;
import org.example.system.system.dept.PostService;
import org.example.system.vo.dept.vo.post.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.example.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static org.example.pojo.CommonResult.success;

/**
 * The type Post controller.
 */
@Slf4j
@Tag(name = "管理后台 - 岗位")
@RestController
@RequestMapping("/system/post")
@Validated
public class PostController {

    @Resource
    private PostService postService;

    /**
     * Create post common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PostMapping("/create")
    @SystemCheckPermission(value = "system:post:create")
    @TLogAspectExt(str = "创建岗位", moduleName = "post", type = OperateTypeEnum.CREATE, convert = SystemTLogConvert.class)
    public CommonResult<Long> createPost(@Valid @RequestBody PostCreateReqVO reqVO) {
        Long postId = postService.createPost(reqVO);
        return success(postId);
    }

    /**
     * Update post common result.
     *
     * @param reqVO the req vo
     * @return the common result
     */
    @PutMapping("/update")
    @SystemCheckPermission(value = "system:post:update")
    @TLogAspectExt(str = "修改岗位", moduleName = "post", type = OperateTypeEnum.UPDATE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> updatePost(@Valid @RequestBody PostUpdateReqVO reqVO) {
        postService.updatePost(reqVO);
        return success(true);
    }

    /**
     * Delete post common result.
     *
     * @param id the id
     * @return the common result
     */
    @PostMapping("/delete")
    @SystemCheckPermission(value = "system:post:delete")
    @TLogAspectExt(str = "删除岗位", moduleName = "post", type = OperateTypeEnum.DELETE, convert = SystemTLogConvert.class)
    public CommonResult<Boolean> deletePost(@RequestParam("id") Long id) {
        postService.deletePost(id);
        return success(true);
    }

    /**
     * Gets post.
     *
     * @param id the id
     * @return the post
     */
    @GetMapping(value = "/get")
    @SystemCheckPermission(value = "system:post:query")
    @TLogAspectExt(str = "获得岗位信息", moduleName = "post", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PostRespVO> getPost(@RequestParam("id") Long id) {
        return success(PostConvert.INSTANCE.convert(postService.getPost(id)));
    }

    /**
     * Gets simple post list.
     *
     * @return the simple post list
     */
    @GetMapping("/list-all-simple")
    @SystemCheckPermission(value = "system:post:query")
    @TLogAspectExt(str = "获取岗位精简信息列表", moduleName = "post", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<List<PostSimpleRespVO>> getSimplePostList() {
        // 获得岗位列表，只要开启状态的
        List<PostDO> list = postService.getPostList(null, Collections.singleton(CommonStatusEnum.ENABLE.getStatus()));
        // 排序后，返回给前端
        list.sort(Comparator.comparing(PostDO::getSort));
        log.info("获取岗位精简信息列表");
        return success(PostConvert.INSTANCE.convertList02(list));
    }

    /**
     * Gets post page.
     *
     * @param reqVO the req vo
     * @return the post page
     */
    @GetMapping("/page")
    @SystemCheckPermission(value = "system:post:query")
    @TLogAspectExt(str = "获得岗位分页列表", moduleName = "post", type = OperateTypeEnum.GET, convert = SystemTLogConvert.class)
    public CommonResult<PageResult<PostRespVO>> getPostPage(@Validated PostPageReqVO reqVO) {
        return success(PostConvert.INSTANCE.convertPage(postService.getPostPage(reqVO)));
    }

    /**
     * Export.
     *
     * @param response the response
     * @param reqVO    the req vo
     * @throws IOException the io exception
     */
    @GetMapping("/export")
    @SystemCheckPermission(value = "system:post:export")
    @TLogAspectExt(str = "岗位管理", moduleName = "post", type = EXPORT)
    public void export(HttpServletResponse response, @Validated PostExportReqVO reqVO) throws IOException {
        List<PostDO> posts = postService.getPostList(reqVO);
        List<PostExcelVO> data = PostConvert.INSTANCE.convertList03(posts);
        // 输出
        ExcelUtils.write(response, "岗位数据.xls", "岗位列表", PostExcelVO.class, data);
    }

}
