package com.wzy.mhealth.model;

/**
 * Created by Administrator on 2016/3/30.
 */
public class MeNewsDa {

    /**
     * count : 666
     * description : 广东省工商局近日公布的一批缺陷商品名单中，知名品牌水星家纺的一款纯棉床单被检出含有致癌物可分解致癌芳香胺染料，引起了全国消费者关注
     * fcount : 0
     * id : 101
     * img : /info/150729/dcad0753a1fbcfb1b482570377c8fe43.jpg
     * infoclass : 1
     * keywords : 水星 产品 可分解 消费者 床上用品
     * message : <p>  </p>
     <p> 广东省工商局近日公布的一批缺陷商品名单中，知名品牌水星家纺的一款纯棉床单被检出含有致癌物可分解致癌芳香胺染料，引起了全国消费者关注。水星家纺方面称，消费者可全额退款。 </p>
     <p> 广 东省工商局3月31日公布了今年首批缺陷商品名单，包括床上用品、玩具、童车、服装、空调等产品。其中共有18批次的床上用品、服装被检出含有可分解致癌 芳香胺染料，其中包括由上海水星家用纺织品股份有限公司生产的一款“MERCURY水星家纺”纯棉床单。据悉，可分解芳香胺主要来自于服装中的偶氮染料， 毒性和致癌性远强于甲醛，这种染料在与人的皮肤接触后，可引发多种恶性疾病、吸收致癌。 </p>
     <p> 水星家纺近日也通过官方微博发出召回通告，称存货和召回的产品，将统一无害化销毁。并为消费者及时办理退货，不论是否使用均按实际销售价格全额退款。水星家纺方面称，该款产品为4年前生产，目前尚有少量产品在售，已通知经销商立即将该产品下架并回收。 </p>
     <br>
     * rcount : 0
     * status : true
     * time : 1438141592000
     * title : 水星家纺曝致癌物厂家称消费者可全额退款
     * url : http://www.tngou.net/info/show/101
     */

    private int count;
    private String description;
    private int fcount;
    private int id;
    private String img;
    private int infoclass;
    private String keywords;
    private String message;
    private int rcount;
    private boolean status;
    private long time;
    private String title;
    private String url;

    public void setCount(int count) {
        this.count = count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setInfoclass(int infoclass) {
        this.infoclass = infoclass;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public String getDescription() {
        return description;
    }

    public int getFcount() {
        return fcount;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public int getInfoclass() {
        return infoclass;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getMessage() {
        return message;
    }

    public int getRcount() {
        return rcount;
    }

    public boolean isStatus() {
        return status;
    }

    public long getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
