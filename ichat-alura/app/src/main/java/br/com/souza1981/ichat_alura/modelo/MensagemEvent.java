package br.com.souza1981.ichat_alura.modelo;

import br.com.souza1981.ichat_alura.modelo.Mensagem;

public class MensagemEvent {
    private final Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return this.mensagem;
    }
}
