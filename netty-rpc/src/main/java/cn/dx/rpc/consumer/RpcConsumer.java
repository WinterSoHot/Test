package cn.dx.rpc.consumer;

import cn.dx.rpc.api.IRpcHelloService;
import cn.dx.rpc.api.IRpcService;
import cn.dx.rpc.consumer.proxy.RpcProxy;

/**
 * @author gudongxian
 * @date 2022/1/15
 */
public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHelloService rpcHello = RpcProxy.create(IRpcHelloService.class);
        System.out.println(rpcHello.hello("Tom"));

        IRpcService rpcService = RpcProxy.create(IRpcService.class);
        System.out.println("8 + 2 = " + rpcService.add(8, 2));
        System.out.println("8 - 2 = " + rpcService.sub(8, 2));
        System.out.println("8 * 2 = " + rpcService.mult(8, 2));
        System.out.println("8 / 2 = " + rpcService.div(8, 2));
    }
}
