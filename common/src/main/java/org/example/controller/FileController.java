package org.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.exception.enums.GlobalErrorCodeConstants;
import org.example.pojo.CommonResult;
import org.example.vo.FileReqDTO;
import org.example.vo.system.FileVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static org.example.exception.util.ServiceExceptionUtil.exception;


@Slf4j
@RestController
@RequestMapping("/system/file")
public class FileController {
    private static final String PATH = "/%s/%s/%s";


    @Value("${project.upload.domain:''}")
    private String domain;
    @Value("${project.upload.path:'/var/www/upload_file'}")
    private String uploadFilPath;


    @GetMapping("/file/{path}")
    public String getFile(@PathVariable String path) {
        String s = Base64.getEncoder().encodeToString(FileUtil.readBytes(FileUtil.file(uploadFilPath + path)));
        return s;
    }


    @PostMapping("/uploadFileByBody")
    public CommonResult<FileVO> uploadImage(@RequestBody FileReqDTO fileReqDTO) {
        return this.uploadImage(fileReqDTO.getImageData(), fileReqDTO.getType(), fileReqDTO.getOriginalFileName());
    }

    /**
     * 上传Base64 图片 返回全路径地址
     *
     * @param imageData
     * @return
     */
    @RequestMapping("/uploadFile")
    public CommonResult<FileVO> uploadImage(@RequestParam(value = "imageData") String imageData,
                                            @RequestParam(value = "type", required = false) String type,
                                            @RequestParam(value = "fileName", required = true) String originalFileName) {

        //去掉格式说明    base64前缀 data:image/jpeg;base64,
        imageData = imageData.substring(imageData.indexOf(",", 1) + 1);
        byte[] decode = Base64.getDecoder().decode(imageData);
        String subfix = ".png";
        if (StringUtils.isNotBlank(originalFileName)){
            subfix = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        if (StrUtil.isBlank(type)) {
            type = "default";
        }

        String fileName = UUID.fastUUID().toString(Boolean.TRUE) + subfix;
        String format = String.format(PATH, DateUtil.format(new Date(), "yyyy-MM-dd"), type, fileName);
        if (!FileUtil.exist(format)) {
            String nowFileName = uploadFilPath + format;
            log.info("上传路径文件名称：{}",nowFileName);
            File file = FileUtil.writeBytes(decode, nowFileName);
            log.info("保存文件成功");
        }

        //先存放到本地服务器，后面考虑OSS
        FileVO fileVO = new FileVO();
        fileVO.setFileName(fileName);
        fileVO.setUrl(domain + format);
        fileVO.setPath(format);
        fileVO.setDomain(format);
        return CommonResult.success(fileVO);
    }


    @RequestMapping("/uploadFileByMultipartFile")
    public CommonResult<List<FileVO>> uploadFileByMulitparfile(@RequestParam("files") MultipartFile[] files, @RequestParam(value = "type", required = false) String type) {

        List<FileVO> fileVOList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String originalFilename = files[i].getOriginalFilename();  // 文件名
            String subfix = originalFilename.substring(originalFilename.lastIndexOf("."));
            if (StrUtil.isBlank(type)) {
                type = "default";
            }
            String fileName = UUID.fastUUID().toString(Boolean.TRUE) + subfix;
            String format = String.format(PATH, DateUtil.format(new Date(), "yyyy-MM-dd"), type, fileName);
            File dest = new File(FileUtil.getTmpDirPath() + format);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(dest);
            } catch (Exception e) {
                log.error("文件转化失败", e);
                throw exception(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR);
            }
            //先存放到本地服务器，后面考虑OSS
            FileVO fileVO = new FileVO();
            fileVO.setFileName(fileName);
            fileVO.setUrl(domain + format);
            fileVO.setPath(format);
            fileVO.setDomain(format);
            fileVOList.add(fileVO);
        }


        return CommonResult.success(fileVOList);
    }

}
