package com.luzko.spring.service;

import com.luzko.spring.models.Smartphone;

import java.util.List;

public interface SmartphoneServiceInterface {
    List<Smartphone> index();

    Smartphone show(int id);

    void save(Smartphone smartphone);

    boolean update(int id, Smartphone updatedSmartphone);

    boolean delete(int id);
}
