package com.casestudy.dbmonitor.back.serviceMock.service;

import com.casestudy.dbmonitor.back.serviceMock.dao.*;
import com.casestudy.dbmonitor.back.serviceMock.entity.Inbound;
import com.casestudy.dbmonitor.back.serviceMock.entity.Outbound;
import com.casestudy.dbmonitor.back.serviceMock.entity.Preprocessing;
import com.casestudy.dbmonitor.back.serviceMock.entity.Processing;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
@Log4j2
public class MockService {
    private final InboundDao inboundDao;
    private final PreprocessingDao preprocessingDao;
    private final ProcessingDao processingDao;
    private final OutboundDao outboundDao;

    public String addInbound() {
        Inbound inbound = Inbound.builder()
                .INBD_ID("firstInsert")
                .INBD_CHANNEL_ID(1)
                .INBD_TIME(new Date())
                .INBD_STATUS(1)
                .build();
        log.info(inbound);
        inboundDao.save(inbound);
        return inbound.getINBD_ID();
    }

    public String correctProcessing() {
//
        Inbound inbound = Inbound.builder()
                .INBD_ID("first_id")
                .INBD_CHANNEL_ID(1)
                .INBD_TIME(new Date())
                .INBD_STATUS(1)
                .build();
        inboundDao.save(inbound);
//
        Preprocessing preprocessing = Preprocessing.builder()
                .PREPROC_ID("first_id_preproc")
                .PREPROC_FILE_TYPE("XML")
                .PREPROC_FILE_SZE("1000 KB")
                .PREPROC_INBD_ID("first_id")
                .build();
        preprocessingDao.save(preprocessing);
//        
        Processing processing1 = Processing.builder()
                .PROC_ID("first_id_proc_1")
                .PROC_CUSTOMER_ID("some id")
                .PROC_COUNTERPARTY_ID("some id")
                .PROC_TYPE(20)
                .PROC_BUSINESS_TYPE("big deal")
                .PROC_STATUS(10)
                .PROC_PREPROC_ID("first_id_preproc")
                .build();
        processingDao.save(processing1);

        Outbound outbound1 = Outbound.builder()
                .OUTBOUND_ID("first_id_out_1")
                .OUTBOUND_CHANNEL_ID("some channel")
                .OUTBOUND_STATUS(200)
                .OUTBOUND_PROC_ID("first_id_proc_1")
                .build();
        outboundDao.save(outbound1);

//
        Processing processing2 = Processing.builder()
                .PROC_ID("first_id_proc_2")
                .PROC_CUSTOMER_ID("anther id")
                .PROC_COUNTERPARTY_ID("another id")
                .PROC_TYPE(20)
                .PROC_BUSINESS_TYPE("big deal")
                .PROC_STATUS(10)
                .PROC_PREPROC_ID("first_id_preproc")
                .build();
        processingDao.save(processing2);

        Outbound outbound2 = Outbound.builder()
                .OUTBOUND_ID("first_id_out_2")
                .OUTBOUND_CHANNEL_ID("another channel")
                .OUTBOUND_STATUS(200)
                .OUTBOUND_PROC_ID("first_id_proc_2")
                .build();
        outboundDao.save(outbound2);

        Processing processing3 = Processing.builder()
                .PROC_ID("first_id_proc_3")
                .PROC_CUSTOMER_ID("customer id")
                .PROC_COUNTERPARTY_ID("counterparty id")
                .PROC_TYPE(20)
                .PROC_BUSINESS_TYPE("really big deal")
                .PROC_STATUS(11)
                .PROC_PREPROC_ID("first_id_preproc")
                .build();
        processingDao.save(processing3);

        Outbound outbound3 = Outbound.builder()
                .OUTBOUND_ID("first_id_out_3")
                .OUTBOUND_CHANNEL_ID("channel id")
                .OUTBOUND_STATUS(200)
                .OUTBOUND_PROC_ID("first_id_proc_3")
                .build();
        outboundDao.save(outbound3);
        return inbound.getINBD_ID();
    }

//    public String longProcessing() {
////
//        return inbound.getINBD_ID();
//    }
//
    public String invalidProcessing() {
//
        Inbound inbound = Inbound.builder()
                .INBD_ID("invalid_proc_id")
                .INBD_CHANNEL_ID(200)
                .INBD_TIME(new Date())
                .INBD_STATUS(1)
                .build();
        inboundDao.save(inbound);
//
        Preprocessing preprocessing = Preprocessing.builder()
                .PREPROC_ID("invalid_proc_id_preproc")
                .PREPROC_FILE_TYPE("JSON")
                .PREPROC_FILE_SZE("5 MB")
                .PREPROC_INBD_ID("invalid_proc_id")
                .build();
        preprocessingDao.save(preprocessing);
//
        Processing processing1 = Processing.builder()
                .PROC_ID("invalid_proc_id_proc")
                .PROC_CUSTOMER_ID("customer id")
                .PROC_COUNTERPARTY_ID("counterparty id")
                .PROC_TYPE(20)
                .PROC_BUSINESS_TYPE("deal")
                .PROC_STATUS(400)
                .PROC_PREPROC_ID("invalid_proc_id_preproc")
                .build();
        processingDao.save(processing1);
        return inbound.getINBD_ID();
    }
}
