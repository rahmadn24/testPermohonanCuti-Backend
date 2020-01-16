/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     16/01/2020 16:06:32                          */
/*==============================================================*/


drop index RELATIONSHIP_5_FK;

drop index BUCKET_APPROVAL_PK;

drop table BUCKET_APPROVAL;

drop index RELATIONSHIP_3_FK;

drop index POSITION_PK;

drop table "POSITION";

drop index RELATIONSHIP_2_FK;

drop index POSITION_LEAVE_PK;

drop table POSITION_LEAVE;

drop index RELATIONSHIP_1_FK;

drop index USERS_PK;

drop table USERS;

drop index RELATIONSHIP_6_FK;

drop index RELATIONSHIP_4_FK;

drop index USER_LEAVE_REQUEST_PK;

drop table USER_LEAVE_REQUEST;

/*==============================================================*/
/* Table: BUCKET_APPROVAL                                       */
/*==============================================================*/
create table BUCKET_APPROVAL (
   BUCKET_APPROVAL_ID   INT8                 not null,
   USER_LEAVE_REQUEST_ID INT8                 null,
   RESOLVER_REASON      VARCHAR(100)         null,
   RESOLVED_DATE        DATE                 null,
   RESOLVER_BY          VARCHAR(100)         null,
   CREATED_DATE         DATE                 null,
   CREATED_BY           VARCHAR(255)         null,
   UPDATE_DATE          DATE                 null,
   UPDATE_BY            VARCHAR(255)         null,
   constraint PK_BUCKET_APPROVAL primary key (BUCKET_APPROVAL_ID)
);

/*==============================================================*/
/* Index: BUCKET_APPROVAL_PK                                    */
/*==============================================================*/
create unique index BUCKET_APPROVAL_PK on BUCKET_APPROVAL (
BUCKET_APPROVAL_ID
);

/*==============================================================*/
/* Index: RELATIONSHIP_5_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_5_FK on BUCKET_APPROVAL (
USER_LEAVE_REQUEST_ID
);

/*==============================================================*/
/* Table: "POSITION"                                            */
/*==============================================================*/
create table "POSITION" (
   POSITION_ID          INT8                 not null,
   POSITION_LEAVE_ID    INT4                 null,
   POSITION_NAME        VARCHAR(30)          null,
   LEVEL                INT4                 null,
   CREATED_DATE         DATE                 null,
   CREATED_BY           VARCHAR(255)         null,
   UPDATE_DATE          DATE                 null,
   UPDATE_BY            VARCHAR(255)         null,
   constraint PK_POSITION primary key (POSITION_ID)
);

/*==============================================================*/
/* Index: POSITION_PK                                           */
/*==============================================================*/
create unique index POSITION_PK on "POSITION" (
POSITION_ID
);

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_3_FK on "POSITION" (
POSITION_LEAVE_ID
);

/*==============================================================*/
/* Table: POSITION_LEAVE                                        */
/*==============================================================*/
create table POSITION_LEAVE (
   POSITION_LEAVE_ID    INT4                 not null,
   POSITION_ID          INT8                 null,
   LEAVE_APPROVE        INT4                 null,
   CREATED_DATE         DATE                 null,
   CREATED_BY           VARCHAR(255)         null,
   UPDATE_DATE          DATE                 null,
   UPDATE_BY            VARCHAR(255)         null,
   constraint PK_POSITION_LEAVE primary key (POSITION_LEAVE_ID)
);

/*==============================================================*/
/* Index: POSITION_LEAVE_PK                                     */
/*==============================================================*/
create unique index POSITION_LEAVE_PK on POSITION_LEAVE (
POSITION_LEAVE_ID
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_2_FK on POSITION_LEAVE (
POSITION_ID
);

/*==============================================================*/
/* Table: USERS                                                 */
/*==============================================================*/
create table USERS (
   USER_ID              INT8                 not null,
   POSITION_ID          INT8                 null,
   USERNAME             VARCHAR(30)          null,
   LEAVE_AVAIBLE        INT4                 null,
   CREATED_DATE         DATE                 null,
   CREATED_BY           VARCHAR(255)         null,
   UPDATE_DATE          DATE                 null,
   UPDATE_BY            VARCHAR(255)         null,
   constraint PK_USERS primary key (USER_ID)
);

/*==============================================================*/
/* Index: USERS_PK                                              */
/*==============================================================*/
create unique index USERS_PK on USERS (
USER_ID
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on USERS (
POSITION_ID
);

/*==============================================================*/
/* Table: USER_LEAVE_REQUEST                                    */
/*==============================================================*/
create table USER_LEAVE_REQUEST (
   USER_LEAVE_REQUEST_ID INT8                 not null,
   USER_ID              INT8                 null,
   BUCKET_APPROVAL_ID   INT8                 null,
   LEAVE_DATE_FROM      DATE                 null,
   LEAVE_DATE_TO        DATE                 null,
   STATUS               VARCHAR(30)          null,
   DESCRIPTION          VARCHAR(255)         null,
   CREATED_DATE         DATE                 null,
   CREATED_BY           VARCHAR(255)         null,
   UPDATE_DATE          DATE                 null,
   UPDATE_BY            VARCHAR(255)         null,
   constraint PK_USER_LEAVE_REQUEST primary key (USER_LEAVE_REQUEST_ID)
);

/*==============================================================*/
/* Index: USER_LEAVE_REQUEST_PK                                 */
/*==============================================================*/
create unique index USER_LEAVE_REQUEST_PK on USER_LEAVE_REQUEST (
USER_LEAVE_REQUEST_ID
);

/*==============================================================*/
/* Index: RELATIONSHIP_4_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_4_FK on USER_LEAVE_REQUEST (
USER_ID
);

/*==============================================================*/
/* Index: RELATIONSHIP_6_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_6_FK on USER_LEAVE_REQUEST (
BUCKET_APPROVAL_ID
);

alter table BUCKET_APPROVAL
   add constraint FK_BUCKET_A_RELATIONS_USER_LEA foreign key (USER_LEAVE_REQUEST_ID)
      references USER_LEAVE_REQUEST (USER_LEAVE_REQUEST_ID)
      on delete restrict on update restrict;

alter table "POSITION"
   add constraint FK_POSITION_RELATIONS_POSITION foreign key (POSITION_LEAVE_ID)
      references POSITION_LEAVE (POSITION_LEAVE_ID)
      on delete restrict on update restrict;

alter table POSITION_LEAVE
   add constraint FK_POSITION_RELATIONS_POSITION foreign key (POSITION_ID)
      references "POSITION" (POSITION_ID)
      on delete restrict on update restrict;

alter table USERS
   add constraint FK_USERS_RELATIONS_POSITION foreign key (POSITION_ID)
      references "POSITION" (POSITION_ID)
      on delete restrict on update restrict;

alter table USER_LEAVE_REQUEST
   add constraint FK_USER_LEA_RELATIONS_USERS foreign key (USER_ID)
      references USERS (USER_ID)
      on delete restrict on update restrict;

alter table USER_LEAVE_REQUEST
   add constraint FK_USER_LEA_RELATIONS_BUCKET_A foreign key (BUCKET_APPROVAL_ID)
      references BUCKET_APPROVAL (BUCKET_APPROVAL_ID)
      on delete restrict on update restrict;

