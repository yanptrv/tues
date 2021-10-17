import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                BigInteger check = new BigInteger(arg);
                if (check.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 || check.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
                    System.out.println(check + " is out of bound");
                    continue;
                }

                int num = Integer.parseInt(arg);
                boolean flag = false;
                if (num > 0) {
                    for (int i = 2; i <= num / 2; ++i) {
                        if (num % i == 0) {
                            flag = true;
                            break;
                        }
                    }
                }  else {
                    flag = true;
                }
                if (!flag)
                    System.out.println(num + " is a prime");
                else
                    System.out.println(num + " is not a prime");
            }   catch (NumberFormatException n) {
                try {
                    float checkIfFloat = Float.parseFloat(arg);
                } catch(Exception e) {
                    System.out.println(arg + " is not a number");
                    continue;
                }
                System.out.println(arg + " is not an integer");
            }
        }
    }
}
