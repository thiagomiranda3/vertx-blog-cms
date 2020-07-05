package br.com.tommiranda.blog.config;

/**
 * @author wwthi
 */
public final class Config {

    private final int httpPort;
    private final String dbHost;
    private final int dbPort;
    private final String dbName;
    private final String dbUser;
    private final String dbPass;
    private final String logLevel;

    public Config(int httpPort, String dbHost, int dbPort, String dbName, String dbUser, String dbPass, String logLevel) {
        this.httpPort = httpPort;
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        this.logLevel = logLevel;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public String getDbHost() {
        return dbHost;
    }

    public int getDbPort() {
        return dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public String getLogLevel() {
        return logLevel;
    }
}