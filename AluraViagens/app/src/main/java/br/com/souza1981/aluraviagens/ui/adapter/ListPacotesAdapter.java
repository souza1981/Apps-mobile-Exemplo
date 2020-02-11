package br.com.souza1981.aluraviagens.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.souza1981.aluraviagens.model.Pacote;
import br.com.souza1981.aluraviagens.R;

public class ListPacotesAdapter extends BaseAdapter {


    private final List<Pacote> _pacotes;
    private final Context _context;

    public ListPacotesAdapter(List<Pacote> pacotes, Context context){
        _pacotes = pacotes;
        _context = context;
    }

    @Override
    public int getCount() {
        return _pacotes.size();
    }

    @Override
    public Pacote getItem(int position) {
        return _pacotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewCriada = LayoutInflater.from(_context).inflate(R.layout.item_pacote, parent, false);

        Pacote pacote = _pacotes.get(position);

        TextView local = viewCriada.findViewById(R.id.item_pacote_local);
        local.setText(pacote.getLocal());

        ImageView imagem = viewCriada.findViewById(R.id.item_pacote_imagem);
        Resources resources = _context.getResources();
        int idDoDrawable = resources.getIdentifier(pacote.getImagem(), "drawable", _context.getOpPackageName());
        Drawable drawableImagemPacote = resources.getDrawable(idDoDrawable);
        imagem.setImageDrawable(drawableImagemPacote);

        TextView dias = viewCriada.findViewById(R.id.item_pacote_dias);
        dias.setText(pacote.getDias() + " dias");

        TextView preco = viewCriada.findViewById(R.id.item_pacote_preco);
        preco.setText(pacote.getPreco().toString());


        return viewCriada;
    }
}
