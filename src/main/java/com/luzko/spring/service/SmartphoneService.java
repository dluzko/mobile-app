package com.luzko.spring.service;

import com.luzko.spring.models.Smartphone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartphoneService implements SmartphoneServiceInterface {

    private final JdbcTemplate jdbcTemplate;
    private final SmartphoneMapper smartphoneMapper;

    @Autowired
    public SmartphoneService(JdbcTemplate jdbcTemplate, SmartphoneMapper smartphoneMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.smartphoneMapper = smartphoneMapper;
    }

    static int SMARTPHONE_COUNT;

    public List<Smartphone> index() {
        return jdbcTemplate.query("SELECT * FROM Smartphone", smartphoneMapper);
    }

    public Smartphone show(int id) {
        return jdbcTemplate.query("SELECT * FROM Smartphone WHERE id=?", smartphoneMapper, new Object[]{id})
                .stream().findAny().orElse(null);
    }

    public void save(Smartphone smartphone) {
        jdbcTemplate.update("INSERT INTO Smartphone VALUES(?, ?, ?, ?)", ++SMARTPHONE_COUNT, smartphone.getName(),
                smartphone.getYearOfRelease(), smartphone.getDisplayWidthInches());
    }

    public boolean update(int id, Smartphone updatedSmartphone) {
        if (jdbcTemplate.update("UPDATE Smartphone SET name=?, yearOfRelease=?, displayWidthInches=? WHERE id=?",
                updatedSmartphone.getName(), updatedSmartphone.getYearOfRelease(),
                updatedSmartphone.getDisplayWidthInches(), id) > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean delete(int id) {
        if (jdbcTemplate.update("DELETE FROM Smartphone WHERE id=?", id) > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}