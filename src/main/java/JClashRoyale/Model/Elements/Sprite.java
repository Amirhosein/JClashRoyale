// BWOTSHEWCHB

package JClashRoyale.Model.Elements ;

import JClashRoyale.Model.Cards.InfernoTowerCard;
import javafx.geometry.Point2D ;
import javafx.scene.image.Image ;
import javafx.scene.paint.Color ;
import javafx.scene.shape.Circle ;
import javafx.scene.canvas.GraphicsContext ;

import java.util.ArrayList ;

import JClashRoyale.Model.Elements.Enums.ColorType ;
import JClashRoyale.Model.Elements.Enums.TroopType ;

import JClashRoyale.Model.Elements.Sprites.Buildings.InfernoTower ;

public abstract class Sprite {
	// Fields
	protected Point2D location ;

	protected Circle rangeCircle ;
	protected Circle healthCircle ;

	protected double rangeCircleRadius ;
	protected double healthCircleRadius ;

	protected int cost ;
	protected double damage ;
	protected double runSpeed ;
	protected double hitpoints ;
	protected double attackSpeed ;
	protected double lastAttack ;
	protected boolean attackState ;
	protected ColorType colorType ;
	protected TroopType troopType ;
	protected TroopType targetType ;

	protected Image stateImage ;
	// Constructor
	public Sprite() {
		lastAttack = -100.0 ;
		attackState = false ;
	}
	// Methods : Setters
	public void setLocation(double x , double y) {
		this.location = new Point2D(x , y) ;

		double offsetX = stateImage.getWidth() / 2.0 ;
		double offsetY = stateImage.getHeight() / 2.0 ;

		this.rangeCircle = new Circle(location.getX() + offsetX , location.getY() + offsetY , rangeCircleRadius) ;
		this.healthCircle = new Circle(location.getX() + offsetX , location.getY() + offsetY , healthCircleRadius) ;
	}
	public void setRangeCircleRadius(double radius) {
		this.rangeCircleRadius = radius ;
	}
	public void setHealthCircleRadius(double radius) {
		this.healthCircleRadius = radius ;
	}
	public void removeRangeCircle() {
		this.rangeCircle = null ;
	}
	public void removeHealthCircle() {
		this.healthCircle = null ;
	}
	public void setCost(int cost) {
		this.cost = cost ;	
	}
	public void setDamage(double damage) {
		this.damage = damage ;
	}
	public void setRunSpeed(double speed) {
		this.runSpeed = speed ;
	}
	public void setHitpoints(double hitpoints) {
		this.hitpoints = hitpoints ;
	}
	public void setAttackSpeed(double speed) {
		this.attackSpeed = speed ;	
	}
	public void setLastAttack(double lastAttack) {
		this.lastAttack = lastAttack ;
	}
	public void setAttackState(boolean attackState) {
		this.attackState = attackState ;
		if ( this instanceof InfernoTower && !attackState )
			this.setDamage(20) ;
	}
	public void setColorType(ColorType color) {
		this.colorType = color ;
	}
	public void setTroopType(TroopType type) {
		this.troopType = type ;
	}
	public void setTargetType(TroopType target) {
		this.targetType = target ;
	}
	public void setStateImage(Image stateImage) {
		this.stateImage = stateImage ;
	}
	// Methods : Getters
	public double getX() {
		return this.location.getX() ;
	}
	public double getY() {
		return this.location.getY() ;
	}
	public Circle getRangeCircle() {
		return this.rangeCircle ;
	}
	public Circle getHealthCircle() {
		return this.healthCircle ;
	}
	public int getCost() {
		return this.cost ;
	}
	public double getDamage() {
		return this.damage ;
	}
	public double getRunSpeed() {
		return this.runSpeed ;
	}
	public double getHitpoints() {
		return this.hitpoints ;
	}
	public double getAttackSpeed() {
		return this.attackSpeed ;
	}
	public double getLastAttack() {
		return this.lastAttack ;
	}
	public boolean getAttackState() {
		return this.attackState ;
	}
	public ColorType getColorType() {
		return this.colorType ;
	}
	public TroopType getTroopType() {
		return this.troopType ;
	}
	public TroopType getTargetType() {
		return this.targetType ;
	}
	public Image getStateImage() {
		return this.stateImage ;
	}
	// Methods : Private
	private boolean circleIntersects(Circle first , Circle second) {
		if ( first == null || second == null )
			return false ;

		Point2D firstCenter = new Point2D(first.getCenterX() , first.getCenterY()) ;
		Point2D secondCenter = new Point2D(second.getCenterX() , second.getCenterY()) ;

		return (firstCenter.distance(secondCenter) < (first.getRadius() + second.getRadius()) / 2.0) ;
	}
	// Methods : Public
	public boolean rangeIntersects(Circle circle) {
		return circleIntersects(rangeCircle , circle) ;
	}
	public boolean healthIntersects(Circle circle) {
		return circleIntersects(healthCircle , circle) ;
	}
	public boolean canAttack(Sprite defender) {
		if ( this.getColorType() == defender.getColorType() )
			return false ;
		if ( !this.rangeIntersects(defender.getHealthCircle()) )
			return false ;
		return (this.getTargetType() == TroopType.ALL || this.getTargetType() == defender.getTroopType() || defender.getTroopType() == TroopType.BUILDING) ;
	}
	public void showRangeCircle(GraphicsContext graphics) {
		double offsetX = (stateImage.getWidth() - rangeCircleRadius) / 2.0 ;
		double offsetY = (stateImage.getHeight() - rangeCircleRadius) / 2.0 ;
		graphics.setFill(Color.BLUE) ;
		graphics.fillOval(this.getX() + offsetX , this.getY() + offsetY , rangeCircle.getRadius() , rangeCircle.getRadius()) ;
	}
	public void showHealthCircle(GraphicsContext graphics) {
		double offsetX = (stateImage.getWidth() - healthCircleRadius) / 2.0 ;
		double offsetY = (stateImage.getHeight() - healthCircleRadius) / 2.0 ;
		graphics.setFill(Color.RED) ;
		graphics.fillOval(this.getX() + offsetX , this.getY() + offsetY , healthCircle.getRadius() , healthCircle.getRadius()) ;
	}
	public void attack(ArrayList<Sprite> defenders) {
		this.setAttackState(true) ;
		for ( Sprite defender : defenders )
			defender.setHitpoints(defender.getHitpoints() - this.getDamage()) ;
		if ( this instanceof InfernoTower ) {
			InfernoTowerCard temp = new InfernoTowerCard();
			this.setDamage(Math.max(this.getDamage() + 5, temp.getRange() ));
		}
	}
	// Methods : Abstract
	public abstract void walkForward() ;
	public abstract void walkBridge() ;
	public abstract void walkKingTower() ;
	public abstract void walk(boolean leftArcherTowerDestroyed , boolean rightArcherTowerDestroyed) ;
	public abstract void draw(GraphicsContext graphics) ;
}

