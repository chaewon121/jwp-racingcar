package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.GameResultEntity;

/**
 * jdbcTest로 바꿔보기
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameResultJdbcDaoTest {

    @Autowired
    private GameResultJdbcDao gameResultJdbcDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void save() {
        Long id = gameResultJdbcDao.insert(new GameResultEntity(2));
        Assertions.assertThat(1L).isEqualTo(id);
    }

}