import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class DomainOccurrenceHelper {

    Map<String, Long> countDomainOccurrence(final List<String> domainList) {
        return domainList
                .stream()
                .collect(Collectors.groupingBy(domain -> domain, Collectors.counting()));
    }
}
