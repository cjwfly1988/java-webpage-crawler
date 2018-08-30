import java.util.List;
import java.util.Map;

public class Main {
    private static final String TARGET_URL = System.getenv("TEST_URL");
    private static final String MESSAGE = "Domain = %s appears %s times";

    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler(TARGET_URL);
        DomainOccurrenceHelper domainOccurrenceHelper = new DomainOccurrenceHelper();

        List<String> links = webCrawler.crawLinksFromHyperLinkTags();
        Map<String, Long> domainOccurrenceMap = domainOccurrenceHelper.countDomainOccurrence(webCrawler.extractDomainFromLinks(links));

        //this is only for demo purpose, otherwise println should be removed
        domainOccurrenceMap.forEach((domain, occurrence) -> System.out.println(String.format(MESSAGE, domain, occurrence)));
    }
}
