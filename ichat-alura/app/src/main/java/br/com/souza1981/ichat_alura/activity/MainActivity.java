package br.com.souza1981.ichat_alura.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.souza1981.ichat_alura.R;
import br.com.souza1981.ichat_alura.adapter.MensagemAdapter;
import br.com.souza1981.ichat_alura.app.ChatApplication;
import br.com.souza1981.ichat_alura.callback.EnviarMensagemCallBack;
import br.com.souza1981.ichat_alura.callback.OuvirMensagemCallback;
import br.com.souza1981.ichat_alura.componente.ChatComponent;
import br.com.souza1981.ichat_alura.modelo.FailureEvent;
import br.com.souza1981.ichat_alura.modelo.Mensagem;
import br.com.souza1981.ichat_alura.modelo.MensagemEvent;
import br.com.souza1981.ichat_alura.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;

    @BindView(R.id.ed_texto)
    EditText editText;

    @BindView(R.id.bt_enviar)
    Button button;

    @BindView(R.id.lv_mensagens)
    ListView listaMensagens;

    @BindView(R.id.iv_avatar_usuario)
    ImageView avatarUsuario;

    private List<Mensagem> mensagens;

    @Inject
    public ChatService chatService;

    @Inject
    Picasso picasso;

    @Inject
    EventBus eventBus;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        ButterKnife.bind(this);


        picasso.get()
                .load("https://api.adorable.io/avatars/285/" + idDoCliente + ".png")
                .into(avatarUsuario);

        if (savedInstanceState != null){
            mensagens = (ArrayList<Mensagem>) savedInstanceState.getSerializable("mensagens");
        } else {
            mensagens = new ArrayList<>();

        }


        MensagemAdapter adapter = new MensagemAdapter(mensagens, this, idDoCliente);

        listaMensagens.setAdapter(adapter);

        ouvirMensagem(new MensagemEvent(null));

        eventBus.register(this);

    }

    @OnClick(R.id.bt_enviar)
    public void enviarMensagem(){
        chatService.enviar(new Mensagem(idDoCliente,editText.getText().toString())).enqueue(new EnviarMensagemCallBack());
        editText.getText().clear();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }

    @Subscribe
    public void ouvirMensagem(MensagemEvent mensagemEvent) {
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagemCallback(eventBus,this));
    }

    @Subscribe
    public void colocaNaLista(MensagemEvent mensagemEvent) {
        mensagens.add(mensagemEvent.getMensagem());
        MensagemAdapter adapter = new MensagemAdapter(mensagens, this, idDoCliente);
        listaMensagens.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Subscribe
    public void lidarCom(FailureEvent event){
        ouvirMensagem(null);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putSerializable("mensagen",(ArrayList<Mensagem>) mensagens);
    }
}
