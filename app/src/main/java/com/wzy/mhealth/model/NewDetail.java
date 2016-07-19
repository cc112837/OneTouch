package com.wzy.mhealth.model;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/7/18 14:27
 * 修改人：Administrator
 * 修改时间：2016/7/18 14:27
 * 修改备注：
 */
public class NewDetail {

    /**
     * code : 0
     * message :
     * data : {"content":{"article":{"contentid":505380,"modelid":1,"html":"<!doctype html>\n<html lang=\"zh-CN\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"format-detection\" content=\"telephone=no,email=no\"/>\n    <meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no\"/>\n    <title>食药监：七批次产品不合格 天猫又上榜<\/title>\n    <style type=\"text/css\">\n        * {\n            margin: 0;\n            padding: 0;\n            -webkit-box-sizing: border-box;\n            -moz-box-sizing: border-box;\n            box-sizing: border-box;\n        }\n        article,\n        aside,\n        details,\n        figcaption,\n        figure,\n        footer,\n        header,\n        main,\n        menu,\n        nav,\n        section,\n        summary {\n             \n            display: block;\n        }\n        audio,\n        canvas,\n        progress,\n        video {\n            display: inline-block;\n        }\n        audio:not([controls]) {\n            display: none;\n            height: 0;\n        }\n        progress {\n            vertical-align: baseline;\n        }\n        template,\n        [hidden] {\n            display: none;\n        }\n        a {\n            background-color: transparent;\n             \n            -webkit-text-decoration-skip: objects;\n             \n        }\n        a:active,\n        a:hover {\n            outline-width: 0;\n        }\n        abbr[title] {\n            border-bottom: none;\n             \n            text-decoration: underline;\n             \n            text-decoration: underline dotted;\n             \n        }\n        b,\n        strong {\n            font-weight: inherit;\n        }\n        b,\n        strong {\n            font-weight: bolder;\n        }\n        dfn {\n            font-style: italic;\n        }\n        mark {\n            background-color: #ff0;\n            color: #000;\n        }\n        small {\n            font-size: 80%;\n        }\n        sub,\n        sup {\n            font-size: 75%;\n            line-height: 0;\n            position: relative;\n            vertical-align: baseline;\n        }\n        sub {\n            bottom: -0.25em;\n        }\n        sup {\n            top: -0.5em;\n        }\n        img {\n            border-style: none;\n        }\n        svg:not(:root) {\n            overflow: hidden;\n        }\n        code,\n        kbd,\n        pre,\n        samp {\n            font-family: monospace, monospace;\n             \n            font-size: 1em;\n             \n        }\n        figure {\n            margin: 1em 40px;\n        }\n        hr {\n            box-sizing: content-box;\n             \n            height: 0;\n             \n            overflow: visible;\n             \n        }\n        button,\n        input,\n        select,\n        textarea {\n            font: inherit;\n             \n            margin: 0;\n             \n        }\n        optgroup {\n            font-weight: bold;\n        }\n        button,\n        input {\n             \n            overflow: visible;\n        }\n        button,\n        select {\n             \n            text-transform: none;\n        }\n        button,\n        html [type=\"button\"],\n        [type=\"reset\"],\n        [type=\"submit\"] {\n            -webkit-appearance: button;\n             \n        }\n        button::-moz-focus-inner,\n        [type=\"button\"]::-moz-focus-inner,\n        [type=\"reset\"]::-moz-focus-inner,\n        [type=\"submit\"]::-moz-focus-inner {\n            border-style: none;\n            padding: 0;\n        }\n        button:-moz-focusring,\n        [type=\"button\"]:-moz-focusring,\n        [type=\"reset\"]:-moz-focusring,\n        [type=\"submit\"]:-moz-focusring {\n            outline: 1px dotted ButtonText;\n        }\n        fieldset {\n            border: 1px solid #c0c0c0;\n            margin: 0 2px;\n            padding: 0.35em 0.625em 0.75em;\n        }\n        legend {\n            box-sizing: border-box;\n             \n            color: inherit;\n             \n            display: table;\n             \n            max-width: 100%;\n             \n            padding: 0;\n             \n            white-space: normal;\n             \n        }\n        html,\n        body {\n            background: #fff;\n            font-family: \"Helvetica Neue\", Helvetica, sans-serif;\n            text-rendering: optimizelegibility;\n            -webkit-font-smoothing: antialiased;\n            -moz-osx-font-smoothing: grayscale;\n            -webkit-text-size-adjust: 100%;\n            -ms-text-size-adjust: 100%;\n        }\n        .entry {\n            margin: 0.69444444rem 0 0.69444444rem 0.69444444rem;\n        }\n        .entry .title {\n            padding-right: 0.69444444rem;\n            font-size: 0.93749999rem;\n            line-height: 1.33680555rem;\n            color: #333;\n        }\n        .entry .meta {\n            margin-top: 0.34722222rem;\n            padding-right: 0.69444444rem;\n            padding-bottom: 0.52083333rem;\n            border-bottom: solid 1px #dddddd;\n            font-size: 0.69444444rem;\n            color: #999999;\n        }\n        .entry .meta .datetime {\n            margin-left: 0.86805555rem;\n        }\n        .entry .content {\n            margin-top: 0.52083333rem;\n            padding-right: 0.69444444rem;\n            color: #555;\n            font-size: 0.83333333rem;\n        }\n        .entry .content p {\n            margin-bottom: 0.69444444rem;\n            line-height: 1.35416666rem;\n        }\n        .entry .content p > img {\n            display: block;\n            margin: 0 auto;\n            max-width: 100%;\n        }\n    <\/style>\n    <script>\n        (function (doc, win) {\n            var docEl = doc.documentElement,\n                resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',\n                recalc = function () {\n                    var clientWidth = docEl.clientWidth;\n                    if (!clientWidth) return;\n                    docEl.style.fontSize = 20 * (clientWidth / 360) + 'px';\n                };\n            if (!doc.addEventListener) return;\n            win.addEventListener(resizeEvt, recalc, false);\n            doc.addEventListener('DOMContentLoaded', recalc, false);\n        })(document, window);\n    <\/script>\n<\/head>\n<body>\n<div class=\"entry\">\n    <h1 class=\"title\">食药监：七批次产品不合格 天猫又上榜<\/h1>\n    <div class=\"meta\">\n        <span class=\"source\">来源：健康一线<\/span>\n        <span class=\"datetime\">时间：2016-07-13 04:17<\/span>\n    <\/div>\n    <div class=\"content typo\">\n        <p>近期，国家食品药品监督管理总局组织抽检水产制品、食糖、调味品、粮食及粮食制品、食用油、油脂及其制品、酒类等6类食品618批次样品，抽样检验项目合格样品611批次，不合格样品7批次。<\/p>\n<p style=\"text-align: center;\"><img src=\"http://upload.vodjk.com/2016/0713/1468397803310.jpg\" border=\"0\" alt=\"1\" /><\/p>\n<p><strong>具体的不合格产品情况如下：<\/strong><\/p>\n<p>(一)深圳岁宝百货有限公司景田店销售的标称你口四洲(汕头)有限公司生产的四洲紫菜(原味)，菌落总数检出值为2800000 CFU/g。比标准规定(不超过30000 CFU/g)高出92.3倍。<\/p>\n<p>(二)喵在岛旗舰店在天猫(网站)销售的标称青岛大道品牌管理有限公司委托(中外合资)烟台优美食品有限公司生产的香辣鳗鱼丝，菌落总数检出值为83000 CFU/g。比标准规定(不超过30000 CFU/g)高出1.8倍。<\/p>\n<p>(三)武汉武商量贩连锁有限公司常青花园量贩店销售的标称洪湖市水乡特产开发有限公司生产的武昌鱼(红烧)，挥发性盐基氮检出值为143 mg/kg。比标准规定(不超过40 mg/kg)高出2.6倍。<\/p>\n<p>(四)谷谷香官方旗舰店在1号店(网站)销售的标称深圳市谷力贸易有限公司委托云南大理市八千方食品厂生产的红冰糖(多晶黄冰糖)，色值检出值为837IU。比标准规定(不超过270IU)高出2.1倍。<\/p>\n<p style=\"text-align: center;\"><img src=\"http://upload.vodjk.com/2016/0713/1468397827771.jpg\" border=\"0\" alt=\"2\" /><\/p>\n<p>(五)中伟餐饮配料调味之家在淘宝网销售的标称张家港市新菊味精有限公司生产的无盐味精，谷氨酸钠检出值为90.50%。比标准规定(不低于99.0%)低8.5个百分点。<\/p>\n<p>(六)谷谷香官方旗舰店在1号店(网站)销售的标称深圳市谷力贸易有限公司委托云南大理市八千方食品厂生产的传统古法手工红糖(赤砂糖)，总糖分检出值为86.58%。比标准规定(不低于92.0%)低5.42个百分点。<\/p>\n<p>(七)圣农食品专营店在天猫(网站)销售的标称大理市亚润食品有限公司生产的单晶冰糖，蔗糖分检出值为99.2%。比标准规定(不低于99.7%)低0.5个百分点。<\/p>\n<p>对上述抽检中发现的不合格产品，生产企业所在地江苏、山东、湖北、广东、云南等省食品药品监管部门已责令企业查清产品流向，召回不合格产品，并分析原因进行整改;经营单位所在地上海、浙江、湖北、广东等省(市)食品药品监管部门已要求有关单位立即采取下架等措施，控制风险，并依法予以查处。查处情况于2016年8月31日前报国家食品药品监督管理总局并向社会公布。<\/p>\n<p>原标题：七批产品被检出不合格天猫、1号店又上榜<\/p>\n    <\/div>\n<\/div>\n<\/body>\n<\/html>","comments":0,"share":{"url":"http://m.vodjk.com/yldt/160713/505380.shtml","title":"食药监：七批次产品不合格 天猫又上榜","info":"","thumb":"http://upload.vodjk.com/2016/0713/1468397833558.jpg"},"published":1468397841},"related":[{"contentid":526368,"modelid":1,"title":"天猫首次发布医疗及健康服务行业标准","url":"http://m.vodjk.com/yldt/160714/526368.shtml","thumb":"http://upload.vodjk.com/2016/0714/1468485710302.jpg"},{"contentid":520286,"modelid":1,"title":"国家食药监总局：安全用药 务必注意四点","url":"http://m.vodjk.com/yldt/160714/520286.shtml","thumb":"http://upload.vodjk.com/2016/0714/1468473065709.jpg"},{"contentid":505380,"modelid":1,"title":"食药监：七批次产品不合格 天猫又上榜","url":"http://m.vodjk.com/yldt/160713/505380.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468397833558.jpg"},{"contentid":505378,"modelid":1,"title":"食药监：强生、罗氏诊断等外企主动召回3款产品","url":"http://m.vodjk.com/yjfc/160713/505378.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468397323444.jpg"},{"contentid":505328,"modelid":1,"title":"食药监：天津2家药企获得GMP证书","url":"http://m.vodjk.com/yjfc/160713/505328.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468381979987.jpg"}],"version":"V1"}}
     */

