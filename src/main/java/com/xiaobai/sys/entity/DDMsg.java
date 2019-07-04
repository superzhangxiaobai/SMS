package com.xiaobai.sys.entity;

public class DDMsg {

    private String msgtype;
    /**
        text 类型
     *  {
     *     "msgtype": "text",
     *     "text": {
     *         "content": "我就是我, 是不一样的烟火@156xxxx8827"
     *     },
     *     "at": {
     *         "atMobiles": [
     *             "156xxxx8827",
     *             "189xxxx8325"
     *         ],
     *         "isAtAll": false
     *     }
     * }
     */
    public String toTextJsonString(){
        StringBuilder builder=new StringBuilder();
        builder.append("{");
        builder.append("'msgtype':'text',");//强制性要求
        builder.append("'text':{'content':'"+this.getContent()+"'},");
        builder.append("'at':{'atMobiles':["+this.getAtMobiles()+"]},");
        builder.append("'isAtAll':"+this.isAtAll+",");//强制性要求
        builder.append("}");
        return builder.toString();
    }
    private String text;//消息内容 外 text/link
    private String at;
    private String link;
    private String content;//消息内容 内
    private String atMobiles;//被艾特的人手机号
    private boolean isAtAll;//是否艾特所有人
    /**
    //link 类型
     * {
     *     "msgtype": "link",
     *     "link": {
     *         "text": "这个即将发布的新版本，",
     *         "title": "时代的火车向前开",
     *         "picUrl": "",
     *         "messageUrl": "https://www.dingtalk.com/"
     *     }
     * }
     */
    public String toLinkJsonString(){
        StringBuilder builder=new StringBuilder();
        builder.append("{");
        builder.append("'msgtype':'link',");//强制性要求
        builder.append("'link':{");
        builder.append("'text':'"+this.getText()+"',");
        builder.append("'title':'"+this.getTitle()+"',");
        builder.append("'picUrl':'"+this.getPicUrl()+"',");
        builder.append("'messageUrl':'"+this.getMessageUrl()+"',");
        builder.append("}");
        builder.append("}");
        return builder.toString();
    }
    private String title;
    private String messageUrl;
    private String picUrl;

    /**
     * markdown 类型 固定Markdown格式
     * {
     *      "msgtype": "markdown",
     *      "markdown": {
     *          "title":"杭州天气",
     *          "text": "#### 杭州天气 @156xxxx8827\n" +
     *                  "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
     *                  "> ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n"  +
     *                  "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n"
     *      },
     *     "at": {
     *         "atMobiles": [
     *             "156xxxx8827",
     *             "189xxxx8325"
     *         ],
     *         "isAtAll": false
     *     }
     *  }
     */
    public String toMarkdownJsonString(){
        StringBuilder builder=new StringBuilder();
        builder.append("{");
        builder.append("    'msgtype':'markdown',");//强制性要求
        builder.append("    'markdown':{");
        builder.append("        'title':'"+this.getTitle()+"',");
        builder.append("        'text':'"+this.getText()+"',");
        builder.append("     },");
        builder.append("    'at':{");
        builder.append("        'atMobiles':["+this.getAtMobiles()+"],");
        builder.append("        'isAtAll':"+this.isAtAll+",");
        builder.append("    },");
        builder.append("}");
        return builder.toString();
    }
    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(String atMobiles) {
        this.atMobiles = atMobiles;
    }

    public boolean isAtAll() {
        return isAtAll;
    }

    public void setAtAll(boolean atAll) {
        isAtAll = atAll;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
