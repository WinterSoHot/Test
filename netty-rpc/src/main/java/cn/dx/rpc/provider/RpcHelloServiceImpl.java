package cn.dx.rpc.provider;

import cn.dx.rpc.api.IRpcHelloService;

/**
 * @author gudongxian
 * @date 2022/1/14
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String hello(String name) {
        return "Hello, " + name + " !";
    }
}
