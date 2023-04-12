package racingcar.domain;

import java.util.Objects;

public class Car {
    private final Name name;
    private final Position position;

    public Car(Name name, Position position) {
        this.name = name;
        this.position = position;
    }

    public Car(Name name) {
        this(name, Position.create());
    }

    public Car(Car car) {
        this.name = new Name(car.getName());
        this.position = new Position(car.getPosition());
    }

    public void move(boolean movable) {
        if (movable) {
            position.move();
        }
    }

    public Position getPosition() {
        return position;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(position, car.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }
}
