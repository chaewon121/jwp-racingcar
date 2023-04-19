package racingcar.web.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.CarDao;
import racingcar.dao.GameResultDao;
import racingcar.dao.WinnerDao;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;
import racingcar.entity.WinnerEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WinnerDaoTest {

    @Autowired
    private CarDao carDao;

    @Autowired
    private GameResultDao gameResultDao;

    @Autowired
    private WinnerDao winnerDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void save() {
        Long gameResultId = gameResultDao.insert(new GameResultEntity(3));
        Assertions.assertThat(1L).isEqualTo(gameResultId);

        CarEntity carEntity1 = new CarEntity("헙크", 3,  1L);
        Long carId1 = carDao.insert(carEntity1);
        Assertions.assertThat(1L).isEqualTo(carId1);

        CarEntity carEntity2 = new CarEntity("채채", 4,  1L);
        Long carId2 = carDao.insert(carEntity2);
        Assertions.assertThat(2L).isEqualTo(carId2);

        WinnerEntity winnerEntity = new WinnerEntity("채채",1L);
        Long winnerId = winnerDao.insert(winnerEntity);
        Assertions.assertThat(1L).isEqualTo(winnerId);
    }

}