    private int code;
    private String message;
    /**
     * content : {"article":{"contentid":505380,"modelid":1,"html":"<!doctype html>\n<html lang=\"zh-CN\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"format-detection\" content=\"telephone=no,email=no\"/>\n    <meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no\"/>\n    <title>食药监：七批次产品不合格 天猫又上榜<\/title>\n    <style type=\"text/css\">\n        * {\n            margin: 0;\n            padding: 0;\n            -webkit-box-sizing: border-box;\n            -moz-box-sizing: border-box;\n            box-sizing: border-box;\n        }\n        article,\n        aside,\n        details,\n        figcaption,\n        figure,\n        footer,\n        header,\n        main,\n        menu,\n        nav,\n        section,\n        summary {\n             \n            display: block;\n        }\n        audio,\n        canvas,\n        progress,\n        video {\n            display: inline-block;\n        }\n        audio:not([controls]) {\n            display: none;\n            height: 0;\n        }\n        progress {\n            vertical-align: baseline;\n        }\n        template,\n        [hidden] {\n            display: none;\n        }\n        a {\n            background-color: transparent;\n             \n            -webkit-text-decoration-skip: objects;\n             \n        }\n        a:active,\n        a:hover {\n            outline-width: 0;\n        }\n        abbr[title] {\n            border-bottom: none;\n             \n            text-decoration: underline;\n             \n            text-decoration: underline dotted;\n             \n        }\n        b,\n        strong {\n            font-weight: inherit;\n        }\n        b,\n        strong {\n            font-weight: bolder;\n        }\n        dfn {\n            font-style: italic;\n        }\n        mark {\n            background-color: #ff0;\n            color: #000;\n        }\n        small {\n            font-size: 80%;\n        }\n        sub,\n        sup {\n            font-size: 75%;\n            line-height: 0;\n            position: relative;\n            vertical-align: baseline;\n        }\n        sub {\n            bottom: -0.25em;\n        }\n        sup {\n            top: -0.5em;\n        }\n        img {\n            border-style: none;\n        }\n        svg:not(:root) {\n            overflow: hidden;\n        }\n        code,\n        kbd,\n        pre,\n        samp {\n            font-family: monospace, monospace;\n             \n            font-size: 1em;\n             \n        }\n        figure {\n            margin: 1em 40px;\n        }\n        hr {\n            box-sizing: content-box;\n             \n            height: 0;\n             \n            overflow: visible;\n             \n        }\n        button,\n        input,\n        select,\n        textarea {\n            font: inherit;\n             \n            margin: 0;\n             \n        }\n        optgroup {\n            font-weight: bold;\n        }\n        button,\n        input {\n             \n            overflow: visible;\n        }\n        button,\n        select {\n             \n            text-transform: none;\n        }\n        button,\n        html [type=\"button\"],\n        [type=\"reset\"],\n        [type=\"submit\"] {\n            -webkit-appearance: button;\n             \n        }\n        button::-moz-focus-inner,\n        [type=\"button\"]::-moz-focus-inner,\n        [type=\"reset\"]::-moz-focus-inner,\n        [type=\"submit\"]::-moz-focus-inner {\n            border-style: none;\n            padding: 0;\n        }\n        button:-moz-focusring,\n        [type=\"button\"]:-moz-focusring,\n        [type=\"reset\"]:-moz-focusring,\n        [type=\"submit\"]:-moz-focusring {\n            outline: 1px dotted ButtonText;\n        }\n        fieldset {\n            border: 1px solid #c0c0c0;\n            margin: 0 2px;\n            padding: 0.35em 0.625em 0.75em;\n        }\n        legend {\n            box-sizing: border-box;\n             \n            color: inherit;\n             \n            display: table;\n             \n            max-width: 100%;\n             \n            padding: 0;\n             \n            white-space: normal;\n             \n        }\n        html,\n        body {\n            background: #fff;\n            font-family: \"Helvetica Neue\", Helvetica, sans-serif;\n            text-rendering: optimizelegibility;\n            -webkit-font-smoothing: antialiased;\n            -moz-osx-font-smoothing: grayscale;\n            -webkit-text-size-adjust: 100%;\n            -ms-text-size-adjust: 100%;\n        }\n        .entry {\n            margin: 0.69444444rem 0 0.69444444rem 0.69444444rem;\n        }\n        .entry .title {\n            padding-right: 0.69444444rem;\n            font-size: 0.93749999rem;\n            line-height: 1.33680555rem;\n            color: #333;\n        }\n        .entry .meta {\n            margin-top: 0.34722222rem;\n            padding-right: 0.69444444rem;\n            padding-bottom: 0.52083333rem;\n            border-bottom: solid 1px #dddddd;\n            font-size: 0.69444444rem;\n            color: #999999;\n        }\n        .entry .meta .datetime {\n            margin-left: 0.86805555rem;\n        }\n        .entry .content {\n            margin-top: 0.52083333rem;\n            padding-right: 0.69444444rem;\n            color: #555;\n            font-size: 0.83333333rem;\n        }\n        .entry .content p {\n            margin-bottom: 0.69444444rem;\n            line-height: 1.35416666rem;\n        }\n        .entry .content p > img {\n            display: block;\n            margin: 0 auto;\n            max-width: 100%;\n        }\n    <\/style>\n    <script>\n        (function (doc, win) {\n            var docEl = doc.documentElement,\n                resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',\n                recalc = function () {\n                    var clientWidth = docEl.clientWidth;\n                    if (!clientWidth) return;\n                    docEl.style.fontSize = 20 * (clientWidth / 360) + 'px';\n                };\n            if (!doc.addEventListener) return;\n            win.addEventListener(resizeEvt, recalc, false);\n            doc.addEventListener('DOMContentLoaded', recalc, false);\n        })(document, window);\n    <\/script>\n<\/head>\n<body>\n<div class=\"entry\">\n    <h1 class=\"title\">食药监：七批次产品不合格 天猫又上榜<\/h1>\n    <div class=\"meta\">\n        <span class=\"source\">来源：健康一线<\/span>\n        <span class=\"datetime\">时间：2016-07-13 04:17<\/span>\n    <\/div>\n    <div class=\"content typo\">\n        <p>近期，国家食品药品监督管理总局组织抽检水产制品、食糖、调味品、粮食及粮食制品、食用油、油脂及其制品、酒类等6类食品618批次样品，抽样检验项目合格样品611批次，不合格样品7批次。<\/p>\n<p style=\"text-align: center;\"><img src=\"http://upload.vodjk.com/2016/0713/1468397803310.jpg\" border=\"0\" alt=\"1\" /><\/p>\n<p><strong>具体的不合格产品情况如下：<\/strong><\/p>\n<p>(一)深圳岁宝百货有限公司景田店销售的标称你口四洲(汕头)有限公司生产的四洲紫菜(原味)，菌落总数检出值为2800000 CFU/g。比标准规定(不超过30000 CFU/g)高出92.3倍。<\/p>\n<p>(二)喵在岛旗舰店在天猫(网站)销售的标称青岛大道品牌管理有限公司委托(中外合资)烟台优美食品有限公司生产的香辣鳗鱼丝，菌落总数检出值为83000 CFU/g。比标准规定(不超过30000 CFU/g)高出1.8倍。<\/p>\n<p>(三)武汉武商量贩连锁有限公司常青花园量贩店销售的标称洪湖市水乡特产开发有限公司生产的武昌鱼(红烧)，挥发性盐基氮检出值为143 mg/kg。比标准规定(不超过40 mg/kg)高出2.6倍。<\/p>\n<p>(四)谷谷香官方旗舰店在1号店(网站)销售的标称深圳市谷力贸易有限公司委托云南大理市八千方食品厂生产的红冰糖(多晶黄冰糖)，色值检出值为837IU。比标准规定(不超过270IU)高出2.1倍。<\/p>\n<p style=\"text-align: center;\"><img src=\"http://upload.vodjk.com/2016/0713/1468397827771.jpg\" border=\"0\" alt=\"2\" /><\/p>\n<p>(五)中伟餐饮配料调味之家在淘宝网销售的标称张家港市新菊味精有限公司生产的无盐味精，谷氨酸钠检出值为90.50%。比标准规定(不低于99.0%)低8.5个百分点。<\/p>\n<p>(六)谷谷香官方旗舰店在1号店(网站)销售的标称深圳市谷力贸易有限公司委托云南大理市八千方食品厂生产的传统古法手工红糖(赤砂糖)，总糖分检出值为86.58%。比标准规定(不低于92.0%)低5.42个百分点。<\/p>\n<p>(七)圣农食品专营店在天猫(网站)销售的标称大理市亚润食品有限公司生产的单晶冰糖，蔗糖分检出值为99.2%。比标准规定(不低于99.7%)低0.5个百分点。<\/p>\n<p>对上述抽检中发现的不合格产品，生产企业所在地江苏、山东、湖北、广东、云南等省食品药品监管部门已责令企业查清产品流向，召回不合格产品，并分析原因进行整改;经营单位所在地上海、浙江、湖北、广东等省(市)食品药品监管部门已要求有关单位立即采取下架等措施，控制风险，并依法予以查处。查处情况于2016年8月31日前报国家食品药品监督管理总局并向社会公布。<\/p>\n<p>原标题：七批产品被检出不合格天猫、1号店又上榜<\/p>\n    <\/div>\n<\/div>\n<\/body>\n<\/html>","comments":0,"share":{"url":"http://m.vodjk.com/yldt/160713/505380.shtml","title":"食药监：七批次产品不合格 天猫又上榜","info":"","thumb":"http://upload.vodjk.com/2016/0713/1468397833558.jpg"},"published":1468397841},"related":[{"contentid":526368,"modelid":1,"title":"天猫首次发布医疗及健康服务行业标准","url":"http://m.vodjk.com/yldt/160714/526368.shtml","thumb":"http://upload.vodjk.com/2016/0714/1468485710302.jpg"},{"contentid":520286,"modelid":1,"title":"国家食药监总局：安全用药 务必注意四点","url":"http://m.vodjk.com/yldt/160714/520286.shtml","thumb":"http://upload.vodjk.com/2016/0714/1468473065709.jpg"},{"contentid":505380,"modelid":1,"title":"食药监：七批次产品不合格 天猫又上榜","url":"http://m.vodjk.com/yldt/160713/505380.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468397833558.jpg"},{"contentid":505378,"modelid":1,"title":"食药监：强生、罗氏诊断等外企主动召回3款产品","url":"http://m.vodjk.com/yjfc/160713/505378.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468397323444.jpg"},{"contentid":505328,"modelid":1,"title":"食药监：天津2家药企获得GMP证书","url":"http://m.vodjk.com/yjfc/160713/505328.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468381979987.jpg"}],"version":"V1"}
     */

    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * article : {"contentid":505380,"modelid":1,"html":"<!doctype html>\n<html lang=\"zh-CN\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"format-detection\" content=\"telephone=no,email=no\"/>\n    <meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no\"/>\n    <title>食药监：七批次产品不合格 天猫又上榜<\/title>\n    <style type=\"text/css\">\n        * {\n            margin: 0;\n            padding: 0;\n            -webkit-box-sizing: border-box;\n            -moz-box-sizing: border-box;\n            box-sizing: border-box;\n        }\n        article,\n        aside,\n        details,\n        figcaption,\n        figure,\n        footer,\n        header,\n        main,\n        menu,\n        nav,\n        section,\n        summary {\n             \n            display: block;\n        }\n        audio,\n        canvas,\n        progress,\n        video {\n            display: inline-block;\n        }\n        audio:not([controls]) {\n            display: none;\n            height: 0;\n        }\n        progress {\n            vertical-align: baseline;\n        }\n        template,\n        [hidden] {\n            display: none;\n        }\n        a {\n            background-color: transparent;\n             \n            -webkit-text-decoration-skip: objects;\n             \n        }\n        a:active,\n        a:hover {\n            outline-width: 0;\n        }\n        abbr[title] {\n            border-bottom: none;\n             \n            text-decoration: underline;\n             \n            text-decoration: underline dotted;\n             \n        }\n        b,\n        strong {\n            font-weight: inherit;\n        }\n        b,\n        strong {\n            font-weight: bolder;\n        }\n        dfn {\n            font-style: italic;\n        }\n        mark {\n            background-color: #ff0;\n            color: #000;\n        }\n        small {\n            font-size: 80%;\n        }\n        sub,\n        sup {\n            font-size: 75%;\n            line-height: 0;\n            position: relative;\n            vertical-align: baseline;\n        }\n        sub {\n            bottom: -0.25em;\n        }\n        sup {\n            top: -0.5em;\n        }\n        img {\n            border-style: none;\n        }\n        svg:not(:root) {\n            overflow: hidden;\n        }\n        code,\n        kbd,\n        pre,\n        samp {\n            font-family: monospace, monospace;\n             \n            font-size: 1em;\n             \n        }\n        figure {\n            margin: 1em 40px;\n        }\n        hr {\n            box-sizing: content-box;\n             \n            height: 0;\n             \n            overflow: visible;\n             \n        }\n        button,\n        input,\n        select,\n        textarea {\n            font: inherit;\n             \n            margin: 0;\n             \n        }\n        optgroup {\n            font-weight: bold;\n        }\n        button,\n        input {\n             \n            overflow: visible;\n        }\n        button,\n        select {\n             \n            text-transform: none;\n        }\n        button,\n        html [type=\"button\"],\n        [type=\"reset\"],\n        [type=\"submit\"] {\n            -webkit-appearance: button;\n             \n        }\n        button::-moz-focus-inner,\n        [type=\"button\"]::-moz-focus-inner,\n        [type=\"reset\"]::-moz-focus-inner,\n        [type=\"submit\"]::-moz-focus-inner {\n            border-style: none;\n            padding: 0;\n        }\n        button:-moz-focusring,\n        [type=\"button\"]:-moz-focusring,\n        [type=\"reset\"]:-moz-focusring,\n        [type=\"submit\"]:-moz-focusring {\n            outline: 1px dotted ButtonText;\n        }\n        fieldset {\n            border: 1px solid #c0c0c0;\n            margin: 0 2px;\n            padding: 0.35em 0.625em 0.75em;\n        }\n        legend {\n            box-sizing: border-box;\n             \n            color: inherit;\n             \n            display: table;\n             \n            max-width: 100%;\n             \n            padding: 0;\n             \n            white-space: normal;\n             \n        }\n        html,\n        body {\n            background: #fff;\n            font-family: \"Helvetica Neue\", Helvetica, sans-serif;\n            text-rendering: optimizelegibility;\n            -webkit-font-smoothing: antialiased;\n            -moz-osx-font-smoothing: grayscale;\n            -webkit-text-size-adjust: 100%;\n            -ms-text-size-adjust: 100%;\n        }\n        .entry {\n            margin: 0.69444444rem 0 0.69444444rem 0.69444444rem;\n        }\n        .entry .title {\n            padding-right: 0.69444444rem;\n            font-size: 0.93749999rem;\n            line-height: 1.33680555rem;\n            color: #333;\n        }\n        .entry .meta {\n            margin-top: 0.34722222rem;\n            padding-right: 0.69444444rem;\n            padding-bottom: 0.52083333rem;\n            border-bottom: solid 1px #dddddd;\n            font-size: 0.69444444rem;\n            color: #999999;\n        }\n        .entry .meta .datetime {\n            margin-left: 0.86805555rem;\n        }\n        .entry .content {\n            margin-top: 0.52083333rem;\n            padding-right: 0.69444444rem;\n            color: #555;\n            font-size: 0.83333333rem;\n        }\n        .entry .content p {\n            margin-bottom: 0.69444444rem;\n            line-height: 1.35416666rem;\n        }\n        .entry .content p > img {\n            display: block;\n            margin: 0 auto;\n            max-width: 100%;\n        }\n    <\/style>\n    <script>\n        (function (doc, win) {\n            var docEl = doc.documentElement,\n                resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',\n                recalc = function () {\n                    var clientWidth = docEl.clientWidth;\n                    if (!clientWidth) return;\n                    docEl.style.fontSize = 20 * (clientWidth / 360) + 'px';\n                };\n            if (!doc.addEventListener) return;\n            win.addEventListener(resizeEvt, recalc, false);\n            doc.addEventListener('DOMContentLoaded', recalc, false);\n        })(document, window);\n    <\/script>\n<\/head>\n<body>\n<div class=\"entry\">\n    <h1 class=\"title\">食药监：七批次产品不合格 天猫又上榜<\/h1>\n    <div class=\"meta\">\n        <span class=\"source\">来源：健康一线<\/span>\n        <span class=\"datetime\">时间：2016-07-13 04:17<\/span>\n    <\/div>\n    <div class=\"content typo\">\n        <p>近期，国家食品药品监督管理总局组织抽检水产制品、食糖、调味品、粮食及粮食制品、食用油、油脂及其制品、酒类等6类食品618批次样品，抽样检验项目合格样品611批次，不合格样品7批次。<\/p>\n<p style=\"text-align: center;\"><img src=\"http://upload.vodjk.com/2016/0713/1468397803310.jpg\" border=\"0\" alt=\"1\" /><\/p>\n<p><strong>具体的不合格产品情况如下：<\/strong><\/p>\n<p>(一)深圳岁宝百货有限公司景田店销售的标称你口四洲(汕头)有限公司生产的四洲紫菜(原味)，菌落总数检出值为2800000 CFU/g。比标准规定(不超过30000 CFU/g)高出92.3倍。<\/p>\n<p>(二)喵在岛旗舰店在天猫(网站)销售的标称青岛大道品牌管理有限公司委托(中外合资)烟台优美食品有限公司生产的香辣鳗鱼丝，菌落总数检出值为83000 CFU/g。比标准规定(不超过30000 CFU/g)高出1.8倍。<\/p>\n<p>(三)武汉武商量贩连锁有限公司常青花园量贩店销售的标称洪湖市水乡特产开发有限公司生产的武昌鱼(红烧)，挥发性盐基氮检出值为143 mg/kg。比标准规定(不超过40 mg/kg)高出2.6倍。<\/p>\n<p>(四)谷谷香官方旗舰店在1号店(网站)销售的标称深圳市谷力贸易有限公司委托云南大理市八千方食品厂生产的红冰糖(多晶黄冰糖)，色值检出值为837IU。比标准规定(不超过270IU)高出2.1倍。<\/p>\n<p style=\"text-align: center;\"><img src=\"http://upload.vodjk.com/2016/0713/1468397827771.jpg\" border=\"0\" alt=\"2\" /><\/p>\n<p>(五)中伟餐饮配料调味之家在淘宝网销售的标称张家港市新菊味精有限公司生产的无盐味精，谷氨酸钠检出值为90.50%。比标准规定(不低于99.0%)低8.5个百分点。<\/p>\n<p>(六)谷谷香官方旗舰店在1号店(网站)销售的标称深圳市谷力贸易有限公司委托云南大理市八千方食品厂生产的传统古法手工红糖(赤砂糖)，总糖分检出值为86.58%。比标准规定(不低于92.0%)低5.42个百分点。<\/p>\n<p>(七)圣农食品专营店在天猫(网站)销售的标称大理市亚润食品有限公司生产的单晶冰糖，蔗糖分检出值为99.2%。比标准规定(不低于99.7%)低0.5个百分点。<\/p>\n<p>对上述抽检中发现的不合格产品，生产企业所在地江苏、山东、湖北、广东、云南等省食品药品监管部门已责令企业查清产品流向，召回不合格产品，并分析原因进行整改;经营单位所在地上海、浙江、湖北、广东等省(市)食品药品监管部门已要求有关单位立即采取下架等措施，控制风险，并依法予以查处。查处情况于2016年8月31日前报国家食品药品监督管理总局并向社会公布。<\/p>\n<p>原标题：七批产品被检出不合格天猫、1号店又上榜<\/p>\n    <\/div>\n<\/div>\n<\/body>\n<\/html>","comments":0,"share":{"url":"http://m.vodjk.com/yldt/160713/505380.shtml","title":"食药监：七批次产品不合格 天猫又上榜","info":"","thumb":"http://upload.vodjk.com/2016/0713/1468397833558.jpg"},"published":1468397841}
         * related : [{"contentid":526368,"modelid":1,"title":"天猫首次发布医疗及健康服务行业标准","url":"http://m.vodjk.com/yldt/160714/526368.shtml","thumb":"http://upload.vodjk.com/2016/0714/1468485710302.jpg"},{"contentid":520286,"modelid":1,"title":"国家食药监总局：安全用药 务必注意四点","url":"http://m.vodjk.com/yldt/160714/520286.shtml","thumb":"http://upload.vodjk.com/2016/0714/1468473065709.jpg"},{"contentid":505380,"modelid":1,"title":"食药监：七批次产品不合格 天猫又上榜","url":"http://m.vodjk.com/yldt/160713/505380.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468397833558.jpg"},{"contentid":505378,"modelid":1,"title":"食药监：强生、罗氏诊断等外企主动召回3款产品","url":"http://m.vodjk.com/yjfc/160713/505378.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468397323444.jpg"},{"contentid":505328,"modelid":1,"title":"食药监：天津2家药企获得GMP证书","url":"http://m.vodjk.com/yjfc/160713/505328.shtml","thumb":"http://upload.vodjk.com/2016/0713/1468381979987.jpg"}]
         * version : V1
         */

