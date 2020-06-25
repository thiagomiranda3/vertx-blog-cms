package br.com.tommiranda;

import br.com.tommiranda.notifications.NotificationSuccess;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class MainVerticle extends AbstractVerticle {

    private Gson gson = new Gson();
    private PgPool pgClient;

    private Future<Void> prepareDatabase() {
        Promise<Void> promise = Promise.promise();

        PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
        PgConnectOptions connectOptions = new PgConnectOptions().setPort(5432)
                                                                .setHost("192.168.0.111")
                                                                .setDatabase("blog_cms")
                                                                .setUser("blog_cms")
                                                                .setPassword("blog_cms");

        PgPool pgClient = PgPool.pool(vertx, connectOptions, poolOptions);

        promise.complete();

        return promise.future();
    }

    private Future<Void> startHttpServer() {
        Promise<Void> promise = Promise.promise();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.get("/hello").handler(ctx -> {
            ctx.response()
               .putHeader("Content-Type", "application/json; charset=utf-8")
               .end(gson.toJson(new NotificationSuccess("Olá Mundo")));
        });

        server.requestHandler(router)
              .listen(8080, ar -> {
                  if (ar.succeeded()) {
                      System.out.println("Servidor iniciado na porta 8080");
                      promise.complete();
                  } else {
                      System.out.println("Não foi possível iniciar o servidor HTTP na porta 8080");
                      promise.fail(ar.cause());
                  }
              });

        return promise.future();
    }

    @Override
    public void start(Promise<Void> promise) throws Exception {
        Future<Void> steps = prepareDatabase().compose(p -> startHttpServer());
        steps.onComplete(promise);
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {
        pgClient.close();
    }
}
