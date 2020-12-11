public class FractionNumber {

    public int numerator;
    public int denominator;

    FractionNumber(int a, int b) {
        int gcd = 1;

        if ((a > 0) && (b > 0)) {
            for (int i = 1; i <= a && i <= b; i++) {
                if (a % i == 0 && b % i == 0)
                    gcd = i;
            }
        } else if ((a < 0) && (b > 0)) {
            for (int i = 1; i <= Math.abs(a) && i <= b; i++) {
                if (Math.abs(a) % i == 0 && b % i == 0)
                    gcd = i;
            }
        } else if ((a > 0) && (b < 0)) {
            for (int i = 1; i <= a && i <= Math.abs(b); i++) {
                if (a % i == 0 && Math.abs(b) % i == 0)
                    gcd = i;
            }
        } else if ((a < 0) && (b < 0)) {
            b = Math.abs(b);
            a = Math.abs(a);
            for (int i = 1; i <= a && i <= b; i++) {
                if (a % i == 0 && b % i == 0)
                    gcd = i;
            }
        }

        final int c = a / gcd;
        final int d = b / gcd;
        numerator = c;
        denominator = d;
    }


    public FractionNumber add(FractionNumber other) {
        int sumNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int sumDenominator = this.denominator * other.denominator;
        return new FractionNumber(sumNumerator, sumDenominator);
    }

    public FractionNumber substract(FractionNumber other) {
        int subNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int subDenominator = this.denominator * other.denominator;
        return new FractionNumber(subNumerator, subDenominator);
    }

    public FractionNumber multiply(FractionNumber other) {
        int multNumerator = this.numerator * other.numerator;
        int multDenominator = this.denominator * other.denominator;
        return new FractionNumber(multNumerator, multDenominator);
    }

    public FractionNumber divide(FractionNumber other) {
        int divNumerator = this.numerator * other.denominator;
        int divDenominator = this.denominator * other.numerator;
        return new FractionNumber(divNumerator, divDenominator);
    }

    @Override
    public String toString() {
        if (denominator == 1)
            return String.format("%d", numerator);

        if (denominator == 0)
            return "DIVISION BY ZERO";

        if ((denominator < 0) && (numerator < Math.abs(denominator)) || (numerator < denominator)) {
            return String.format("%d/%d", numerator, denominator);
        }

        if (((numerator > Math.abs(denominator)) && (denominator < 0)) || (numerator > denominator)) {
            int integer = numerator / denominator;
            int newNumerator = numerator - (integer * denominator);
            return String.format("%d %d/%d", integer, newNumerator, denominator);
        }

        return String.format("%d/%d", numerator, denominator);
    }

}
