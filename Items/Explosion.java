package gruppe38.Items;

//Explosionsthread

public class Explosion extends Thread {

	public String bombe = "explosion.png";
	double x = 1;
	double y = 1;
	private boolean existent = false;
	int x_feld;
	int y_feld;
	String objekt_typ;

	public Explosion(double x_coor, double y_coor, int x_field, int y_field,
			boolean bool, String objekt_typ_eingabe) {
		x = x_coor;
		y = y_coor;
		setExistent(bool);
		x_feld = x_field;
		y_feld = y_field;
		objekt_typ = objekt_typ_eingabe;
	}

	public void run() {

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setExistent(false);

	}

	public boolean isExistent() {
		return existent;
	}

	public void setExistent(boolean existent) {
		this.existent = existent;
	}
}
