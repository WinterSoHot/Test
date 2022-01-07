package cn.dx.adapter;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public class FishingBoatAdapter implements RowingBoat {
    private final FishingBoat fishingBoat = new FishingBoat();

    @Override
    public void row() {
        fishingBoat.sail();
    }
}
