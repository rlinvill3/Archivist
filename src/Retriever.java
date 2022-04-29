import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Retriever {

    URL url;
    HttpClient client;
    String apiKey;

    Retriever(String url,String apiKey) throws IOException, InterruptedException{

        try {
            this.url=new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        client =HttpClient.newHttpClient();
        this.apiKey=apiKey;
        var request=HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer "+apiKey)
            .build();

        HttpResponse<Path> response=client.send(request, BodyHandlers.ofFile(Paths.get("test")));
        System.out.println(response.body());
        

    }


    public static void main(String[] args) throws Exception {

        Retriever retriever=new Retriever("https://api.wanikani.com/v2/assignments","d296c425-0887-4059-8f16-a1f62bfe1e19");

    }
}
