package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameResultDao;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.dto.web.CarDto;
import racingcar.dto.web.HistoryDto;
import racingcar.dto.web.ResultDto;
import racingcar.dto.web.UserInputDto;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;
import racingcar.utils.DefaultMovingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RacingGameService {

    private final CarDao carDao;
    private final GameResultDao gameResultDao;

    public RacingGameService(CarDao carDao, GameResultDao gameResultDao) {
        this.carDao = carDao;
        this.gameResultDao = gameResultDao;
    }

    @Transactional
    public ResultDto getResult(UserInputDto inputDto) {
        RacingGame racingGame = getRacingGame(inputDto);
        TryCount tryCount = new TryCount(inputDto.getCount());

        Cars finalResult = getResults(racingGame);
        Cars winnersResult = racingGame.decideWinners();

        Long gameResultId = gameResultDao.insert(new GameResultEntity(tryCount.getCount()));
        saveCars(gameResultId, finalResult);

        return new ResultDto(winnersResult, finalResult);
    }

    private RacingGame getRacingGame(UserInputDto inputDto) {
        String names = inputDto.getNames();
        List<String> splitNames = List.of(names.split(","));
        List<Name> nameList = splitNames.stream()
                .map(Name::of)
                .collect(Collectors.toList());
        TryCount tryCount = new TryCount(inputDto.getCount());

        return new RacingGame(nameList, tryCount);
    }

    private void saveCars(Long gameResultId, Cars finalResult) {
        finalResult.getCars()
                .stream()
                .map(car -> new CarEntity(car.getName().getName(), car.getPosition().getPosition(), gameResultId))
                .forEach(carDao::insert);
    }

    private Cars getResults(RacingGame racingGame) {
        return racingGame.start(new DefaultMovingStrategy());
    }

    public List<HistoryDto> getHistory() {
        List<HistoryDto> maps = new ArrayList<>();
        List<Map<Long, GameResultEntity>> gameResults = gameResultDao.findAll();
        for (Map<Long, GameResultEntity> gameResult : gameResults) {
            Long id = new ArrayList<>(gameResult.keySet()).get(0);

            List<CarDto> cars = carDao.findByGameResultId(id).stream()
                    .map(car -> new CarDto(car.getPlayerName(), car.getFinalPosition()))
                    .collect(Collectors.toList());
            List<String> winnerNames = getWinners(cars);
            maps.add(new HistoryDto(winnerNames, cars));
        }
        return maps;
    }

    private List<String> getWinners(List<CarDto> cars) {
        int maxPosition = getMaxPosition(cars);
        return cars.stream()
                .filter(car -> car.getPosition() == (maxPosition))
                .map(CarDto::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private int getMaxPosition(List<CarDto> cars) {
        return cars.stream()
                .map(CarDto::getPosition)
                .max(Integer::compare)
                .orElse(Integer.MIN_VALUE);
    }
}
