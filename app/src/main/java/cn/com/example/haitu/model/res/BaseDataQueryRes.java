package cn.com.example.haitu.model.res;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Dell on 2018/2/5.
 */

public class BaseDataQueryRes implements Parcelable {

    private int numberCount;
    private NumberDataBean numberData;
    private int Code;
    private Object Message;

    protected BaseDataQueryRes(Parcel in) {
        numberCount = in.readInt();
        Code = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberCount);
        dest.writeInt(Code);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseDataQueryRes> CREATOR = new Creator<BaseDataQueryRes>() {
        @Override
        public BaseDataQueryRes createFromParcel(Parcel in) {
            return new BaseDataQueryRes(in);
        }

        @Override
        public BaseDataQueryRes[] newArray(int size) {
            return new BaseDataQueryRes[size];
        }
    };

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
    }

    public NumberDataBean getNumberData() {
        return numberData;
    }

    public void setNumberData(NumberDataBean numberData) {
        this.numberData = numberData;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public static class NumberDataBean implements Parcelable {
        private List<PeipeiBean> peipei;
        private List<?> tese;
        private List<CityBean> city;
        private List<UserBean> user;

        protected NumberDataBean(Parcel in) {
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<NumberDataBean> CREATOR = new Creator<NumberDataBean>() {
            @Override
            public NumberDataBean createFromParcel(Parcel in) {
                return new NumberDataBean(in);
            }

            @Override
            public NumberDataBean[] newArray(int size) {
                return new NumberDataBean[size];
            }
        };

        public List<PeipeiBean> getPeipei() {
            return peipei;
        }

        public void setPeipei(List<PeipeiBean> peipei) {
            this.peipei = peipei;
        }

        public List<?> getTese() {
            return tese;
        }

        public void setTese(List<?> tese) {
            this.tese = tese;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public static class PeipeiBean implements Parcelable {
            /**
             * Id : 51
             * Name : 问问
             * Code : WEN4WEN4
             * Type : 0
             * IsActive : 1
             * pageindex : 0
             * pagesize : 0
             */

            private int Id;
            private String Name;
            private String Code;
            private int Type;
            private int IsActive;
            private int pageindex;
            private int pagesize;

            protected PeipeiBean(Parcel in) {
                Id = in.readInt();
                Name = in.readString();
                Code = in.readString();
                Type = in.readInt();
                IsActive = in.readInt();
                pageindex = in.readInt();
                pagesize = in.readInt();
            }

            public static final Creator<PeipeiBean> CREATOR = new Creator<PeipeiBean>() {
                @Override
                public PeipeiBean createFromParcel(Parcel in) {
                    return new PeipeiBean(in);
                }

                @Override
                public PeipeiBean[] newArray(int size) {
                    return new PeipeiBean[size];
                }
            };

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public int getIsActive() {
                return IsActive;
            }

            public void setIsActive(int IsActive) {
                this.IsActive = IsActive;
            }

            public int getPageindex() {
                return pageindex;
            }

            public void setPageindex(int pageindex) {
                this.pageindex = pageindex;
            }

            public int getPagesize() {
                return pagesize;
            }

            public void setPagesize(int pagesize) {
                this.pagesize = pagesize;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(Id);
                parcel.writeString(Name);
                parcel.writeString(Code);
                parcel.writeInt(Type);
                parcel.writeInt(IsActive);
                parcel.writeInt(pageindex);
                parcel.writeInt(pagesize);
            }
        }

        public static class CityBean implements Parcelable {
            /**
             * Id : 320400
             * RegionName : 常州市
             * RegType : 3
             * pageindex : 0
             * pagesize : 0
             */

            private int Id;
            private String RegionName;
            private int RegType;
            private int pageindex;
            private int pagesize;

            protected CityBean(Parcel in) {
                Id = in.readInt();
                RegionName = in.readString();
                RegType = in.readInt();
                pageindex = in.readInt();
                pagesize = in.readInt();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(Id);
                dest.writeString(RegionName);
                dest.writeInt(RegType);
                dest.writeInt(pageindex);
                dest.writeInt(pagesize);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<CityBean> CREATOR = new Creator<CityBean>() {
                @Override
                public CityBean createFromParcel(Parcel in) {
                    return new CityBean(in);
                }

                @Override
                public CityBean[] newArray(int size) {
                    return new CityBean[size];
                }
            };

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getRegionName() {
                return RegionName;
            }

            public void setRegionName(String RegionName) {
                this.RegionName = RegionName;
            }

            public int getRegType() {
                return RegType;
            }

            public void setRegType(int RegType) {
                this.RegType = RegType;
            }

            public int getPageindex() {
                return pageindex;
            }

            public void setPageindex(int pageindex) {
                this.pageindex = pageindex;
            }

            public int getPagesize() {
                return pagesize;
            }

            public void setPagesize(int pagesize) {
                this.pagesize = pagesize;
            }
        }

        public static class UserBean implements Parcelable {
            /**
             * Id : 0
             * Name : EC02
             * Password : null
             * pageindex : 0
             * pagesize : 0
             */

            private int Id;
            private String Name;
            private Object Password;
            private int pageindex;
            private int pagesize;

            protected UserBean(Parcel in) {
                Id = in.readInt();
                Name = in.readString();
                pageindex = in.readInt();
                pagesize = in.readInt();
            }

            public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                @Override
                public UserBean createFromParcel(Parcel in) {
                    return new UserBean(in);
                }

                @Override
                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public Object getPassword() {
                return Password;
            }

            public void setPassword(Object Password) {
                this.Password = Password;
            }

            public int getPageindex() {
                return pageindex;
            }

            public void setPageindex(int pageindex) {
                this.pageindex = pageindex;
            }

            public int getPagesize() {
                return pagesize;
            }

            public void setPagesize(int pagesize) {
                this.pagesize = pagesize;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(Id);
                parcel.writeString(Name);
                parcel.writeInt(pageindex);
                parcel.writeInt(pagesize);
            }
        }
    }
}
