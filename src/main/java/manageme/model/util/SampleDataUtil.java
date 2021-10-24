package manageme.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import manageme.model.ManageMe;
import manageme.model.ReadOnlyManageMe;
import manageme.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ManageMe} with sample data.
 */
public class SampleDataUtil {

    public static ReadOnlyManageMe getSampleManageMe() {
        ManageMe sampleMm = new ManageMe();
        return sampleMm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
