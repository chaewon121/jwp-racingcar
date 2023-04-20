package racingcar.dto.output;

import racingcar.domain.Cars;

public class PrintMovingStatusDto {

    private final Cars movingStatus;

    public PrintMovingStatusDto(final Cars movingStatus) {
        this.movingStatus = movingStatus;
    }

    public Cars getMovingStatus() {
        return movingStatus;
    }
}
