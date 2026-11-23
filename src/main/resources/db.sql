CREATE TABLE zona_fit_db.customer (
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  firstname character varying,
  lastname character varying,
  membership bigint NOT NULL UNIQUE,
  CONSTRAINT customer_pkey PRIMARY KEY (id)
);