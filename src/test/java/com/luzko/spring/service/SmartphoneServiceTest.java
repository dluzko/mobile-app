package com.luzko.spring.service;

import com.luzko.spring.models.Smartphone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SmartphoneServiceTest {

    Smartphone smartphone1, smartphone2, smartphone3, smartphone4;

    @InjectMocks
    private SmartphoneService smartphoneService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private SmartphoneMapper smartphoneMapper;

    @Before
    public void setUpBeforeTest() {
        smartphone1 = new Smartphone(10, "iPhone 12 Pro Max", 2020, 6.7);
        smartphone2 = new Smartphone(12, "iPhone 12 Pro", 2020, 6.1);
        smartphone3 = new Smartphone(15, "iPhone 12", 2020, 6.1);
        smartphone4 = new Smartphone(20, "iPhone 12 Mini", 2020, 5.4);
    }

    @Test
    public void saveTest() {
        int smartphone_count = SmartphoneService.SMARTPHONE_COUNT;
        smartphoneService.save(smartphone2);
        verify(jdbcTemplate).update("INSERT INTO Smartphone VALUES(?, ?, ?, ?)",
                smartphone_count + 1, smartphone2.getName(),
                smartphone2.getYearOfRelease(), smartphone2.getDisplayWidthInches());
    }

    @Test
    public void showTest() {
        when(jdbcTemplate.query("SELECT * FROM Smartphone WHERE id=?", smartphoneMapper, smartphone2.getId()))
                .thenReturn(new ArrayList<Smartphone>(){{add(smartphone2);}});
        Smartphone actual = smartphoneService.show(smartphone2.getId());
        assertEquals(smartphone2, actual);
    }

    @Test
    public void indexTest() {
        when(jdbcTemplate.query("SELECT * FROM Smartphone", smartphoneMapper))
                .thenReturn(new ArrayList<Smartphone>(){{add(smartphone1); add(smartphone2); add(smartphone3); add(smartphone4);}});
        List<Smartphone> actualList = smartphoneService.index();
        Smartphone actualSmartphone = actualList.get(0);
        assertEquals(smartphone1, actualSmartphone);
    }

    @Test
    public void updateTest() {
        smartphoneService.update(smartphone2.getId(), smartphone4);
        verify(jdbcTemplate).update(
                "UPDATE Smartphone SET name=?, yearOfRelease=?, displayWidthInches=? WHERE id=?",
                smartphone4.getName(), smartphone4.getYearOfRelease(), smartphone4.getDisplayWidthInches(),
                smartphone2.getId());
    }

    @Test
    public void deleteTest() {
        smartphoneService.delete(smartphone2.getId());
        verify(jdbcTemplate).update("DELETE FROM Smartphone WHERE id=?", smartphone2.getId());
    }
}
