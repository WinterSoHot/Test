package cn.dx.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class AgentDemo {

    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("====premain方法执行1====");
        System.out.println(agentArgs);
        instrumentation = inst;
        ClassFileTransformer transformer = new SimpleClassTransformer();
        inst.addTransformer(transformer);
    }

    public static void premain(String agentArgs) {
        System.out.println("====premain方法执行2====");
        System.out.println(agentArgs);
    }
}
