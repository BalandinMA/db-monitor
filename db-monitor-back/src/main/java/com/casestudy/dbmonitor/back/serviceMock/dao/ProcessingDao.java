package com.casestudy.dbmonitor.back.serviceMock.dao;


import com.casestudy.dbmonitor.back.serviceMock.entity.Processing;
import org.springframework.data.repository.CrudRepository;

public interface ProcessingDao extends CrudRepository<Processing, Long> {

}
