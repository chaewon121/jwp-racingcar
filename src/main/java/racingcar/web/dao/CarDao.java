package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.web.entity.CarEntity;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class CarDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CarEntity> rowMapper = (rs, rowNum) -> new CarEntity(
            rs.getString("player_name"),
            rs.getInt("final_position"),
            rs.getLong("game_result_id")
    );

    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(final CarEntity carEntity) {
        String sql = "insert into car (player_name, final_position, game_result_id) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, carEntity.getPlayerName());
            ps.setInt(2, carEntity.getFinalPosition());
            ps.setLong(3, carEntity.getGameResultId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<CarEntity> findByGameResultId(final Long gameResultId) {
        return jdbcTemplate.query("SELECT * FROM CAR WHERE game_result_id = ?", rowMapper, gameResultId);
    }
}
