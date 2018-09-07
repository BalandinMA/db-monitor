CREATE TABLE INBOUND
(
    INBD_ID VARCHAR(255) PRIMARY KEY NOT NULL,
    INBD_CHANNEL_ID INT,
    INBD_TIME TIMESTAMP,
    INBD_STATUS INT
);
CREATE UNIQUE INDEX INBOUND_INBD_ID_uindex ON INBOUND (INBD_ID);

CREATE TABLE PREPROCESSING
(
    PREPROC_ID VARCHAR(255) PRIMARY KEY NOT NULL,
    PREPROC_FILE_TYPE VARCHAR(255),
    PREPROC_FILE_SZE VARCHAR(255),
    PREPROC_INBD_ID VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX PREPROCESSING_PREPROC_ID_uindex ON PREPROCESSING (PREPROC_ID);

CREATE TABLE PROCESSING
(
    PROC_ID VARCHAR(255) PRIMARY KEY NOT NULL,
    PROC_CUSTOMER_ID VARCHAR(255),
    PROC_COUNTERPARTY_ID VARCHAR(255),
    PROC_TYPE INT,
    PROC_BUSINESS_TYPE VARCHAR(255),
    PROC_STATUS INT,
    PROC_PREPROC_ID VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX POSTPROCESSING_POSTPROC_ID_uindex ON POSTPROCESSING (POSTPROC_ID);

CREATE TABLE OUTBOUND
(
    OUTBOUND_ID VARCHAR(255) PRIMARY KEY NOT NULL,
    OUTBOUND_CHANNEL_ID VARCHAR(255),
    OUTBOUND_STATUS INT,
    OUTBOUND_PROC_ID VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX OUTBOUND_OUTBOUND_ID_uindex ON OUTBOUND (OUTBOUND_ID);