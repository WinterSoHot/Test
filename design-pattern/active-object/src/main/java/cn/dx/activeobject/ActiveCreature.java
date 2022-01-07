package cn.dx.activeobject;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author gudongxian
 * @date 2021/10/21
 */
@Slf4j
public abstract class ActiveCreature {

    private BlockingQueue<Runnable> requests;

    private int status;

    private Thread thread;

    private String name;

    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            4, 8, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)
            , new ThreadFactory() {
        final AtomicLong index = new AtomicLong(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, ActiveCreature.class.getSimpleName() + "#" + index.getAndIncrement());
        }
    }
    );


    protected ActiveCreature(String name) {
        this.name = name;
        this.status = 0;
        this.requests = new LinkedBlockingQueue<>();
        executor.execute(() -> {
            boolean infinite = true;
            while (infinite) {
                try {
                    requests.take().run();
                } catch (InterruptedException e) {
                    if (this.status != 0) {
                        log.error("Thread was interrupted, --> {}", e.getMessage());
                    }
                    infinite = false;
                    Thread.currentThread().interrupt();
                }
            }
        });
        this.thread.start();
    }


    /**
     * Eats the porridge.
     *
     * @throws InterruptedException due to firing a new Runnable.
     */
    public void eat() throws InterruptedException {
        requests.put(() -> {
            log.info("{} is eating!", name());
            log.info("{} has finished eating!", name());
        });
    }

    /**
     * Roam in the wastelands.
     *
     * @throws InterruptedException due to firing a new Runnable.
     */
    public void roam() throws InterruptedException {
        requests.put(() ->
                log.info("{} has started to roam in the wastelands.", name())
        );
    }

    /**
     * Returns the name of the creature.
     *
     * @return the name of the creature.
     */
    public String name() {
        return this.name;
    }

    /**
     * Kills the thread of execution.
     *
     * @param status of the thread of execution. 0 == OK, the rest is logging an error.
     */
    public void kill(int status) {
        this.status = status;
        this.thread.interrupt();
    }

    /**
     * Returns the status of the thread of execution.
     *
     * @return the status of the thread of execution.
     */
    public int getStatus() {
        return this.status;
    }
}
