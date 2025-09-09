package calkylator;

public class Calculator {
    private double x;
    private int k;
    private double epsilon;
    private double exactValue;
    private double calculatedValue;
    private int iterations;

    public Calculator(double x, int k) {
        this.x = x;
        this.k = k;
        this.epsilon = Math.pow(10, -k);
        this.calculatedValue = 0;
        this.iterations = 0;
    }

    public void calculateExponential() {
        double now = 1;
        int n = 1;

        while (Math.abs(now) >= epsilon) {
            calculatedValue += now;
            now = now * x / n;
            n++;
            iterations++;
        }

        exactValue = Math.exp(x);
    }

    public double getX() {
        return x;
    }

    public int getK() {
        return k;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public double getExactValue() {
        return exactValue;
    }

    public double getCalculatedValue() {
        return calculatedValue;
    }

}