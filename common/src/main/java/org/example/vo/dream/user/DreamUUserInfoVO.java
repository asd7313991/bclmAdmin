package org.example.vo.dream.user;

import lombok.Data;
import org.example.vo.dream.financeAcc.DreamUFinanceAccVO;
import org.example.vo.dream.financeMain.DreamUFinanceMainVO;


@Data
public class DreamUUserInfoVO {


    private DreamUUserVO dreamUUserVO;

    private DreamUFinanceMainVO dreamUFinanceMainVO;

    private DreamUFinanceAccVO dreamUFinanceAccVO;

}
