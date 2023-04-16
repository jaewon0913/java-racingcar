package study.racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import study.StringAddCalculator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.InputMismatchException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTest {

    @DisplayName("숫자를 입력시 자동차 대수를 반환 해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"2"})
    public void inputView_setCars_숫자(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThat(InputView.setCars()).isEqualTo(Integer.parseInt(input));
    }

    @DisplayName("숫자를 입력시 게임 횟수를 반환 해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"5"})
    public void inputView_setGames_숫자(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThat(InputView.setGames()).isEqualTo(Integer.parseInt(input));
    }

    @DisplayName("자동차 이름을 입력하면 해당 문자열을 반환 해야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,honux"})
    public void inputView_CarNames(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThat(InputView.setCarNames()).isEqualTo(input);
    }

    @DisplayName("자동차 이름 문자열을 입력하면 ','를 기준으로 자동차 이름이 나뉘어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,honux"})
    public void inputValue_splitCarNameString(String input) {

        RacingGameInputs racingGameInputs = new RacingGameInputs(5, input);

        assertThat(racingGameInputs.getNameOfCars()).hasSize(3);
    }

    @DisplayName("자동차 이름 문자열을 입력하고, ','를 기준으로 자동차 이름을 나누고 5글자 이상의 이름이 있으면 에외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,bbororo,honux"})
    public void inputValue_splitCarNameString_실패(String input) {

        assertThatThrownBy(() -> new RacingGameInputs(5, input))
                .isInstanceOf(InputMismatchException.class);
    }

    @DisplayName("자동차 이름 문자열을 입력하고, 구분자가 ','가 아니면 분리하지 못하고 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi:bbororo:honux"})
    public void inputValue_splitCarNameString_분리실패(String input) {

        assertThatThrownBy(() -> new RacingGameInputs(5, input))
                .isInstanceOf(InputMismatchException.class);
    }

    @DisplayName("0이하의 수는 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -3, -1})
    public void inputValue_isNegativeNumberAndZero(int input) throws Exception {

        String carNameStr = "pobbi,crong,hounx";

        assertThatThrownBy(() -> new RacingGameInputs(input, carNameStr))
                .isInstanceOf(InputMismatchException.class);
    }

    @DisplayName("자동차 이름은 5글자를 넘을 수 없다.")
    @Test
    public void inputValue_isMoreThanFiveLength() {

        String carNameStr = "bbororo,jaewon,tester";
        int numberOfGames = 5;

        assertThatThrownBy(() -> new RacingGameInputs(numberOfGames, carNameStr))
                .isInstanceOf(InputMismatchException.class);
    }
}
