package com.ly.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试监控类
 *
 * @author
 */
public class Demo {

    private String name = UUID.randomUUID().toString();


    /**
     * 测试函数
     *
     * @throws InterruptedException
     */
    public List<String> testThread() throws InterruptedException {
        int threadNum = 2;
        List<String> list = new ArrayList<>();
        // 初始化countDown  
        CountDownLatch threadSignal = new CountDownLatch(threadNum);
        // 创建固定长度的线程池  
//      Executor executor = Executors.newFixedThreadPool(threadNum);  
        //此处不可以用接口 需要使用Executor的实现类 ExecutorService  Executor未提供shutdown等方法  
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
//        for (int i = 0; i < threadNum; i++) { // 开threadNum个线程
//            Runnable task = new TestThread(threadSignal);
//            // 执行
//            executor.execute(task);
//
//        }
        executor.execute(new TestThread2(threadSignal, "线程1111", list));
        executor.execute(new TestThread3(threadSignal, "线程2222", list));
        threadSignal.await(); // 等待所有子线程执行完  
        //固定线程池执行完成后 将释放掉资源 退出主进程  
        executor.shutdown();//并不是终止线程的运行，而是禁止在这个Executor中添加新的任务  
        // do work end  
        //退出主进程  
        System.out.println(Thread.currentThread().getName() + "-----------结束.");
        return list;
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) throws InterruptedException {
        Demo test = new Demo();
        List<String> list = test.testThread();
        System.out.println("----获得结果：");
        list.stream().forEach(System.out::println);
    }

    /**
     * @author wangmuming
     *         此可以做完内部类 也可以不做未内部类
     *         作为内部类的时候 有一个好处 就是可以直接引用给类的主对象的成员变量 如此处的name
     *         当然
     */

    private class TestThread2 implements Runnable {
        private CountDownLatch threadsSignal;
        private String myName;
        private List<String> list;

        public TestThread2(CountDownLatch threadsSignal, String myName, List<String> list) {
            this.threadsSignal = threadsSignal;
            this.myName = myName;
            this.list = list;
        }


        public void run() {
            System.out.println(myName + "开始-----");
            int j = 0;
            for (int i = 0; i < 999999999; i++) {
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
            }
            System.out.println("j = " + j);
            list.add("线程1111111");
            threadsSignal.countDown();
            System.out.println(myName + "结束=======  ");
        }
    }

    private class TestThread3 implements Runnable {
        private CountDownLatch threadsSignal;
        private String myName;
        private List<String> list;

        public TestThread3(CountDownLatch threadsSignal, String myName, List<String> list) {
            this.threadsSignal = threadsSignal;
            this.myName = myName;
            this.list = list;
        }


        public void run() {
            System.out.println(myName + "开始-----  ");
            list.add("线程22222");
            int j = 0;
            for (int i = 0; i < 100000000; i++) {
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
                j++;
                j--;
                j++;
            }
            System.out.println("j = " + j);
            threadsSignal.countDown();
            System.out.println(myName + "结束=======");
        }
    }

}