package com.example.tema5_baseadapter;

public class Datos {
    private int imageResId;
    private String title;
    private String content;
    public Datos(int imageResId, String title, String content){
        this.imageResId=imageResId;
        this.title=title;
        this.content=content;
    }

    public int getImageResId(){
        return imageResId;
    }
    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}
