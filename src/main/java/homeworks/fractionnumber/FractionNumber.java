package homeworks.fractionnumber;

public class FractionNumber {
    int numerator;
    int denominator;

    public FractionNumber(int numerator, int denominator) {

        int nod = nod(numerator, denominator);
        this.numerator = numerator / nod;
        this.denominator = denominator / nod;
    }

    public FractionNumber add(FractionNumber second) {
        return calculate(second, true);
    }

    public FractionNumber subtract(FractionNumber second) {
        return calculate(second, false);
    }

    public FractionNumber multiply(FractionNumber second) {
        return new FractionNumber(this.numerator * second.numerator, this.denominator * second.denominator);
    }

    public FractionNumber divide(FractionNumber second) {
        return new FractionNumber(this.numerator * second.denominator, this.denominator * second.numerator);
    }

    public String toString() {
        if (denominator == 0) {
            return "DIVISION BY ZERO";
        }
        //else
        if (Math.abs(denominator) == 1) {
            return String.format("%d", numerator);
        }
        //  else
        if (Math.abs(denominator) > Math.abs(numerator)) {
            return String.format("%d/%d", numerator, denominator);
        }
        //else
        {
            int integerFromFractionNumber = numerator / denominator;
            return String.format("%d %d/%d", integerFromFractionNumber, Math.abs(numerator % denominator), denominator);
        }
    }

    //наибольший общий делитель
    private int nod(int numerator, int denominator) {
        int nod = 1;
        if (denominator != 0) {
            // Math.max(numerator, denominator)
            int limit = Math.abs(numerator) < Math.abs(denominator) ? Math.abs(denominator) : Math.abs(numerator);
            for (int i = limit; i > 1; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    nod = i;
                    break;
                }
            }
        }
        return nod;
    }

    //наименьшее общее кратное
    private int nok(int denominator_1, int denominator_2) {
        int nod = nod(denominator_1, denominator_2);
        if (nod == 1) {
            return denominator_1 * denominator_2;
        } else {
            return denominator_1 / nod * denominator_2;
        }
    }

    private FractionNumber calculate(FractionNumber second, boolean is_plus) {

        int numerator = 0;
        int denominator = 0;

        if (this.denominator == second.denominator) {
            denominator = this.denominator;
            numerator = is_plus ? this.numerator + second.numerator : this.numerator - second.numerator;

        } else {
            int nok = nok(this.denominator, second.denominator);
            if (nok == 0) {
                denominator = nok;
                numerator = nok;
            } else {
                denominator = nok;
                if (is_plus) {
                    numerator = this.numerator * (nok / this.denominator) + second.numerator * (nok / second.denominator);
                } else {
                    numerator = this.numerator * (nok / this.denominator) - second.numerator * (nok / second.denominator);
                }
            }
        }
        return new FractionNumber(numerator, denominator);
    }
}
