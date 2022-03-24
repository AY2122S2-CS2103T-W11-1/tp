package seedu.address.model.person;

import java.util.List;
import java.util.function.Function;

import seedu.address.commons.util.StringUtil;



public abstract class TagContainsKeywordsPredicateOr extends ContainsKeywordsPredicate<List<String>> {
    private final List<String> keywords;
    private final Function<Person, List<String>> field;

    TagContainsKeywordsPredicateOr(List<String> keywords, Function<Person, List<String>> field) {
        super(keywords, field);
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> field.apply(person).stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicateOr // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicateOr) other).keywords)); // state check
    }

}