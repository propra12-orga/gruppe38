package gruppe38.Tests;

/**
 * @author Tom Berwald
 */

import gruppe38.Main;
import gruppe38.Items.BombeSp2;
import gruppe38.Spieler.Spieler;

public class Spieler2Test {

	double bombX;
	double bombY;
	int radius;
	double spRadius;
	double spielerX;
	double spielerY;
	Spieler spieler;
	
	public Spieler2Test(BombeSp2 b, Spieler sp){
		bombX = b.getX();
		bombY = b.getY();
		radius = Main.getSp2().getExplosions_staerke();
		spRadius = sp.getRadius();
		spielerX = sp.getX();
		spielerY = sp.getY();
		spieler = sp;
	}
	
	public void test(){
		if (bombX+radius*(1/Main.getSpielfelder())>=spielerX & ((bombY+spRadius)>=spielerY & (bombY-spRadius)<=spielerY))
		{
			spieler.setLeben(spieler.getLeben()-1);
			if (spieler.getLeben()<=0){
			System.out.println(spieler.getName()+" ist Tod");
			}
		}
		else if (bombX-radius*(1/Main.getSpielfelder())>=spielerX & ((bombY+spRadius)>=spielerY & (bombY-spRadius)<=spielerY))
		{
			spieler.setLeben(spieler.getLeben()-1);
			if (spieler.getLeben()<=0){
			System.out.println(spieler.getName()+" ist Tod");
			}
		}
		else if (bombY+radius*(1/Main.getSpielfelder())<=spielerY & ((bombX+spRadius)>=spielerX & (bombX-spRadius)<=spielerX))
		{
			spieler.setLeben(spieler.getLeben()-1);
			if (spieler.getLeben()<=0){
			System.out.println(spieler.getName()+" ist Tod");
			}
		}
		else if (bombY-radius*(1/Main.getSpielfelder())<= spielerY & ((bombX+spRadius)>=spielerX & (bombX-spRadius)<=spielerX))
		{
			spieler.setLeben(spieler.getLeben()-1);
			if (spieler.getLeben()<=0){
			System.out.println(spieler.getName()+" ist Tod");
			}
		}
	}

}
