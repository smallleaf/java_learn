package com.share1024.es;

/**
 * \* @Author: yesheng
 * \* Date: 2021/5/10 17:59
 * \* Description:
 * \
 */
public class WebSite {
    public WebSite() {
    }

    public WebSite(String title, String text, String date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    private String title;
    private String text;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}