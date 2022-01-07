package cn.dx.adapter;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public class App {
    public static void main(String[] args) {
        Captain captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
