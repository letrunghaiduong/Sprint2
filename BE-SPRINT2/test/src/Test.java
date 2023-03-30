public class Test {
    public static void main(String[] args) {
        int decimalNumber = 10;
        int quotient = decimalNumber;
        String binaryNumber = "";

        while (quotient > 0) {
            int remainder = quotient % 2;
            binaryNumber = remainder + binaryNumber;
            quotient = quotient / 2;
        }

        System.out.println(binaryNumber);
    }
}
