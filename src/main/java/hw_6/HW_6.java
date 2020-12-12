package hw_6;

public class HW_6 {
    public static void main(String[] args) {
        FractionNumber fractionNumber1 = new FractionNumber(8, 6);
        FractionNumber fractionNumber2 = new FractionNumber(2, 13);

        FractionNumber resultAdd = fractionNumber1.add(fractionNumber2);
        System.out.println(resultAdd);

        FractionNumber resultSubtract = fractionNumber1.subtract(fractionNumber2);
        System.out.println(resultSubtract);

        FractionNumber resultMultiply = fractionNumber1.multiply(fractionNumber2);
        System.out.println(resultMultiply);

        FractionNumber resultDivide = fractionNumber1.divide(fractionNumber2);
        System.out.println(resultDivide);
    }
}
