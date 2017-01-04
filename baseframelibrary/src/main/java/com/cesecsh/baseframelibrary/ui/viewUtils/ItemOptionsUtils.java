package com.cesecsh.baseframelibrary.ui.viewUtils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.cesecsh.ics.contstant.ResultConstant;
import com.cesecsh.ics.domain.Area;
import com.cesecsh.ics.domain.City;
import com.cesecsh.ics.domain.Province;
import com.cesecsh.ics.ui.view.SettingItemView;
import com.cesecsh.ics.utils.DbUtils.DbUtils;
import com.cesecsh.ics.utils.sysUtils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.cesecsh.ics.utils.DbUtils.DbUtils.getProvince;

/**
 * Created by zcy on 2016/10/8.
 */

public class ItemOptionsUtils {
    /**
     * 时间表选择器
     */

    private static TimePickerView tpView;
    /**
     * 显示选择的item
     */
    private static OptionsPickerView optionsPickerView;
    /**
     * 选择的时间
     */
    private static Date date;

    public static void selectItemOptions(Context context, final List<String> list, Handler handler) {
        selectItemFromOptions(context, list, handler);
    }

    /**
     * 有handler 做选择后
     *
     * @param context
     * @param list
     * @param handler
     * @return
     */
    private static String selectItemFromOptions(Context context, final List<String> list, final Handler handler) {
        optionsPickerView = new OptionsPickerView(context);
        optionsPickerView.setPicker((ArrayList) list);
        optionsPickerView.setCancelable(true);
        optionsPickerView.setSelectOptions(0);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                handler.sendMessage(Message.obtain(handler, ResultConstant.PRIORITY_SUCCESS, options1));
            }
        });
        optionsPickerView.show();
        return null;
    }

    public static void selectItemOptions(Context context, final List<String> list, View v) {
        selectItemFromOptions(context, list, v);
    }

    /**
     * 直接显示 数据 没有handler做选择后的消息通知
     *
     * @param context
     * @param list
     * @param v
     * @return
     */
    private static String selectItemFromOptions(Context context, final List<String> list, final View v) {
        optionsPickerView = new OptionsPickerView(context);
        optionsPickerView.setPicker((ArrayList) list);
        optionsPickerView.setCancelable(true);
        optionsPickerView.setSelectOptions(0);
        optionsPickerView.setCyclic(false);
        optionsPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if (v instanceof SettingItemView) {
                    ((SettingItemView) v).setContent(list.get(options1));
                }
            }
        });
        optionsPickerView.show();
        return null;
    }

    public static void getDate(Context context, final View tv, Handler handler) {
        getPickerDate(context, new Date(), tv, handler);
    }

    /**
     * @param context
     * @param date
     * @param tv
     * @param handler
     * @return
     */
    private static Date getPickerDate(Context context, Date date, final View tv, final Handler handler) {
        tpView = new TimePickerView(context, TimePickerView.Type.ALL);
        tpView.setTime(date);
        tpView.setCyclic(false);

        tpView.setCancelable(true);
        tpView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                if (tv instanceof TextView) {
                    ((TextView) tv).setText(TimeUtils.milliseconds2String(date.getTime()));
                } else if (tv instanceof SettingItemView) {
                    ((SettingItemView) tv).setHint(TimeUtils.milliseconds2String(date.getTime()));
                }
                handler.sendMessage(Message.obtain(handler, ResultConstant.DATE_SUCCESS, date.getTime()));
            }
        });
        tpView.show();
        return null;
    }


    public static void getRegion(Context context, final View v) {
        List<Province> list = getProvince();
        System.out.println(list);
        //获取省市区3级列表的所有数据
        final ArrayList<String> items1 = new ArrayList<>();
        final ArrayList<ArrayList<String>> items2 = new ArrayList<>();
        final ArrayList<ArrayList<ArrayList<String>>> items3 = new ArrayList<>();
        List<Province> provinces = DbUtils.getProvince();
        for (Province province : provinces) {
            items1.add(province.getName());
            List<City> cities = DbUtils.getCity(province.getId());
            final ArrayList<ArrayList<String>> items3_2 = new ArrayList<>();
            ArrayList<String> items2_1 = new ArrayList<>();
            for (City city : cities) {
                items2_1.add(city.getName());
                List<Area> areas = DbUtils.getArea(city.getId());
                ArrayList<String> items3_1_1 = new ArrayList<>();
                for (Area area : areas) {
                    items3_1_1.add(area.getName());
                }
                items3_2.add(items3_1_1);
            }
            items2.add(items2_1);
            items3.add(items3_2);
        }
        //选项选择器
        OptionsPickerView pvOptions = new OptionsPickerView(context);
        //三级联动效果
        pvOptions.setPicker(items1, items2, items3, true);
        //设置选择的三级单位
        //        pvOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = items1.get(options1)
                        + items2.get(options1).get(option2)
                        + items3.get(options1).get(option2).get(options3);
                ((SettingItemView) v).setHint(tx);
            }
        });
        pvOptions.show();
    }


}
