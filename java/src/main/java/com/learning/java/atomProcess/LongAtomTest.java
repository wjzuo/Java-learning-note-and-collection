package com.learning.java.atomProcess;

/**
 * @program java
 * @author jiuson
 * @create 2019/08/26 15:17
 * @description long atom test
 */
public class LongAtomTest implements Runnable{

    private static long field = 0;

    private volatile long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public LongAtomTest(long value) {
        this.setValue(value);
    }


    public void run() {
        int i = 0;
        while (i < 100000){
            LongAtomTest.field = this.getValue();
            i++;
            long temp = LongAtomTest.field;
            if (temp != 1 && temp != -1){
                System.out.println("出现错误结果" + temp);
                System.exit(0);
            }
        }
        System.out.println("运行正确");
    }

    public static void main(String[] args) throws InterruptedException{
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch + "-bit");
        LongAtomTest t1 = new LongAtomTest(1);
        LongAtomTest t2 = new LongAtomTest(-1);
        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);
        T1.start();
        T2.start();
//        T1.join();
//        T2.join();
    }




}
