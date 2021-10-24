package manageme.logic.parser.module;

import static java.util.Objects.requireNonNull;
import static manageme.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageme.logic.parser.CliSyntax.PREFIX_LINK;
import static manageme.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import manageme.logic.commands.module.AddModuleCommand;
import manageme.logic.parser.ArgumentMultimap;
import manageme.logic.parser.ArgumentTokenizer;
import manageme.logic.parser.Parser;
import manageme.logic.parser.ParserUtil;
import manageme.logic.parser.Prefix;
import manageme.logic.parser.exceptions.ParseException;
import manageme.model.module.ModuleName;

public class AddModuleCommandParser implements Parser<AddModuleCommand> {
    @Override
    public AddModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LINK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LINK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }

        ModuleName name = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_NAME).get());

        return new AddModuleCommand(name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
