package manageme.logic.parser.task;

//Maybe can extend from the DeleteCommand in .parser?

import manageme.commons.core.Messages;
import manageme.commons.core.index.Index;
import manageme.logic.commands.task.DeleteTaskCommand;
import manageme.logic.parser.exceptions.ParseException;
import manageme.logic.parser.Parser;
import manageme.logic.parser.ParserUtil;


public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand
     * and returns a DeleteTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTaskCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
