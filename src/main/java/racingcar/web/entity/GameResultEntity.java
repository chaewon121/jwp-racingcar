package racingcar.web.entity;

public class GameResultEntity {

    private final int tryCount;
    private final String winners;

    public GameResultEntity(int tryCount, String winners) {
        this.tryCount = tryCount;
        this.winners = winners;
    }

    public int getTryCount() {
        return tryCount;
    }

    public String getWinners() {
        return winners;
    }
}
