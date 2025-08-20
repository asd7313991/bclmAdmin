package org.example.util;


import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinUtil {

    public static String convertToPinyin(String chinese) {
        StringBuilder pinyin = new StringBuilder();
        char[] chars = chinese.toCharArray();

        for (char c : chars) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);

            if (pinyinArray != null && pinyinArray.length > 0) {
                pinyin.append(pinyinArray[0]);
            } else {
                pinyin.append(c);
            }
        }

        return pinyin.toString();
    }
}
