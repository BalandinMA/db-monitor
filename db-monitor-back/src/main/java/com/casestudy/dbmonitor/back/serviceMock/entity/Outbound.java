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
    public class Outbound {
    @Id
    private String OUTBOUND_ID;
    private String OUTBOUND_CHANNEL_ID;
    private int OUTBOUND_STATUS;
    private String OUTBOUND_PROC_ID;
}
