package com.example.study.step1;

public class FizzBuzzGameImpl implements FizzBuzzGame {
    @Override
    public String playGame(int number) {

        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        }

        if (number % 3 == 0) {
            return "Fizz";
        }

        if (number % 5 == 0) {
            return "Buzz";
        }

        return "Fail";
    }
}
