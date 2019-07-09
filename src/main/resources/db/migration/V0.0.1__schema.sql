create table if not exists users(
    id serial primary key ,
    login varchar(50) not null unique ,
    company varchar(255) ,
    department varchar(255)
);

create table if not exists os(
    id serial primary key ,
    osname varchar(255) not null
);

create table if not exists processors(
    id serial primary key ,
    procname varchar(255) not null
);

create table if not exists users_history(
    id serial primary key ,
    pc varchar(255) not null ,
    currentuser varchar(255) not null ,
    start_date varchar(255) ,
    end_date varchar(255)
);

create table if not exists computers(
    id serial primary key ,
    procname integer ,
    memory varchar(255) ,
    hdd varchar(255) ,
    user_login integer ,
    inet_addr varchar(255) ,
    mac varchar(255) not null ,
    display varchar(255) ,
    chipset varchar(255)  ,
    hdd_space varchar(255) ,
    last_names varchar(255) ,
    motherboard varchar(255) ,
    pcname varchar(255) not null ,
    osname integer ,
    constraint user_id_fkey foreign key (user_login) references users(id),
    constraint os_name_fkey foreign key (osname) references os(id),
    constraint processor_name_fkey foreign key (procname) references processors(id)
);

