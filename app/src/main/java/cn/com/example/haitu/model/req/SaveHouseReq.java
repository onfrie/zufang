package cn.com.example.haitu.model.req;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yt on 2018/2/13.
 * 提交合租房源 请求实体
 */

public class SaveHouseReq implements Parcelable {

    private String RecrntType;        //房源类型 1整租2合租
    private String Adress;    //小区地址百度地图自动带出
    private String BuildingNumbe;            //楼号
    private String Unit;    //单元
    private String City;    //市编号
    private String CityName;    //市名称
    private String CellName;    //小区名称
    private String RoomId;    //门牌号
    private String ShiNumber;    //几室
    private String TingNumber;        //几厅
    private String WeiNumber;    //几卫
    private String NowFloor;    //当前层
    private String AllFloor;    //总层
    private String Area;     //面积
    private String Price;    //价格
    private String PublicPeibei;        //配备
    private String PublicTeshe;        //特色
    private String PublicImg;    //公开区域图片
    private String Fangguanyuan;        //房管员
    private String CreatePerson;        //创建人

    public SaveHouseReq() {
    }

    public SaveHouseReq(String recrntType, String adress, String buildingNumbe, String unit, String city, String cityName, String cellName, String roomId, String shiNumber, String tingNumber, String weiNumber, String nowFloor, String allFloor, String area, String price, String publicPeibei, String publicTeshe, String publicImg, String fangguanyuan, String createPerson) {
        RecrntType = recrntType;
        Adress = adress;
        BuildingNumbe = buildingNumbe;
        Unit = unit;
        City = city;
        CityName = cityName;
        CellName = cellName;
        RoomId = roomId;
        ShiNumber = shiNumber;
        TingNumber = tingNumber;
        WeiNumber = weiNumber;
        NowFloor = nowFloor;
        AllFloor = allFloor;
        Area = area;
        Price = price;
        PublicPeibei = publicPeibei;
        PublicTeshe = publicTeshe;
        PublicImg = publicImg;
        Fangguanyuan = fangguanyuan;
        CreatePerson = createPerson;
    }

    protected SaveHouseReq(Parcel in) {
        RecrntType = in.readString();
        Adress = in.readString();
        BuildingNumbe = in.readString();
        Unit = in.readString();
        City = in.readString();
        CityName = in.readString();
        CellName = in.readString();
        RoomId = in.readString();
        ShiNumber = in.readString();
        TingNumber = in.readString();
        WeiNumber = in.readString();
        NowFloor = in.readString();
        AllFloor = in.readString();
        Area = in.readString();
        Price = in.readString();
        PublicPeibei = in.readString();
        PublicTeshe = in.readString();
        PublicImg = in.readString();
        Fangguanyuan = in.readString();
        CreatePerson = in.readString();
    }

    public static final Creator<SaveHouseReq> CREATOR = new Creator<SaveHouseReq>() {
        @Override
        public SaveHouseReq createFromParcel(Parcel in) {
            return new SaveHouseReq(in);
        }

        @Override
        public SaveHouseReq[] newArray(int size) {
            return new SaveHouseReq[size];
        }
    };

    public String getRecrntType() {
        return RecrntType;
    }

    public void setRecrntType(String recrntType) {
        RecrntType = recrntType;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getBuildingNumbe() {
        return BuildingNumbe;
    }

    public void setBuildingNumbe(String buildingNumbe) {
        BuildingNumbe = buildingNumbe;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getCellName() {
        return CellName;
    }

    public void setCellName(String cellName) {
        CellName = cellName;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public String getShiNumber() {
        return ShiNumber;
    }

    public void setShiNumber(String shiNumber) {
        ShiNumber = shiNumber;
    }

    public String getTingNumber() {
        return TingNumber;
    }

    public void setTingNumber(String tingNumber) {
        TingNumber = tingNumber;
    }

    public String getWeiNumber() {
        return WeiNumber;
    }

    public void setWeiNumber(String weiNumber) {
        WeiNumber = weiNumber;
    }

    public String getNowFloor() {
        return NowFloor;
    }

    public void setNowFloor(String nowFloor) {
        NowFloor = nowFloor;
    }

    public String getAllFloor() {
        return AllFloor;
    }

    public void setAllFloor(String allFloor) {
        AllFloor = allFloor;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPublicPeibei() {
        return PublicPeibei;
    }

    public void setPublicPeibei(String publicPeibei) {
        PublicPeibei = publicPeibei;
    }

    public String getPublicTeshe() {
        return PublicTeshe;
    }

    public void setPublicTeshe(String publicTeshe) {
        PublicTeshe = publicTeshe;
    }

    public String getPublicImg() {
        return PublicImg;
    }

    public void setPublicImg(String publicImg) {
        PublicImg = publicImg;
    }

    public String getFangguanyuan() {
        return Fangguanyuan;
    }

    public void setFangguanyuan(String fangguanyuan) {
        Fangguanyuan = fangguanyuan;
    }

    public String getCreatePerson() {
        return CreatePerson;
    }

    public void setCreatePerson(String createPerson) {
        CreatePerson = createPerson;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(RecrntType);
        dest.writeString(Adress);
        dest.writeString(BuildingNumbe);
        dest.writeString(Unit);
        dest.writeString(City);
        dest.writeString(CityName);
        dest.writeString(CellName);
        dest.writeString(RoomId);
        dest.writeString(ShiNumber);
        dest.writeString(TingNumber);
        dest.writeString(WeiNumber);
        dest.writeString(NowFloor);
        dest.writeString(AllFloor);
        dest.writeString(Area);
        dest.writeString(Price);
        dest.writeString(PublicPeibei);
        dest.writeString(PublicTeshe);
        dest.writeString(PublicImg);
        dest.writeString(Fangguanyuan);
        dest.writeString(CreatePerson);
    }
}
