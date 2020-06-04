package cn.dx.io.agent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class SimpleClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader classLoader, String className, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
        if (className.endsWith("sun/net/www/protocol/http/HttpURLConnection")) {
            ClassPool classPool = ClassPool.getDefault();
            CtClass clazz = null;
            try {
                clazz = classPool.get("sun.net.www.protocol.http.HttpURLConnection");
                CtConstructor[] cs = clazz.getConstructors();
                for (CtConstructor constructor : cs) {
                    constructor.insertAfter("System.out.println(this.getURL);\n");
                }
                byte[] bytecode = clazz.toBytecode();
                clazz.detach();
                return bytecode;
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
