package uk.co.swilson.advent;

public class SolverFactory {
    public Solver forDay(String day) {
        try {
            Class c = Class.forName("uk.co.swilson.advent.day" + day + ".Day" + day);
            return (Solver) c.getConstructor(new Class[0]).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Solver forDay(String day, String input) {
        throw new RuntimeException("Not implemented");
    }
}
