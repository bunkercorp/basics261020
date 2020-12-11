public class Main {
    public static void main (String args[])
    {
        FractionNumber a = new FractionNumber(-1,-2);
        FractionNumber b = new FractionNumber(-3,4);
        System.out.println(a.add(b));
        System.out.println(a.substract(b));
        System.out.println(a.multiply(b));
        System.out.println(a.divide(b));
    }
}
