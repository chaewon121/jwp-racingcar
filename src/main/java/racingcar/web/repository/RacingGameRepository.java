package racingcar.web.repository;

import org.springframework.stereotype.Repository;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.web.dao.CarDao;
import racingcar.web.dao.GameResultDao;
import racingcar.web.entity.CarEntity;
import racingcar.web.entity.GameResultEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RacingGameRepository {

    private final GameResultDao gameResultDao;
    private final CarDao carDao;

    public RacingGameRepository(GameResultDao gameResultDao, CarDao carDao) {
        this.gameResultDao = gameResultDao;
        this.carDao = carDao;
    }

    public void saveCars(Long gameResultId, Cars finalResult, Cars winnersResult) {

        finalResult.getCars()
                .stream()
                .map(car -> new CarEntity(car.getName().getName(), car.getPosition().getPosition(), gameResultId))
                .forEach(carEntity -> carDao.insert(carEntity));
    }

    public Long saveGameResult(TryCount tryCount, Cars winnersResult) {
        List<String> winnerNames = winnersResult.getCars()
                .stream()
                .map(car -> car.getName().getName())
                .collect(Collectors.toUnmodifiableList());
        GameResultEntity gameResultEntity = new GameResultEntity(tryCount.getCount(),String.join(",",winnerNames));
        return gameResultDao.insert(gameResultEntity);
    }

}
