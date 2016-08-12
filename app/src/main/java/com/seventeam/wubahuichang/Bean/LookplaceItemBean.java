package com.seventeam.wubahuichang.Bean;

/**
 * Author: gengjiarong
 * Date: 2016/8/12
 */
public class LookplaceItemBean {

    public String name;
    public int imageId;

    public LookplaceItemBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
