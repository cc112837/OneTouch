package com.wzy.mhealth.model;

import java.util.List;

/**
 * 急病诊疗
 */
public class NewsAids {


    /**
     * title : 突发脑溢血的处理
     * detail : 对脑溢血病人，家属应尽量克制自己的感情，及时拨打急救电话。切勿大声叫喊、哭闹或随意搬动或颠簸病人。病人周围的环境应保持安静避光，减少声音的刺激，同时，应专人看管，以免病人因躁动而坠床，应让病人取平卧位，头偏向一侧，枕后不放枕头。将病人领口解开，用纱布包住病人舌头拉出，清除口腔的粘液；分泌物和呕吐物，以保持气道通畅。用冰袋或冷水毛巾敷在病人前额使头部降温，以利止血和降低大脑耗氧;搬运病人动作要轻，途中仍需不断清除病人口腔内分泌物、痰液和其他异物，保持气道通畅。
     */

    private List<HeadEntity> head;
    /**
     * title : 骨折
     * detail : 确定有骨折后，一定要对伤肢(指)作固定再送医院，否则骨折断端异常活动，会加重损伤。可因地制宜用木板、木棍、树枝、竹竿、杂志等作为固定用的临时夹板。若无上述材料，可将上肢固定在躯干上，下肢固定在对侧的健肢上。
     */

    private List<HandEntity> hand;
    /**
     * title : 猝死急救重在争分夺秒
     * detail : 所谓猝死是指自然发生、出乎意料的突然死亡。其临床特征主要是突发意识丧失，大动脉搏动消失，在20—30秒的叹息样呼吸后呼吸停止。猝死发生前可能胸疼，气急，也可毫无预兆就突然发生。在成人发生的全部猝死中，心脏性猝死占80%以上。猝死发生后，如果在2—4分钟内没有获得有效的治疗，大脑就会出现不可逆的损害，超过8分钟人就死亡了。当发现有人突然意识丧失而倒地时，应立即使其平卧，拍击其面颊并呼叫，同时用手触摸其颈动脉部位以确定有无搏动，若无反应且没有动脉搏动，就应立刻进行心肺复苏。首先使其头部后仰以畅通气道，继之进行有效的胸外按压，同时进行口对口人工呼吸。这些基本的救治措施应持续进行到专业急救人员到场。
     */

    private List<BrestEntity> brest;
    /**
     * title : 男人腰疼是怎么回事？
     * detail : 身体虚弱，过度疲惫，睡眠不足，紧张持久的脑力劳动，都是发病因素。因此，应当从增强体质方面入手，比如平时积极从事体育锻炼，注意作息合理，尤其是要多休息，防止过度劳累，从而调整中枢神经系统的功能失衡。另外，外贴医圣神贴也有助于缓解过劳。
     */

    private List<SpecialEntity> special;
    /**
     * title : 幼儿气管异物急救
     * detail : 1.倒立拍背法,对于婴幼儿，家长可立即倒提其两腿，头向下垂，同时轻拍其背部。这样可以通过异物的自身重力和呛咳时胸腔内气体的冲力，迫使异物向外咳出。2.推压腹部法,可让患儿坐着或站着，救助者站其身后，用两手臂抱住患儿，一手握拳，大拇指向内放在患儿的脐与剑突之间，用另一手掌压住拳头，有节奏地使劲向上向内推压，以促使横膈抬起，压迫肺底让肺内产生一股强大的气流，使之从气管内向外冲出，逼使异物随气流直达口腔，将其排除。若上述方法无效或情况紧急，应立即将患儿急送医院，医生会根据病情施行气管镜钳取术或做气管切开术。
     */

    private List<BabyEntity> baby;

    public void setHead(List<HeadEntity> head) {
        this.head = head;
    }

    public void setHand(List<HandEntity> hand) {
        this.hand = hand;
    }

    public void setBrest(List<BrestEntity> brest) {
        this.brest = brest;
    }

    public void setSpecial(List<SpecialEntity> special) {
        this.special = special;
    }

    public void setBaby(List<BabyEntity> baby) {
        this.baby = baby;
    }

    public List<HeadEntity> getHead() {
        return head;
    }

    public List<HandEntity> getHand() {
        return hand;
    }

    public List<BrestEntity> getBrest() {
        return brest;
    }

    public List<SpecialEntity> getSpecial() {
        return special;
    }

    public List<BabyEntity> getBaby() {
        return baby;
    }

    public static class HeadEntity {
        private String title;
        private String detail;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public String getDetail() {
            return detail;
        }
    }

    public static class HandEntity {
        private String title;
        private String detail;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public String getDetail() {
            return detail;
        }
    }

    public static class BrestEntity {
        private String title;
        private String detail;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public String getDetail() {
            return detail;
        }
    }

    public static class SpecialEntity {
        private String title;
        private String detail;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public String getDetail() {
            return detail;
        }
    }

    public static class BabyEntity {
        private String title;
        private String detail;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public String getDetail() {
            return detail;
        }
    }
}
