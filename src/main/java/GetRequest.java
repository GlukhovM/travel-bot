import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GetRequest {
    private static String requestURL = "https://api.skypicker.com/flights?fly_from=CEK&fly_to=LED&date_from=01/06/2020&date_to=01/07/2020&partner=picky&curr=RUB";

    public static void main(String[] args) {
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = client.execute(new HttpGet(requestURL))
        ) {
            System.out.println("Status line: " + response.getStatusLine());
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String data = IOUtils.toString(entity.getContent());
                System.out.println("Data :" + data);
            }
        } catch (Throwable cause) {
          cause.printStackTrace();
        }
    }
}
