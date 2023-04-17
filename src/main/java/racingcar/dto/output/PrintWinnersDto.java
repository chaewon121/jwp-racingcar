package racingcar.dto.output;

import racingcar.domain.Cars;

public class PrintWinnersDto {
    private final Cars cars;

    public PrintWinnersDto(final Cars cars) {
        this.cars = cars;
    }

    public Cars getCars() {
        return cars;
    }
}
