package cn.com.example.haitu.model.res;

import java.util.List;

/**
 * Created by Dell on 2018/2/5.
 */

public class CityListRes {
    /**
     * numberCount : 0
     * numberData : [{"Id":320400,"RegionName":"常州市","RegType":3,"pageindex":0,"pagesize":0}]
     * Message : null
     */

    private int numberCount;
    private int Code;
    private Object Message;
    private List<NumberDataBean> numberData;

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
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

    public List<NumberDataBean> getNumberData() {
        return numberData;
    }

    public void setNumberData(List<NumberDataBean> numberData) {
        this.numberData = numberData;
    }

    public static class NumberDataBean {
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
}
