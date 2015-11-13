package com.epam.sc.repository.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.sc.beans.Identifiable;

public class AbstractMapRepository<T extends Identifiable> { 
  
  protected long idCounter = 0;
  
  protected final Map<Long, T> map = new HashMap<Long, T>();
  
  public AbstractMapRepository() {
      this(0);
  }
  
  public AbstractMapRepository(long initIdValue) {
      super();
      this.idCounter = initIdValue;
  }

  protected final Long getNextId() {
      return ++idCounter;
  }
 
  protected T get(Long id) {
      return map.get(id);
  }
  
  protected Long create(T entity) {
      Long id = getNextId();
      entity.setId(id);
      map.put(id, entity);
      return id;
  }
 
  protected void remove(Long id) {
      map.remove(id);
  }
  
  protected void update(T entity) {
    if (entity.getId() != null) {
      map.put(entity.getId(), entity);
    }
  }
  
  protected List<T> getAll(){
    List<T> list = new ArrayList<T>(map.values());
    return list;
  }
 
}

