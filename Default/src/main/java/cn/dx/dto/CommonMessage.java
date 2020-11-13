package cn.dx.dto;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/7
 */
public class CommonMessage<T> {
    public static final Integer HEATMAP = 1;
    private Integer id;
    private Integer type;
    private T data;

    public CommonMessage() {
    }

    public CommonMessage(Integer id, Integer type) {
        this.id = id;
        this.type = type;
    }

    public CommonMessage(Integer id, Integer type, T data) {
        this.id = id;
        this.type = type;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
