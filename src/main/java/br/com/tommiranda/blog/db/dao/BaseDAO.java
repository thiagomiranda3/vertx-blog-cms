package br.com.tommiranda.blog.db.dao;

import io.vertx.pgclient.PgPool;

public abstract class BaseDAO {

    protected final PgPool pgClient;

    public BaseDAO(PgPool pgClient) {
        this.pgClient = pgClient;
    }
}
