package com.bigdata.webshell.utils;

/**
 * Created by Administrator on 2014/11/21.
 */
public class InitConfig {
    public String getAuthurl() {
        return authurl;
    }

    public void setAuthurl(String authurl) {
        this.authurl = authurl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDefaulturl() {
        return defaulturl;
    }

    public void setDefaulturl(String defaulturl) {
        this.defaulturl = defaulturl;
    }

    public String getRedirectloginrole() {
        return redirectloginrole;
    }

    public void setRedirectloginrole(String redirectloginrole) {
        this.redirectloginrole = redirectloginrole;
    }

    public String getPicid() {
        return picid;
    }

    public void setPicid(String picid) {
        this.picid = picid;
    }

    public String getInitpic() {
        return initpic;
    }

    public void setInitpic(String initpic) {
        this.initpic = initpic;
    }

    private String initpic;
    private String picid;
    private String redirectloginrole;
    private String defaulturl;
    private String msg;
    private String authurl;
    private String navigationbar;

    public String getNavigationbarcolor() {
        return navigationbarcolor;
    }

    public void setNavigationbarcolor(String navigationbarcolor) {
        this.navigationbarcolor = navigationbarcolor;
    }

    public String getNavigationbar() {
        return navigationbar;
    }

    public void setNavigationbar(String navigationbar) {
        this.navigationbar = navigationbar;
    }

    private String navigationbarcolor;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String version;

    public String getUpdateurl() {
        return updateurl;
    }

    public void setUpdateurl(String updateurl) {
        this.updateurl = updateurl;
    }

    private String updateurl;


}
