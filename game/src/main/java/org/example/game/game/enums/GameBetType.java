package org.example.game.game.enums;

import java.util.Arrays;

public enum GameBetType {
    P28(28, 1.999, "单"),
    P29(29, 1.999, "双"),
    P30(30, 1.999, "小"),
    P31(31, 1.999, "大"),

    P32(32, 17.84, "小边"),
    P33(33, 17.84, "大边"),

    P34(34, 4.328, "大单"),
    P37(37, 4.328, "小双"),

    P35(35, 3.716, "大双"),
    P36(36, 3.716, "小单"),

    P0(0, 966, "0"),
    P27(27, 966, "27"),
    P1(1, 333, "1"),
    P26(26, 333, "26"),

    P2(2, 166.5, "2"),
    P25(25, 166.5, "25"),

    P3(3, 99.9, "3"),
    P24(24, 99.9, "24"),

    P4(4, 66.5, "4"),
    P23(23, 66.5, "23"),
    P5(5, 47.5, "5"),
    P22(22, 47.5, "22"),
    P6(6, 35.6, "6"),
    P21(21, 35.6, "21"),
    P7(7, 27.6, "7"),
    P20(20, 27.6, "20"),
    P8(8, 22.2, "8"),
    P19(19, 22.2, "19"),
    P9(9, 18.15, "9"),
    P18(18, 18.15, "18"),
    P10(10, 15.85, "10"),
    P17(17, 15.85, "17"),
    P11(11, 14.47, "11"),
    P16(16, 14.47, "16"),

    P12(12, 13.66, "12"),
    P15(15, 13.66, "15"),
    P13(13, 13.33, "13"),
    P14(14, 13.33, "14");

    /**
     * 结果
     */
    private final int ret;

    /**
     * 赔率
     */
    private final double rate;
    private final String name;

    public int getRet() {
        return ret;
    }

    GameBetType(int ret, double rate, String name) {
        this.ret = ret;
        this.rate = rate;
        this.name = name;
    }


    public double getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }

    public static String getName(int ret) {
        GameBetType[] betResult = GameBetType.values();
        GameBetType result = Arrays.asList(betResult).stream()
                .filter(i -> i.getRet() == ret)
                .findFirst().orElse(null);
        return result.getName();
    }

    public static double getRate(int ret) {
        GameBetType[] betResult = GameBetType.values();
        GameBetType result = Arrays.asList(betResult).stream()
                .filter(i -> i.getRet() == ret)
                .findFirst().orElse(null);
        return result.getRate();
    }

}
