package manageme.testutil;


import manageme.model.link.Link;

public class TypicalLinks {
    public static final Link LINK_LUMINUS = new LinkBuilder().withAddress("www.luminus.com")
            .withName("Tutorial").build();
    public static final Link LINK_YOUTUBE = new LinkBuilder().withAddress("www.youtube.com")
            .withName("Lecture").build();
}
