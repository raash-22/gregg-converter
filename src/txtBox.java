import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
//txtbox
public class txtBox extends Application {
    private BorderPane pane;
    private Canvas canvas;
    private TextField txtField;
    //for user to enter text and bringing output on canvas

    @Override
    public void init() throws Exception {
        /*ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        engine.eval(Files.newBufferedReader(Paths.get("src/resourrces/script.js"), StandardCharsets.UTF_8));
        Invocable inv = (Invocable) engine;
        inv.invokeFunction("speak");
        inv.invokeFunction("textt");*/
        letterImgMapping.loadImages();
        txtField = new TextField();
        pane = new BorderPane();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        canvas = new Canvas(screenBounds.getWidth() / 2,screenBounds.getHeight() / 2);
        GraphicsContext gc =  canvas.getGraphicsContext2D();
        pane.setCenter(canvas);
        pane.setBottom(txtField);
        txtField.setOnKeyReleased(event -> drawStrokes(txtField.getText(), gc));
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene translation = new Scene(pane);
        primaryStage.setScene(translation);
        primaryStage.setTitle("'TEST'");
        primaryStage.show();
        primaryStage.widthProperty().addListener(((observable, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue() - 100)));
        primaryStage.heightProperty().addListener(((observable, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue() - 100)));
    }
    private void drawStrokes(String s, GraphicsContext gc){

        Canvas canvas = gc.getCanvas();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        Character[][] phones = letterImgMapping.phones(s);
        int x = 90;
        int y = 90;
        int line = 1;
        shrtHand current;
        for (Character[] word : phones) {
            if((canvas.getWidth() - 90)  - x < letterDisplay.wordLength(word)){
                line++;
                x = 80;
                y = line * 80;
            }
            y -= letterDisplay.wordHeight(word);
            for (Character c : word) {
                if (letterImgMapping.isVowel(c)){
                    continue;
                }
                current = letterImgMapping.strokeMap.get(c);
                Point start = current.getStart();
                Point end = current.getEnd();

                gc.drawImage(current.getImage(), x - start.x, y - start.y);
                x += end.x - start.x;
                y += end.y - start.y;
            }
            x += 80;
            y = line * 100;
        }

    }

}
