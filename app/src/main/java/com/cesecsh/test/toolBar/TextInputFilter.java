package com.cesecsh.test.toolBar;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * 类名
 * 创建者：RockQ
 * 实现的主要功能：限制字数的过滤器，默认中文10个，英文20个
 * 创建日期：  2017/5/3
 */

public class TextInputFilter implements InputFilter {
    private int maxLength;

    public TextInputFilter() {
        this.maxLength = 20;
    }

    public TextInputFilter(int maxLength) {
        if (maxLength <= 0)
            maxLength = 20;
        this.maxLength = maxLength;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (source.length() < maxLength)
            return source;
        int count = 0;
        while (count <= maxLength && maxLength < dest.length()) {
            char c = dest.charAt(maxLength++);
            if (c < 128) {
                count = count + 1;
            } else {
                count = count + 2;
            }
        }
        if (count > maxLength) {
            return dest.subSequence(0, maxLength - 1);
        }
        int sindex = 0;
        while (count <= maxLength && sindex < source.length()) {
            char c = source.charAt(sindex++);
            if (c < 128) {
                count = count + 1;
            } else {
                count = count + 2;
            }
        }
        if (count > maxLength) {
            sindex--;
        }
        return source.subSequence(0, sindex) + "..";
    }
}