        private ContentEntity content;

        public void setContent(ContentEntity content) {
            this.content = content;
        }

        public ContentEntity getContent() {
            return content;
        }

        public static class ContentEntity {
            /**
             * contentid : 505380
             * modelid : 1
             * html : <!doctype html>
             <html lang="zh-CN">
             <head>
             <meta charset="UTF-8">
             <meta name="format-detection" content="telephone=no,email=no"/>
             <meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
             <title>食药监：七批次产品不合格 天猫又上榜</title>
             <style type="text/css">
             * {
             margin: 0;
             padding: 0;
             -webkit-box-sizing: border-box;
             -moz-box-sizing: border-box;
             box-sizing: border-box;
             }
             article,
             aside,
             details,
             figcaption,
             figure,
             footer,
             header,
             main,
             menu,
             nav,
             section,
             summary {

             display: block;
             }
             audio,
             canvas,
             progress,
             video {
             display: inline-block;
             }
             audio:not([controls]) {
             display: none;
             height: 0;
             }
             progress {
             vertical-align: baseline;
             }
             template,
             [hidden] {
             display: none;
             }
             a {
             background-color: transparent;

             -webkit-text-decoration-skip: objects;

             }
             a:active,
             a:hover {
             outline-width: 0;
             }
             abbr[title] {
             border-bottom: none;

             text-decoration: underline;

             text-decoration: underline dotted;

             }
             b,
             strong {
             font-weight: inherit;
             }
             b,
             strong {
             font-weight: bolder;
             }
             dfn {
             font-style: italic;
             }
             mark {
             background-color: #ff0;
             color: #000;
             }
             small {
             font-size: 80%;
             }
             sub,
             sup {
             font-size: 75%;
             line-height: 0;
             position: relative;
             vertical-align: baseline;
             }
             sub {
             bottom: -0.25em;
             }
             sup {
             top: -0.5em;
             }
             img {
             border-style: none;
             }
             svg:not(:root) {
             overflow: hidden;
             }
             code,
             kbd,
             pre,
             samp {
             font-family: monospace, monospace;

             font-size: 1em;

             }
             figure {
             margin: 1em 40px;
             }
             hr {
             box-sizing: content-box;

             height: 0;

             overflow: visible;

             }
             button,
             input,
             select,
             textarea {
             font: inherit;

             margin: 0;

             }
             optgroup {
             font-weight: bold;
             }
             button,
             input {

             overflow: visible;
             }
             button,
             select {

             text-transform: none;
             }
             button,
             html [type="button"],
             [type="reset"],
             [type="submit"] {
             -webkit-appearance: button;

             }
             button::-moz-focus-inner,
             [type="button"]::-moz-focus-inner,
             [type="reset"]::-moz-focus-inner,
             [type="submit"]::-moz-focus-inner {
             border-style: none;
             padding: 0;
             }
             button:-moz-focusring,
             [type="button"]:-moz-focusring,
             [type="reset"]:-moz-focusring,
             [type="submit"]:-moz-focusring {
             outline: 1px dotted ButtonText;
             }
             fieldset {
             border: 1px solid #c0c0c0;
             margin: 0 2px;
             padding: 0.35em 0.625em 0.75em;
             }
             legend {
             box-sizing: border-box;

             color: inherit;

             display: table;

             max-width: 100%;

             padding: 0;

             white-space: normal;

             }
             html,
             body {
             background: #fff;
             font-family: "Helvetica Neue", Helvetica, sans-serif;
             text-rendering: optimizelegibility;
             -webkit-font-smoothing: antialiased;
             -moz-osx-font-smoothing: grayscale;
             -webkit-text-size-adjust: 100%;
             -ms-text-size-adjust: 100%;
             }
             .entry {
             margin: 0.69444444rem 0 0.69444444rem 0.69444444rem;
             }
             .entry .title {
             padding-right: 0.69444444rem;
             font-size: 0.93749999rem;
             line-height: 1.33680555rem;
             color: #333;
             }
             .entry .meta {
             margin-top: 0.34722222rem;
             padding-right: 0.69444444rem;
             padding-bottom: 0.52083333rem;
             border-bottom: solid 1px #dddddd;
             font-size: 0.69444444rem;
             color: #999999;
             }
             .entry .meta .datetime {
             margin-left: 0.86805555rem;
             }
             .entry .content {
             margin-top: 0.52083333rem;
             padding-right: 0.69444444rem;
             color: #555;
             font-size: 0.83333333rem;
             }
             .entry .content p {
             margin-bottom: 0.69444444rem;
             line-height: 1.35416666rem;
             }
             .entry .content p > img {
             display: block;
             margin: 0 auto;
             max-width: 100%;
             }
             </style>
             <script>
             (function (doc, win) {
             var docEl = doc.documentElement,
             resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
             recalc = function () {
             var clientWidth = docEl.clientWidth;
             if (!clientWidth) return;
             docEl.style.fontSize = 20 * (clientWidth / 360) + 'px';
             };
             if (!doc.addEventListener) return;
             win.addEventListener(resizeEvt, recalc, false);
             doc.addEventListener('DOMContentLoaded', recalc, false);
             })(document, window);
             </script>
             </head>
             <body>
             <div class="entry">
             <h1 class="title">食药监：七批次产品不合格 天猫又上榜</h1>
             <div class="meta">
             <span class="source">来源：健康一线</span>
             <span class="datetime">时间：2016-07-13 04:17</span>
             </div>
             <div class="content typo">
             <p>近期，国家食品药品监督管理总局组织抽检水产制品、食糖、调味品、粮食及粮食制品、食用油、油脂及其制品、酒类等6类食品618批次样品，抽样检验项目合格样品611批次，不合格样品7批次。</p>
             <p style="text-align: center;"><img src="http://upload.vodjk.com/2016/0713/1468397803310.jpg" border="0" alt="1" /></p>
             <p><strong>具体的不合格产品情况如下：</strong></p>
             <p>(一)深圳岁宝百货有限公司景田店销售的标称你口四洲(汕头)有限公司生产的四洲紫菜(原味)，菌落总数检出值为2800000 CFU/g。比标准规定(不超过30000 CFU/g)高出92.3倍。</p>
             <p>(二)喵在岛旗舰店在天猫(网站)销售的标称青岛大道品牌管理有限公司委托(中外合资)烟台优美食品有限公司生产的香辣鳗鱼丝，菌落总数检出值为83000 CFU/g。比标准规定(不超过30000 CFU/g)高出1.8倍。</p>
             <p>(三)武汉武商量贩连锁有限公司常青花园量贩店销售的标称洪湖市水乡特产开发有限公司生产的武昌鱼(红烧)，挥发性盐基氮检出值为143 mg/kg。比标准规定(不超过40 mg/kg)高出2.6倍。</p>
             <p>(四)谷谷香官方旗舰店在1号店(网站)销售的标称深圳市谷力贸易有限公司委托云南大理市八千方食品厂生产的红冰糖(多晶黄冰糖)，色值检出值为837IU。比标准规定(不超过270IU)高出2.1倍。</p>
             <p style="text-align: center;"><img src="http://upload.vodjk.com/2016/0713/1468397827771.jpg" border="0" alt="2" /></p>
             <p>(五)中伟餐饮配料调味之家在淘宝网销售的标称张家港市新菊味精有限公司生产的无盐味精，谷氨酸钠检出值为90.50%。比标准规定(不低于99.0%)低8.5个百分点。</p>
             <p>(六)谷谷香官方旗舰店在1号店(网站)销售的标称深圳市谷力贸易有限公司委托云南大理市八千方食品厂生产的传统古法手工红糖(赤砂糖)，总糖分检出值为86.58%。比标准规定(不低于92.0%)低5.42个百分点。</p>
             <p>(七)圣农食品专营店在天猫(网站)销售的标称大理市亚润食品有限公司生产的单晶冰糖，蔗糖分检出值为99.2%。比标准规定(不低于99.7%)低0.5个百分点。</p>
             <p>对上述抽检中发现的不合格产品，生产企业所在地江苏、山东、湖北、广东、云南等省食品药品监管部门已责令企业查清产品流向，召回不合格产品，并分析原因进行整改;经营单位所在地上海、浙江、湖北、广东等省(市)食品药品监管部门已要求有关单位立即采取下架等措施，控制风险，并依法予以查处。查处情况于2016年8月31日前报国家食品药品监督管理总局并向社会公布。</p>
             <p>原标题：七批产品被检出不合格天猫、1号店又上榜</p>
             </div>
             </div>
             </body>
             </html>
             * comments : 0
             * share : {"url":"http://m.vodjk.com/yldt/160713/505380.shtml","title":"食药监：七批次产品不合格 天猫又上榜","info":"","thumb":"http://upload.vodjk.com/2016/0713/1468397833558.jpg"}
             * published : 1468397841
             */

