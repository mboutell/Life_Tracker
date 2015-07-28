package edu.rose_hulman.life_tracker;

/**
 * Created by Billy York on 7/19/2015.
 */
public class Item {

    private String mName;
    private String mDescription;
    private float mPrice;
    private int mQuantity;
    private long mId;

    public Item(){

    }

    public String getName(){
        return this.mName;
    }
    public void setName(String name){
        mName = name;
    }
    public String getDescription(){
        return this.mDescription;
    }
    public void setDescription(String description){
        mDescription = description;
    }
    public float getPrice(){
        return this.mPrice;
    }
    public void setPrice(float price){
        mPrice = price;
    }
    public int getQuantity(){
        return this.mQuantity;
    }
    public void setQuantity(int quantity){
        mQuantity = quantity;
    }

    public long getId(){
        return mId;
    }
    public void setId(long id){
        mId = id;
    }

}
