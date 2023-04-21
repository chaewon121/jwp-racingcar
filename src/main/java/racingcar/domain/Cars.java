package racingcar.domain;

import racingcar.utils.MovingStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cars implements Iterable<Car> {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public Cars(Cars cars) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            result.add(new Car(car));
        }
        this.cars = result;
    }

    public void moveCars(MovingStrategy strategy) {
        for (Car car : cars) {
            car.move(strategy.movable());
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public Iterator<Car> iterator() {
        return cars.iterator();
    }
}
