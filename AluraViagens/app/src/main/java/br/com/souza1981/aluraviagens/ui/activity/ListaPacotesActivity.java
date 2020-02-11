package br.com.souza1981.aluraviagens.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.souza1981.aluraviagens.R;
import br.com.souza1981.aluraviagens.dao.PacoteDAO;
import br.com.souza1981.aluraviagens.model.Pacote;
import br.com.souza1981.aluraviagens.ui.adapter.ListPacotesAdapter;

public class ListaPacotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacotes);

        ListView listaDePacotes = findViewById(R.id.lista_pacotes_listview);

        List<Pacote> lista = new PacoteDAO().lista();


        listaDePacotes.setAdapter(new ListPacotesAdapter(lista,this));


    }
}
