/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package uk.co.swilson.advent;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;

public class App {
    private static final SolverFactory solverFactory = new SolverFactory();

    public static void main(String[] args) {
        String day = getDay(args);
        if (day != null) {
            System.out.println("Running day " + day);
            try {
                InputStream is  = App.class.getClassLoader().getResourceAsStream("input" + day + ".txt");
                var input = new String(is.readAllBytes());
                var solver = solverFactory.forDay(day);

                var resultPartOne = solver.solvePartOneToString(input);
                System.out.println("Part 1:");
                System.out.println(resultPartOne);

                var resultPartTwo = solver.solvePartTwoToString(input);
                System.out.println("Part 2:");
                System.out.println(resultPartTwo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Specify a day to run, or run during advent");
        }
    }

    private static String getDay(String[] args) {
        if (args.length > 0) {
            return args[0];
        }
        var now = LocalDate.now();
        if (now.getMonth() == Month.DECEMBER && now.getDayOfMonth() <= 25) {
            if (now.getDayOfMonth() == 25) {
                System.out.println("Merry Christmas!");
            } else {
                System.out.printf(
                        "Only %d day%s until Christmas!%n",
                        25 - now.getDayOfMonth(),
                        now.getDayOfMonth() == 24 ? "" : "s");
            }
            return String.format("%02d", now.getDayOfMonth());
        }
        return null;
    }
}
