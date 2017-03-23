package com.zhh.circularmenuapp;

/**
 * 常量类
 */
public interface Constants {

    //以下item是根据手机480*800---》1080*1920计算的菜单弧度（菜单个数为3-10个）
    int[] ITEM3 = {-20, 0, 20};
    int[] ITEM4 = {-30, -10, 10, 30};
    int[] ITEM5 = {-40, -20, 0, 20, 40};
    int[] ITEM6 = {-50, -30, -10, 10, 30, 50};
    int[] ITEM7 = {-55, -34, -16, 0, 16, 34, 55};
    int[] ITEM8 = {-54, -36, -21, -7, 7, 21, 36, 54};
    int[] ITEM9 = {-60, -41, -26, -13, 0, 13, 26, 41, 60};
    int[] ITEM10 = {-63, -45, -31, -18, -6, 6, 18, 31, 45, 63};
    //菜单个数
    String[] MENUALL=new String[]{"飞猪旅行", "滴滴出行", "城市服务", "我的快递","蚂蚁金服","蚂蚁森林","蚂蚁花呗"};
}
