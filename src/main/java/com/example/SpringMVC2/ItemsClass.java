package com.example.SpringMVC2;

public class ItemsClass {
    private String catcode;
    private String itemdesc;
    private String itemcode;

    public ItemsClass(String itemcode, String itemdesc, String catcode) {
        this.itemcode = itemcode;
        this.itemdesc = itemdesc;
        this.catcode = catcode;
    }

    public String getCatcode() {
        return catcode;
    }

    public void setCatcode(String catcode) {
        this.catcode = catcode;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }


}
