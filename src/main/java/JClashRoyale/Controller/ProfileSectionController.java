package JClashRoyale.Controller;

import JClashRoyale.Model.App;
import JClashRoyale.Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Amir Iravanimanesh & Manni Moghimi
 * @since 7/11/2021
 */
public class ProfileSectionController {
    @FXML
    private Pane titlePane;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private Label cupLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressBarLabel;
    @FXML
    private Label levelLabel;

    private double x, y;

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        cupLabel.setText(Integer.toString(Player.player.getCup()));
        progressBarUpdate(Player.player.getXP());
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    private void progressBarUpdate(int XP) {
        if (XP <= 300) {
            progressBar.setProgress((double) XP / 300);
            progressBarLabel.setText(Integer.toString(XP) + '/' + "300");
            levelLabel.setText("1");
        } else if (XP <= 800) {
            progressBar.setProgress((double) XP / 800);
            progressBarLabel.setText(Integer.toString(XP) + '/' + "800");
            levelLabel.setText("2");
        } else if (XP <= 1700) {
            progressBar.setProgress((double) XP / 1700);
            progressBarLabel.setText(Integer.toString(XP) + '/' + "1700");
            levelLabel.setText("3");
        } else if (XP <= 3400) {
            progressBar.setProgress((double) XP / 3400);
            progressBarLabel.setText(Integer.toString(XP) + '/' + "3400");
            levelLabel.setText("4");
        } else if (XP <= 5900) {
            progressBar.setProgress((double) XP / 5900);
            progressBarLabel.setText(Integer.toString(XP) + '/' + "5900");
            levelLabel.setText("5");
        }
    }
}
