package manageme.testutil;

import manageme.model.link.Link;
import manageme.model.link.LinkAddress;
import manageme.model.link.LinkModule;
import manageme.model.link.LinkName;

public class LinkBuilder {
    public static final String DEFAULT_NAME = "Tutorial Link";
    public static final String DEFAULT_LINK = "www.luminus.com";

    private LinkName name;
    private LinkAddress address;
    private LinkModule module;

    /**
     * Creates a {@code LinkBuilder} with the default details.
     */
    public LinkBuilder() {
        name = new LinkName(DEFAULT_NAME);
        address = new LinkAddress(DEFAULT_LINK);
        module = new LinkModule();
    }

    /**
     * Initializes the LinkBuilder with the data of {@code LinkToCopy}.
     */
    public LinkBuilder(Link linkToCopy) {
        name = linkToCopy.getName();
        address = linkToCopy.getAddress();
        module = linkToCopy.getLinkModule();
    }

    /**
     * Sets the {@code LinkName} of the {@code Link} that we are building.
     */
    public LinkBuilder withName(String name) {
        this.name = new LinkName(name);
        return this;
    }

    /**
     * Sets the {@code LinkAddress} of the {@code Link} that we are building.
     */
    public LinkBuilder withAddress(String address) {
        this.address = new LinkAddress(address);
        return this;
    }

    /**
     * Sets the {@code LinkModule} of the {@code Task} that we are building.
     */
    public LinkBuilder withModule(String linkModule) {
        this.module = linkModule.equals("") ? LinkModule.empty() : new LinkModule(linkModule);
        return this;
    }

    public Link build() {
        return new Link(name, address, module);
    }
}
