package org.tsofen.ourstory;

public class ImgItem {
    String imgName;
    String img;

    public ImgItem(String name, String img) {
        this.imgName=name;
        this.img=img;
    }

    public String getImgName(){
        return imgName;
    }

    public void setImgName(String name){
        this.imgName=name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
