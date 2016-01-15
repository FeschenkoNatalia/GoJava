CREATE TABLE payments
(
project_id NUMBER(10) NOT NULL,
user_name VARCHAR2(100) NOT NULL,
number_card NUMBER(16) NOT NULL,
amount_donation NUMBER(10) NOT NULL
);

CREATE TABLE quotes
(
id NUMBER NOT NULL PRIMARY KEY,
content VARCHAR2(500) NOT NULL,
author VARCHAR2(100) NOT NULL
);

CREATE TABLE categories
(
id NUMBER(10) NOT NULL,
name VARCHAR2(100) NOT NULL
);

CREATE TABLE rewards
(
project_id NUMBER(10) NOT NULL,
donating_sum NUMBER(10) NOT NULL,
description VARCHAR2(100)
);

CREATE TABLE projects
(
id NUMBER NOT NULL PRIMARY KEY,
id_category NUMBER(10) NOT NULL,
name VARCHAR2(200) NOT NULL,
general_description VARCHAR2(500) NOT NULL,
full_description VARCHAR2(900),
video_link VARCHAR2(200),
required_sum NUMBER(10) NOT NULL,
collected_sum NUMBER(10),
days_left NUMBER(10) NOT NULL
);