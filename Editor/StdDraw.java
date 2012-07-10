package gruppe38.Editor;

/*************************************************************************
 *  Compilation:  javac StdDraw.java
 *  Execution:    java StdDraw
 *
 *  Standard drawing library. This class provides a basic capability for
 *  creating drawings with your programs. It uses a simple graphics model that
 *  allows you to create drawings consisting of points, lines, and curves
 *  in a window on your computer and to save the drawings to a file.
 *
 *  Todo
 *  ----
 *    -  Add support for gradient fill, etc.
 *
 *  Remarks
 *  -------
 *    -  don't use AffineTransform for rescaling since it inverts
 *       images and strings
 *    -  careful using setFont in inner loop within an animation -
 *       it can cause flicker
 *
 *************************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * <i>Standard draw</i>. This class provides a basic capability for creating
 * drawings with your programs. It uses a simple graphics model that allows you
 * to create drawings consisting of points, lines, and curves in a window on
 * your computer and to save the drawings to a file.
 * <p>
 * For additional documentation, see <a
 * href="http://introcs.cs.princeton.edu/15inout">Section 1.5</a> of
 * <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by
 * Robert Sedgewick and Kevin Wayne.
 */
public final class StdDraw
		implements
			ActionListener,
			MouseListener,
			MouseMotionListener,
			KeyListener {

	// pre-defined colors
	public static final Color BLACK = Color.BLACK;
	public static final Color BLUE = Color.BLUE;
	public static final Color CYAN = Color.CYAN;
	public static final Color DARK_GRAY = Color.DARK_GRAY;
	public static final Color GRAY = Color.GRAY;
	public static final Color GREEN = Color.GREEN;
	public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
	public static final Color MAGENTA = Color.MAGENTA;
	public static final Color ORANGE = Color.ORANGE;
	public static final Color PINK = Color.PINK;
	public static final Color RED = Color.RED;
	public static final Color WHITE = Color.WHITE;
	public static final Color YELLOW = Color.YELLOW;

	/**
	 * Shade of blue used in Introduction to Programming in Java. It is Pantone
	 * 300U. The RGB values are approximately (9, 90, 266).
	 */
	public static final Color BOOK_BLUE = new Color(9, 90, 166);
	public static final Color BOOK_LIGHT_BLUE = new Color(103, 198, 243);

	/**
	 * Shade of red used in Algorithms 4th edition. It is Pantone 1805U. The RGB
	 * values are approximately (150, 35, 31).
	 */
	public static final Color BOOK_RED = new Color(150, 35, 31);

	// current pen color
	private static Color penColor;

	private static final Color DEFAULT_CLEAR_COLOR = WHITE;

	// default canvas size is DEFAULT_SIZE-by-DEFAULT_SIZE
	public static final int DEFAULT_SIZE = 300;
	private static final String DEFAULT_PEN_COLOR = null;
	public static int width = 600;
	public static int height = 600;
	public static Dimension screensize = java.awt.Toolkit.getDefaultToolkit()
			.getScreenSize();

	// show we draw immediately or wait until next show?
	private static boolean defer = false;

	// boundary of drawing canvas, 5% border
	// private static final double BORDER = 0.05;

	public static double xmin, ymin, xmax, ymax;

	// for synchronization
	private static Object mouseLock = new Object();

	// double buffered graphics
	public static BufferedImage offscreenImage, onscreenImage;
	public static Graphics2D offscreen, onscreen;
	public static BufferedImage offscreenImage2, onscreenImage2;
	public static Graphics2D offscreen2, onscreen2;

	// singleton for callbacks: avoids generation of extra .class files
	private static StdDraw std = new StdDraw();

	// the frame for drawing to the screen
	public static JFrame frame;
	public static JFrame frame_items;
	public static JPanel jpanel;

	// mouse state
	private static boolean mousePressed = false;
	private static double mouseX = 0;
	private static double mouseY = 0;

	// queue of typed key characters
	private static LinkedList<Character> keysTyped = new LinkedList<Character>();

	// set of key codes currently pressed down
	private static TreeSet<Integer> keysDown = new TreeSet<Integer>();

	// not instantiable
	private StdDraw() {
	}

	// static initializer
	static {
		init();
	}

	/**
	 * Set the window size to the default size 512-by-512 pixels.
	 */
	public static void setCanvasSize() {
		setCanvasSize(DEFAULT_SIZE, DEFAULT_SIZE);
	}

	/**
	 * Set the window size to w-by-h pixels.
	 * 
	 * @param w
	 *            the width as a number of pixels
	 * @param h
	 *            the height as a number of pixels
	 * @throws a
	 *             RunTimeException if the width or height is 0 or negative
	 */
	public static void setCanvasSize(int w, int h) {
		if (w < 1 || h < 1)
			throw new RuntimeException("width and height must be positive");
		width = w;
		height = h;
		init();
	}

	// init
	public static void init() {

		if (frame != null)
			frame.setVisible(false);
		if (frame_items != null)
			frame_items.setVisible(false);

		frame = new JFrame();
		frame_items = new JFrame();

		offscreenImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		onscreenImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		offscreen = offscreenImage.createGraphics();
		onscreen = onscreenImage.createGraphics();
		offscreen.setColor(DEFAULT_CLEAR_COLOR);
		offscreen.fillRect(0, 0, width, height);

		// Images for the items-frame
		offscreenImage2 = new BufferedImage(600, 100,
				BufferedImage.TYPE_INT_ARGB);
		onscreenImage2 = new BufferedImage(600, 100,
				BufferedImage.TYPE_INT_ARGB);
		offscreen2 = offscreenImage.createGraphics();
		onscreen2 = onscreenImage.createGraphics();
		offscreen2.setColor(DEFAULT_CLEAR_COLOR);
		offscreen2.fillRect(0, 0, 600, 600);

		// add antialiasing
		// RenderingHints hints = new RenderingHints(
		// RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		// hints.put(RenderingHints.KEY_RENDERING,
		// RenderingHints.VALUE_RENDER_QUALITY);
		// offscreen.addRenderingHints(hints);

		// frame stuff
		ImageIcon icon = new ImageIcon(onscreenImage);
		JLabel draw = new JLabel(icon);

		ImageIcon icon2 = new ImageIcon(onscreenImage2);
		JLabel draw2 = new JLabel(icon2);

		draw.addMouseListener(std);
		draw.addMouseMotionListener(std);

		frame_items.setContentPane(draw2);
		frame_items.addKeyListener(std); // JLabel cannot get keyboard focus
		frame_items.setJMenuBar(createMenuBar());
		frame_items.setResizable(true);
		frame_items.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes
																	// all
		// windows
		frame_items.setTitle("Editor-Items");
		frame_items.setLocation(screensize.height / 8 + 650,
				screensize.width / 8 + 0);
		frame_items.setVisible(true);

		frame.setContentPane(draw);

		frame.addKeyListener(std); // JLabel cannot get keyboard focus
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes all
																// windows

		frame.setTitle("Bomberman: Editor");

		frame.setLocation(screensize.height / 8, screensize.width / 8);
		frame.pack();
		frame.requestFocusInWindow();
		frame.setVisible(true);

		// // Frame fï¿½r das Laden einer Map
		// frame2.setContentPane(draw);
		// frame2.addKeyListener(std); // JLabel cannot get keyboard focus
		// frame2.setResizable(false);
		// frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes all
		// // windows
		//
		// frame2.setTitle("Map laden");
		// // frame2.setJMenuBar(createMenuBar());
		// frame2.pack();
		// frame2.requestFocusInWindow();
		// frame2.setVisible(false);
		menu();

	}
	public static void menu() {
		ImageIcon iconMauer = new ImageIcon("src/gruppe38/Bilder/mauer.png");
		ImageIcon iconSpawn = new ImageIcon("src/gruppe38/Bilder/spawn.png");
		ImageIcon iconBombe_energie = new ImageIcon(
				"src/gruppe38/Bilder/bombe_energie.png");
		ImageIcon iconBombe_extra = new ImageIcon(
				"src/gruppe38/Bilder/bombe_extra.png");
		ImageIcon iconMauer_destroyable = new ImageIcon(
				"src/gruppe38/Bilder/mauer_destroyable.png");
		ImageIcon iconNichts = new ImageIcon(
				"src/gruppe38/Bilder/SB_Accelerator.png");
		ImageIcon icon3 = new ImageIcon("src/gruppe38/Bilder/bombe.gif");

		jpanel = new JPanel();

		frame_items.add(jpanel);

		JLabel label1 = new JLabel("Items");
		jpanel.add(label1, BorderLayout.SOUTH);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 16));
		label1.setBackground(new Color(0, 153, 255));

		// JLabel labelbild = new JLabel("");
		// labelbild.setIcon(new ImageIcon ("bombe.gif"));
		// labelbild.setToolTipText( "blub");
		// getContentPane().add(labelbild, BorderLayout.CENTER);

		JPanel jpanel2 = new JPanel();
		jpanel.add(jpanel2, BorderLayout.WEST);

		JButton buttonMauer = new JButton(iconMauer);
		buttonMauer.setActionCommand("mauer");
		buttonMauer.addActionListener(std);
		jpanel2.add(buttonMauer);

		JButton buttonBombe_energie = new JButton(iconBombe_energie);
		buttonBombe_energie.setActionCommand("bombe_energie");
		buttonBombe_energie.addActionListener(std);
		jpanel2.add(buttonBombe_energie);

		JButton buttonBombe_extra = new JButton(iconBombe_extra);
		buttonBombe_extra.setActionCommand("bombe_extra");
		buttonBombe_extra.addActionListener(std);
		jpanel2.add(buttonBombe_extra);

		JButton buttonMauer_destroyable = new JButton(iconMauer_destroyable);
		buttonMauer_destroyable.setActionCommand("mauer_destroyable");
		buttonMauer_destroyable.addActionListener(std);
		jpanel2.add(buttonMauer_destroyable);

		JButton buttonSpawn = new JButton(iconSpawn);
		buttonSpawn.setActionCommand("spawn");
		buttonSpawn.addActionListener(std);
		jpanel2.add(buttonSpawn);

		JButton buttonNichts = new JButton(iconNichts);
		buttonNichts.setActionCommand("nothing");
		buttonNichts.addActionListener(std);
		jpanel2.add(buttonNichts);

		jpanel.setSize(600, 400);
		jpanel.setLocation(0, 0);

		// ((JFrame) jpanel).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpanel.setVisible(true);
		frame_items.pack();
	}

	// create the menu bar (changed to private)
	private static JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu2 = new JMenu("Menus");

		JMenuItem start = new JMenuItem("Neue Map");
		start.setActionCommand("neu");
		start.addActionListener(std);
		menu2.add(start);

		JMenuItem random = new JMenuItem("Zufällige Karte generieren");
		random.setActionCommand("random");
		random.addActionListener(std);
		menu2.add(random);

		JMenuItem karte_laden = new JMenuItem("Map laden");
		karte_laden.setActionCommand("karte laden");
		karte_laden.addActionListener(std);
		menu2.add(karte_laden);

		JMenuItem karte_speichern = new JMenuItem("Map speichern");
		karte_speichern.setActionCommand("karte speichern");
		karte_speichern.addActionListener(std);
		menu2.add(karte_speichern);

		JMenuItem beenden = new JMenuItem("Beenden");
		beenden.addActionListener(std);
		beenden.setActionCommand("beenden");
		menu2.add(beenden);
		menuBar.add(menu2);

		return menuBar;
	}

	private static void pixel(double x, double y) {
		offscreen.fillRect((int) Math.round(scaleX(x)),
				(int) Math.round(scaleY(y)), 1, 1);
	}

	private static double scaleX(double x) {
		return width * (x - xmin) / (xmax - xmin);
	}

	private static double scaleY(double y) {
		return height * (ymax - y) / (ymax - ymin);
	}

	private static double factorX(double w) {
		return w * width / Math.abs(xmax - xmin);
	}

	private static double factorY(double h) {
		return h * height / Math.abs(ymax - ymin);
	}

	public static void filledSquare(double x, double y, double r) {
		if (r < 0)
			throw new RuntimeException("square side length can't be negative");
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * r);
		double hs = factorY(2 * r);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.fill(new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws,
					hs));
		// draw();
	}

	public static void setPenColor(Color color) {
		penColor = color;
		offscreen.setColor(penColor);
	}

	// helper functions that scale from user coordinates to screen coordinates
	// and back

	public static void show(int t) {
		defer = false;
		// draw();
		try {
			Thread.currentThread().sleep(t);
		} catch (InterruptedException e) {
			System.out.println("Error sleeping");
		}
		defer = true;
	}

	/**
	 * Display on-screen and turn off animation mode: subsequent calls to
	 * drawing methods such as <tt>line()</tt>, <tt>circle()</tt>, and
	 * <tt>square()</tt> will be displayed on screen when called. This is the
	 * default.
	 */
	public static void show() {
		defer = false;
		// draw();
	}

	// draw onscreen if defer is false
	private static void draw() {
		if (defer)
			return;
		onscreen.drawImage(offscreenImage, 0, 0, null);
		frame.repaint();
	}

	/*************************************************************************
	 * Save drawing to a file.
	 *************************************************************************/

	/**
	 * Save onscreen image to file - suffix must be png, jpg, or gif.
	 * 
	 * @param filename
	 *            the name of the file with one of the required suffixes
	 */
	public static void save(String filename) {
		File file = new File(filename);
		String suffix = filename.substring(filename.lastIndexOf('.') + 1);

		// png files
		if (suffix.toLowerCase().equals("png")) {
			try {
				ImageIO.write(onscreenImage, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// need to change from ARGB to RGB for jpeg
		// reference:
		// http://archives.java.sun.com/cgi-bin/wa?A2=ind0404&L=java2d-interest&D=0&P=2727
		else if (suffix.toLowerCase().equals("jpg")) {
			WritableRaster raster = onscreenImage.getRaster();
			WritableRaster newRaster;
			newRaster = raster.createWritableChild(0, 0, width, height, 0, 0,
					new int[]{0, 1, 2});
			DirectColorModel cm = (DirectColorModel) onscreenImage
					.getColorModel();
			DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(),
					cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());
			BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster,
					false, null);
			try {
				ImageIO.write(rgbBuffer, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else {
			System.out.println("Invalid image file type: " + suffix);
		}
	}

	public static void load2(String filename) {
		File file = new File(filename);
		String suffix = filename.substring(filename.lastIndexOf('.') + 1);

		// png files
		if (suffix.toLowerCase().equals("txt")) {
			try {

				ImageIO.write(onscreenImage, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// need to change from ARGB to RGB for jpeg
		// reference:
		// http://archives.java.sun.com/cgi-bin/wa?A2=ind0404&L=java2d-interest&D=0&P=2727
		else if (suffix.toLowerCase().equals("jpg")) {
			WritableRaster raster = onscreenImage.getRaster();
			WritableRaster newRaster;
			newRaster = raster.createWritableChild(0, 0, width, height, 0, 0,
					new int[]{0, 1, 2});
			DirectColorModel cm = (DirectColorModel) onscreenImage
					.getColorModel();
			DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(),
					cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());
			BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster,
					false, null);
			try {
				ImageIO.write(rgbBuffer, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else {
			System.out.println("Invalid image file type: " + suffix);
		}
	}

	public static void load(String filename) {
		File file = new File(filename);
		String suffix = filename.substring(filename.lastIndexOf('.') + 1);

		// png files
		if (suffix.toLowerCase().equals("txt")) {
			try {

				ImageIO.write(onscreenImage, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// need to change from ARGB to RGB for jpeg
		// reference:
		// http://archives.java.sun.com/cgi-bin/wa?A2=ind0404&L=java2d-interest&D=0&P=2727
		else if (suffix.toLowerCase().equals("jpg")) {
			WritableRaster raster = onscreenImage.getRaster();
			WritableRaster newRaster;
			newRaster = raster.createWritableChild(0, 0, width, height, 0, 0,
					new int[]{0, 1, 2});
			DirectColorModel cm = (DirectColorModel) onscreenImage
					.getColorModel();
			DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(),
					cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());
			BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster,
					false, null);
			try {
				ImageIO.write(rgbBuffer, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else {
			System.out.println("Invalid image file type: " + suffix);
		}
	}

	/**
	 * This method cannot be called directly.
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {

		// wenn der button "Starten" aufgerufen wird, starte das spiel
		if (e.getActionCommand().equals("mauer")) {
			Editor.item = e.getActionCommand().toString();
		}
		if (e.getActionCommand().equals("bombe_energie")) {
			Editor.item = e.getActionCommand().toString();
		}
		if (e.getActionCommand().equals("bombe_extra")) {
			Editor.item = e.getActionCommand().toString();
		}
		if (e.getActionCommand().equals("mauer_destroyable")) {
			Editor.item = e.getActionCommand().toString();
		}
		if (e.getActionCommand().equals("spawn")) {
			Editor.item = e.getActionCommand().toString();
		}
		if (e.getActionCommand().equals("nothing")) {
			Editor.item = e.getActionCommand().toString();
		}
		System.out.println(Editor.item);

		if (e.getActionCommand().equals("neu")) {
			InitEditor.init();
		}
		if (e.getActionCommand().equals("random")) {
			InitEditor.random();
		}
		if (e.getActionCommand().equals("beenden")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("karte laden")) {
			FileDialog chooser = new FileDialog(StdDraw.frame,
					"Wähle eine Datei aus", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if (filename != null) {
				MapLoader map = new MapLoader();
				map.load(chooser.getDirectory() + File.separator + filename);
				System.out.println(chooser.getDirectory() + File.separator
						+ filename);

			}
			// MapLoader map = new MapLoader();
			// map.load(filename);
		}
		if (e.getActionCommand().equals("karte speichern")) {
			// Test ob map spielbar ist bevor die karte gespeichert wird
			for (int x = 0; x < Editor.getSpielfelder(); x++) {
				for (int y = 0; y < Editor.getSpielfelder(); y++) {
					if (Editor.getFeld()[x][y].beinhaltet.equals("spawn")) {
						MapVerifier mv = new MapVerifier(x, y);
						if (!mv.verifier())
							return;
					}
				}
			}

			FileDialog chooser = new FileDialog(StdDraw.frame,
					"Level speichern", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if (filename != null) {
				MapSaver map = new MapSaver();
				map.save(chooser.getDirectory() + File.separator + filename);
				System.out.println(chooser.getDirectory() + File.separator
						+ filename);
				// StdDraw.save(chooser.getDirectory() + File.separator
				// + chooser.getFile());
			}

		}

	}

	/*************************************************************************
	 * Mouse interactions.
	 *************************************************************************/

	/**
	 * Is the mouse being pressed?
	 * 
	 * @return true or false
	 */
	public static boolean mousePressed() {
		synchronized (mouseLock) {
			return mousePressed;
		}
	}

	/**
	 * What is the x-coordinate of the mouse?
	 * 
	 * @return the value of the x-coordinate of the mouse
	 */
	public static double mouseX() {
		synchronized (mouseLock) {
			return mouseX;
		}
	}

	/**
	 * What is the y-coordinate of the mouse?
	 * 
	 * @return the value of the y-coordinate of the mouse
	 */
	public static double mouseY() {
		synchronized (mouseLock) {
			return mouseY;
		}
	}

	/**
	 * This method cannot be called directly.
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Feldwiedergabe fw = new Feldwiedergabe();
			// fw = EditorFeldCheck
			// .check((double) ((double) (frame.getMousePosition().x - 2) /
			// (double) frame
			// .getWidth()),
			// 1 - ((double) ((double) (frame.getMousePosition().y - ((double)
			// (48 - frame
			// .getMousePosition().y
			// / frame.getHeight()
			// * 48))))
			//
			// / (double) frame.getHeight()));

			double ausgleichwert = Editor.getSpielfeldgroesse()
					- (double) ((double) frame.getMousePosition().y
							/ (double) frame.getHeight() * Editor
								.getSpielfeldgroesse());

			fw = EditorFeldCheck
					.check((double) ((double) (frame.getMousePosition().x) / (double) frame
							.getWidth()),
							1 - (((double) (frame.getMousePosition().y))

							/ (double) frame.getHeight()) + ausgleichwert);

			if (!Editor.item.equals("nothing")) {

				Editor.setX_feld(fw.getX());
				Editor.setY_feld(fw.getY());

				Editor.getFeld()[Editor.getX_feld()][Editor.getY_feld()].beinhaltet = Editor.item;

				Editor.getFeld()[Editor.getX_feld()][Editor.getY_feld()].belegt = true;

				// if (Editor.item.equals("mauer")) {
				// Editor.getFeld()[Editor.getX_feld()][Editor.getY_feld()].beinhaltet
				// = Editor.item;
				// }
				// if (Editor.item.equals("mauer_destroyable")) {
				// Editor.getFeld()[Editor.getX_feld()][Editor.getY_feld()].beinhaltet
				// = Editor.item;
				//
				// }
				// if (Editor.item.equals("bombe_energie")) {
				// Editor.getFeld()[Editor.getX_feld()][Editor.getY_feld()].beinhaltet
				// = Editor.item;
				//
				// }
				// if (Editor.item.equals("bombe_extra")) {
				//
				//
				// }
			} else {
				Editor.getFeld()[Editor.getX_feld()][Editor.getY_feld()].beinhaltet = Editor.item;
				Editor.getFeld()[Editor.getX_feld()][Editor.getY_feld()].belegt = false;
			}
		}
		System.out.println(Editor.getFeld()[Editor.getX_feld()][Editor
				.getY_feld()].beinhaltet);

	}

	/**
	 * This method cannot be called directly.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * This method cannot be called directly.
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * This method cannot be called directly.
	 */
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * This method cannot be called directly.
	 */
	public void mouseReleased(MouseEvent e) {

	}
	/**
	 * This method cannot be called directly.
	 */
	public void mouseDragged(MouseEvent e) {

	}

	/**
	 * This method cannot be called directly.
	 */
	public void mouseMoved(MouseEvent e) {

	}

	/*************************************************************************
	 * Keyboard interactions.
	 *************************************************************************/

	/**
	 * Is the keycode currently being pressed? This method takes as an argument
	 * the keycode (corresponding to a physical key). It can handle action keys
	 * (such as F1 and arrow keys) and modifier keys (such as shift and
	 * control). See <a href =
	 * "http://download.oracle.com/javase/6/docs/api/java/awt/event/KeyEvent.html"
	 * >KeyEvent.java</a> for a description of key codes.
	 * 
	 * @return true if keycode is currently being pressed, false otherwise
	 */
	public static boolean isKeyPressed(int keycode) {
		return keysDown.contains(keycode);
	}

	/**
	 * This method cannot be called directly.
	 */
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * This method cannot be called directly.
	 */
	public void keyPressed(KeyEvent e) {
		System.out.println("keyPressed");

	}

	/**
	 * This method cannot be called directly.
	 */
	public void keyReleased(KeyEvent e) {
		System.out.println("keyReleased");

	}

}
