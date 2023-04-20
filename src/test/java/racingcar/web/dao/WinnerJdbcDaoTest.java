package racingcar.web.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.CarJdbcDao;
import racingcar.dao.GameResultJdbcDao;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WinnerJdbcDaoTest {

    @Autowired
    private CarJdbcDao carJdbcDao;

    @Autowired
    private GameResultJdbcDao gameResultJdbcDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void save() {
        Long gameResultId = gameResultJdbcDao.insert(new GameResultEntity(3));
        Assertions.assertThat(1L).isEqualTo(gameResultId);

        CarEntity carEntity1 = new CarEntity("헙크", 3,  1L);
        Long carId1 = carJdbcDao.insert(carEntity1);
        Assertions.assertThat(1L).isEqualTo(carId1);

        CarEntity carEntity2 = new CarEntity("채채", 4,  1L);
        Long carId2 = carJdbcDao.insert(carEntity2);
        Assertions.assertThat(2L).isEqualTo(carId2);

    }

}
