package org.example.game.game.constants;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FinanceType extends ConstType {
    public FinanceType() {
    }

    ;

    public FinanceType(Integer type, Integer typeDetail, String remark) {
        super(type, typeDetail, remark);
    }


//    public static final FinanceType RECHARGE = new FinanceType(1, 1000, "充值");
//    public static final FinanceType PAY_IN = new FinanceType(2, 2000, "支付");
//    public static final FinanceType TRANSFER = new FinanceType(-4, -4000, "转账");
//    public static final FinanceType DEPOSIT = new FinanceType(-1, -1000, "用户提现");
//    public static final FinanceType DRAW_REFUSE = new FinanceType(-1, -10001, "拒绝提现");


    private static List<FinanceType> list = new ArrayList<FinanceType>();

    private static void init() throws Exception {
        initForAllType(FinanceType.class);
        initForAllType(FinanceFlowType.class);
    }

    public static List<FinanceType> getFinancyType() throws Exception {
        if (list.size() == 0) {
            init();
        }
        return list;
    }


    private static <T extends FinanceType> void initForAllType(Class<T> clazz) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            if (FinanceType.class.isAssignableFrom(f.getType())) {
                T type = (T) f.get(clazz);
                list.add(type);
            }
        }
    }
}
