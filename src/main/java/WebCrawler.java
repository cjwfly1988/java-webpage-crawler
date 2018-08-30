import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class WebCrawler {
    private Document webPage;
    private URI uri;
    private String urlAddress;

    WebCrawler(String givenUrlAddress) {
        this.urlAddress = givenUrlAddress;
    }

    List<String> crawLinksFromHyperLinkTags() {
        try {
            webPage = Jsoup.connect(urlAddress).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return webPage
                .select("a[href]")
                .stream()
                .map(element -> element.attr("href"))
                .collect(Collectors.toList());
    }

    List<String> extractDomainFromLinks(final List<String> links) {
        if (links.isEmpty()) {
            throw new RuntimeException("The list of links is empty!");
        }

        List<String> listOfDomains = new ArrayList<>();

        for (String link : links) {
            try {
                uri = new URI(link);
            } catch (URISyntaxException ignored) {
            }
            listOfDomains.add(uri.getHost());
        }

        return listOfDomains
                .parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
