package racingcar_step3;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class Racing {

    private List<Car> cars = new ArrayList<>();

    private int carNum, tryNum;

    public Racing() {

        InputValue iv = InputView.input(); //입력받는 행위를 InputView 객체에 위임

        this.carNum = iv.getCarNum();
        this.tryNum = iv.getTryNum();

        for (int i = 0; i < carNum; i++) { //관리할 레이시카를 등록하기
            cars.add(new Car());
        }
    }
    
    public void showResult(){
        ResultView.showAllStates(cars, tryNum);
    }
    
    public void moveAllTime(){
        //주어진 횟수동안
        for (int tryCnt = 0; tryCnt < tryNum; tryCnt++) {
            moveEachCar();
        }
    }

    private void moveEachCar() {
        //전진 또는 멈춤 시키기 Car객체에게
        for (Car car : cars) {
            car.move(getRandomNum());
        }
    }

    public int getRandomNum() {
        return new Random().nextInt(10);
    }

    public static void main(String[] args) {
        Racing racing = new Racing();
        racing.moveAllTime();
        racing.showResult();
    }
}
