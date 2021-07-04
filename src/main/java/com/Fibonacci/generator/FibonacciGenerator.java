package com.Fibonacci.generator;

import java.util.HashSet;

public class   FibonacciGenerator {
    private HashSet<Integer> fibonacciNumbers;

    public Boolean isNumberExist(int numberInserted){

        if (fibonacciNumbers == null) {
            fibonacciNumbers = new HashSet<>();

            int b = 1 , c=1;
            for (int a = 0 ;a<100000000 ; a = b){
                fibonacciNumbers.add(a);
                b = c;
                c = a + b;
            }
        }

        if (fibonacciNumbers.contains(numberInserted)){
            fibonacciNumbers.remove(numberInserted);
            return true;
        }
        return false;
    }
}
