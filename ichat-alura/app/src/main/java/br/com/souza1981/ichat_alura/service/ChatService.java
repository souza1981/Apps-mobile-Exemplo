package br.com.souza1981.ichat_alura.service;

import br.com.souza1981.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ChatService {

    @POST("polling")
    Call<Void> enviar(@Body Mensagem mensagem);

    @GET("polling")
    Call<Mensagem> ouvirMensagens();

}

/*
public class ChatService {
   private String endpoint = "http://192.168.0.22:8080/polling";
    private MainActivity activity;

    public ChatService(MainActivity activity){
        this.activity = activity;
    }

    public void enviar(final Mensagem mensagem) {

        new Thread(new Runnable() {

            @Override
            public void run() {

                String texto = mensagem.getText();

                try {

                    // Altere para o seu IP
                    URL url = new URL(endpoint);

                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

                    httpConnection.setRequestMethod("POST");

                    httpConnection.setRequestProperty("Content-type", "application/json");

                    JSONStringer json = new JSONStringer()
                            .object()
                            .key("text")
                            .value(texto)
                            .key("id")
                            .value(mensagem.getId())
                            .endObject();

                    OutputStream saida = httpConnection.getOutputStream();
                    PrintStream ps = new PrintStream(saida);

                    ps.println(json.toString());

                    httpConnection.connect();
                    httpConnection.getInputStream();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void ouvirMensagens(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    // Altere para o seu IP
                    URL url = new URL(endpoint);

                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

                    httpConnection.setRequestMethod("GET");

                    httpConnection.setRequestProperty("Accept", "application/json");

                    httpConnection.connect();

                    Scanner scanner = new Scanner(httpConnection.getInputStream());
                    StringBuilder builder = new StringBuilder();

                    while(scanner.hasNextLine()) {
                        builder.append(scanner.nextLine());
                    }

                    String json = builder.toString();

                    JSONObject jsonObject = new JSONObject(json);
                    final Mensagem mensagem = new Mensagem(jsonObject.getInt("id"), jsonObject.getString("text"));

                    // Colocando a mensagem na lista, mas esse método não existe ainda, certo?
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.colocaNaLista(mensagem);
                        }
                    });

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
*/