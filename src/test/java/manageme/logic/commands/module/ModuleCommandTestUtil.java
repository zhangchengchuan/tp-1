package manageme.logic.commands.module;

import static manageme.logic.parser.CliSyntax.PREFIX_LINK;
import static manageme.logic.parser.CliSyntax.PREFIX_NAME;

public class ModuleCommandTestUtil {
    public static final String VALID_NAME_MODULE_A = "CS111";
    public static final String VALID_NAME_MODULE_B = "CS222";
    public static final String VALID_LINK_MODULE_A = "https://www.google.com";
    public static final String VALID_LINK_MODULE_B = "https://www.github.com";

    public static final String NAME_DESC_MODULE_A = " " + PREFIX_NAME + VALID_NAME_MODULE_A;
    public static final String NAME_DESC_MODULE_B = " " + PREFIX_NAME + VALID_NAME_MODULE_B;
    public static final String LINK_DESC_MODULE_A = " " + PREFIX_LINK + VALID_LINK_MODULE_A;
    public static final String LINK_DESC_MODULE_B = " " + PREFIX_LINK + VALID_LINK_MODULE_B;
}
