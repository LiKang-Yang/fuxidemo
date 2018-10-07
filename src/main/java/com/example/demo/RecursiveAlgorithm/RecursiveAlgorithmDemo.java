package com.example.demo.RecursiveAlgorithm;

public class RecursiveAlgorithmDemo {

    /*
    斐波那契数列的排列是：0，1，1，2，3，5，8，13，21，34，55，89，144……依次类推下去，
   你会发现，它后一个数等于前面两个数的和。在这个数列中的数字，就被称为斐波那契数。
     */
    public  static long F(int n){
        if (n < 1){
            return 1;
        }else{
            return F(n-1) + F(n-2);
        }
    }


    public static void main(String[] args) {
        System.out.println(F(3));
        System.out.println(fac(3));
        printDigit(34567);
    }


    //阶乘

    public static long fac(int n){
        if(n<=1){
            return 1;
        }else{
            return fac(n-1) * n;
        }

    }

    //倒序输出一个正整数
    public static void printDigit(int n){
        System.out.print(n%10);
        if(n>10){
            printDigit(n/10);
        }
    }
}
