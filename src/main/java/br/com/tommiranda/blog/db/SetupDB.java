package br.com.tommiranda.blog.db;

import br.com.tommiranda.blog.config.Config;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class SetupDB {

    private final PgPool pgClient;

    public SetupDB(Vertx vertx, Config config) {
        PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
        PgConnectOptions connectOptions = new PgConnectOptions().setPort(config.getDbPort())
                                                                .setHost(config.getDbHost())
                                                                .setDatabase(config.getDbName())
                                                                .setUser(config.getDbUser())
                                                                .setPassword(config.getDbPass());

        this.pgClient = PgPool.pool(vertx, connectOptions, poolOptions);
    }

    public PgPool getPgClient() {
        return pgClient;
    }
}
