package org.example.system.vo.errorcode;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 错误码的 Response DTO
 *
 * @author 后台源码
 */
@Data
public class ErrorCodeRespDTO {

    /**
     * 错误码编码
     */
    private Integer code;
    /**
     * 错误码错误提示
     */
    private String message;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
