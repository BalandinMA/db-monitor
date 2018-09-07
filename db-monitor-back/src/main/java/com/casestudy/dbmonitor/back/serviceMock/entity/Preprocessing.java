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
public class Preprocessing {
    @Id
    private String PREPROC_ID;
    private String PREPROC_FILE_TYPE;
    private String PREPROC_FILE_SZE;
    private String PREPROC_INBD_ID;
}
