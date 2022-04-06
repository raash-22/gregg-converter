import javafx.application.Application;

import java.io.IOException;

public class txtToGregg {
    //txtTogregg
    public static void main(String[] args) throws IOException {
        letterImgMapping.load();
        Application.launch(txtBox.class, args);
    }

}