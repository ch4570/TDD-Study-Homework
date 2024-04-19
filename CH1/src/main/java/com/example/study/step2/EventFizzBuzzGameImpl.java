package com.example.study.step2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventFizzBuzzGameImpl implements EventFizzBuzzGame {

    private final RandomValueGenerator generator;

    @Override
    public String playGame(int number) {

        if (isMultipleOfThreeAndFive(number)) {
            return "FizzBuzz";
        }

        if (isMultipleOfThree(number)) {
            return "Fizz";
        }

        if (isMultipleOfFive(number)) {
            return "Buzz";
        }

        return "Fail";
    }

    @Override
    public String playGame(int number, String[] userInputs) {
        for (String userInput : userInputs) {
            if (isSuccess(generator.generateValue(), userInput)) {
                return "Congratulations!";
            }
        }

        return "Loose!";
    }

    private boolean isSuccess(int multiplyNumber, String userInput) {
        return playGame(multiplyNumber).equals(userInput);
    }

    private boolean isMultipleOfThree(int number) {
        return number % 3 == 0;
    }

    private boolean isMultipleOfFive(int number) {
        return number % 5 == 0;
    }

    private boolean isMultipleOfThreeAndFive(int number) {
        return number % 15 == 0;
    }
}
