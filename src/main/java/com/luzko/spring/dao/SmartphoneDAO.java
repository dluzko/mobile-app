package com.luzko.spring.dao;

import com.luzko.spring.models.Smartphone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmartphoneDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SmartphoneDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static int SMARTPHONE_COUNT;

    public List<Smartphone> index() {
        return jdbcTemplate.query("SELECT * FROM Smartphone", new SmartphoneMapper());
    }

    public Smartphone show(int id) {
        return jdbcTemplate.query("SELECT * FROM Smartphone WHERE id=?", new SmartphoneMapper(), new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void save(Smartphone smartphone) {
        jdbcTemplate.update("INSERT INTO Smartphone VALUES(?, ?, ?, ?)", ++SMARTPHONE_COUNT, smartphone.getName(),
                smartphone.getYearOfRelease(), smartphone.getDisplayWidthInches());
    }

    public void update(int id, Smartphone updatedSmartphone) {
        jdbcTemplate.update("UPDATE Smartphone SET name=?, yearOfRelease=?, displayWidthInches=? WHERE id=?",
                updatedSmartphone.getName(), updatedSmartphone.getYearOfRelease(),
                updatedSmartphone.getDisplayWidthInches(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Smartphone WHERE id=?", id);
    }
}