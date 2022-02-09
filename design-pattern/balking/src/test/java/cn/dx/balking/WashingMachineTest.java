package cn.dx.balking;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gudongxian
 * @date 2022/2/9
 */
class WashingMachineTest {

    private final FakeDelayProvider fakeDelayProvider = new FakeDelayProvider();

    @Test
    void wash() {
        WashingMachine washingMachine = new WashingMachine(fakeDelayProvider);
        washingMachine.wash();
        washingMachine.wash();

        WashingMachineState washingMachineState = washingMachine.getWashingMachineState();
        fakeDelayProvider.task.run();

        assertEquals(WashingMachineState.WASHING, washingMachineState);
        assertEquals(WashingMachineState.ENABLED, washingMachine.getWashingMachineState());
    }


    @Test
    void endOfWashing() {
        WashingMachine washingMachine = new WashingMachine();
        washingMachine.wash();
        assertEquals(WashingMachineState.ENABLED, washingMachine.getWashingMachineState());
    }

    private static class FakeDelayProvider implements DelayProvider {
        private Runnable task;

        @Override
        public void executeAfterDelay(long interval, TimeUnit timeUnit, Runnable task) {
            this.task = task;
        }
    }
}