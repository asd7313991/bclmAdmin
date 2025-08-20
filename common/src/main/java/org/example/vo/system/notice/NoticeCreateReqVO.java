package org.example.vo.system.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 通知公告创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class NoticeCreateReqVO extends NoticeBaseVO {
}
