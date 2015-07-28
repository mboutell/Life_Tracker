package edu.rose_hulman.life_tracker;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Billy York on 7/19/2015.
 */
public class List {
    private HashMap<Attribute, Item> items = new HashMap<>();
    private HashSet<Attribute.AttributeType> activeAttributes = new HashSet<>();
    private String name;
    private long mId;

    public List() {
        //this.name = getResources().getString(R.string.list_title_text);
    }

    public List(String name){
        this.name = name;
    }

    public long getId(){
        return mId;
    }

    public void setId(long id){
        this.mId = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

//    public String getDataByAttribute(Attribute attribute){
//        return this.items.get(attribute).getData();
//    }

//    public void setDataByAttribute(Attribute attribute, String data){
//        this.items.put(attribute, new Item(data));
//    }

    public HashMap<Attribute, Item> getList(){
        return this.items;
    }

    public HashSet<Attribute.AttributeType> getActiveAttributes(){
        return this.activeAttributes;
    }

    public void setAttributeActive(Attribute.AttributeType attribute){
        if(!this.activeAttributes.contains(attribute)) {
            this.activeAttributes.add(attribute);
        }
        else {
            this.activeAttributes.remove(attribute);
        }
    }

    public void saveActiveAttributes(boolean[] savedAttributes){
        for(int i = 0; i < savedAttributes.length; i++){
            if(savedAttributes[i] && !activeAttributes.contains(new Attribute(i).getAttributeType())){
                activeAttributes.add(new Attribute(i).getAttributeType());
            }
        }
    }

//    public void addToList(Attribute attribute, String data){
//        this.items.put(attribute, new Item(data));
//    }

    Item removeFromList(Item item){
        return this.items.remove(item);
    }
}
