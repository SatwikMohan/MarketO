package com.gigawattstechnology.marketo;

public class Item {
    String itemname,itemhead;
    public Item(String itemname, String itemhead) {
        this.itemname = itemname;
        this.itemhead = itemhead;
    }
    public String getItemname() {
        return itemname;
    }

    public String getItemhead() {
        return itemhead;
    }

}
