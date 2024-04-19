package com.example.study.lastStep;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class LastStepFizzBuzzGameTest {


    @Test
    @DisplayName("FizzBuzzGame을 오후 이벤트 시간에 2번(+750 * 2) 이기고 1번(-400) 지면 1100골드를 획득해야 한다.")
    void winAndLooseGamesOnEveningTimeTest() {
        // given
        LocalTime currentTime = LocalTime.of(13, 0, 0, 1);
        User player = new User(0, false, 0);

        RandomValueGenerator winValueGenerator = () -> 3;
        RandomValueGenerator looseValueGenerator = () -> 7;

        int numberInput = 8;

        String[] winUserInputs = new String[] {"Fizz", "Fizz"};
        String[] looseUserInputs = new String[] {"FizzBuzz"};

        LastStepFizzBuzzGame winFizzBuzzGame = new LastStepFizzBuzzGameImpl(winValueGenerator);
        LastStepFizzBuzzGame looseFizzBuzzGame = new LastStepFizzBuzzGameImpl(looseValueGenerator);

        // when
        winFizzBuzzGame.playGame(numberInput, winUserInputs, player, currentTime);
        looseFizzBuzzGame.playGame(numberInput, looseUserInputs, player, currentTime);

        // then
        assertThat(player.getGold()).isEqualTo(1100);
    }

    @Test
    @DisplayName("FizzBuzzGame을 오전 이벤트 시간에 1번 이기고(+1000) 2번(-100*2) 지면 800골드를 획득해야 한다.")
    void winAndLooseOnMorningTimeTest() {
        // given
        LocalTime currentTime = LocalTime.of(0, 0, 0, 1);
        User player = new User(0, false, 0);

        RandomValueGenerator winValueGenerator = () -> 3;
        RandomValueGenerator looseValueGenerator = () -> 7;

        int numberInput = 8;

        String[] winUserInputs = new String[] {"Fizz"};
        String[] looseUserInputs = new String[] {"FizzBuzz", "FizzBuzz"};

        LastStepFizzBuzzGame winFizzBuzzGame = new LastStepFizzBuzzGameImpl(winValueGenerator);
        LastStepFizzBuzzGame looseFizzBuzzGame = new LastStepFizzBuzzGameImpl(looseValueGenerator);

        // when
        winFizzBuzzGame.playGame(numberInput, winUserInputs, player, currentTime);
        looseFizzBuzzGame.playGame(numberInput, looseUserInputs, player, currentTime);

        // then
        assertThat(player.getGold()).isEqualTo(800);
    }

    @Test
    @DisplayName("FizzBuzzGame을 23:01 ~ 23:59 사이에 플레이하면 골드 획득/차감량이 기본값(+500/-200)으로 적용되어야 한다.")
    void winAndLooseGameOnNotEventTime() {
        // given
        LocalTime currentTime = LocalTime.of(23, 0, 0, 1);
        User player = new User(0 , false, 0);

        RandomValueGenerator winValueGenerator = () -> 3;
        RandomValueGenerator looseValueGenerator = () -> 7;

        LastStepFizzBuzzGame winFizzBuzzGame = new LastStepFizzBuzzGameImpl(winValueGenerator);
        LastStepFizzBuzzGame looseFizzBuzzGame = new LastStepFizzBuzzGameImpl(looseValueGenerator);

        int numberInput = 2;
        String[] userWinInputs = new String[] {"Fizz", "Fizz"};
        String[] userLooseInputs = new String[] {"Buzz", "Buzz"};

        // when
        winFizzBuzzGame.playGame(numberInput, userWinInputs, player, currentTime);
        looseFizzBuzzGame.playGame(numberInput, userLooseInputs, player, currentTime);


        // then
        assertThat(player.getGold()).isEqualTo(600);
    }

    @Test
    @DisplayName("게임 중 회원의 잔여 골드가 -10000원 이하로 떨어지면 게임을 할 수 없어야 한다.")
    void playerHasNotEnoughMoneyTest() {
        // given
        LocalTime currentTime = LocalTime.of(13, 0, 0, 1);
        User player = new User(0, false, -10001);

        RandomValueGenerator generator = () -> 3;
        int userInput = 4;

        LastStepFizzBuzzGame lastStepFizzBuzzGame = new LastStepFizzBuzzGameImpl(generator);
        // when
        AbstractThrowableAssert<?, ? extends Throwable> result =
                assertThatThrownBy(() -> lastStepFizzBuzzGame.playGame(userInput, new String[]{"Fizz"}, player, currentTime));

        // then
        result.isInstanceOf(IllegalArgumentException.class)
                .hasMessage("골드가 부족합니다. 충전 후 이용해주시기 바랍니다.");
        assertThat(player.getGold()).isEqualTo(-10001);
    }
}
