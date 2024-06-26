package com.example.study.step3;


public class InvalidUserFizzBuzzGameImpl implements InvalidUserFizzBuzzGame {
    @Override
    public String playGame(int number, User player) {
        checkInvalidUser(player);
        checkInvalidValue(number, player);

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

    private void checkInvalidValue(int number, User player) {
        if (number <= 0) {
            player.increaseIllegalBehaviorCnt();
        } else {
            player.clearIllegalBehaviorCnt();
        }
    }

    private void checkInvalidUser(User player) {
        if (player.isIllegalBannedUser())
            throw new IllegalArgumentException("부정행위로 영구 정지 된 계정입니다.");
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
