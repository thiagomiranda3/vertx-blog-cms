package br.com.tommiranda.blog.api.notifications;

public class NotificationError extends Notification {

    private NotificationError() {
        super();
    }

    public NotificationError(String mensagem) {
        super(mensagem);
        this.sucesso = false;
    }

    public NotificationError(String mensagem, Object retorno) {
        super(mensagem, retorno);
        this.sucesso = false;
    }
}
