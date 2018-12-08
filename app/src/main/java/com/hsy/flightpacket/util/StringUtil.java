/*******************************************************************************
 * @(#)StringUtil.java 2012-12-27
 * <p>
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.hsy.flightpacket.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 *
 * @author <a href="mailto:wenxw@neusoft.com">sherly.wen </a>
 * @version $Revision 1.1 $ 2012-12-27 下午02:46:14
 */
public final class StringUtil {
    private static final String NULL = "null";

    private static final String EMPTY = "";

    /**
     * ASCII表中可见字符从!开始，偏移位值为33(Decimal)
     */
    private static final char DBC_CHAR_START = 33; // 半角!

    /**
     * ASCII表中可见字符到~结束，偏移位值为126(Decimal)
     */
    private static final char DBC_CHAR_END = 126; // 半角~

    /**
     * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
     */
    private static final char SBC_CHAR_START = 65281; // 全角！

    /**
     * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
     */
    private static final char SBC_CHAR_END = 65374; // 全角～

    /**
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
     */
    private static final int CONVERT_STEP = 65248; // 全角半角转换间隔

    /**
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
     */
    private static final char SBC_SPACE = 12288; // 全角空格 12288

    /**
     * 半角空格的值，在ASCII中为32(Decimal)
     */
    private static final char DBC_SPACE = ' '; // 半角空格

    private StringUtil() {

    }

    /**
     * 把Object转为字符串
     */
    public static String parseObj2Str(Object object) {
        StringBuilder sb = new StringBuilder();
        if (object == null)
            return NULL;
        if (object instanceof Throwable) {
            Throwable e = (Throwable) object;
            sb.append(e);
            sb.append(e.getCause());
            StackTraceElement[] trace = e.getStackTrace();
            if (trace != null)
                for (StackTraceElement t : trace)
                    sb.append("\n\tat ").append(t);
            return sb.toString();
        } else if (object instanceof Object[]) {
            Object[] objectArray = (Object[]) object;
            sb.append(objectArray.getClass().getSimpleName()).append("[");

            for (Object _object : objectArray)
                sb.append(parseObj2Str(_object)).append(",");
            sb.append("]");
            return sb.toString();
        } else
            return object.toString();
    }

