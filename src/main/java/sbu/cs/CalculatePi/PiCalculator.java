package sbu.cs.CalculatePi;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    private static BigDecimal pi = new BigDecimal(3);


    public static class SectionCalculate implements Runnable {
        private final BigDecimal n;

        public SectionCalculate(BigDecimal n) //nth section
        {
            this.n = n;
        }

        @Override
        public void run() {
            if (Integer.parseInt(String.valueOf(n)) % 2 == 0) {
                BigDecimal numerator = new BigDecimal(4);
//                BigDecimal mmm = new BigDecimal(n.multiply(new BigDecimal(2)))
                BigDecimal denominator = (n.multiply(new BigDecimal(2)).multiply(n.multiply(new BigDecimal(2)).add(new BigDecimal(1))).multiply(n.multiply(new BigDecimal(2)).add(new BigDecimal(2))));
                BigDecimal result = numerator.divide(denominator, new MathContext(1000));
                add(result);
            } else if (Integer.parseInt(String.valueOf(n)) % 2 == 1) {
//                BigDecimal result = new BigDecimal(4 / ((2 * n) * ((2 * n) + 1) * ((2 * n) + 2)) * (-1));
//                add(result);
            }
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

    public static synchronized void add(BigDecimal result){
        pi = pi.add(result);
    }

    public static String calculate(int floatingPoint) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 100; i++) {
            SectionCalculate sectionCalculate = new SectionCalculate(new BigDecimal(i));
            threadPool.execute(sectionCalculate);
        }
        threadPool.shutdown();


        return pi.toString();
    }

    public static void main(String[] args) {
        // Use the main function to test the code yourself

        System.out.println(calculate(1000000));
    }
}
