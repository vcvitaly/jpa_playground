CREATE SEQUENCE public.users_pk_seq;

ALTER SEQUENCE public.users_pk_seq
    OWNER TO postgres;

CREATE TABLE public.users
(
    id         BIGINT DEFAULT NEXTVAL('users_pk_seq') NOT NULL
        CONSTRAINT users_pk
            PRIMARY KEY,
    first_name VARCHAR(30)                            NOT NULL,
    last_name  VARCHAR(30)                            NOT NULL,
    created_at DATE                                   NOT NULL,
    updated_at DATE
);