    /**
     * 获取字符串的MD5串
     *
     * @param src
     * @param char_set 编码
     * @return src字符串对应的MD5串
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String string2Md5(String src, String char_set) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        MessageDigest messageDigest = null;
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(src.getBytes(char_set));
        return bytes2Hex(messageDigest.digest());
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static String bytes2Hex(byte[] bArray) {
        StringBuilder sb = new StringBuilder();
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase(Locale.ENGLISH));
        }
        return sb.toString();
    }

    /**
     * 验证字符串是否等于空或等于null 或等于“null”
     *
     * @param srcs
     * @return
     */
    public static boolean isNull(String... srcs) {
        if (srcs == null || srcs.length == 0) {
            return true;
        }
        for (String src : srcs) {
            boolean isEmpty = EMPTY.equals(src);
            boolean isNull = NULL.equals(src);
            boolean isNull_S = src == null;
            if (isEmpty || isNull || isNull_S) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证输入字符串是否包包含空格，tab，是否为null
     *
     * @param str
     * @return
     */
    public static boolean containSpace(String str) {
        boolean result = false;
        if (str == null || str.equals(EMPTY)) {
            result = true;
        } else {
            char[] strChars = str.toCharArray();
            for (char strChar : strChars) {
                if (Character.isWhitespace(strChar)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 转换Object为String类型
     *
     * @param object
     * @return String
     */
    public static String parseStr(Object object) {
        if (object != null)
            return String.valueOf(object).trim();
        else
            return null;
    }

    /**
     * 转换CharSequence为String类型
     *
     * @param sequence
     * @return String
     */
    public static String parseStr(CharSequence sequence) {
        if (sequence != null) {
            return String.valueOf(sequence);
        } else {
            return null;
        }
    }

    /**
     * <PRE>
     * 全角字符->半角字符转换
     * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
     * </PRE>
     */
    public static String qj2bj(String src) {
        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < src.length(); i++) {
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内
                buf.append((char) (ca[i] - CONVERT_STEP));
            } else if (ca[i] == SBC_SPACE) { // 如果是全角空格
                buf.append(DBC_SPACE);
            } else { // 不处理全角空格，全角！到全角～区间外的字符
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * <PRE>
     * 半角字符->全角字符转换
     * 只处理空格，!到&tilde;之间的字符，忽略其他
     * </PRE>
     */
    public static String bj2qj(String src) {
        if (src == null) {
            return src;
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == DBC_SPACE) { // 如果是半角空格，直接用全角空格替代
                buf.append(SBC_SPACE);
            } else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) { // 字符是!到~之间的可见字符
                buf.append((char) (ca[i] + CONVERT_STEP));
            } else { // 不对空格以及ascii表中其他可见字符之外的字符做任何处理
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 提取信息中的网络链接:(h|H)(r|R)(e|E)(f|F) *= *('|")?(\w|\\|\/|\.)+('|"| *|>)?
     * 提取信息中的邮件地址:\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
     * 提取信息中的图片链接:(s|S)(r|R)(c|C) *= *('|")?(\w|\\|\/|\.)+('|"| |>)?
     * 提取信息中的IP地址:(\d+)\.(\d+)\.(\d+)\.(\d+) 提取信息中的中国手机号码:(86)*0*13\d{9}
     * 提取信息中的中国固定电话号码:(\(\d{3,4}\)|\d{3,4}-|\s)?\d{8}
     * 提取信息中的中国电话号码（包括移动和固定电话）:(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}
     * 提取信息中的中国邮政编码:[1-9]{1}(\d+){5} 提取信息中的中国身份证号码:\d{18}|\d{15} 提取信息中的整数：\d+
     * 提取信息中的浮点数（即小数）：(-?\d*)\.?\d+ 提取信息中的任何数字 ：(-?\d*)(\.\d+)?
     * 提取信息中的中文字符串：[\u4e00-\u9fa5]* 提取信息中的双字节字符串 (汉字)：[^\x00-\xff]*
     */

    public static final String PATTERN_TEL = "(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)";

    public static List<String> getCallNumber(String lists) {
        List<String> receiverList = new ArrayList<String>();
        receiverList.clear();
        // String reg =
        // "(?<!(\\+86)\\d)(?:(?:1[23456789]\\d{9})|(?:0[1-9]\\d{1,2}-?\\d{7,8}))(?!\\d)";
        String reg = "(?:(?:1[23456789]\\d{9})|(?:0[1-9]\\d{1,2}-?\\d{7,8}))(?!\\d)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(lists);
        while (m.find()) {
            receiverList.add(m.group().replace("-", ""));
        }
        return receiverList;
    }

    /**
     * 四舍五入双精度数
     *
     * @param d
     * @return
     */
    public static String cutDoubleNumber(double d, int scale) {
        BigDecimal bd = new BigDecimal(d).setScale(scale, BigDecimal.ROUND_HALF_UP);
        return StringUtil.parseStr(bd);
    }

    /**
     * 十六进制转换为二进制
     *
     * @param hexString
     * @return
     */
    public static byte[] hexString2Bytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase(Locale.getDefault());
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String byte2HexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 剔除电话号码中的-
     *
     * @param callNumber
     * @return
     */
    public static String formatCallNumber(String callNumber) {
        callNumber = callNumber.replace("-", "");
        String reg = "(?:(?:1[23456789]\\d{9})|(?:0[1-9]\\d{1,2}-?\\d{7,8}))(?!\\d)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(callNumber);
        String result = "";
        while (m.find()) {
            result = m.group();
        }
        return result;
    }

    /**
     * 从输入字符串中提取对应pattern的内容
     *
     * @param res
     * @param pattern_str
     * @return
     */
    public static String getSpecContent(String res, String pattern_str) {
        Pattern pattern = Pattern.compile(pattern_str);
        Matcher matcher = pattern.matcher(res);
        StringBuilder bf = new StringBuilder(64);
        while (matcher.find()) {
            bf.append(matcher.group()).append("|");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }


    public static String repaceAllSpace(String str) {
        if (isNull(str)) {
            return str;
        }
        StringBuilder builder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (!Character.isWhitespace(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&type=123"，解析出Action:del,type:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("&");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("=");
            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (!isNull(arrSplitEqual[0])) {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], EMPTY);
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("\\?");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    public static String ean13(String code) {
        int sumj = 0, sume = 0;
        int result = 0;
        for (int i = 0; i < code.length() - 1; i = i + 2) {
            sumj += code.charAt(i) - '0';
            sume += code.charAt(i + 1) - '0';
        }
        result = sumj + sume * 3;
        result = result % 10;
        if (result == 10 || result == 0)
            result = 0;
        else
            result = 10 - result;
        return code + result;
    }

    /**
     * @param str      待格式化字符串
     * @param splitNum 格式化间隔字符数量
     * @param split    准备插入的格式化字符
     * @return
     */
    public static String formatStrAddSplit(String str, int splitNum, String split) {
        if (isNull(str) || str.length() <= splitNum) {
            return str;
        }
        StringBuilder builder = new StringBuilder(str);
        str = builder.reverse().toString();
        builder.delete(0, builder.length());
        char[] chars = str.toCharArray();
        for (int position = 0; position < chars.length; position++) {
            builder.append(chars[position]);
            if (position != 0 && (position + 1) % splitNum == 0) {
                builder.append(split);
            }
        }
        return parseStr(builder.reverse());
    }

    public static byte[] File2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
