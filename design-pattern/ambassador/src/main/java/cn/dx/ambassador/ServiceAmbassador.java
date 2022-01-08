

package cn.dx.ambassador;

import static cn.dx.ambassador.RemoteServiceStatus.FAILURE;
import static java.lang.Thread.sleep;

import lombok.extern.slf4j.Slf4j;

/**
 * ServiceAmbassador provides an interface for a ({@link Client}) to access ({@link RemoteService}).
 * The interface adds logging, latency testing and usage of the service in a safe way that will not
 * add stress to the remote service when connectivity issues occur.
 */
@Slf4j
public class ServiceAmbassador implements RemoteServiceInterface {

  private static final int RETRIES = 3;
  private static final int DELAY_MS = 3000;

  ServiceAmbassador() {
  }

  @Override
  public long doRemoteFunction(int value) {
    return safeCall(value);
  }

  private long checkLatency(int value) {
    long startTime = System.currentTimeMillis();
    long result = RemoteService.getRemoteService().doRemoteFunction(value);
    long timeTaken = System.currentTimeMillis() - startTime;

    log.info("Time taken (ms): {}", timeTaken);
    return result;
  }

  private long safeCall(int value) {
    int retries = 0;
    long result = FAILURE.getRemoteServiceStatusValue();

    for (int i = 0; i < RETRIES; i++) {
      if (retries >= RETRIES) {
        return FAILURE.getRemoteServiceStatusValue();
      }

      if ((result = checkLatency(value)) == FAILURE.getRemoteServiceStatusValue()) {
        log.info("Failed to reach remote: ({})", i + 1);
        retries++;
        try {
          sleep(DELAY_MS);
        } catch (InterruptedException e) {
          log.error("Thread sleep state interrupted", e);
          Thread.currentThread().interrupt();
        }
      } else {
        break;
      }
    }
    return result;
  }
}
