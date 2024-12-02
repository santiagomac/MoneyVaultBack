create table public.users
(
    id       serial
        primary key,
    email    varchar default ''::character varying not null,
    password varchar default ''::character varying not null,
    username varchar default ''::character varying not null
);

create table public.transactions
(
    id            serial primary key,
    title         varchar   not null,
    amount        double precision,
    category      varchar   not null,
    date          timestamp not null,
    budget_id     integer
        references public.budgets,
    movement_type varchar,
    user_id integer,
    foreign key (user_id) references users(id)
);

create table public.budgets
(
    id    serial
        primary key,
    title varchar not null,
    user_id integer,
    foreign key (user_id) references users(id)
);