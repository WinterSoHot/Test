package cn.dx.balking;

import ch.qos.logback.classic.pattern.ThreadConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author gudongxian
 * @date 2022/2/9
 */
@Slf4j
public class WashingMachine {
    private final DelayProvider delayProvider;
    private WashingMachineState washingMachineState;


    public WashingMachine() {
        this(((interval, timeUnit, task) -> {
            try {
                Thread.sleep(timeUnit.toMillis(interval));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.run();
        }));
    }

    public WashingMachine(DelayProvider delayProvider) {
        this.delayProvider = delayProvider;
        this.washingMachineState = WashingMachineState.ENABLED;
    }

    public WashingMachineState getWashingMachineState() {
        return washingMachineState;
    }

    public void wash() {
        synchronized (this) {
            WashingMachineState state = getWashingMachineState();
            LOGGER.info("{}: Actual machine state:{}", Thread.currentThread().getName(), state);
            if (this.washingMachineState == WashingMachineState.WASHING) {
                LOGGER.error("Cannot wash if the machine has been already washing!");
                return;
            }
            this.washingMachineState = WashingMachineState.WASHING;
        }
        LOGGER.info("{}: Doing the washing", Thread.currentThread().getName());
        this.delayProvider.executeAfterDelay(50, TimeUnit.MILLISECONDS, this::endOfWashing);
    }

    public synchronized void endOfWashing() {
        washingMachineState = WashingMachineState.ENABLED;
        LOGGER.info("{}: Washing completed", Thread.currentThread().getName());
    }
}
