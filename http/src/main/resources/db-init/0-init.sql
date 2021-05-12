CREATE TABLE car
(
    id int auto_increment,
    vin text not null,
    model text not null,
    year int not null,
    rent_office_id int not null,
    constraint car_pk
        primary key (id)
);
