package com.example.lenovo.baodingchejian;

public class ProjectItemForBanZuTuBiao {
    private String tuBiaoDa;
    private String tuBiaoXiao;
    private String tuBiaoZhong;
    private int imageId;

    public ProjectItemForBanZuTuBiao(String tuBiaoDa, String tuBiaoXiao, String tuBiaoZhong, int imageId) {
        this.imageId = imageId;
        this.tuBiaoDa = tuBiaoDa;
        this.tuBiaoXiao = tuBiaoXiao;
        this.tuBiaoZhong = tuBiaoZhong;
    }

    public String getTuBiaoDa() {
        return tuBiaoDa;
    }

    public void setTuBiaoDa(String tuBiaoDa) {
        this.tuBiaoDa = tuBiaoDa;
    }

    public String getTuBiaoXiao() {
        return tuBiaoXiao;
    }

    public void setTuBiaoXiao(String tuBiaoXiao) {
        this.tuBiaoXiao = tuBiaoXiao;
    }

    public String getTuBiaoZhong() {
        return tuBiaoZhong;
    }

    public void setTuBiaoZhong(String tuBiaoZhong) {
        this.tuBiaoZhong = tuBiaoZhong;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
