package br.com.tommiranda.blog.db.dao.impl;

import br.com.tommiranda.blog.db.dao.ArtigoDAO;
import br.com.tommiranda.blog.db.dao.BaseDAO;
import io.vertx.pgclient.PgPool;

public class ArtigoDAOImpl extends BaseDAO implements ArtigoDAO {

    public ArtigoDAOImpl(PgPool pgClient) {
        super(pgClient);
    }
}
