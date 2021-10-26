package manageme.model.link;

import java.util.List;
import java.util.function.Predicate;

import manageme.commons.util.StringUtil;

public class LinkNameContainsKeywordsPredicate implements Predicate<Link> {
    private final List<String> keywords;

    public LinkNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Link link) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(link.getName().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LinkNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
