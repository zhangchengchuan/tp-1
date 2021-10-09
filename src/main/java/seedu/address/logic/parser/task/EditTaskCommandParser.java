//package seedu.address.logic.parser.task;
//
//import static java.util.Objects.requireNonNull;
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.parser.CliSyntax.*;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Optional;
//import java.util.Set;
//
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.task.EditTaskCommand;
//import seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
//import seedu.address.logic.parser.ArgumentMultimap;
//import seedu.address.logic.parser.ArgumentTokenizer;
//import seedu.address.logic.parser.Parser;
//import seedu.address.logic.parser.ParserUtil;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.tag.Tag;
//
///**
// * Parses input arguments and creates a new EditTaskCommand object
// */
//public class EditTaskCommandParser implements Parser<EditTaskCommand> {
//
//    /**
//     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
//     * and returns an EditTaskCommand object for execution.
//     * @throws ParseException if the user input does not conform the expected format
//     */
//    public EditTaskCommand parse(String args) throws ParseException {
//        requireNonNull(args);
//        ArgumentMultimap argMultimap =
//                ArgumentTokenizer.tokenize(args, PREFIX_NAME,
//                        PREFIX_DESCRIPTION, PREFIX_MODULE, PREFIX_START, PREFIX_END);
//
//        Index index;
//
//        try {
//            index = ParserUtil.parseIndex(argMultimap.getPreamble());
//        } catch (ParseException pe) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
//                    EditTaskCommand.MESSAGE_USAGE), pe);
//        }
//
//        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
//        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
//            editTaskDescriptor.setName(TaskParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
//        }
//        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
//            editTaskDescriptor.setDescription(TaskParserUtil.
//                    parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
//        }
//        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
//            editTaskDescriptor.setModule(TaskParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get()));
//        }
//        if (argMultimap.getValue(PREFIX_START).isPresent()) {
//            editTaskDescriptor.setStart(TaskParserUtil.parseStart(argMultimap.getValue(PREFIX_START).get()));
//        }
//        if (argMultimap.getValue(PREFIX_END).isPresent()) {
//            editTaskDescriptor.setEnd(TaskParserUtil.parseEnd(argMultimap.getValue(PREFIX_END).get()));
//        }
////        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTaskDescriptor::setTags);
//
//        if (!editTaskDescriptor.isAnyFieldEdited()) {
//            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
//        }
//
//        return new EditTaskCommand(index, editTaskDescriptor);
//    }
//
//    /**
//     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
//     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
//     * {@code Set<Tag>} containing zero tags.
//     */
//    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
//        assert tags != null;
//
//        if (tags.isEmpty()) {
//            return Optional.empty();
//        }
//        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
//        return Optional.of(ParserUtil.parseTags(tagSet));
//    }
//
//}
