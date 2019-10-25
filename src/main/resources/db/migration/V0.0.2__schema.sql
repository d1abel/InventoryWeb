create table if not exists users
(
    id         serial primary key,
    login      varchar(50) not null unique,
    company    varchar(255),
    department varchar(255)
);

create table if not exists computers
(
    id          serial primary key,
    procname    varchar(255),
    memory      varchar(255),
    hdd         varchar(255),
    user_login  integer,
    inet_addr   varchar(255),
    mac         varchar(255) not null,
    display     varchar(255),
    chipset     varchar(255),
    hdd_space   varchar(255),
    last_names  varchar(255),
    motherboard varchar(255),
    pcname      varchar(255) not null,
    osname      varchar(255),
    constraint user_id_fkey foreign key (user_login) references users (id)
);

create table if not exists users_history
(
    id          serial primary key,
    pc          varchar(255) not null,
    currentuser varchar(255) not null,
    start_date  varchar(255),
    end_date    varchar(255)
);
