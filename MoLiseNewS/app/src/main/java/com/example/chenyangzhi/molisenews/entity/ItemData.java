package com.example.chenyangzhi.molisenews.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenyangzhi on 2016/9/5.
 */
public class ItemData implements Parcelable {
    private String iconUrl;
    private String title;
    private String contentUrl;

    protected ItemData(Parcel in) {
        iconUrl = in.readString();
        title = in.readString();
        contentUrl = in.readString();
    }

    public static final Creator<ItemData> CREATOR = new Creator<ItemData>() {
        @Override
        public ItemData createFromParcel(Parcel in) {
            ItemData data=new ItemData();
            data.setIconUrl(in.readString());
            data.setTitle(in.readString());
            data.setContentUrl(in.readString());
            return data;
        }

        @Override
        public ItemData[] newArray(int size) {
            return new ItemData[size];
        }
    };

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public ItemData(String iconUrl, String title, String contentUrl) {
        this.iconUrl = iconUrl;
        this.title = title;
        this.contentUrl = contentUrl;
    }

    public ItemData() {
    }

    @Override
    public String toString() {
        return "ItemData{" +
                "iconUrl='" + iconUrl + '\'' +
                ", title='" + title + '\'' +
                ", contentUrl='" + contentUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iconUrl);
        dest.writeString(title);
        dest.writeString(contentUrl);
    }
}
