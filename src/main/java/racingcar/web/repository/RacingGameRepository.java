package racingcar.web.repository;

import org.springframework.stereotype.Repository;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.web.dao.CarDao;
import racingcar.web.dao.GameResultDao;
import racingcar.web.dao.WinnerDao;
import racingcar.web.dto.CarDto;
import racingcar.web.dto.HistoryDto;
import racingcar.web.entity.CarEntity;
import racingcar.web.entity.GameResultEntity;
import racingcar.web.entity.WinnerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<HistoryDto> findAllHistory() {
        List<HistoryDto> maps = new ArrayList<>();
        List<Map<Long, GameResultEntity>> gameResults = gameResultDao.findAll();
        for (Map<Long, GameResultEntity> gameResult : gameResults) {
            Long id = new ArrayList<>(gameResult.keySet()).get(0);

            List<String> winnerNames = winnerDao.findByGameResultId(id).stream()
                    .map(WinnerEntity::getName).collect(Collectors.toList());
            List<CarDto> cars = carDao.findByGameResultId(id).stream()
                    .map(car -> new CarDto(car.getPlayerName(), car.getFinalPosition()))
                    .collect(Collectors.toList());

            maps.add(new HistoryDto(winnerNames, cars));
        }
        return maps;
    }
}
