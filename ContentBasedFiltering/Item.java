package com.company;

public class Item {
    int ID;
    //vector
    tags;
    public Item() {
        ID = 0;
        tags = null;
    }
    public Item(int id) {
        ID = id;
        tags = null;
    }
    public int getID() {
        return this.ID;
    }
    public //vector//
    getTags() {
        return this.tags;
    }
    public void addTag(String tag1) {
        tags = //add tag1 to tags;
    }
    public void setID(int id) {
        ID = id;
    }
}