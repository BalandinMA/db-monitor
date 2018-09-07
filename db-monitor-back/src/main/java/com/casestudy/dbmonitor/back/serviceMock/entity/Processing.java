package com.casestudy.dbmonitor.back.serviceMock.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Processing {
    @Id
    private String PROC_ID;
    private String PROC_CUSTOMER_ID;
    private String PROC_COUNTERPARTY_ID;
    private int PROC_TYPE;
    private String PROC_BUSINESS_TYPE;
    private int PROC_STATUS;
    private String PROC_PREPROC_ID;
}
