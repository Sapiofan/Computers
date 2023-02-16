create table computers
(
    id         bigserial primary key,
    name       text not null,
    brand      text not null,
    price      integer not null
);

create table laptops
(
    ram             text not null,
    os              text not null,
    storage         integer not null,
    cores           integer not null,
    screen_diagonal  text not null,
    id bigint references computers(id) on delete cascade
);

create table tablets
(
    main_camera         text not null,
    front_camera        text not null,
    bluetooth          boolean not null,
    housing_material   text,
    id bigint references computers(id) on delete cascade
);

create table notes
(
    id         bigserial primary key,
    phone      text not null,
    address    text not null,
    created_at timestamp default now(),
    device_id bigserial references computers(id) on delete cascade
);


insert into computers(id, name, brand, price)
values (1, 'Aspire 3', 'Acer', 12999),
(2, 'ThinkPad', 'Lenovo', 7400),
(3, 'ExpertBook', 'Asus', 17999),
(4, 'YogaTab', 'Lenovo', 16900),
(5, 'T40', 'Teclast', 8100);

insert into laptops(ram, os, storage, cores, screen_diagonal, id)
values ('4 Gb', 'Windows 10', 512, 4, '15.7', 1),
('4 Gb', 'Windows 10', 128, 4, '13.6', 2),
('8 Gb', 'Windows 10', 1024, 8, '15.7', 3);

insert into tablets(main_camera, front_camera, bluetooth, housing_material, id)
values ('48 Mp', '32 Mp', true, 'metal', 4),
('32 Mp', '16 Mp', true, 'metal', 5);
