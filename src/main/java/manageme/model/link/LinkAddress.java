package manageme.model.link;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import manageme.commons.util.AppUtil;
import manageme.model.link.exceptions.DesktopNotSupportException;
import manageme.model.link.exceptions.FileNotOpenException;
import manageme.model.link.exceptions.LinkNotOpenException;

public class LinkAddress {

    public static final String MESSAGE_CONSTRAINTS =
            "The link should be a valid url, beginning with https://, ftp:// or file:/";

    /*
     * The address should be in a valid url format.
     */
    public static final String VALIDATION_REGEX = "^(https://|http://|ftp://|file:/)"
            + "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public final URI linkAddress;
    public final String value;

    public static void openUrl(URL url) throws LinkNotOpenException {

        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();

        try{

            if (os.contains("win")) {

                // this doesn't support showing urls in the form of "page.html"
                rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);

            } else if (os.contains( "mac" )) {

                rt.exec( "open " + url);

            } else if (os.contains( "nix") || os.contains( "nux")) {

                // Do a best guess on unix until we get a platform independent way
                // Build a list of browsers to try, in this order.
                String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                        "netscape","opera","links","lynx"};

                // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                StringBuffer cmd = new StringBuffer();
                for (int i=0; i < browsers.length; i++)
                    cmd.append( ( i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");

                rt.exec(new String[] { "sh", "-c", cmd.toString() });

            }

        } catch (Exception e) {
            throw new LinkNotOpenException();
        }

    }

    public static void openFile(URI uri) throws DesktopNotSupportException, FileNotOpenException {
        File toOpen = new File(uri.toString().substring(6));
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains( "nix") || os.contains( "nux")) {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + toOpen);
            } catch (IOException e) {
                throw new FileNotOpenException();
            }
        } else if(! Desktop.isDesktopSupported()) {
            System.out.println("java desktop not supported");
            throw new DesktopNotSupportException();
        } else {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(toOpen);
            } catch (IOException e){
                throw new FileNotOpenException();
            }
        }
    }

    /**
     * Constructs a {@code LinkAddress}.
     *
     * @param linkAddress A valid url for the link.
     */
    public LinkAddress(String linkAddress) {
        requireNonNull(linkAddress);
        AppUtil.checkArgument(isValidLinkAddress(linkAddress), MESSAGE_CONSTRAINTS);
        try {
            this.value = linkAddress;
            this.linkAddress = new URI(linkAddress);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    public void open() throws RuntimeException{
        if (linkAddress.getScheme().equals("file")) {
            try {
                openFile(linkAddress);
            } catch (RuntimeException e) {
                throw e;
            }
        } else {
            try {
                openUrl(linkAddress.toURL());
            } catch (RuntimeException | MalformedURLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /**
     * Returns true if a given string is a valid link address.
     */
    public static boolean isValidLinkAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return linkAddress.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkAddress // instanceof handles nulls
                && linkAddress.equals(((LinkAddress) other).linkAddress)); // state check
    }

    @Override
    public int hashCode() {
        return linkAddress.hashCode();
    }
}
