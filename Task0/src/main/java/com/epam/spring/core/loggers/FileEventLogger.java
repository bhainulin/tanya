package com.epam.spring.core.loggers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.epam.spring.core.beans.Event;


public class FileEventLogger implements EventLogger {
  
  private String fileName;
  private File file;

  public FileEventLogger() {
  }

  public FileEventLogger(String fileName) {
    this.fileName = fileName;
  }
  
  public void init() throws IOException{
    this.file = new File(fileName);
    if(!this.file.canWrite()) {
      throw new IOException();
    }
  }

  public void logEvent(Event event){
    try {
      FileUtils.writeStringToFile(file, event.getMsg(), true);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

}
