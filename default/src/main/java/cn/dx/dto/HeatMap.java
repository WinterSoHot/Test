package cn.dx.dto;

import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/7
 */
public class HeatMap {

    private Integer count;
    private String location;
    private List<Info> info;

    public HeatMap() {
    }

    public HeatMap(Integer count, String location) {
        this.count = count;
        this.location = location;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "HeatMap{" +
                "count=" + count +
                ", location='" + location + '\'' +
                ", info=" + info +
                '}';
    }

    public static class Info {
        private String txtId;
        private String msg;

        public Info() {
        }

        public Info(String txtId, String msg) {
            this.txtId = txtId;
            this.msg = msg;
        }

        public String getTxtId() {
            return txtId;
        }

        public void setTxtId(String txtId) {
            this.txtId = txtId;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "txtId='" + txtId + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
