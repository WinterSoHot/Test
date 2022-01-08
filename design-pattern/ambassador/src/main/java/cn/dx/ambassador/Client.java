
package cn.dx.ambassador;

import lombok.extern.slf4j.Slf4j;

/**
 * A simple Client.
 */
@Slf4j
public class Client {

  private final ServiceAmbassador serviceAmbassador = new ServiceAmbassador();

  long useService(int value) {
    long result = serviceAmbassador.doRemoteFunction(value);
    log.info("Service result: {}", result);
    return result;
  }
}
