package com.example.study.step3;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InvalidUserFizzBuzzGameTest {

    private static final InvalidUserFizzBuzzGame invalidUserGame = new InvalidUserFizzBuzzGameImpl();

    @Test
    @DisplayName("부정행위를 한 유저는 게임을 진행할 수 없다.")
    void illegalUserPlayGameTest() {
        // given
        User player = new User(3, true);

        // when
        AbstractThrowableAssert<?, ? extends Throwable> result = assertThatThrownBy(() -> invalidUserGame.playGame(3, player));

        // then
        result.isInstanceOf(IllegalArgumentException.class)
                .hasMessage("부정행위로 영구 정지 된 계정입니다.");
    }

    @Test
    @DisplayName("유저가 연속으로 3번 부정행위를 하면 영구정지 처리 된다.")
    void doIllegalBehaviorThreeTimesTest() {
        // given
        User player = new User(0, false);

        // when
        for (int i=0; i<3; i++) {
            invalidUserGame.playGame(0, player);
        }

        // then
        assertThat(player.getIllegalBehaviorCnt()).isEqualTo(3);
        assertThat(player.isIllegalBannedUser()).isTrue();
    }

    @Test
    @DisplayName("유저가 연속으로 3번 미만으로 부정행위를 하면 영구정지 되지 않고 부정행위 횟수가 초기화 된다.")
    void doIllegalBehaviorNotMoreThanThree() {
        // given
        User player = new User(0, false);

        // when
        for (int i=0; i<2; i++) {
            invalidUserGame.playGame(0, player);
        }
        invalidUserGame.playGame(1, player);

        // then
        assertThat(player.getIllegalBehaviorCnt()).isEqualTo(0);
        assertThat(player.isIllegalBannedUser()).isFalse();
    }

    @Test
    @DisplayName("유저가 연속으로 3번 부정행위를 하면 영구정지 된다.")
    void doIllegalBehaviorMoreThanThree() {
        // given
        User player = new User(0, false);

        // when
        for (int i=0; i<3; i++) {
            invalidUserGame.playGame(-3, player);
        }

        // then
        assertThat(player.getIllegalBehaviorCnt()).isEqualTo(3);
        assertThat(player.isIllegalBannedUser()).isTrue();
    }


}
