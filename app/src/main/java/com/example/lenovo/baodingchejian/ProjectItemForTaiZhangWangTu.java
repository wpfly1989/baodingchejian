package com.example.lenovo.baodingchejian;

import android.widget.Button;
import android.widget.TextView;

public class ProjectItemForTaiZhangWangTu {
    private String url;
    private String name;

    public ProjectItemForTaiZhangWangTu(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

