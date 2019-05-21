package com.servicy.servicy.model;

import org.apache.ibatis.annotations.One;

public class Model {

  private int model_id;
  private String model;
  private int make_id;

  public int getModel_id() {
    return model_id;
  }

  public void setModel_id(int model_id) {
    this.model_id = model_id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getMake_id() {
    return make_id;
  }

  public void setMake_id(int make_id) {
    this.make_id = make_id;
  }
}