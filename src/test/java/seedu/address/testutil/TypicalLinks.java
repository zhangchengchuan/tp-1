package seedu.address.testutil;


import seedu.address.model.link.Link;

public class TypicalLinks {
    public static final Link LINK_LUMINUS = new LinkBuilder().withLink("www.luminus.com")
            .withName("Tutorial").build();
    public static final Link LINK_YOUTUBE = new LinkBuilder().withLink("www.youtube.com")
            .withName("Lecture").build();
}
