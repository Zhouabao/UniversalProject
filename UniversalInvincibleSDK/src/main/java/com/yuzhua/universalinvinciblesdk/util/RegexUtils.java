package com.yuzhua.universalinvinciblesdk.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zhou Fengmei
 * @create 2018/4/23
 * @Describe 正则工具类：判断电话、邮箱等格式
 */
public class RegexUtils {
    //简单匹配电话号码
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    //移动电话匹配
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$";
    //座机匹配
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    //身份证15位
    public static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    //身份证18位
    public static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    //邮箱匹配
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    //url匹配
    public static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";
    //中文字符匹配
    public static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";
    //用户名匹配
    public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    //日期匹配 "yyyy-MM-dd".
    public static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    //IP地址匹配
    public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";


    private RegexUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断是否是简单的电话号码
     *
     * @param input
     * @return
     */
    public static boolean isMobileSimple(final CharSequence input) {
        return isMatch(REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 判断是否是移动号码
     *
     * @param input
     * @return
     */
    public static boolean isMobileExact(final CharSequence input) {
        return isMatch(REGEX_MOBILE_EXACT, input);
    }

    /**
     * 判断是否是座机号码
     *
     * @param input
     * @return
     */
    public static boolean isTel(final CharSequence input) {
        return isMatch(REGEX_TEL, input);
    }

    /**
     * 是否是15位身份证号码
     *
     * @param input
     * @return
     */
    public static boolean isIDCard15(final CharSequence input) {
        return isMatch(REGEX_ID_CARD15, input);
    }

    /**
     * 18位身份证号码
     *
     * @param input
     * @return
     */
    public static boolean isIDCard18(final CharSequence input) {
        return isMatch(REGEX_ID_CARD18, input);
    }

    /**
     * 是否是邮箱
     *
     * @param input
     * @return
     */
    public static boolean isEmail(final CharSequence input) {
        return isMatch(REGEX_EMAIL, input);
    }

    /**
     * Return whether input matches regex of url.
     *
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isURL(final CharSequence input) {
        return isMatch(REGEX_URL, input);
    }

    /**
     * 中文字符判断
     *
     * @param input
     * @return
     */
    public static boolean isZh(final CharSequence input) {
        return isMatch(REGEX_ZH, input);
    }

    /**
     * 判断是否是用户名  字母数字下划线  6-20个字符
     *
     * @param input
     * @return
     */
    public static boolean isUsername(final CharSequence input) {
        return isMatch(REGEX_USERNAME, input);
    }

    /**
     * 日期匹配   "yyyy-MM-dd".
     *
     * @param input
     * @return
     */
    public static boolean isDate(final CharSequence input) {
        return isMatch(REGEX_DATE, input);
    }

    /**
     * IP地址匹配
     *
     * @param input
     * @return
     */
    public static boolean isIP(final CharSequence input) {
        return isMatch(REGEX_IP, input);
    }

    /**
     * Return whether input matches the regex.
     *
     * @param regex The regex.
     * @param input The input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * Return the list of input matches the regex.
     *
     * @param regex The regex.
     * @param input The input.
     * @return the list of input matches the regex
     */
    public static List<String> getMatches(final String regex, final CharSequence input) {
        if (input == null) return null;
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * Splits input around matches of the regex.
     *
     * @param input The input.
     * @param regex The regex.
     * @return the array of strings computed by splitting input around matches of regex
     */
    public static String[] getSplits(final String input, final String regex) {
        if (input == null) return null;
        return input.split(regex);
    }

    /**
     * Replace the first subsequence of the input sequence that matches the
     * regex with the given replacement string.
     *
     * @param input       The input.
     * @param regex       The regex.
     * @param replacement The replacement string.
     * @return the string constructed by replacing the first matching
     * subsequence by the replacement string, substituting captured
     * subsequences as needed
     */
    public static String getReplaceFirst(final String input, final String regex, final String replacement) {
        if (input == null) return null;
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }

    /**
     * Replace every subsequence of the input sequence that matches the
     * pattern with the given replacement string.
     *
     * @param input       The input.
     * @param regex       The regex.
     * @param replacement The replacement string.
     * @return the string constructed by replacing each matching subsequence
     * by the replacement string, substituting captured subsequences
     * as needed
     */
    public static String getReplaceAll(final String input, final String regex, final String replacement) {
        if (input == null) return null;
        return Pattern.compile(regex).matcher(input).replaceAll(replacement);
    }
}
