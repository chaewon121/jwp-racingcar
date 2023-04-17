package racingcar.web.entity;

public class WinnerEntity {

    private final String name;
    private final Long gameResultId;

    public WinnerEntity(String name, Long gameResultId) {
        this.name = name;
        this.gameResultId = gameResultId;
    }

    public String getName() {
        return name;
    }

    public Long getGameResultId() {
        return gameResultId;
    }
}
