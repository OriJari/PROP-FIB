package item;

import java.util.*;
import java.io.*;


public class Item {
    int ID;
    String[] tags;
    //Constructoras
    public Item() {
        ID = 0;
        tags = null;
    }
    public Item(int id) {
        ID = id;
        tags = null;
    }
    //Consultoras
    public int getID() {
        return this.ID;
    }
    public String[] getTags() {
        return this.tags;
    }

    public int getNumTags() {
        return tags.length();
    }
    //Modificadoras
    public void addTag(String tag1) {
        int n = tags.length();
        String[] new_tags = new String[n+1];
        int i;
        for (i = 0; i < n; i++) new_tags[i] = tags[i];
        new_tags[n] = tag1;
        tags = new_tags;
    }

    public void setID(int id) {
        this.ID = id;
    }
}