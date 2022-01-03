package com.luzko.spring.dao;

import com.luzko.spring.models.Smartphone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SmartphoneMapper implements RowMapper<Smartphone> {

    @Override
    public Smartphone mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Smartphone smartphone = new Smartphone();

        smartphone.setId(resultSet.getInt("id"));
        smartphone.setName(resultSet.getString("name"));
        smartphone.setYearOfRelease(resultSet.getInt("yearOfRelease"));
        smartphone.setDisplayWidthInches(resultSet.getDouble("displayWidthInches"));

        if (smartphone.getId() > SmartphoneDAO.SMARTPHONE_COUNT) {
            SmartphoneDAO.SMARTPHONE_COUNT = smartphone.getId();
        }

        return smartphone;
    }
}
