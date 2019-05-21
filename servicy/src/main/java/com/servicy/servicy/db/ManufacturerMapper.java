package com.servicy.servicy.db;

import com.servicy.servicy.annotations.Db;
import com.servicy.servicy.model.Manufacturer;
import java.util.List;
import org.apache.ibatis.annotations.Select;

@Db
public interface ManufacturerMapper {

//  @Select("SELECT * FROM servicy.car_makes;")
  List<Manufacturer> selectAll();

  List<Manufacturer> a();
}
