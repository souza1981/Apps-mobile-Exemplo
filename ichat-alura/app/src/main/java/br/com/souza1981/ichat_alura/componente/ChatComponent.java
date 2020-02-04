package br.com.souza1981.ichat_alura.componente;

import org.greenrobot.eventbus.EventBus;

import br.com.souza1981.ichat_alura.activity.MainActivity;
import br.com.souza1981.ichat_alura.adapter.MensagemAdapter;
import br.com.souza1981.ichat_alura.module.ChatModule;
import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);
    void inject(MensagemAdapter mensagemAdapter);
    void inject(EventBus eventBus);

}
