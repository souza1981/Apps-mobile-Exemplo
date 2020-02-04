package br.com.souza1981.ichat_alura.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Mensagem implements Serializable {
    private String text;
    private int id;

    public Mensagem(int id, String mensagem) {
        this.id = id;
        this.text = mensagem;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return this.id;
    }

}
