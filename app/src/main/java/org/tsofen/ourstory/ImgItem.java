package org.tsofen.ourstory;

public class ImgItem {
    String imgName;
    Integer img;

    public ImgItem(String name, Integer img) {
        this.imgName=name;
        this.img=img;
    }

    public String getImgName(){
        return imgName;
    }

    public void setImgName(String name){
        this.imgName=name;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }
}
