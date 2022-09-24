package ru.yandex.practicum.filmorate.controller.database;


import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.RatingMPA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Data
public class MPADbStorage implements MPAStorage {
    private static final String GET_ALL_MPA = "SELECT * FROM MPA";
    private static final String GET_MPA_BY_ID = "SELECT * FROM MPA WHERE mpa_id = ?";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<RatingMPA> getAllMpa() {
        return jdbcTemplate.query(GET_ALL_MPA, (rs, rowNum) -> mapRowToMpa(rs));
    }

    @Override
    public RatingMPA getMpaById(int id) {
        final List<RatingMPA> mpaRating = jdbcTemplate.query(GET_MPA_BY_ID, (rs, rowNum) -> mapRowToMpa(rs), id);
        return mpaRating.size() > 0 ? mpaRating.get(0) : null;
    }

    private RatingMPA mapRowToMpa(ResultSet rs) throws SQLException {
        return RatingMPA.builder()
                .id(rs.getInt("mpa_id"))
                .title(rs.getString("title"))
                .build();
    }
}

