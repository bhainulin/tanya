package com.epam.sc.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.sc.beans.Identifiable;
import com.epam.sc.beans.User;
import com.epam.sc.repository.CounterRepository;
import com.epam.sc.services.api.CounterService;

@Service
public class CounterServiceImpl<T extends Identifiable> implements CounterService{
  
  @Autowired
  private CounterRepository<T> repository;

  @Override
  public Integer getValue(Identifiable entity) {
    return repository.getValue(entity);
  }

  @Override
  public void save(Identifiable entity, Integer count) {
    repository.save(entity, count);   
  }

  @Override
  public Map getAll() {
    return repository.getAll();
  }
  
  @Override
  public Map<User, Map<String, Integer>> getUserDiscountMap() {
    return repository.getUserDiscountMap();
  }

  @Override
  public void setUserDiscountMap(Map userDiscountMap) {
    repository.setUserDiscountMap(userDiscountMap);
  }

}
