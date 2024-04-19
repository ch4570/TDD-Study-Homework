package com.example.study.step2;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventFizzBuzzGameTest {

    @Test
    @DisplayName("Random FizzBuzzGame에서 한 번이라도 성공하면 Congratulations!가 출력된다.")
    void winGameTest() {
        // given
        RandomValueGenerator generator = () -> 3;
        EventFizzBuzzGame eventFizzBuzzGame = new EventFizzBuzzGameImpl(generator);

        int numberInput = 2;
        String[] userInput = new String[] {"Fizz", "Fizz", "Buzz"};


        // when
        String result = eventFizzBuzzGame.playGame(numberInput, userInput);

        // then
        assertThat(result).isEqualTo("Congratulations!");
    }

    @Test
    @DisplayName("Random FizzBuzzGame에서 한 번도 성공하지 못하면 Loose!가 출력된다.")
    void test() {
        // given
        RandomValueGenerator generator = () -> 4;
        EventFizzBuzzGame eventFizzBuzzGame = new EventFizzBuzzGameImpl(generator);
        String[] userInput = new String[] {"Fizz", "FizzBuzz", "Buzz"};

        int numberInput = 1;

        // when
        String result = eventFizzBuzzGame.playGame(numberInput, userInput);

        // then
        assertThat(result).isEqualTo("Loose!");
    }
}
