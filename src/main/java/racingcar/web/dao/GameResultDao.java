package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.web.entity.GameResultEntity;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

@Component
public class GameResultDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Map<Long, GameResultEntity>> rowMapper = (rs, rowNum) ->
            Map.of(
                    rs.getLong("id"),
                    new GameResultEntity(rs.getInt("try_count")
                    ));

    public GameResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(GameResultEntity gameResultEntity) {
        String sql = "insert into game_result (try_count) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, gameResultEntity.getTryCount());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<Map<Long, GameResultEntity>> findAll() {
        return jdbcTemplate.query("SELECT * FROM game_result", rowMapper);
    }

}