            private ArticleEntity article;
            private String version;
            /**
             * contentid : 526368
             * modelid : 1
             * title : 天猫首次发布医疗及健康服务行业标准
             * url : http://m.vodjk.com/yldt/160714/526368.shtml
             * thumb : http://upload.vodjk.com/2016/0714/1468485710302.jpg
             */

            private List<RelatedEntity> related;

            public void setArticle(ArticleEntity article) {
                this.article = article;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public void setRelated(List<RelatedEntity> related) {
                this.related = related;
            }

            public ArticleEntity getArticle() {
                return article;
            }

            public String getVersion() {
                return version;
            }

            public List<RelatedEntity> getRelated() {
                return related;
            }

            public static class ArticleEntity {
                private int contentid;
                private int modelid;
                private String html;
                private int comments;
                /**
                 * url : http://m.vodjk.com/yldt/160713/505380.shtml
                 * title : 食药监：七批次产品不合格 天猫又上榜
                 * info :
                 * thumb : http://upload.vodjk.com/2016/0713/1468397833558.jpg
                 */

                private ShareEntity share;
                private int published;

                public void setContentid(int contentid) {
                    this.contentid = contentid;
                }

                public void setModelid(int modelid) {
                    this.modelid = modelid;
                }

                public void setHtml(String html) {
                    this.html = html;
                }

                public void setComments(int comments) {
                    this.comments = comments;
                }

                public void setShare(ShareEntity share) {
                    this.share = share;
                }

                public void setPublished(int published) {
                    this.published = published;
                }

                public int getContentid() {
                    return contentid;
                }

                public int getModelid() {
                    return modelid;
                }

                public String getHtml() {
                    return html;
                }

                public int getComments() {
                    return comments;
                }

                public ShareEntity getShare() {
                    return share;
                }

                public int getPublished() {
                    return published;
                }

                public static class ShareEntity {
                    private String url;
                    private String title;
                    private String info;
                    private String thumb;

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public void setThumb(String thumb) {
                        this.thumb = thumb;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public String getThumb() {
                        return thumb;
                    }
                }
            }

            public static class RelatedEntity {
                private int contentid;
                private int modelid;
                private String title;
                private String url;
                private String thumb;

                public void setContentid(int contentid) {
                    this.contentid = contentid;
                }

                public void setModelid(int modelid) {
                    this.modelid = modelid;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public int getContentid() {
                    return contentid;
                }

                public int getModelid() {
                    return modelid;
                }

                public String getTitle() {
                    return title;
                }

                public String getUrl() {
                    return url;
                }

                public String getThumb() {
                    return thumb;
                }
            }
        }
    }
}
