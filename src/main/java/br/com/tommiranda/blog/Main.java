package br.com.tommiranda.blog;

import br.com.tommiranda.blog.api.MainVerticle;
import br.com.tommiranda.blog.config.Config;
import br.com.tommiranda.blog.config.ConfigBuilder;
import br.com.tommiranda.blog.db.SetupDB;
import br.com.tommiranda.blog.util.ExceptionPrinter;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            final Vertx vertx = Vertx.vertx();
            final Config config = ConfigBuilder.getConfiguration();
            final PgPool pgClient = new SetupDB(vertx, config).getPgClient();

            vertx.deployVerticle(new MainVerticle(pgClient));

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                logger.info("A APLICAÇÃO SERÁ FECHADA!");

                vertx.close();
                pgClient.close();
            }));
        } catch (IOException e) {
            logger.error("Exception na inicialização da aplicação: " + ExceptionPrinter.print(e));
        }
    }
}
