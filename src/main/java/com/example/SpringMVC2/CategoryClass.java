package com.example.SpringMVC2;

public class CategoryClass {

    private String catcode;
    private String catdesc;




    public String getCatcode() {
        return catcode;
    }

    public void setCatcode(String catcode) {
        this.catcode = catcode;
    }

    public String getCatdesc() {
        return catdesc;
    }

    public void setCatdesc(String catdesc) {
        this.catdesc = catdesc;
    }

    public CategoryClass(String catcode, String catdesc){
        this.catcode = catcode;
        this.catdesc = catdesc;
    }


}
