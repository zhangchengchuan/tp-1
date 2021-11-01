package manageme.model.module;

import java.util.List;
import java.util.function.Predicate;

import manageme.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code Name} matches any of the keywords given.
 */
public class ModNameContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public ModNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getModuleName().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
