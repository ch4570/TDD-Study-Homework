package com.example.study.lastStep;

import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@RequiredArgsConstructor
public class LastStepFizzBuzzGameImpl implements LastStepFizzBuzzGame {

    private static final LocalTime morningEventDateFrom = LocalTime.MAX;
    private static final LocalTime morningEventDateTo = LocalTime.of(12, 0);

    private static final LocalTime eveningEventDateFrom = LocalTime.of(13, 0);
    private static final LocalTime eveningEventDateTo = LocalTime.of(23, 0);

    private static final double victoryGold = 500;
    private static final double looseGold = 200;

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
    public String playGame(int number, String[] userInputs, User player, LocalTime currentTime) {
        boolean isVictory = false;

        for (String userInput : userInputs) {
            if (isSuccess(generator.generateValue() * number, userInput)) {
                isVictory = true;
                player.increaseMoney(calculateEarnMoney(currentTime));
            } else {
                player.decreaseMoney(calculateLoseMoney(currentTime));
            }
        }


        return isVictory ? "Congratulations!" : "Loose!";
    }

    private double calculateLoseMoney(LocalTime currentTime) {
        if (morningEventDateFrom.isAfter(currentTime) || morningEventDateTo.isBefore(currentTime)) {
            return looseGold / 2;
        }

        if (eveningEventDateFrom.isAfter(currentTime) || eveningEventDateTo.isBefore(currentTime)) {
            return looseGold * 2;
        }

        return looseGold;
    }

    private double calculateEarnMoney(LocalTime currentTime) {
        if (morningEventDateFrom.isAfter(currentTime) || morningEventDateTo.isBefore(currentTime)) {
            return victoryGold * 2;
        }

        if (eveningEventDateFrom.isAfter(currentTime) || eveningEventDateTo.isBefore(currentTime)) {
            return victoryGold * 1.5;
        }

        return victoryGold;
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
