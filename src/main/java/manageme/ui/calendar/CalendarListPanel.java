package manageme.ui.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import manageme.commons.core.LogsCenter;
import manageme.logic.commands.calendar.NextMonthCommand;
import manageme.logic.commands.calendar.PreviousMonthCommand;
import manageme.logic.commands.calendar.ReadDayCommand;
import manageme.model.task.Task;
import manageme.ui.UiPart;
import manageme.ui.task.TaskListPanel;

/**
 * Panel containing the list of persons.
 */
public class CalendarListPanel extends UiPart<Region> {
    private static final String FXML = "CalendarListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarListPanel.class);

    private LocalDate currentDate;
    private LocalDate referenceDate;
    private ObservableList<Task> taskList;

    @FXML
    private Label date;

    @FXML
    private GridPane calendarPlaceholder;

    @FXML
    private StackPane readDayPanelPlaceholder;

    /**
     * Creates a {@code CalendarListPanel} with the given {@code ObservableList}.
     */
    public CalendarListPanel(ObservableList<Task> taskList) {
        super(FXML);
        this.taskList = taskList;
        currentDate = LocalDate.now();
        referenceDate = LocalDate.now();
        fillCalendar();
        showDay(currentDate);
        taskList.addListener((ListChangeListener<? super Task>) change -> {
            clearCalendar();
            fillCalendar();
        });
    }

    /**
     * Clears the calendar.
     */
    private void clearCalendar() {
        calendarPlaceholder.getChildren().clear();
    }

    /**
     * Fills up the current month of the calendar.
     */
    private void fillCalendar() {
        date.setText(currentDate.toString());
        fillLayout();
        fillDay();
    }

    /**
     * Fills up the layout of a calendar.
     */
    private void fillLayout() {
        calendarPlaceholder.add(new Label(String.format("%s %s",
                referenceDate.getMonth().toString(), referenceDate.getYear())), 0, 0, 7, 1);
        calendarPlaceholder.add(new Label("Su"), 0, 1, 1, 1);
        calendarPlaceholder.add(new Label("Mo"), 1, 1, 1, 1);
        calendarPlaceholder.add(new Label("Tu"), 2, 1, 1, 1);
        calendarPlaceholder.add(new Label("We"), 3, 1, 1, 1);
        calendarPlaceholder.add(new Label("Th"), 4, 1, 1, 1);
        calendarPlaceholder.add(new Label("Fr"), 5, 1, 1, 1);
        calendarPlaceholder.add(new Label("Sa"), 6, 1, 1, 1);
    }

    /**
     * Fills up the days in a month.
     */
    private void fillDay() {
        int numOfDayInMonth = referenceDate.lengthOfMonth();
        int currentDay = 1;
        int row = 2;
        int column = startColumn();

        while (currentDay <= numOfDayInMonth) {
            LocalDate date = LocalDate.of(referenceDate.getYear(), referenceDate.getMonth(), currentDay);

            calendarPlaceholder.add(new DayCard(getTaskInCurrentDay(taskList, date), date).getRoot(),
                    column, row, 1, 1);

            column++;
            if (column > 6) {
                column %= 7;
                row++;
            }
            currentDay++;
        }
    }

    /**
     * Returns the list of tasks happening on specified date from an original list of tasks.
     *
     * @param taskList the original list of tasks.
     * @param currentDay the specified date.
     * @return the filtered task list
     */
    private ObservableList<Task> getTaskInCurrentDay(ObservableList<Task> taskList, LocalDate currentDay) {
        return taskList.filtered((task -> task.getSpan().anyMatch(dates -> dates.equals(currentDay))));
    }

    /**
     * Determines what is the column index of the first day of the month.
     *
     * @return the column index
     */
    private int startColumn() {
        int column;
        LocalDate firstDayOfMonth = referenceDate.withDayOfMonth(1);
        DayOfWeek dayOfWeek = firstDayOfMonth.getDayOfWeek();
        switch (dayOfWeek) {
        case SUNDAY:
            column = 0;
            break;
        case MONDAY:
            column = 1;
            break;
        case TUESDAY:
            column = 2;
            break;
        case WEDNESDAY:
            column = 3;
            break;
        case THURSDAY:
            column = 4;
            break;
        case FRIDAY:
            column = 5;
            break;
        case SATURDAY:
            column = 6;
            break;
        default:
            column = -1;
            break;
        }
        return column;
    }

    /**
     * Parses system input to perform calendar-specific execution.
     *
     * @param feedbackToSystem system input
     */
    public void parseCommand(String feedbackToSystem) {
        String[] feedback = feedbackToSystem.split(" ");
        switch (feedback[0]) {
        case NextMonthCommand.COMMAND_WORD:
            showNextMonth();
            break;

        case PreviousMonthCommand.COMMAND_WORD:
            showPreviousMonth();
            break;

        case ReadDayCommand.COMMAND_WORD:
            LocalDate date = LocalDate.parse(feedback[1]);
            showDay(date);
            break;

        default:
            return;
        }
    }

    /**
     * Displays the next month of the calendar.
     */
    private void showNextMonth() {
        referenceDate = referenceDate.plusMonths(1);
        clearCalendar();
        fillCalendar();
    }

    /**
     * Displays the previous month of the calendar.
     */
    private void showPreviousMonth() {
        referenceDate = referenceDate.minusMonths(1);
        clearCalendar();
        fillCalendar();
    }

    /**
     * Displays the list of tasks on the specified date.
     *
     * @param date the specified date.
     */
    private void showDay(LocalDate date) {
        TaskListPanel taskListPanel = new TaskListPanel(getTaskInCurrentDay(taskList, date));
        readDayPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }
}
