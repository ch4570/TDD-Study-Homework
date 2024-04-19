package com.example.study.lastStep;

import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalTime;

@RequiredArgsConstructor
public class LastStepFizzBuzzGameImpl implements LastStepFizzBuzzGame {

    // 00:00 ~ 12:00
    private static final LocalTime morningEventDateFrom = LocalTime.of(0, 0, 0, 0);
    private static final LocalTime morningEventDateTo = LocalTime.of(12, 0, 0, 0);

    // 13:00 ~ 23:00
    private static final LocalTime eveningEventDateFrom = LocalTime.of(13, 0, 0, 0);
    private static final LocalTime eveningEventDateTo = LocalTime.of(23, 0, 0, 0);

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
            checkEnoughMoneyToPlayGame(player);

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
        Assert.notNull(currentTime, "Current Time Cannot Be Null!");

        if (currentTime.isBefore(morningEventDateTo) && currentTime.isAfter(morningEventDateFrom)) {
            return looseGold / 2;
        }

        if (currentTime.isBefore(eveningEventDateTo) && currentTime.isAfter(eveningEventDateFrom)) {
            return looseGold * 2;
        }

        return looseGold;
    }

    private double calculateEarnMoney(LocalTime currentTime) {
        Assert.notNull(currentTime, "Current Time Cannot Be Null!");

        if (currentTime.isBefore(morningEventDateTo) && currentTime.isAfter(morningEventDateFrom)) {
            return victoryGold * 2;
        }

        if (currentTime.isBefore(eveningEventDateTo) && currentTime.isAfter(eveningEventDateFrom)) {
            return victoryGold * 1.5;
        }

        return victoryGold;
    }

    private boolean isSuccess(int multiplyNumber, String userInput) {
        return playGame(multiplyNumber).equals(userInput);
    }

    private void checkEnoughMoneyToPlayGame(User player) {
        if (player.getGold() <= -10000)
            throw new IllegalArgumentException("골드가 부족합니다. 충전 후 이용해주시기 바랍니다.");
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
