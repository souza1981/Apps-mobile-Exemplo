package br.com.souza1981.ichat_alura.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.souza1981.ichat_alura.R;
import br.com.souza1981.ichat_alura.modelo.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemAdapter extends BaseAdapter {

    private final int idCliente;
    private final List<Mensagem> mensagens;
    private final Activity activity;


    @Inject
    Picasso picasso;

    @BindView(R.id.tv_texto)
    TextView texto;

    @BindView(R.id.iv_avatar_mensagem)
    ImageView imageViewAvatar;

    public MensagemAdapter(List<Mensagem> mensagens,
                           Activity activity,
                           int idClient){
        this.mensagens = mensagens;
        this.activity = activity;
        this.idCliente = idClient;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Object getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem,parent, false);

        ButterKnife.bind(this,linha);


        Mensagem mensagem = (Mensagem) getItem(position);

        picasso.get()
                .load("https://api.adorable.io/avatars/285/" + mensagem.getId() + ".png")
                .into(imageViewAvatar);

        if (idCliente != mensagem.getId()){
            linha.setBackgroundColor(Color.CYAN);
        }

        texto.setText(mensagem.getText());

        return linha;
    }
}
