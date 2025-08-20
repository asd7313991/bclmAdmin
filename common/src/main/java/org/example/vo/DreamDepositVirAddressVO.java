package org.example.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.po.dream.dream.DreamDepositVirAddressDO;

@EqualsAndHashCode(callSuper = true)
@Data
public class DreamDepositVirAddressVO extends DreamDepositVirAddressDO {

    private String payTypeName;

}
