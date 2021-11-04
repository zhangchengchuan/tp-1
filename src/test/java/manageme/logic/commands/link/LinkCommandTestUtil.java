package manageme.logic.commands.link;

import static manageme.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static manageme.logic.parser.CliSyntax.PREFIX_MODULE;
import static manageme.logic.parser.CliSyntax.PREFIX_NAME;

import manageme.testutil.EditLinkDescriptorBuilder;

public class LinkCommandTestUtil {
    public static final String VALID_LINKNAME_A = "CS Tutorial";
    public static final String VALID_LINKNAME_B = "CS Lecture";
    public static final String VALID_LINKADDRESS_A = "https://www.luminus.com";
    public static final String VALID_LINKADDRESS_B = "https://www.zoom.com";
    public static final String VALID_LINKMODULE_A = "CS2100";
    public static final String VALID_LINKMODULE_B = "CS2103";

    public static final String LINKNAME_DESC_A = " " + PREFIX_NAME + VALID_LINKNAME_A;
    public static final String LINKNAME_DESC_B = " " + PREFIX_NAME + VALID_LINKNAME_B;
    public static final String LINKADDRESS_DESC_A = " " + PREFIX_ADDRESS + VALID_LINKADDRESS_A;
    public static final String LINKADDRESS_DESC_B = " " + PREFIX_ADDRESS + VALID_LINKADDRESS_B;
    public static final String LINKMODULE_DESC_A = " " + PREFIX_MODULE + VALID_LINKMODULE_A;
    public static final String LINKMODULE_DESC_B = " " + PREFIX_MODULE + VALID_LINKMODULE_B;

    public static final String INVALID_LINKNAME_DESC = " " + PREFIX_NAME + "Tutorial$";
    public static final String INVALID_LINKADDRESS_DESC = " " + PREFIX_ADDRESS + "invalidAddress";
    public static final String INVALID_LINKMODULE_DESC = " " + PREFIX_MODULE + "CS2100#";


    public static final EditLinkCommand.EditLinkDescriptor DESC_LINK_A;
    public static final EditLinkCommand.EditLinkDescriptor DESC_LINK_B;

    static {
        DESC_LINK_A = new EditLinkDescriptorBuilder().withName(VALID_LINKNAME_A).withAddress(VALID_LINKADDRESS_A)
                .withModule(VALID_LINKMODULE_A).build();
        DESC_LINK_B = new EditLinkDescriptorBuilder().withName(VALID_LINKNAME_B).withAddress(VALID_LINKADDRESS_B)
                .withModule(VALID_LINKMODULE_B).build();
    }
}
