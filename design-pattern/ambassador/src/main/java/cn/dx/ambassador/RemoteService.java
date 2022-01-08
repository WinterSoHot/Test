package cn.dx.ambassador;

import cn.dx.ambassador.util.RandomProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gudongxian
 * @date 2022/1/8
 */
@Slf4j
public class RemoteService implements RemoteServiceInterface {

    private static final int THRESHOLD = 200;
    private static RemoteService service = null;
    private final RandomProvider randomProvider;

    RemoteService(RandomProvider randomProvider) {
        this.randomProvider = randomProvider;
    }

    private RemoteService() {
        this(Math::random);
    }

    static synchronized RemoteService getRemoteService() {
        if (service == null) {
            service = new RemoteService();
        }
        return service;
    }

    @Override
    public long doRemoteFunction(int value) {
        long waitTime = (long) Math.floor(randomProvider.random() * 1000);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            log.error("Thread sleep state interrupted", e);
            Thread.currentThread().interrupt();
        }
        return waitTime <= THRESHOLD ? value * 10L : RemoteServiceStatus.FAILURE.getRemoteServiceStatusValue();
    }
}
