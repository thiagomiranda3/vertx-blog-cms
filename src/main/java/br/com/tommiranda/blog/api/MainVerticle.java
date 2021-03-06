package br.com.tommiranda.blog.api;

import br.com.tommiranda.blog.api.notifications.NotificationSuccess;
import br.com.tommiranda.blog.db.dao.ArtigoDAO;
import br.com.tommiranda.blog.db.dao.DAOLocator;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

    private final DAOLocator daoLocator;
    private final Gson gson = new Gson();

    public MainVerticle(DAOLocator daoLocator) {
        this.daoLocator = daoLocator;
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

        ArtigoDAO artigoDAO = daoLocator.getService(ArtigoDAO.class);

        router.get("/artigos").handler(ctx -> {
            ctx.response()
               .putHeader("Content-Type", "application/json; charset=utf-8")
               .end(gson.toJson(new NotificationSuccess("Listagem de artigos")));
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
    public void start(Promise<Void> promise) {
        startHttpServer().onComplete(promise);
    }
}
