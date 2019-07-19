package bookstore.sideout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class NewHttpConnection {

    public static void main(String arg[]) {

        String csv = returnAsyncFileCSV();

        Stream.of(csv.split("\n"));

    }

    public static String getBooks() {
        String retorno = "";
        try {
            URI uri = new URI("https://turini.github.io/livro-java-9/books.csv");
            HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            retorno = response.body();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public static String returnAsyncCSV() {
        String retorno = "";
        try {
            URI uri = new URI("https://turini.github.io/livro-java-9/books.csv");
            HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            CompletableFuture<HttpResponse<String>> responseCompletableFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            retorno = responseCompletableFuture.get().body();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public static String returnAsyncFileCSV() {
        String retorno = "";
        try {
            URI uri = new URI("https://turini.github.io/livro-java-9/books.csv");
            HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

            //Path tempFile = Files.createTempFile("books",".csv");
            Path tempFile = Paths.get("books.csv");
            CompletableFuture<HttpResponse<Path>> csv2 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofFile(tempFile));

            csv2.whenComplete((resp,t) -> {
                if (t != null) {
                    t.printStackTrace();
                } else {
                    System.out.println(resp.body());
                    System.out.println(resp.statusCode());
                }
            }).join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @Deprecated
    private static void oldHttp() {
        try {
            URL url = new URL("https://www.brasil247.com/");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception exc) {

        }
    }
}
