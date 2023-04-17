package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.web.entity.WinnerEntity;

import java.sql.PreparedStatement;

@Component
public class WinnerDao {

    private final JdbcTemplate jdbcTemplate;

    public WinnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(WinnerEntity winnerEntity) {
        String sql = "insert into winner (winner_name, game_result_id) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, winnerEntity.getName());
            ps.setLong(2,winnerEntity.getGameResultId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}