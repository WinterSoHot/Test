package cn.dx.java.senior.core;

import java.util.Base64;

/**
 * @author gudongxian
 * @date 2022/2/15
 */
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        new HelloClassLoader().findClass("cn.dx.java.senior.core.Hello").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "yv66vgAAADQAHwoABgARCQASABMIABQKABUAFgcAFwcAGAEABjxpbml0PgEAAygpVgEABENvZGUB" +
                "AA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAeTGNuL2R4L2ph" +
                "dmEvc2VuaW9yL2NvcmUvSGVsbG87AQAIPGNsaW5pdD4BAApTb3VyY2VGaWxlAQAKSGVsbG8uamF2" +
                "YQwABwAIBwAZDAAaABsBABhIZWxsbyBDbGFzcyBJbml0aWFsaXplZCEHABwMAB0AHgEAHGNuL2R4" +
                "L2phdmEvc2VuaW9yL2NvcmUvSGVsbG8BABBqYXZhL2xhbmcvT2JqZWN0AQAQamF2YS9sYW5nL1N5" +
                "c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0B" +
                "AAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABQAGAAAAAAACAAEABwAIAAEACQAA" +
                "AC8AAQABAAAABSq3AAGxAAAAAgAKAAAABgABAAAABwALAAAADAABAAAABQAMAA0AAAAIAA4ACAAB" +
                "AAkAAAAlAAIAAAAAAAmyAAISA7YABLEAAAABAAoAAAAKAAIAAAAJAAgACgABAA8AAAACABA=";
        byte[] bytes = decode(helloBase64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
