package com.servicy.servicy.service;

import com.servicy.servicy.db.ManufacturerMapper;
import com.servicy.servicy.model.Manufacturer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  @Autowired
  private ManufacturerMapper manufacturerMapper;

  public List<Manufacturer> getModels() {
    return manufacturerMapper.selectAll();
  }

}
