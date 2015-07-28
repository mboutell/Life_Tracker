package edu.rose_hulman.life_tracker;

/**
 * Created by Billy York on 7/19/2015.
 */
public class Attribute {
    private AttributeType attributeType;

    public static enum AttributeType {
        NAME, DESCRIPTION, PRICE, QUANTITY, PRIORITY, VOICE, IMAGE, REMINDER, LOCATION, WEB_SOURCE
    }

    public Attribute(int attributeNumber){
        switch (attributeNumber){
            case 0:
                this.attributeType = AttributeType.NAME;
                break;
            case 1:
                this.attributeType = AttributeType.DESCRIPTION;
                break;
            case 2:
                this.attributeType = AttributeType.PRICE;
                break;
            case 3:
                this.attributeType = AttributeType.QUANTITY;
                break;
            case 4:
                this.attributeType = AttributeType.PRIORITY;
                break;
            case 5:
                this.attributeType = AttributeType.VOICE;
                break;
            case 6:
                this.attributeType = AttributeType.IMAGE;
                break;
            case 7:
                this.attributeType = AttributeType.REMINDER;
                break;
            case 8:
                this.attributeType = AttributeType.LOCATION;
                break;
            case 9:
                this.attributeType = AttributeType.WEB_SOURCE;
                break;
            default:
                break;
        }
    }

    public Attribute(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public AttributeType getAttributeType() {
        return this.attributeType;
    }

    public String toString(){
        String type;
        switch (this.attributeType){
            case NAME:
                type = "Name";
                break;
            case DESCRIPTION:
                type = "Description";
                break;
            case PRICE:
                type = "Price";
                break;
            case QUANTITY:
                type = "Quantity";
                break;
            case PRIORITY:
                type = "Priority";
                break;
            case VOICE:
                type = "Voice";
                break;
            case IMAGE:
                type = "Image";
                break;
            case REMINDER:
                type = "Reminder";
                break;
            case LOCATION:
                type = "Location";
                break;
            case WEB_SOURCE:
                type = "Web Source";
                break;
            default:
                type = "null";
                break;
        }
       return  type;
    }
}
