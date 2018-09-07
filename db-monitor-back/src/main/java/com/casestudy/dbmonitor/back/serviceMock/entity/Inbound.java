package com.casestudy.dbmonitor.back.serviceMock.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Inbound {
    @Id
    private String INBD_ID;
    private int INBD_CHANNEL_ID;
    private Date INBD_TIME;
    private int INBD_STATUS;
}
