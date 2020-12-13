import java.util.Arrays;

public class FractionNumber {
    final private long num;
    final private long deno;
    private static long gcd;
    //private long lcm;

    public FractionNumber(long numerator, long denominator) {
        if(isNotNull(numerator, denominator)) {

            gcd = defineGcdWithSign(numerator, denominator);
            num = numerator / gcd;
            deno = denominator / gcd;
        }
        else{
            gcd = 0;
            num = 0;
            deno = 0;

        }
    }

    public FractionNumber() {
        this(0,0);
    }

    public FractionNumber( long numerator){
        this(numerator, 1);
    }

    private static long defineGcdWithSign( long a, long b){
        if( a < 0 && b < 0){
            return gcd(a,b)* (-1);
        }
        else return Math.abs(gcd(a,b));
    }

    private static long gcd(long a, long b) {
        if(a == 0 || b == 0)
            return 0;
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private static long lcm (long a, long b){
        return gcd(a,b) == 0? 0 : a / Math.abs(gcd(a,b)) * b;
    }

    private static boolean isNotNull(long a, long b){
      // числитель может быть нулем
        return a != 0 && b != 0;
    }
    private static boolean isNull(FractionNumber a){
        return a.num == 0 && a.deno == 0;
    }

    private  static long[] indexOfSameDeno(FractionNumber a1, FractionNumber a2){
        long[] arrOfIndex = new long[2];

        if (a1.deno == a2.deno) {
            arrOfIndex[0] = arrOfIndex[1] = 1;
        }
        else {
            long lcm = lcm(a1.deno, a2.deno);
            arrOfIndex[0] = lcm / a1.deno;
            arrOfIndex[1] = lcm / a2.deno;
        }
        return arrOfIndex;
    }

    public FractionNumber add(FractionNumber a2) {
        if(isNull(this) || isNull(a2)){
            return this.deno > 0? this: a2;
        }

        long[] arrOfIndex = indexOfSameDeno(this, a2);

        return new FractionNumber(this.num*arrOfIndex[0] + a2.num*arrOfIndex[1],
                lcm(this.deno,a2.deno) );

    }

    public FractionNumber subtract( FractionNumber a2){
        long[] arrOfIndex = indexOfSameDeno(this, a2);

        return new FractionNumber(this.num*arrOfIndex[0] - a2.num*arrOfIndex[1],
                lcm(this.deno,a2.deno) );
    }

    public FractionNumber multiply(FractionNumber a2){
        return new FractionNumber(this.num*a2.num, this.deno*a2.deno);

    }
    public FractionNumber divide(FractionNumber a2){
        return new FractionNumber(this.num*a2.deno, this.deno*a2.num);
    }

    @Override
    public String toString(){
        String str = new String();
        if(deno == 0){
            return "DIVISION BY ZERO";
        }
        if(deno == 1){
            return new String(String.valueOf(num));
        }
        if( Math.abs(num) < Math.abs(deno)){
            str = num + "/" + deno;

            //return new String(String.valueOf(num)+ "/"+ String.valueOf(deno));
        }
        if( Math.abs(num) > Math.abs(deno)){
            if((num > 0 && deno > 0 )) {
                str = Math.abs(num / deno) + " " + Math.abs(num % deno) + "/" + Math.abs(deno);
            }else
                str = num / deno + " " + Math.abs(num % deno) + "/" + Math.abs(deno);
        }
        return str;
    }
}
