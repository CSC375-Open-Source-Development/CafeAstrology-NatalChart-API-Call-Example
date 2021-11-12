import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        RequestFormParams params = new RequestFormParams();
        params.addParam("name", "Alex");
        params.addParam("month", "2");
        params.addParam("day", "11");
        params.addParam("year", "1994");
        params.addParam("hour", "06");
        params.addParam("minute", "00");
        params.addParam("zp-report-variation", "birthreport");
        params.addParam("place", "New Hyde Park, New York, United States");
        params.addParam("geo_timezone_id", "America/New_York");
        params.addParam("zp_lat_decimal", "40.7351");
        params.addParam("zp_long_decimal", "-73.68791");
        params.addParam("zp_offset_geo", "-5");
        params.addParam("action", "zp_birthreport");

        RequestHeaders headers = new RequestHeaders();
        headers.addHeader("accept", "*/*");
        headers.addHeader("accept-language", "en-US,en;q=0.9");
        headers.addHeader("content-type", "application/x-www-form-urlencoded");
        headers.addHeader("origin", "https://cafeastrology.com");
        headers.addHeader("referer", "https://cafeastrology.com/free-natal-chart-report.html");
        headers.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");

        Response response = HttpRequest.post("https://cafeastrology.com/wp-admin/admin-ajax.php", params, headers);

        String report = response.getBody().getAsJsonObject().get("report").toString();
        System.out.println(report);

        Document document = Jsoup.parse(report);
        System.out.println(document.toString());

        String imageBase64Encoded = response.getBody().getAsJsonObject().get("image").toString();
        System.out.println(imageBase64Encoded);
    }
}
