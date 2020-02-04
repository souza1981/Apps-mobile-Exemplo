package br.com.souza1981.ichat_alura.app;

import android.app.Application;

import br.com.souza1981.ichat_alura.componente.ChatComponent;
import br.com.souza1981.ichat_alura.componente.DaggerChatComponent;
import br.com.souza1981.ichat_alura.module.ChatModule;

public class ChatApplication extends Application {
    private ChatComponent component;

    @Override
    public void onCreate() {
        component = DaggerChatComponent
                .builder()
                .chatModule(new ChatModule(this))
                .build();
        super.onCreate();
    }

    public ChatComponent getComponent(){
        return component;
    }

}
