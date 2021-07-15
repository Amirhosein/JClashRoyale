// BWOTSHEWCHB

package JClashRoyale.Controller;

import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.*;

import JClashRoyale.Model.Player ;

import JClashRoyale.Model.Cards.Card ;
import JClashRoyale.Model.Cards.GiantCard ;
import JClashRoyale.Model.Cards.ArcherCard ;
import JClashRoyale.Model.Cards.WizardCard ;
import JClashRoyale.Model.Cards.CannonCard ;
import JClashRoyale.Model.Cards.ValkyrieCard ;
import JClashRoyale.Model.Cards.MiniPekkaCard ;
import JClashRoyale.Model.Cards.BabyDragonCard ;
import JClashRoyale.Model.Cards.BarbariansCard ;
import JClashRoyale.Model.Cards.InfernoTowerCard ;

import JClashRoyale.Model.Elements.Sprite ;
import JClashRoyale.Model.Elements.Enums.ColorType ;
import JClashRoyale.Model.Elements.Sprites.Troops.Giant ;
import JClashRoyale.Model.Elements.Sprites.Troops.Archer ;
import JClashRoyale.Model.Elements.Sprites.Troops.Wizard ;
import JClashRoyale.Model.Elements.Sprites.Troops.Valkyrie ;
import JClashRoyale.Model.Elements.Sprites.Troops.MiniPekka ;
import JClashRoyale.Model.Elements.Sprites.Troops.Barbarian ;
import JClashRoyale.Model.Elements.Sprites.Troops.BabyDragon ;
import JClashRoyale.Model.Elements.Sprites.Buildings.Cannon ;
import JClashRoyale.Model.Elements.Sprites.Buildings.InfernoTower ;

import JClashRoyale.Model.Logic.GameStarter ;
import JClashRoyale.Model.Logic.GameManager ;

public class BattleController {
    // Fields
    double x, y;
    private final ArrayList<Card> deck = new ArrayList<>(Player.player.getDeck());
    private final ArrayList<ImagePackage> cards = new ArrayList<>();
    private final ArrayList<Card> outOfHandCards = new ArrayList<>();
    private ImagePackage selectedCard;
    private ImagePackage nextCard;

    GameStarter gameStarter ;
	GameManager gameManager ;

    @FXML private Pane titlePane;
    @FXML private TextField timer;
    @FXML private TextField result;
    @FXML private TextField elixer;
    @FXML private Label messageLabel;
    @FXML private Pane deckViewPane;
    @FXML private Pane gameViewPane;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private ImageView one, two, three, four, next;

    // Methods
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
        Collections.shuffle(deck);
        initCards();
        cards.forEach(card -> card.getImageView().setOnMouseClicked(mouseEvent -> deckCardOnMousePressedAction(card.getImageView())));
    }

    public void start() {
        gameStarter = new GameStarter() ;
		gameManager = gameStarter.getGameManager() ;
        gameStarter.initBattle(gameViewPane) ;
    }

    private void initCards() {
        cards.addAll(Arrays.asList(new ImagePackage(one, deck.get(0)),
                new ImagePackage(two, deck.get(1)),
                new ImagePackage(three, deck.get(2)),
                new ImagePackage(four, deck.get(3))));
        nextCard = new ImagePackage(next, deck.get(4));
        for (int i = 5; i < 8; i++) outOfHandCards.add(deck.get(i));
    }

    private void deckCardOnMousePressedAction(ImageView imageView) {
        if (selectedCard != null)
            if (selectedCard.getImageView() == imageView) {
                selectedCard.getImageView().setOpacity(1);
                selectedCard = null;
                return;
            } else if (selectedCard != null) {
                selectedCard.getImageView().setOpacity(1);
                selectedCard = null;
            }
        selectedCard = findImagePackageByImageView(cards, imageView);
        assert selectedCard != null;
        selectedCard.getImageView().setOpacity(0.5);
    }

    private ImagePackage findImagePackageByImageView(ArrayList<ImagePackage> imagePackages, ImageView imageView) {
        for (ImagePackage imagePackage : imagePackages) {
            if (imagePackage.getImageView() == imageView) return imagePackage;
        }
        return null;
    }

    public void deployCard() {
		// TODO :  Alter Location to Mouse Location
		if ( selectedCard.getCard() instanceof ArcherCard ) {
			Archer archer = new Archer(ColorType.BLUE) ;
			archer.setLocation(157.5 , 430) ;
			gameManager.addSprite(archer) ;
		} else if ( selectedCard.getCard() instanceof BabyDragonCard ) {
			BabyDragon babyDragon = new BabyDragon(ColorType.BLUE) ;
			babyDragon.setLocation(157.5 , 430) ;
			gameManager.addSprite(babyDragon) ;
		} else if ( selectedCard.getCard() instanceof BarbariansCard ) {
			Barbarian barbarian = new Barbarian(ColorType.BLUE) ;
			barbarian.setLocation(157.5 , 430) ;
			gameManager.addSprite(barbarian) ;
		} else if ( selectedCard.getCard() instanceof GiantCard ) {
			Giant giant = new Giant(ColorType.BLUE) ;
			giant.setLocation(157.5 , 430) ;
			gameManager.addSprite(giant) ;
		} else if ( selectedCard.getCard() instanceof MiniPekkaCard ) {
			MiniPekka miniPekka = new MiniPekka(ColorType.BLUE) ;
			miniPekka.setLocation(157.5 , 430) ;
			gameManager.addSprite(miniPekka) ;
		} else if ( selectedCard.getCard() instanceof ValkyrieCard ) {
			Valkyrie valkyrie = new Valkyrie(ColorType.BLUE) ;
			valkyrie.setLocation(157.5 , 430) ;
			gameManager.addSprite(valkyrie) ;
		} else if ( selectedCard.getCard() instanceof WizardCard ) {
			Wizard wizard = new Wizard(ColorType.BLUE) ;
			wizard.setLocation(157.5 , 430) ;
			gameManager.addSprite(wizard) ;
		} else if ( selectedCard.getCard() instanceof CannonCard ) {
			Cannon cannon = new Cannon(ColorType.BLUE) ;
			cannon.setLocation(157.5 , 430) ;
			gameManager.addSprite(cannon) ;
		} else if ( selectedCard.getCard() instanceof InfernoTowerCard ) {
			InfernoTower infernoTower = new InfernoTower(ColorType.BLUE) ;
			infernoTower.setLocation(157.7 , 430) ;
			gameManager.addSprite(infernoTower) ;
		} else {
			// Pass
		}

        Card temp = selectedCard.getCard();
        selectedCard.getImageView().setOpacity(1);
        selectedCard.setCard(nextCard.getCard());
        nextCard.setCard(outOfHandCards.get(0));
        outOfHandCards.remove(0);
        outOfHandCards.add(temp);
        selectedCard = null;
    }


    static class ImagePackage {
        private final ImageView imageView;
        private Card card;

        public ImagePackage(ImageView imageView, Card card) {
            this.imageView = imageView;
            this.card = card;
            if (card != null) {
                this.imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(this.card.getImageAddress()
                ))));
            }
        }

        public ImageView getImageView() {
            return imageView;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
            this.imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(this.card.getImageAddress()
            ))));
        }
    }
}

