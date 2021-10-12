package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.stream.Stream;

import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskModule;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;

public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_MODULE,
                        PREFIX_START,
                        PREFIX_END);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION)
                || (arePrefixesPresent(argMultimap, PREFIX_START)
                    && !arePrefixesPresent(argMultimap, PREFIX_END))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTaskCommand.MESSAGE_USAGE));
        }
        TaskName name = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get());
        TaskDescription description =
                ParserUtil.parseTaskDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        TaskModule module =
                ParserUtil.parseTaskModule(argMultimap.getValue(PREFIX_MODULE).orElse(""));
        TaskTime start =
                ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START).orElse(""));
        TaskTime end =
                ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END).orElse(""));
        Task task = new Task(name, description, module, start, end);
        return new AddTaskCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
