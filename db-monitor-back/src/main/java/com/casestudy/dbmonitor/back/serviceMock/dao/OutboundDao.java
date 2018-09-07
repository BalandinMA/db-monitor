package com.casestudy.dbmonitor.back.serviceMock.dao;


import com.casestudy.dbmonitor.back.serviceMock.entity.Outbound;
import org.springframework.data.repository.CrudRepository;

public interface OutboundDao extends CrudRepository<Outbound, Long> {

}
