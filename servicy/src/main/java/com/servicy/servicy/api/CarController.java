package com.servicy.servicy.api;

import com.servicy.servicy.model.Manufacturer;
import com.servicy.servicy.service.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

  @Autowired
  private CarService carService;

  @GetMapping("/models")
  public List<Manufacturer> getModels() {
    return carService.getModels();
  }
}
