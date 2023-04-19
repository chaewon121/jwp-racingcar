package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.entity.WinnerEntity;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class WinnerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<WinnerEntity> rowMapper = (rs, rowNum) -> new WinnerEntity(
            rs.getString("winner_name"),
            rs.getLong("game_result_id")
    );

    public WinnerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(final WinnerEntity winnerEntity) {
        String sql = "insert into winner (winner_name, game_result_id) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, winnerEntity.getName());
            ps.setLong(2, winnerEntity.getGameResultId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<WinnerEntity> findByGameResultId(final Long gameResultId) {
        return jdbcTemplate.query("SELECT * FROM WINNER WHERE game_result_id = ?", rowMapper, gameResultId);
    }
}
