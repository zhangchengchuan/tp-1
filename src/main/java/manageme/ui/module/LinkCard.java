package manageme.ui.module;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import manageme.model.link.Link;
import manageme.ui.UiPart;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class LinkCard extends UiPart<Region> {

    private static final String FXML = "LinkListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Link link;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label url;
    @FXML
    private Button copyButton;

    /**
     * Creates a {@code linkCode} with the given {@code link} and index to display.
     */
    public LinkCard(Link link, int displayedIndex) {
        super(FXML);
        this.link = link;
        id.setText(displayedIndex + ". ");
        //name.setText(link.getName() + ": ");
        url.setText(link.getLink());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkCard)) {
            return false;
        }

        // state check
        LinkCard card = (LinkCard) other;
        return id.getText().equals(card.id.getText())
                && link.equals(card.link);
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(link.getLink());
        clipboard.setContent(url);
    }
}
