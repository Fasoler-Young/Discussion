create TABLE IF NOT EXISTS question (
    id SERIAL PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    comment VARCHAR(300)
);

CREATE TABLE IF NOT EXISTS relation(
    id SERIAL PRIMARY KEY,
    thesis SERIAL NOT NULL,
    argument SERIAL NOT NULL,
    influence REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS answer (
    id SERIAL PRIMARY KEY,
    thesis SERIAL NOT NULL,
    confidence REAL NOT NULL
);