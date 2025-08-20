package org.example.vo.system;


import lombok.Data;

@Data
public class FileVO {

    /**
     * 全路径URl
     */
    private String url;

    /**
     * 文件存放相对路径
     */
    private String path;

    /**
     * 文件名称
     */
    private String fileName;


    private String domain;
}
