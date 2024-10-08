import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {
    public static void main(String[] args) {
        try {
            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Build the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/00449da441f3ea5852cf5d51/latest/USD")) // Replace with your API URL
                    .build();

            // Send the request and get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonResponse = new JSONObject(response.body());
            JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Input the amount to convert: ");
            double amount =  scanner.nextDouble(); // Amount to Convert

            System.out.print("Input the currency to convert from: ");
            String FromCurr = scanner.next().toUpperCase();//Currency to Convert from

            scanner.nextLine();

            System.out.print("Input the currency to convert to: ");
            String ToCurr = scanner.next().toUpperCase();//Currency to Convert to

            double fromRate = conversionRates.getDouble(FromCurr);
            double toRate = conversionRates.getDouble(ToCurr);

            double ConAm = toRate * (amount/fromRate);
            System.out.println(ConAm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
