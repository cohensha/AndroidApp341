package itp341.cohen.sharon.finalproject.Model;

import android.net.Uri;

/**
 * Created by Sharon on 12/10/2016.
 */
public class CardModel {
    String cardName;
    int imageResourceId;
    int isturned;
    String url;

    public int getIsturned(){
        return isturned;
    }

    public void setIsturned(int isturned){
        this.isturned = isturned;

    }

    public void setCardName(String name){
        this.cardName = name;
    }

    public String getCardName(){
        return cardName;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId){
        this.imageResourceId = imageResourceId;
    }

}
