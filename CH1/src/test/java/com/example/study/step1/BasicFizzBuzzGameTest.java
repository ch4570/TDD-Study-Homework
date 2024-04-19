package com.example.study.step1;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BasicFizzBuzzGameTest {

    private final SimpleFizzBuzzGame fizzBuzzGame = new SimpleFizzBuzzGameImpl();

    @Test
    @DisplayName("SimpleFizzBuzzGame Bad Test Case")
    void badTestCase() {
        // given
        int multipleOfThreeInput = 9;
        int multipleOfFiveInput = 10;
        int multipleOfThreeAndFiveInput = 30;
        int failInput = 77;

        // when
        String multipleOfThreeResult = fizzBuzzGame.playGame(multipleOfThreeInput);
        String multipleOfFiveResult = fizzBuzzGame.playGame(multipleOfFiveInput);
        String multipleOfThreeAndFiveResult = fizzBuzzGame.playGame(multipleOfThreeAndFiveInput);
        String failResult = fizzBuzzGame.playGame(failInput);

        // then
        assertThat(multipleOfThreeResult).isEqualTo("Fizz");
        assertThat(multipleOfFiveResult).isEqualTo("Buzz");
        assertThat(multipleOfThreeAndFiveResult).isEqualTo("FizzBuzz");
        assertThat(failResult).isEqualTo("Fail");
    }

    @Test
    @DisplayName("FizzBuzzGame은 입력 한 값이 3의 배수일 경우 Fizz가 출력된다.")
    void multiplesOfThreeTest() {
        // given
        int input = 99;

        // when
        String result = fizzBuzzGame.playGame(input);

        // then
        assertThat(result).isEqualTo("Fizz");
    }

    @Test
    @DisplayName("FizzBuzzGame은 입력 한 값이 5의 배수일 경우 Buzz가 출력된다.")
    void multiplesOfFiveTest() {
        // given
        int input = 50;

        // when
        String result = fizzBuzzGame.playGame(input);

        // then
        assertThat(result).isEqualTo("Buzz");
    }

    @Test
    @DisplayName("FizzBuzzGame은 입력 한 값이 3과 5의 공배수일 경우 FizzBuzz 출력된다.")
    void multiplesOfThreeAndFiveTest() {
        // given
        int input = 45;

        // when
        String result = fizzBuzzGame.playGame(input);

        // then
        assertThat(result).isEqualTo("FizzBuzz");
    }

    @Test
    @DisplayName("FizzBuzzGame은 입력 한 값이 3 또는 5의 배수가 아니면 실패한다.")
    void isNotValidValueTest() {
        // given
        int input = 22;

        // when
        String result = fizzBuzzGame.playGame(input);

        // then
        assertThat(result).isEqualTo("Fail");
    }
}
