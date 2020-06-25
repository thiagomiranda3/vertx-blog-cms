package br.com.tommiranda.notifications;

public abstract class Notification {

    Boolean sucesso;
    private String mensagem;
    private Object retorno;

    protected Notification() {
    }

    Notification(String mensagem) {
        this.mensagem = mensagem;
    }

    Notification(String mensagem, Object retorno) {
        this.mensagem = mensagem;
        this.retorno = retorno;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Object getRetorno() {
        return retorno;
    }
}
