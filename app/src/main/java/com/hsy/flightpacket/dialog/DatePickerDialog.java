package com.hsy.flightpacket.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;


import com.hsy.flightpacket.R;
import com.hsy.flightpacket.component.ShNumberPicker;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xiongweimin on 2017/8/18.
 */

public class DatePickerDialog extends BaseDialogManager {

    private static String str1 = Calendar.getInstance().get(Calendar.YEAR) + "";
    private static String str2 = (Calendar.getInstance().get(Calendar.MONTH) + 1) + "";
    private static String str3 = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
    ShNumberPicker np1;
    ShNumberPicker np2;
    ShNumberPicker np3;
    private int minusYears = 100; // 年份选择范围，当前年份减，默认3
    private int plusYears = 100; // 年份选择范围，当前年份加，默认3

    LinearLayout dialogContent;

    // 对话框按钮监听事件
    private View.OnClickListener backButtonClickListener, confirmButtonClickListener;
    private String[] years;

    final String[] month = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    final String[] dayBigMonth = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    final String[] dayMonth = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
    final String[] dayMonthFeb29 = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29"};
    final String[] dayMonthFeb28 = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28"};

    public DatePickerDialog(Context context) {

        super(context);
        setSubTitleGone();
        np1 = (ShNumberPicker) base.findViewById(R.id.np1);
        np2 = (ShNumberPicker) base.findViewById(R.id.np2);
        np3 = (ShNumberPicker) base.findViewById(R.id.np3);

        years = getYearsArray(Calendar.getInstance().get(Calendar.YEAR));
        int yearIndex = 0;
        for (int i = 0; i < years.length; i++) {
            if (years[i].contains(str1)) {
                yearIndex = i;
                break;
            }
        }
        setNumberPicker(np1, years, yearIndex, listener);
        setNumberPicker(np2, month, Integer.valueOf(str2) - 1, listener);
        setNumberPicker(np3, dayBigMonth, Integer.valueOf(str3) - 1, listener);
    }

    /**
     * 点击确定之后的动做
     *
     * @param dateListener
     */
    public void setOnSureClick(final InputDateListener dateListener) {
        setSureOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateListener != null) {
                    DatePickerDialog.this.dismissDialog();
                    dateListener.sure(getDateWithLine());
                }
            }
        });
    }


    NumberPicker.OnValueChangeListener listener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            switch (picker.getId()) {
                case R.id.np1:
                    str1 = years[np1.getValue()].replace("年", "");

                    if (str2.equals("1") || str2.equals("3") || str2.equals("5") || str2.equals("7")
                            || str2.equals("8") || str2.equals("10") || str2.equals("12")) {
                        np3.setMinValue(0);
                        np3.setMaxValue(dayBigMonth.length - 1);
                    } else if (str2.equals("4") || str2.equals("6") || str2.equals("9") || str2.equals("11")) {
                        np3.setMinValue(0);
                        np3.setMaxValue(dayMonth.length - 1);
                    } else {
                        if (((Integer.parseInt(str1) % 4 == 0) && (Integer.parseInt(str1) % 100 != 0))
                                || (Integer.parseInt(str1) % 400 == 0)) {
                            np3.setMinValue(0);
                            np3.setMaxValue(dayMonthFeb29.length - 1);
                        } else {
                            np3.setMinValue(0);
                            np3.setMaxValue(dayMonthFeb28.length - 1);
                        }
                    }
                    break;
                case R.id.np2:
                    str2 = month[np2.getValue()].replace("月", "");

                    if (str2.equals("1") || str2.equals("3") || str2.equals("5") || str2.equals("7") || str2.equals("8")
                            || str2.equals("10") || str2.equals("12")) {
                        np3.setMinValue(0);
                        np3.setMaxValue(dayBigMonth.length - 1);
                    } else if (str2.equals("4") || str2.equals("6") || str2.equals("9") || str2.equals("11")) {
                        np3.setMinValue(0);
                        np3.setMaxValue(dayMonth.length - 1);
                    } else {
                        if (((Integer.parseInt(str1) % 4 == 0) && (Integer.parseInt(str1) % 100 != 0))
                                || (Integer.parseInt(str1) % 400 == 0)) {
                            np3.setMinValue(0);
                            np3.setMaxValue(dayMonthFeb29.length - 1);
                        } else {
                            np3.setMinValue(0);
                            np3.setMaxValue(dayMonthFeb28.length - 1);
                        }
                    }
                    break;
                case R.id.np3:
                    str3 = np3.getDisplayedValues()[np3.getValue()].replace("日", "");
                    break;
                default:
                    break;
            }
        }
    };


    private String[] getYearsArray(int currentYear) {
        int resultSize = minusYears + plusYears + 1;
        String[] result = new String[resultSize];
        for (int i = 0; i < minusYears; i++) {
            result[i] = String.valueOf(currentYear - minusYears + i);
        }
        result[minusYears] = String.valueOf(currentYear);
        for (int i = 1; i <= plusYears; i++) {
            result[minusYears + i] = String.valueOf(currentYear + i);
        }
        return result;
    }


    /**
     * 设置默认值
     *
     * @param dateStr
     */
    public void setDate(String dateStr) {
        try {
            Calendar cal = getCalendarByTemplate(dateStr, "yyyy年MM月dd日");
            str1 = cal.get(Calendar.YEAR) + "";
            str2 = (cal.get(Calendar.MONTH) + 1) + "";
            str3 = cal.get(Calendar.DAY_OF_MONTH) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SimpleDateFormat fmt;

    /*
     *将yyyy-MM-dd格式转换成date
     */
    public static Date getStringToDate(String datestring) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(datestring);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取当前date
     *
     * @return
     */
    public static Date getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        return curDate;
    }

    /**
     * @param time 时间
     * @return 返回Calendar型时间
     */
    public static Calendar getCalendarByTemplate(String time, String from) {
        fmt = new SimpleDateFormat(from, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        try {

            Date toDate = fmt.parse(time);
            calendar.setTime(toDate);

            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }


    //设置NumberPicker的值
    private void setNumberPicker(NumberPicker np, String[] values, int mValue, NumberPicker.OnValueChangeListener listener) {

        np.setMinValue(0);
        np.setMaxValue(values.length - 1);
        np.setValue(mValue);
        np.setDisplayedValues(values);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setWrapSelectorWheel(false);
        setNumberPickerDividerColor(np);
        if (listener != null)
            np.setOnValueChangedListener(listener);
    }

    protected void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(picker, null);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    /**
     * getDate? description 描述：
     *
     * @return
     */
    public static String getDate() {
        return str1 + "年" + str2 + "月" + str3 + "日";
    }

    /*
    * return 0000-00-00
    * */
    public static String getDateWithLine() {
        return str1 + "-" + str2 + "-" + str3;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.dialog_date_picker;
    }


    public interface InputDateListener {
        void sure(String result);
    }
}

