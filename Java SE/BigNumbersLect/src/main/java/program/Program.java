package program;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Program {
    public static void main(String[] args) {
        BigInteger bigInteger1 = new BigInteger("123456789123456789123456789", 10);
        System.out.println("Print big integer after create from string: " + bigInteger1);

        BigInteger bigInteger2 = BigInteger.valueOf(12345678L);
        System.out.println("\nPrint big integer after create from long: " + bigInteger2);

        BigInteger bigIntegerSum = bigInteger1.add(bigInteger2);
        System.out.println("\nPrint big integer after sum: " + bigIntegerSum);

        BigInteger bigIntegerMul = bigInteger1.multiply(bigInteger2);
        System.out.println("\nPrint big integer after multiplication: " + bigIntegerMul);

        BigInteger bigIntegerSub = bigInteger1.subtract(bigInteger2);
        System.out.println("\nPrint big integer after subtraction: " + bigIntegerSub);

        BigInteger bigIntegerDiv = bigInteger1.divide(bigInteger2);
        System.out.println("\nPrint big integer after division: " + bigIntegerDiv);

        BigInteger bigIntegerPow = bigInteger1.pow(5);
        System.out.println("\nPrint big integer after powered: " + bigIntegerPow);

        BigInteger bigInteger3 = BigInteger.valueOf(-100000000L);
        BigInteger bigIntegerAbs = bigInteger3.abs();
        System.out.println("\nPrint big integer after absolute: " + bigIntegerAbs);

        System.out.println("\nResult of comparing");
        int comp = bigInteger1.compareTo(bigInteger2);
        if(comp < 0){
            System.out.println("1 < 2");
        }
        else if(comp > 0){
            System.out.println("1 > 2");
        }
        else{
            System.out.println("1 = 2");
        }

        boolean equals = bigInteger1.equals(bigInteger2);
        System.out.println("\nResult of equals: " + equals);

        BigInteger gcd = bigInteger1.gcd(bigInteger2);
        System.out.println("\nPrint big integer after gcd: " + gcd);

        int res1 = bigInteger2.intValue();
        System.out.println("\nConvert big integer to int value: " + res1);

        long res2 = bigInteger2.longValue();
        System.out.println("\nConvert big integer to long value: " + res2);

        BigInteger max = bigInteger1.max(bigInteger2);
        System.out.println("\nPrint big integer after max: " + max);

        BigInteger min = bigInteger1.min(bigInteger2);
        System.out.println("\nPrint big integer after min: " + min);

        BigInteger negate = bigInteger1.negate();
        System.out.println("\nPrint big integer after negate: " + negate);

        BigInteger sqrt = bigInteger1.sqrt();
        System.out.println("\nPrint big integer after sqrt: " + sqrt);

        BigDecimal bigDecimal1 = new BigDecimal(123.5);
        System.out.println("\nPrint big decimal after create from double: " + bigDecimal1);

        BigDecimal bigDecimal2 = new BigDecimal("123.5");
        System.out.println("\nPrint big integer after create from string: " + bigDecimal2);

        //TODO методы для BigDecimal схожи с BigInteger, проверить их самостоятельно
    }
}
