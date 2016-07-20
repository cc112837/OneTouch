package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/20 13:54
 * 修改人：Administrator
 * 修改时间：2016/7/20 13:54
 * 修改备注：
 */
public class TestItem {
    private  String header;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    private List<SubjectTest> listHash;

    public TestItem(String header, List<SubjectTest>listHash) {
        this.header = header;
        this.listHash = listHash;
    }

    public List<SubjectTest> getListHash() {
        return listHash;
    }

    public void setListHash( List<SubjectTest> listHash) {
        this.listHash = listHash;
    }
}
