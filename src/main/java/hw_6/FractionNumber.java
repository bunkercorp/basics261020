package hw_6;

public class FractionNumber {
    private int numerator;
    private int denominator;

    public FractionNumber(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        fractionReduction();
    }

    public int greatestCommonDivisor(int a, int b) {
        return b == 0 ? a : greatestCommonDivisor(b, a % b);
    }

    public int leastCommonMultiple(int a, int b) {
        return a / greatestCommonDivisor(a, b) * b;
    }

    public void fractionReduction() {
        int gcd = greatestCommonDivisor(numerator, denominator);
        if (gcd != 1) {
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }
    }

    public FractionNumber add(FractionNumber fraction) {
        int lcm = leastCommonMultiple(denominator, fraction.denominator);
        int resultNumerator = numerator * lcm / denominator + fraction.numerator * lcm / fraction.denominator;
        return new FractionNumber(resultNumerator, lcm);
    }

    public FractionNumber subtract(FractionNumber fraction) {
        int lcm = leastCommonMultiple(denominator, fraction.denominator);
        int resultNumerator = numerator * lcm / denominator - fraction.numerator * lcm / fraction.denominator;
        return new FractionNumber(resultNumerator, lcm);
    }

    public FractionNumber multiply(FractionNumber fraction) {
        int resultNum = numerator * fraction.numerator;
        int resultDen = denominator * fraction.denominator;
        return new FractionNumber(resultNum, resultDen);

    }

    public FractionNumber divide(FractionNumber fraction) {
        int resultNum = numerator * fraction.denominator;
        int resultDen = denominator * fraction.numerator;
        return new FractionNumber(resultNum, resultDen);
    }

    @Override
    public String toString() {
        if (this.denominator == 0) {
            return "DIVISION BY ZERO";
        } else if (denominator == 1) {
            return String.format("%d", numerator);
        } else if (Math.abs(numerator) < Math.abs(denominator)) {
            return String.format("%d/%d", numerator, denominator);
        } else {
            int wholeFraction = numerator / denominator;
            numerator = numerator - wholeFraction * denominator;
            return String.format("%d %d/%d", wholeFraction, numerator, denominator);
        }
    }
}
