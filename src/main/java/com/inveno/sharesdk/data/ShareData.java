package com.inveno.sharesdk.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.inveno.sharesdk.utils.Tools;

/**分享实体
 * Created by zheng.hu on 2016/4/8.
 */
public class ShareData implements Parcelable{

    public static final int SHARETYPE_IMAGEANDTEXT = 0;//图文
    public static final int SHARETYPE_IMAGE = 1;//图片
    public static final int SHARETYPE_TEXT = 2;//文字
    public static final int SHARETYPE_MUSIC = 3;//音乐
    public static final int SHARETYPE_VIDEO = 4;//视频
    public static final int SHARETYPE_WEB = 5;//网页
    public static final int SHARETYPE_VOICE = 6;//声音

    private String text = "";//要分享的文字
    private String appName = "";//返回按钮位置要展示的内容（不填则为：返回）
    private String description;//文字描述
    private String title;//标题
    private String imageUrl;//图片链接
    private String musicUrl;//音乐链接
    private String videoUrl;//视频链接
    private String targetUrl;//点击跳转
    private Bitmap bitmap;//图片资源
    private int byteLenth;//图片数组长度
    private byte[] bytes;//图片的字节数组
    private int shareType = -1;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public int getShareType()
    {
        return this.shareType;
    }

    public void setShareType(int shareType)
    {
        this.shareType = shareType;
    }

    public String getMusicUrl() {
        return this.musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getByteLenth() {
        return byteLenth;
    }

    public void setByteLenth(int byteLenth) {
        this.byteLenth = byteLenth;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        bytes = Tools.getBytes(bitmap);
        byteLenth = bytes.length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public ShareData clone() throws CloneNotSupportedException
    {
        return (ShareData)super.clone();
    }

    public ShareData() {
    }

    public ShareData(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(appName);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(musicUrl);
        dest.writeString(videoUrl);
        dest.writeString(targetUrl);
        dest.writeInt(byteLenth);
        bytes = Tools.getBytes(bitmap);
        dest.writeByteArray(bytes);
        dest.writeInt(shareType);
    }

    @SuppressWarnings("unchecked")
    private void readFromParcel(Parcel in) {
        text = in.readString();
        appName = in.readString();
        description = in.readString();
        title = in.readString();
        imageUrl = in.readString();
        musicUrl = in.readString();
        videoUrl = in.readString();
        targetUrl = in.readString();
        byteLenth = in.readInt();
        bytes = new byte[byteLenth];
        in.readByteArray(bytes);
        bitmap = Tools.getBitmap(bytes);
        shareType = in.readInt();
    }

    public static final Parcelable.Creator<ShareData> CREATOR = new Parcelable.Creator<ShareData>() {
        public ShareData createFromParcel(Parcel in) {
            return new ShareData(in);
        }

        public ShareData[] newArray(int size) {
            return new ShareData[size];
        }
    };
}
