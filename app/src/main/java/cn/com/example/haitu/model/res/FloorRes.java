package cn.com.example.haitu.model.res;

/**
 * Created by Dell on 2018/2/9.
 */

public class FloorRes {
    String floor;
    boolean select;

    public String getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        return "FloorRes{" +
                "floor='" + floor + '\'' +
                ", select=" + select +
                '}';
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
