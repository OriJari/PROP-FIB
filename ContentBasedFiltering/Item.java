import java.util.*;
import java.io.*;


public class Item {
    int ID;
    String[] tags; //TODO: change to List
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
    public void delTag(String tag1) {
        //...
    }
    public void setID(int id) {
        this.ID = id;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int similarity_with(Item item2) {
        int n1 = this.getNumTags();
        int n2 = item2.getNumTags();
        String[] tags1 = this.getTags();
        String[] tags2 = item2.getTags();
        int result = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (tags1[i] == tags2[j]) ++result;
            }
        }
        return result;
    }
}