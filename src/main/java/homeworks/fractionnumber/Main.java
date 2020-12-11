package homeworks.fractionnumber;

public class Main {
    public static void main(String[] args) {


        FractionNumber frac1 = new FractionNumber(-6, 1);
        FractionNumber frac2 = new FractionNumber(7, 5);

        FractionNumber result = frac1.add(frac2);
        System.out.println(result);

        FractionNumber result2 = frac1.subtract(frac2);
        System.out.println(result2);

        FractionNumber result3 = frac1.multiply(frac2);
        System.out.println(result3);

        FractionNumber result4 = frac1.divide(frac2);
        System.out.println(result4);

    }
}
