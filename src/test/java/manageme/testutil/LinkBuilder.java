package manageme.testutil;

import manageme.model.link.Link;

public class LinkBuilder {
    public static final String DEFAULT_NAME = "Tutorial Link";
    public static final String DEFAULT_LINK = "www.luminus.com";

    private String name;
    private String link;

    /**
     * Creates a {@code LinkBuilder} with the default details.
     */
    public LinkBuilder() {
        name = DEFAULT_NAME;
        link = DEFAULT_LINK;
    }

    /**
     * Initializes the LinkBuilder with the data of {@code LinkToCopy}.
     */
    public LinkBuilder(Link linkToCopy) {
        name = linkToCopy.getName();
        link = linkToCopy.getLink();
    }

    /**
     * Sets the {@code Name} of the {@code Link} that we are building.
     */
    public LinkBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code Link} that we are building.
     */
    public LinkBuilder withLink(String link) {
        this.link = link;
        return this;
    }

    public Link build() {
        return new Link(name, link);
    }
}
