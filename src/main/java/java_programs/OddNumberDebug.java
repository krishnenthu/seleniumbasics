package java_programs;

import java.util.Scanner;

public class OddNumberDebug {
    public void check(int a, int b) {
        int remainder;
        System.out.println("The odd numbers are..");
        for (int i=a; i<= b; i++) {
            remainder= i % 2;
            if (remainder!= 0)
                System.out.println(i);
        }
    }
    public static void main(String[] args) {
        OddNumberDebug oddNumberDebug = new OddNumberDebug();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the lower limit");
        int lower = sc.nextInt();
        System.out.println("Enter the upper limit");
        int upper = sc.nextInt();
        oddNumberDebug.check(lower, upper);

    }

}
