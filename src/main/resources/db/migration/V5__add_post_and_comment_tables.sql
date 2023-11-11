CREATE SEQUENCE posts_pk_seq;

ALTER SEQUENCE posts_pk_seq
    OWNER TO postgres;

CREATE TABLE posts
(
    id         BIGINT DEFAULT NEXTVAL('posts_pk_seq') NOT NULL
        CONSTRAINT posts_pk
            PRIMARY KEY,
    user_id    BIGINT                                 NOT NULL,
    title      VARCHAR(255)                           NOT NULL,
    body       VARCHAR(4000)                          NOT NULL,
    created_at DATE                                   NOT NULL,
    updated_at DATE
);

CREATE SEQUENCE post_comments_pk_seq;

ALTER SEQUENCE post_comments_pk_seq
    OWNER TO postgres;

CREATE TABLE post_comments
(
    id         BIGINT DEFAULT NEXTVAL('post_comments_pk_seq') NOT NULL
        CONSTRAINT post_comments_pk
            PRIMARY KEY,
    post_id    BIGINT                                 NOT NULL,
    body       VARCHAR(4000)                          NOT NULL,
    created_at DATE                                   NOT NULL,
    updated_at DATE
);