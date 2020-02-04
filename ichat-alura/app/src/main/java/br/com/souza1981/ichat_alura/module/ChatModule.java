package br.com.souza1981.ichat_alura.module;

import android.app.Application;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.souza1981.ichat_alura.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ChatModule {


    private final Application application;

    public ChatModule(Application application){
        this.application = application;
    }

    @Provides
    public ChatService getChatService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.22:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);
        return chatService;
    }

    @Provides
    public Picasso getPicasso(){
        Picasso picasso = new Picasso.Builder(application).build();
        return picasso;
    }

    @Provides
    public EventBus getEventBus(){
        EventBus eventBus = EventBus.builder().build();
        return eventBus;
    }
}
