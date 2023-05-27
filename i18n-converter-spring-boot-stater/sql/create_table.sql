create table i18n
(
    id    varchar(100) not null
        primary key,
    zh_tw varchar(200) not null,
    en_us varchar(100) not null,
    zh_cn varchar(100) not null
);

create table tag_config
(
    id                   bigint auto_increment
        primary key,
    many                 bigint       null,
    label                varchar(100) not null,
    ui_type              varchar(100) not null,
    select_cate          varchar(100) null,
    parent_select_cate   bigint       null,
    tag_key              varchar(100) null,
    aspect_data_category varchar(100) null,
    placeholder          varchar(100) null
);

create table v_user
(
    uid     varchar(100) not null
        primary key,
    account varchar(200) not null,
    name    varchar(100) not null,
    status  bigint       not null
);

