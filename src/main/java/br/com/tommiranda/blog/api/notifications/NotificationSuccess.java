package br.com.tommiranda.blog.api.notifications;

public class NotificationSuccess extends Notification {

    private NotificationSuccess() {
        super();
    }

    public NotificationSuccess(String mensagem) {
        super(mensagem);
        this.sucesso = true;
    }

    public NotificationSuccess(String mensagem, Object retorno) {
        super(mensagem, retorno);
        this.sucesso = true;
    }
}
