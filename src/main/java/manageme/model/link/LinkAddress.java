package manageme.model.link;

import static java.util.Objects.requireNonNull;

import java.net.MalformedURLException;
import java.net.URL;

import manageme.commons.util.AppUtil;

public class LinkAddress {

    public static final String MESSAGE_CONSTRAINTS =
            "The link should be a valid url, beginning with https:// or ftp://";

    /*
     * The address should be in a valid url format.
     */
    public static final String VALIDATION_REGEX = "^(https?|ftp|file)://" +
            "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public final URL linkAddress;

    /**
     * Constructs a {@code LinkAddress}.
     *
     * @param linkAddress A valid url for the link.
     */
    public LinkAddress(String linkAddress) {
        requireNonNull(linkAddress);
        AppUtil.checkArgument(isValidLinkName(linkAddress), MESSAGE_CONSTRAINTS);
        try {
            this.linkAddress = new URL(linkAddress);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidLinkName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return linkAddress.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkAddress // instanceof handles nulls
                && linkAddress.equals(((LinkAddress) other).linkAddress)); // state check
    }

    @Override
    public int hashCode() {
        return linkAddress.hashCode();
    }
}
