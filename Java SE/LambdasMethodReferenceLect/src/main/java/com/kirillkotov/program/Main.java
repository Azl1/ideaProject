package com.kirillkotov.program;

import com.kirillkotov.util.Util;
import com.kirillkotov.util.Utility;
import com.kirillkotov.util.Worker;
import com.kirillkotov.util.WorkerImpl;

public class Main {
    public static void main(String[] args) {
        /**
         * Implementing interface by class
         */
        WorkerImpl worker1 = new WorkerImpl(10);
        int res1 = worker1.work(5);
        System.out.println("Result of invoke method work in implementing class(Class variable): " + res1);
        Worker worker2 = new WorkerImpl(10);
        int res2 = worker2.work(5);
        System.out.println("Result of invoke method work in implementing class(Interface variable): " + res2);

        int k = 10;
        /**
         * Implementing interface by anonymous class
         */
        Worker worker3 = new Worker() {
            @Override
            public int work(int a) {
                return a * k;
            }
        };
        int res3 = worker3.work(5);
        System.out.println("Result of invoke method work in implementing anonymous class: " + res3);

        /**
         * Implementing interface by lambda expression
         */
        Worker worker4 = a -> a * k;
        int res4 = worker4.work(5);
        System.out.println("Result of invoke method work in implementing lambda expression: " + res4);

        /**
         * Implementing interface by difficult lambda expression
         */
        Worker worker5 = a -> {
            int t = a * 10;
            return t / 10 * k;
        };
        int res5 = worker5.work(5);
        System.out.println("Result of invoke method work in implementing difficult lambda: " + res5);

        /**
         * Implementing interface by static method reference
         */
        Worker worker6 = Util::function;
        int res6 = worker6.work(5);
        System.out.println("Result of invoke method work in implementing static method reference: " + res6);

        /**
         * Implementing interface by method reference
         */
        Utility utility = new Utility(10);
        Worker worker7 = utility::function;
        int res7 = worker7.work(5);
        System.out.println("Result of invoke method work in implementing method reference: " + res7);
    }
}