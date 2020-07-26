package br.com.tommiranda.blog.db.dao;

import br.com.tommiranda.blog.exceptions.InstantiationDAOException;
import io.vertx.pgclient.PgPool;
import org.eclipse.collections.api.factory.Maps;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

/**
 * @author wwthi
 */
public final class DAOLocator {

    private static DAOLocator locator;
    private final Map<Class<?>, Object> daos = Maps.mutable.empty();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private DAOLocator(PgPool pgClient) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Reflections reflections = new Reflections("br.com.tommiranda.blog.db.dao.impl");

        Set<Class<? extends BaseDAO>> daoClasses = reflections.getSubTypesOf(BaseDAO.class);

        for (Class<? extends BaseDAO> daoClass : daoClasses) {
            Class<?>[] interfaces = daoClass.getInterfaces();

            if (interfaces.length != 1) {
                throw new InstantiationDAOException("A classe " + daoClass.getSimpleName() + " deve implementar apenas uma interface DAO");
            }

            logger.info("Instanciando " + daoClass.getSimpleName());
            daos.put(interfaces[0], daoClass.getDeclaredConstructor().newInstance(pgClient));
        }
    }

    public static synchronized void initSingleton(PgPool pgClient) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (locator == null) {
            if (pgClient == null) {
                throw new InstantiationDAOException("Essa classe não pode ser inciado se pgClient for nulo");
            }

            locator = new DAOLocator(pgClient);
        }
    }

    public static <I> I getService(Class<I> type) {
        if(locator == null) {
            throw new IllegalStateException("É necessário inicializar essa classe com initSingleton antes de utilizar esse método");
        }

        if (!type.isInterface()) {
            throw new IllegalArgumentException("É necessário passar uma interface no método");
        }

        if (!locator.daos.containsKey(type)) {
            throw new IllegalArgumentException("A interface passada não é gerenciada por esse DAOLocator");
        }

        return type.cast(locator.daos.get(type));
    }
}
