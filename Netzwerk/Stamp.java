package gruppe38.Netzwerk;

import gruppe38.Main;
import gruppe38.Items.Bombe;
import gruppe38.Items.Explosion;
import gruppe38.Spieler.Spieler;
import gruppe38.Spielfeld.Spielfeld2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Der Stamp
 * 
 * @author Gruppe38
 * 
 */
public class Stamp extends Object implements Serializable {

	int spielfelder1;
	Spielfeld2[][] feld1;
	double spielfeldgroesse1;
	int bombenanzahl1;
	Bombe[] bombe1;
	Explosion[] explosion1;
	double[] W11;
	double[] W21;
	double[] H11;
	double[] H21;
	Spieler sp11;
	Spieler sp21;
	double[] hdraw1;
	final int buffer_size = 1024 * 4;
	byte[] buffer1 = new byte[buffer_size];

	/**
	 * Konstruktor
	 * 
	 * @param spielfelder
	 * @param feld
	 * @param spielfeldgroesse
	 * @param bombenanzahl
	 * @param bombe
	 * @param explosion
	 * @param W1
	 * @param W2
	 * @param H1
	 * @param H2
	 * @param sp1
	 * @param sp2
	 * @param hdraw
	 */
	public Stamp(int spielfelder, Spielfeld2[][] feld, double spielfeldgroesse,
			int bombenanzahl, Bombe[] bombe, Explosion[] explosion,
			double[] W1, double[] W2, double[] H1, double[] H2, Spieler sp1,
			Spieler sp2, double[] hdraw) {

		// Konstruktor
		spielfelder1 = spielfelder;
		feld1 = feld;
		spielfeldgroesse1 = spielfeldgroesse;
		bombenanzahl1 = bombenanzahl;
		bombe1 = bombe;
		explosion1 = explosion;
		W11 = W1;
		W21 = W2;
		H11 = H1;
		H21 = H2;
		sp11 = sp1;
		sp21 = sp2;
		hdraw1 = hdraw;

	}

	/**
	 * Stempel erstellen
	 */
	public void run() {
		// Stempel erstellen
		Stamp stempel = create();
		send(stempel);

	}

	public static Stamp create() {

		Stamp stmp = new Stamp(Main.getSpielfelder(), Main.getFeld(),
				Main.getSpielfeldgroesse(), Main.getBombenanzahl(),
				Main.getBombe(), Main.getExplosion(), Main.getW1(),
				Main.getW2(), Main.getH1(), Main.getH2(), Main.getSp1(),
				Main.getSp2(), Main.getHdraw());
		// System.out.println("Aktuellen Stempel erstellt");

		return stmp;
	}

	/**
	 * Sendet den Stamp
	 * 
	 * @param stampToSend
	 *            Der Stamp der gesendet wird
	 */
	public static void send(Stamp stampToSend) {

		Main.sendcli = toByteArray(stampToSend);
		// System.out.println(Main.sendcli.length);
		Main.packetcli = new DatagramPacket(Main.sendcli, Main.sendcli.length,
				Server.address2, Server.port + 2);
		try {
			Server.socket.send(Main.packetcli);

			// System.out.println(Main.sendcli.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Laed den Stempel
	 * 
	 * @param stempel
	 *            Der zu ladende Stempel
	 */
	public static void load(Stamp stempel) {
		Main.setSpielfelder(stempel.getSpielfelder());
		Main.setFeld(stempel.getFeld());
		Main.setSpielfeldgroesse(stempel.getSpielfeldgroesse());
		Main.setBombenanzahl(stempel.getBombenanzahl());
		Main.setBombe(stempel.getBombe());
		Main.setExplosion(stempel.getExplosion());
		Main.setW1(stempel.getW1());
		Main.setW2(stempel.getW2());
		Main.setH1(stempel.getH1());
		Main.setH2(stempel.getH2());
		Main.setHdraw(stempel.getHdraw());
		Main.setSp1(stempel.getSp1());
		Main.setSp2(stempel.getSp2());
		// System.out.println("Stempel geladen!");
	}

	/**
	 * Komprimiert die Daten
	 * 
	 * @param input
	 *            Was Komprimiert werden soll
	 * @return
	 */
	public static byte[] compressBytes(byte[] input) {
		Deflater deflater = new Deflater();
		deflater.setInput(input);
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(
				input.length);
		deflater.finish();
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			byteStream.write(buffer, 0, count);
		}
		try {
			byteStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteStream.toByteArray();
	}

	/**
	 * Inflates a byte array.
	 * 
	 * @param input
	 *            The byte array to be inflated.
	 * @return A inflated byte array.
	 */
	public static byte[] inflateBytes(byte[] input) {
		Inflater inflater = new Inflater();
		inflater.setInput(input);
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(
				input.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			try {
				int count = inflater.inflate(buffer);
				byteStream.write(buffer, 0, count);
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
		try {
			byteStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteStream.toByteArray();
	}

	/**
	 * Converts an object to a compressed byte array.
	 * 
	 * @param object
	 *            The object to be compressed.
	 * @return A byte array which contains the compressed object.
	 */
	public static byte[] toByteArray(Stamp object) {
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(object);
			objectStream.flush();
			objectStream.close();
			byteStream.close();
			return compressBytes(byteStream.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Creates an object from a compressed byte array.
	 * 
	 * @param data
	 *            The byte array of which the object should be created from.
	 * @return An object created from the byte array.
	 */
	public static Stamp getFromByteArray(byte[] data) {
		data = inflateBytes(data);
		Object object = null;
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
			ObjectInputStream objectStream = new ObjectInputStream(byteStream);
			object = objectStream.readObject();
			objectStream.close();
			byteStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Stamp) object;
	}

	public int getSpielfelder() {
		return spielfelder1;
	}

	public Spielfeld2[][] getFeld() {
		return feld1;
	}

	public double getSpielfeldgroesse() {
		return spielfeldgroesse1;
	}

	public int getBombenanzahl() {
		return bombenanzahl1;
	}

	public Bombe[] getBombe() {
		return bombe1;
	}

	public Explosion[] getExplosion() {
		return explosion1;
	}

	public double[] getW1() {
		return W11;
	}

	public double[] getW2() {
		return W21;
	}

	public double[] getH1() {
		return H11;
	}

	public double[] getH2() {
		return H21;
	}

	public double[] getHdraw() {
		return hdraw1;
	}

	public Spieler getSp1() {
		return sp11;
	}

	public Spieler getSp2() {
		return sp21;
	}
}
