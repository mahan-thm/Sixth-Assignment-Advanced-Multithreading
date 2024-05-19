package sbu.cs.CalculatePi;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.pow;

public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     * Experiment with different algorithms to find accurate results.
     * <p>
     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.
     *
     * @param floatingPoint the exact number of digits after the floating point
     * @return pi in string format (the string representation of the BigDecimal object)
     */



    public static class SectionCalculate implements Runnable {
        private final int n;

        public SectionCalculate(int n) //nth section
        {
            this.n = n;
        }

        @Override
        public void run() {


//___________________________first way:
//            if (Integer.parseInt(String.valueOf(n)) % 2 == 0) {
//                BigDecimal numerator = new BigDecimal(4);
//                BigDecimal denominator = new BigDecimal(((2 * n) * ((2 * n) + 1) * ((2 * n) + 2)) * (-1));
//                BigDecimal result = numerator.divide(denominator, new MathContext(1000));
//                add(result);
//            } else if (Integer.parseInt(String.valueOf(n)) % 2 == 1) {
//                BigDecimal numerator = new BigDecimal(4);
//                BigDecimal denominator = new BigDecimal(((2 * n) * ((2 * n) + 1) * ((2 * n) + 2)));
//                BigDecimal result = numerator.divide(denominator, new MathContext(1000));
//                add(result);
//            }


// ___________________________second way:
            MathContext mathContext = new MathContext(1000);
            BigDecimal x = new BigDecimal(16).pow(n);
            BigDecimal a = new BigDecimal((8 * n) + 1);
            BigDecimal b = new BigDecimal((8 * n) + 4);
            BigDecimal c = new BigDecimal((8 * n) + 5);
            BigDecimal d = new BigDecimal((8 * n) + 6);

            BigDecimal out = new BigDecimal(1).divide(x, mathContext);
            BigDecimal first = new BigDecimal(4).divide(a, mathContext);
            BigDecimal second = new BigDecimal(2).divide(b, mathContext);
            BigDecimal third = new BigDecimal(1).divide(c, mathContext);
            BigDecimal fourth = new BigDecimal(1).divide(d, mathContext);

            BigDecimal result = (first.subtract(second).subtract(third).subtract(fourth)).multiply(out);

            add(result);


        }

        /**
         * When an object implementing interface {@code Runnable} is used
         * to create a thread, starting the thread causes the object's
         * {@code run} method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method {@code run} is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
    }
    private static BigDecimal pi = BigDecimal.ZERO;

    public static synchronized void add(BigDecimal result) {
        pi = pi.add(result);
    }

    public static String calculate(int floatingPoint) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i <= 100; i++) {  //first way should start with i=1
            SectionCalculate sectionCalculate = new SectionCalculate(i);
            threadPool.execute(sectionCalculate);
        }
        threadPool.shutdown();

        try {
            threadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pi = pi.setScale(floatingPoint, RoundingMode.DOWN);

        return pi.toString();
    }

    public static void main(String[] args) {
        // Use the main function to test the code yourself

        System.out.println(calculate(1000));
    }
}
