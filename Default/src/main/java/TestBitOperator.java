/**
 * TestBitOperator 位运算尝试
 *
 * @author dongxian
 * @version 1.0
 * 20-6-8 下午10:44
 **/
public class TestBitOperator {

    public static void main(String[] args) {
        long l = 1L; //　long类型　８个字节，64位运算
        System.out.println("l:" + Long.toBinaryString(l));
        System.out.println("l << 63:" + Long.toBinaryString(l << 63)); // 第64位为1
        System.out.println("l << 64:" + Long.toBinaryString(l << 64)); // 第1位为1
        System.out.println("l << 65:" + Long.toBinaryString(l << 65)); // 第2位位1
        System.out.println("l << 100:" + Long.toBinaryString(l << 100)); // 第 100 - 63 = 37 为1

        int i = 1;
        System.out.println("i :" + Integer.toBinaryString(i));
        System.out.println("i << 31:" + Integer.toBinaryString(i << 31));
        System.out.println("i << 32:" + Integer.toBinaryString(i << 32));
        System.out.println("i << 64:" + Integer.toBinaryString(i << 64));

        int a = 10;
        int b = 32;
        System.out.printf("a(%s) ^ b(%s):%s%n", Integer.toBinaryString(a), Integer.toBinaryString(b), Integer.toBinaryString(a ^ b));
        System.out.printf("a(%s) & b(%s):%s%n", Integer.toBinaryString(a), Integer.toBinaryString(b), Integer.toBinaryString(a & b));
        System.out.printf("a(%s) | b(%s):%s%n", Integer.toBinaryString(a), Integer.toBinaryString(b), Integer.toBinaryString(a | b));
    }
}
