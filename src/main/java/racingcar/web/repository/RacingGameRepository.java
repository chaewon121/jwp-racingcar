package racingcar.web.repository;

import org.springframework.stereotype.Repository;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.web.dao.CarDao;
import racingcar.web.dao.GameResultDao;
import racingcar.web.dao.WinnerDao;
import racingcar.web.entity.CarEntity;
import racingcar.web.entity.GameResultEntity;
import racingcar.web.entity.WinnerEntity;

@Repository
public class RacingGameRepository {

    private final GameResultDao gameResultDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;

    public RacingGameRepository(GameResultDao gameResultDao, CarDao carDao, WinnerDao winnerDao) {
        this.gameResultDao = gameResultDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
    }

    public void saveCars(Long gameResultId, Cars finalResult) {

        finalResult.getCars()
                .stream()
                .map(car -> new CarEntity(car.getName().getName(), car.getPosition().getPosition(), gameResultId))
                .forEach(carEntity -> carDao.insert(carEntity));
    }

    public Long saveGameResult(TryCount tryCount) {
        GameResultEntity gameResultEntity = new GameResultEntity(tryCount.getCount());
        return gameResultDao.insert(gameResultEntity);
    }

    public void saveWinner(Long gameResultId, Cars winnersResult) {
        winnersResult.getCars()
                .stream()
                .map(winner -> new WinnerEntity(winner.getName().getName(), gameResultId))
                .forEach(winnerEntity -> winnerDao.insert(winnerEntity));
    }
}
