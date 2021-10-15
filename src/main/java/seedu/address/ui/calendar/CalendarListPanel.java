package seedu.address.ui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;

/**
 * Panel containing the list of persons.
 */
public class CalendarListPanel extends UiPart<Region> {
    private static final String FXML = "CalendarListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarListPanel.class);

    private LocalDate currentDate;
    private ObservableList<Task> taskList;

    @FXML
    private Label date;

    @FXML
    private GridPane calendarPlaceholder;

    /**
     * Creates a {@code CalendarListPanel} with the given {@code ObservableList}.
     */
    public CalendarListPanel(ObservableList<Task> taskList) {
        super(FXML);
        this.taskList = taskList;
        currentDate = LocalDate.now();
        fillCalendar();
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
        ObservableList<Task> filteredTaskList = getTaskInCurrentMonth(taskList);
        fillDay(filteredTaskList);
    }

    private ObservableList<Task> getTaskInCurrentMonth(ObservableList<Task> taskList) {
        return taskList.filtered(task -> task.getSpan()
                .anyMatch(date -> date.getMonth().equals(currentDate.getMonth())));
    }

    /**
     * Fills up the layout of a calendar.
     */
    private void fillLayout() {
        calendarPlaceholder.add(new Label(String.format("%s",
                currentDate.getMonth().toString())), 0, 0, 7, 1);
        calendarPlaceholder.add(new Label("Su"), 0, 1, 1, 1);
        calendarPlaceholder.add(new Label("Mo"), 1, 1, 1, 1);
        calendarPlaceholder.add(new Label("Tu"), 2, 1, 1, 1);
        calendarPlaceholder.add(new Label("We"), 3, 1, 1, 1);
        calendarPlaceholder.add(new Label("Th"), 4, 1, 1, 1);
        calendarPlaceholder.add(new Label("Fr"), 5, 1, 1, 1);
        calendarPlaceholder.add(new Label("Sa"), 6, 1, 1, 1);
    }

    /**
     * Determines what is the column index of the first day of the month.
     *
     * @return the column index.
     */
    private int startColumn() {
        int column;
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
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
     * Fills up the days in a month.
     *
     * @param filteredTaskList The list of tasks that will happen in the current month.
     */
    private void fillDay(ObservableList<Task> filteredTaskList) {
        int numOfDayInMonth = currentDate.getMonth().maxLength();
        int currentDay = 1;
        int row = 2;
        int column = startColumn();
        while (currentDay <= numOfDayInMonth) {
            calendarPlaceholder.add(new DayCard(filteredTaskList,
                    LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDay)).getRoot(),
                    column, row, 1, 1);
            column++;
            if (column > 6) {
                column %= 7;
                row++;
            }
            currentDay++;
        }
    }
}
