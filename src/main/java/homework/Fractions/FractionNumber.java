package homework.Fractions;

public class FractionNumber {
    private int numerator;
    private int denominator;

    public FractionNumber(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        convert();
    }

    public FractionNumber(int number) {
        this.numerator = number;
        this.denominator = 1;
    }

    private void convert() {
        int gcd = greatestCommonDenominator(numerator, denominator);
        if (denominator < 0) {
            this.numerator = -numerator / gcd;
            this.denominator = -denominator / gcd;
        } else {
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }
    }


    // private, наверное
    public static int greatestCommonDenominator(int a, int b) {
        if (a < 0)
            a = -a;
        if (b < 0)
            b = -b;
        int t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private int lowestCommonDenominator(int a, int b) {
        int lowest = a;
        while ((a % b) != 0)
            a += lowest;
        return a;
    }

    public FractionNumber add(FractionNumber b) {
        if (denominator == 0 || b.denominator == 0)
            return new FractionNumber(numerator, 0);
        int lcd = lowestCommonDenominator(denominator, b.denominator);
        int resultNumerator = numerator * lcd / denominator + b.numerator * lcd / b.denominator;
        return new FractionNumber(resultNumerator, lcd);
    }

    public FractionNumber substract(FractionNumber b) {
        if (denominator == 0 || b.denominator == 0)
            return new FractionNumber(numerator, 0);
        int lcd = lowestCommonDenominator(denominator, b.denominator);
        int resultNumerator = numerator * lcd / denominator - b.numerator * lcd / b.denominator;
        return new FractionNumber(resultNumerator, lcd);
    }

    public FractionNumber multiply(FractionNumber b) {
        if (denominator == 0 || b.denominator == 0)
            return new FractionNumber(numerator, 0);
        int resultNumerator = numerator * b.numerator;
        int resultDenominator = denominator * b.denominator;
        return new FractionNumber(resultNumerator, resultDenominator);
    }

    public FractionNumber devide(FractionNumber b) {
        if (denominator == 0 || b.denominator == 0)
            return new FractionNumber(numerator, 0);
        int resultNumerator = b.numerator * denominator;
        int resultDenominator = b.denominator * numerator;
        return new FractionNumber(resultNumerator, resultDenominator);
    }

    @Override
    public String toString() {
        if (this.denominator == 0)
            return "DIVISION BY ZERO";
        // после ретурна жизни нет, else не имеет смысла
        //else
        if (Math.abs(numerator) > Math.abs(denominator) && Math.abs(denominator) != 1) {
            int integerPart = numerator / denominator;
            numerator = numerator - integerPart * denominator;
            return String.format("%d %d/%d", integerPart, Math.abs(numerator), Math.abs(denominator));
        }
        //else
        if (Math.abs(numerator) > Math.abs(denominator) &&
                Math.abs(denominator) == 1 ||
                numerator == 0 ||
                Math.abs(numerator) == Math.abs(denominator)) {
            int integerPart = numerator / denominator;
            return String.format("%d", integerPart);
        }
        //else
        return String.format("%d/%d", numerator, denominator);
    }
}
