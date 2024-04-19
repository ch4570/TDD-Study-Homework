package com.example.study.lastStep;

import java.time.LocalTime;

public interface LastStepFizzBuzzGame {

    String playGame(int number);
    String playGame(int number, String[] userInputs, User player, LocalTime currentTime);
}
