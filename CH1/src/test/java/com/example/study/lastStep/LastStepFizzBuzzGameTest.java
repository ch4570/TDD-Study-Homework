package com.example.study.lastStep;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class LastStepFizzBuzzGameTest {

    @Test
    @DisplayName("FizzBuzzGame을 오전 이벤트 시간에 3번 이기면 3000골드를 획득해야 한다.")
    void winGamesTest() {
        // given
        LocalTime currentTime = LocalTime.of(10, 32);
        User player = new User(0, false, 0);

        RandomValueGenerator generator = () -> 3;
        LastStepFizzBuzzGame lastStepFizzBuzzGame = new LastStepFizzBuzzGameImpl(generator);

        int numberInput = 2;
        String[] userInput = new String[] {"Fizz", "Fizz", "Fizz"};

        // when
        lastStepFizzBuzzGame.playGame(numberInput, userInput, player, currentTime);

        // then
        assertThat(player.getGold()).isEqualTo(3000);
    }
}
