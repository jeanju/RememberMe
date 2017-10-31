package com.remember.jeanju34min.rememberme;

/**
 * Created by jeanju34.min on 2017-10-31.
 */

public class ListViewItem {
    private String titleStr ;
    private String addrStr ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String addr) {
        addrStr = addr ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getAddr() {
        return this.addrStr ;
    }

}
