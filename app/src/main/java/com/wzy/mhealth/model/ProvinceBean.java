package com.wzy.mhealth.model;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/12/12 11:40
 * 修改人：Administrator
 * 修改时间：2016/12/12 11:40
 * 修改备注：
 */
public class ProvinceBean implements IPickerViewData{
    private String name;


    public ProvinceBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}