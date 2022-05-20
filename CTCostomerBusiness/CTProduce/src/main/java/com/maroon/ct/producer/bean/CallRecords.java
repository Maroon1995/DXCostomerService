package com.maroon.ct.producer.bean;

public class CallRecords {

    private String maincall;
    private String maincall_name;
    private String bycall;
    private String bycall_name;
    private String data_time;
    private String duration;

    public CallRecords(String maincall, String maincall_name, String bycall, String bycall_name, String data_time, String duration) {
        this.maincall = maincall;
        this.maincall_name = maincall_name;
        this.bycall = bycall;
        this.bycall_name = bycall_name;
        this.data_time = data_time;
        this.duration = duration;
    }

    public String getMaincall() {
        return maincall;
    }

    public void setMaincall(String maincall) {
        this.maincall = maincall;
    }

    public String getMaincall_name() {
        return maincall_name;
    }

    public void setMaincall_name(String maincall_name) {
        this.maincall_name = maincall_name;
    }

    public String getBycall() {
        return bycall;
    }

    public void setBycall(String bycall) {
        this.bycall = bycall;
    }

    public String getBycall_name() {
        return bycall_name;
    }

    public void setBycall_name(String bycall_name) {
        this.bycall_name = bycall_name;
    }

    public String getData_time() {
        return data_time;
    }

    public void setData_time(String data_time) {
        this.data_time = data_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CallRecords{" +
                "maincall='" + maincall + '\'' +
                ", maincall_name='" + maincall_name + '\'' +
                ", bycall='" + bycall + '\'' +
                ", bycall_name='" + bycall_name + '\'' +
                ", data_time='" + data_time + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
