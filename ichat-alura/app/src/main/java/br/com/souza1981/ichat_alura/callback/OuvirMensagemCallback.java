package br.com.souza1981.ichat_alura.callback;

import org.greenrobot.eventbus.EventBus;

import br.com.souza1981.ichat_alura.activity.MainActivity;
import br.com.souza1981.ichat_alura.modelo.FailureEvent;
import br.com.souza1981.ichat_alura.modelo.Mensagem;
import br.com.souza1981.ichat_alura.modelo.MensagemEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagemCallback implements Callback<Mensagem> {

    private final EventBus eventBus;
    private MainActivity activity;

    public OuvirMensagemCallback (EventBus eventBus,
                                  MainActivity activity){
        this.activity = activity;
        this.eventBus = eventBus;

    }
    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if (response.isSuccessful()){
            Mensagem mensagem = response.body();

            eventBus.post(new MensagemEvent(mensagem));
        }

    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        eventBus.post(new FailureEvent());
    }

}
