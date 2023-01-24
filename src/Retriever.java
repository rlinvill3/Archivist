import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import  com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Retriever {

    URL url;
    HttpClient client;
    String apiKey;


class UserJson{
    String object;
    Object[] pages;
    int total_count;
    String data_updated;
    Map<String,String>[] data;


public UserJson(String object, String[] pages, int total_count, String data_updated, Map<String,String>[] data){
    this.object=object;
    this.pages=pages;
    this.total_count=total_count;
    this.data_updated=data_updated;
    this.data=data;

}




}
    Retriever(String url,String apiKey) throws IOException, InterruptedException{
        //retrieves wanikanireq.json

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

        HttpResponse<Path> response=client.send(request, BodyHandlers.ofFile(Paths.get("wanikaniReq0.json")));
   
        System.out.println(response.body());

        
        int loops=0;
        while(loops<3){
            String path="wanikaniReq"+loops+".json";
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            Gson gson = new Gson();
            JsonObject js = gson.fromJson(bufferedReader, JsonObject.class);
            JsonObject retrieved = js.get("pages").getAsJsonObject();
            String next_url = retrieved.get("next_url").getAsString();


            request=HttpRequest.newBuilder()
            .uri(URI.create(next_url))
            .header("Authorization", "Bearer "+apiKey)
            .build();
            response=client.send(request, BodyHandlers.ofFile(Paths.get("wanikaniReq"+(loops+1)+".json")));
            System.out.println(response.body());
            loops++;
        }
    }

    static void getPages(int x) throws FileNotFoundException{
        

        //https://stackoverflow.com/questions/8233542/parse-a-nested-json-using-gson
        //http://jsonviewer.stack.hu/

    }


    public static void main(String[] args) throws Exception {

        //https://community.wanikani.com/t/question-on-retrieving-kanji-through-api/56894 on how to get an actual character individually


        Retriever retriever=new Retriever("https://api.wanikani.com/v2/assignments?subject_types=kanji","d296c425-0887-4059-8f16-a1f62bfe1e19");
        //getPages(0);
        
    }
}
