package org.example.enums;

public class Lock4ConfigConstants {


    /**
     * 使用lock4j 实现限流，设置释放锁时间为 3s
     */
    private static final Integer LOCK_LIMIT_EXPIRE_TIME = 3000;


    static final String LOCK_KEY_PREFIX = "lock4j:";

    static final String DREAM_DEPOSIT_RULE = "dreamDepositRule";
    static final String DREAM_DEPOSIT_VIR_ADDRESS = "dreamDepositVirAddress";
    static final String DREAM_FINANCE = "dreamFinance";
    static final String DREAM_DEPOSIT_USER_VIR_ADDRESS = "dreamDepositUserVirAddress";
    static final String DREAM_USER = "dreamUser";


}
