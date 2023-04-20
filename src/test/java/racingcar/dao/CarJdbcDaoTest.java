package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarJdbcDaoTest {

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

        CarEntity carEntity = new CarEntity("헙크", 3,  1L);
        Long carId = carJdbcDao.insert(carEntity);
        Assertions.assertThat(1L).isEqualTo(carId);
    }
}
