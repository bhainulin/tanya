package com.epam.spring.core.loggers;

import java.util.ArrayList;
import java.util.List;

import com.epam.spring.core.beans.Event;


public class CacheFileEventLogger extends FileEventLogger {
  
  private int cacheSize;
  private List<Event> cache = new ArrayList<Event>();
  
  public CacheFileEventLogger() {
    
  }
  
 /* public CacheFileEventLogger(int cacheSize) {
    this.cacheSize = cacheSize;
  }*/

  public CacheFileEventLogger(String fileName, int cacheSize) {
    super(fileName);
    this.cacheSize = cacheSize;
  }
  
  public void destroy(){
    if(!cache.isEmpty()){
      writeEventsFromCache();
    }
  }

  @Override
  public void logEvent(Event event) {
   cache.add(event);
   
   if(cache.size() == cacheSize){
     writeEventsFromCache();
     cache.clear();
   }
  }
  
  private void writeEventsFromCache(){
    for(Event e : cache){
      super.logEvent(e);
    }
  }

  public int getCacheSize() {
    return cacheSize;
  }

  public void setCacheSize(int cacheSize) {
    this.cacheSize = cacheSize;
  }

  public List<Event> getCache() {
    return cache;
  }

  public void setCache(List<Event> cache) {
    this.cache = cache;
  }

}
