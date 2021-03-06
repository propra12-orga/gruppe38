package gruppe38.Sonstiges;

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

import gruppe38.Init;
import gruppe38.Main;
import gruppe38.Editor.Editor;
import gruppe38.Maps.MapLoader;
import gruppe38.Maps.MapSaver;
import gruppe38.Netzwerk.Client;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
public final class StdDraw implements ActionListener, MouseListener,
		MouseMotionListener, KeyListener {

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

	// default colors
	private static final Color DEFAULT_PEN_COLOR = BLACK;
	private static final Color DEFAULT_CLEAR_COLOR = WHITE;

	// current pen color
	private static Color penColor;

	// default canvas size is DEFAULT_SIZE-by-DEFAULT_SIZE
	public static final int DEFAULT_SIZE = 300;
	public static int width = 600;
	public static int height = 600;

	// default pen radius
	private static final double DEFAULT_PEN_RADIUS = 0.002;

	// current pen radius
	private static double penRadius;

	// show we draw immediately or wait until next show?
	private static boolean defer = false;

	// boundary of drawing canvas, 5% border
	// private static final double BORDER = 0.05;
	private static final double BORDER = 0;
	private static final double DEFAULT_XMIN = 0.0;
	private static final double DEFAULT_XMAX = 1.0;
	private static final double DEFAULT_YMIN = 0.0;
	private static final double DEFAULT_YMAX = 1.0;
	public static double xmin, ymin, xmax, ymax;

	// for synchronization
	private static Object mouseLock = new Object();
	private static Object keyLock = new Object();

	// default font
	private static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN,
			16);

	// current font
	private static Font font;

	// double buffered graphics
	public static BufferedImage offscreenImage, onscreenImage;
	public static Graphics2D offscreen, onscreen;

	// singleton for callbacks: avoids generation of extra .class files
	private static StdDraw std = new StdDraw();

	// the frame for drawing to the screen
	public static JFrame frame;
	public static JFrame frame2;
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
		if (frame2 != null)
			frame2.setVisible(false);

		frame = new JFrame();
		frame2 = new JFrame();

		offscreenImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		onscreenImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		offscreen = offscreenImage.createGraphics();
		onscreen = onscreenImage.createGraphics();
		setXscale();
		setYscale();
		offscreen.setColor(DEFAULT_CLEAR_COLOR);
		offscreen.fillRect(0, 0, width, height);
		setPenColor();
		setPenRadius();
		setFont();
		clear();

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

		draw.addMouseListener(std);
		draw.addMouseMotionListener(std);

		frame.setContentPane(draw);
		frame.addKeyListener(std); // JLabel cannot get keyboard focus
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes all
																// windows

		frame.setTitle("Bomberman - Gruppe38");
		frame.setJMenuBar(createMenuBar());
		frame.pack();
		frame.requestFocusInWindow();
		frame.setVisible(true);

		// // Frame f�r das Laden einer Map
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

	private static String text = "";

	public static void Nachricht(String msg) {
		text = msg;
	}

	public static void menu() {
		System.out.println("Zeige Men�");

		jpanel = new JPanel();
		jpanel.setName("BOMBERMAN 2012");

		frame.add(jpanel);

		JLabel label1 = new JLabel("<html><br>BOMBERMAN<br></html>");
		jpanel.add(label1, BorderLayout.PAGE_START);
		label1.setFont(new Font("Stencil Std", Font.PLAIN, 71));
		label1.setBackground(new Color(0, 153, 255));

		JLabel label2 = new JLabel(text);
		jpanel.add(label2);
		label2.setFont(new Font("Quartz", Font.PLAIN, 14));
		label2.setBackground(new Color(0, 153, 255));

		JLabel label3 = new JLabel(
				"<html><br>by Patrick Hippler, Daniel Braune, Tom Berwald, Patrick Szewior, Sebastian Holthausen & Ilja Zelenov<br><br></html>");
		jpanel.add(label3);
		label3.setFont(new Font("Quartz", Font.PLAIN, 11));
		label3.setBackground(new Color(0, 153, 255));

		// JLabel labelbild = new JLabel("");
		// labelbild.setIcon(new ImageIcon ("bombe.gif"));
		// labelbild.setToolTipText( "blub");
		// getContentPane().add(labelbild, BorderLayout.CENTER);

		// JLabel labelleer = new
		// JLabel("                                                  ");
		// jpanel.add(labelleer, BorderLayout.CENTER);
		// labelleer.setFont(new Font("Arial", Font.PLAIN, 40));

		// JLabel labelmitte1 = new
		// JLabel("<html><center><h1>ZIEL DES SPIELS</h1><p>Ziel des Spiel ist es, den Ausgang diagonal gegen�ber des<br>Startpunktes zu erreichen, indem die zerst�rbaren Mauern<br>mithilfe von Bomben zerst�rt werden und somit ein Weg<br>zum Ausgang freigemacht wird.<p/><br><h1>STEUERUNG</h1><p>&#8592; (Pfeil links):<img src='http://img849.imageshack.us/img849/8565/blackbmleft.gif'> nach links laufen<br>&#8594; (Pfeil rechts) <img src='http://img41.imageshack.us/img41/5863/blackbmright.gif'> nach rechts laufen<br>&#8593; (Pfeil oben): <img src='http://img837.imageshack.us/img837/8400/blackbmup.gif'> nach oben laufen<br>&#8595; (Pfeil unten) <img src='http://img535.imageshack.us/img535/8540/blackbmdown.gif'> nach unten laufen<br>Leertaste: <img width=50% height=50% src='http://img526.imageshack.us/img526/6987/boombe.png'> Bombe legen</p></center></html>");
		// jpanel.add(labelmitte1, BorderLayout.CENTER);
		// labelmitte1.setFont(new Font("Arial", Font.PLAIN, 15));

		JPanel jpanel2 = new JPanel();
		JPanel jMenu = new JPanel();
		jpanel.add(jpanel2, BorderLayout.PAGE_END);
		jpanel.add(jMenu, BorderLayout.LINE_END);

		JButton buttonstart = new JButton("Zweispieler");
		buttonstart.addActionListener(std);
		jpanel2.add(buttonstart);

		JButton buttonAllein = new JButton("Einzelspieler");
		buttonAllein.addActionListener(std);
		jpanel2.add(buttonAllein);

		JButton buttontutorial = new JButton("Tutorial");
		buttontutorial.addActionListener(std);
		jMenu.add(buttontutorial);
		
		JButton buttoneditor = new JButton("Editor");
		buttoneditor.addActionListener(std);
		jpanel2.add(buttoneditor);

		JButton buttonsave = new JButton("Level speichern");
		buttonsave.addActionListener(std);
		jMenu.add(buttonsave);

		JButton buttonladen = new JButton("Level laden");
		buttonladen.addActionListener(std);
		jMenu.add(buttonladen);

		JButton buttonende = new JButton("Spiel beenden");
		buttonende.addActionListener(std);
		jpanel2.add(buttonende);

		JLabel labelcopy = new JLabel(
				"<html><center>&#169; 2012 by Gruppe38, HHU Duesseldorf</center></html>");
		jpanel.add(labelcopy, BorderLayout.CENTER);
		labelcopy.setFont(new Font("Arial", Font.PLAIN, 15));

		jpanel.setSize(600, 600);
		// jpanel.setLocation(300,300);

		// ((JFrame) jpanel).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jpanel.setVisible(true);
		if (jpanel.isShowing()) {
			System.out.println("Daniel");
		}
	}

	// create the menu bar (changed to private)
	private static JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu2 = new JMenu("Menus");

		JMenuItem start = new JMenuItem("Starten");
		start.addActionListener(std);
		menu2.add(start);

		JMenuItem startAllein = new JMenuItem("Einzelspieler");
		startAllein.addActionListener(std);
		menu2.add(startAllein);

		JMenuItem netzwerk = new JMenuItem("Netzwerk-Server starten");
		netzwerk.addActionListener(std);
		menu2.add(netzwerk);

		JMenuItem netzwerk_beitreten = new JMenuItem("Netzwerk-Spiel beitreten");
		netzwerk_beitreten.addActionListener(std);
		menu2.add(netzwerk_beitreten);

		JMenuItem tutorial = new JMenuItem("Tutorial");
		tutorial.addActionListener(std);
		menu2.add(tutorial);

		JMenuItem karte_laden = new JMenuItem("Map laden");
		karte_laden.addActionListener(std);
		menu2.add(karte_laden);

		JMenuItem karte_speichern = new JMenuItem("Map speichern");
		karte_speichern.addActionListener(std);
		menu2.add(karte_speichern);

		JMenuItem beenden = new JMenuItem("Beenden");
		beenden.addActionListener(std);
		menu2.add(beenden);
		menuBar.add(menu2);

		return menuBar;
	}

	/*************************************************************************
	 * User and screen coordinate systems
	 *************************************************************************/

	/**
	 * Set the x-scale to be the default (between 0.0 and 1.0).
	 */
	public static void setXscale() {
		setXscale(DEFAULT_XMIN, DEFAULT_XMAX);
	}

	/**
	 * Set the y-scale to be the default (between 0.0 and 1.0).
	 */
	public static void setYscale() {
		setYscale(DEFAULT_YMIN, DEFAULT_YMAX);
	}

	/**
	 * Set the x-scale (a 10% border is added to the values)
	 * 
	 * @param min
	 *            the minimum value of the x-scale
	 * @param max
	 *            the maximum value of the x-scale
	 */
	public static void setXscale(double min, double max) {
		double size = max - min;
		xmin = min - BORDER * size;
		xmax = max + BORDER * size;
	}

	/**
	 * Set the y-scale (a 10% border is added to the values).
	 * 
	 * @param min
	 *            the minimum value of the y-scale
	 * @param max
	 *            the maximum value of the y-scale
	 */
	public static void setYscale(double min, double max) {
		double size = max - min;
		ymin = min - BORDER * size;
		ymax = max + BORDER * size;
	}

	/**
	 * Set the x-scale and y-scale (a 10% border is added to the values)
	 * 
	 * @param min
	 *            the minimum value of the x- and y-scales
	 * @param max
	 *            the maximum value of the x- and y-scales
	 */
	public static void setScale(double min, double max) {
		setXscale(min, max);
		setYscale(min, max);
	}

	// helper functions that scale from user coordinates to screen coordinates
	// and back
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

	private static double userX(double x) {
		return xmin + x * (xmax - xmin) / width;
	}

	private static double userY(double y) {
		return ymax - y * (ymax - ymin) / height;
	}

	/**
	 * Clear the screen to the default color (white).
	 */
	public static void clear() {
		clear(DEFAULT_CLEAR_COLOR);
	}

	/**
	 * Clear the screen to the given color.
	 * 
	 * @param color
	 *            the Color to make the background
	 */
	public static void clear(Color color) {
		offscreen.setColor(color);
		offscreen.fillRect(0, 0, width, height);
		offscreen.setColor(penColor);
		draw();
	}

	/**
	 * Get the current pen radius.
	 */
	public static double getPenRadius() {
		return penRadius;
	}

	/**
	 * Set the pen size to the default (.002).
	 */
	public static void setPenRadius() {
		setPenRadius(DEFAULT_PEN_RADIUS);
	}

	/**
	 * Set the radius of the pen to the given size.
	 * 
	 * @param r
	 *            the radius of the pen
	 * @throws RuntimeException
	 *             if r is negative
	 */
	public static void setPenRadius(double r) {
		if (r < 0)
			throw new RuntimeException("pen radius must be positive");
		penRadius = r * DEFAULT_SIZE;
		BasicStroke stroke = new BasicStroke((float) penRadius,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		// BasicStroke stroke = new BasicStroke((float) penRadius);
		offscreen.setStroke(stroke);
	}

	/**
	 * Get the current pen color.
	 */
	public static Color getPenColor() {
		return penColor;
	}

	/**
	 * Set the pen color to the default color (black).
	 */
	public static void setPenColor() {
		setPenColor(DEFAULT_PEN_COLOR);
	}

	/**
	 * Set the pen color to the given color. The available pen colors are BLACK,
	 * BLUE, CYAN, DARK_GRAY, GRAY, GREEN, LIGHT_GRAY, MAGENTA, ORANGE, PINK,
	 * RED, WHITE, and YELLOW.
	 * 
	 * @param color
	 *            the Color to make the pen
	 */
	public static void setPenColor(Color color) {
		penColor = color;
		offscreen.setColor(penColor);
	}

	/**
	 * Get the current font.
	 */
	public static Font getFont() {
		return font;
	}

	/**
	 * Set the font to the default font (sans serif, 16 point).
	 */
	public static void setFont() {
		setFont(DEFAULT_FONT);
	}

	/**
	 * Set the font to the given value.
	 * 
	 * @param f
	 *            the font to make text
	 */
	public static void setFont(Font f) {
		font = f;
	}

	/*************************************************************************
	 * Drawing geometric shapes.
	 *************************************************************************/

	/**
	 * Draw a line from (x0, y0) to (x1, y1).
	 * 
	 * @param x0
	 *            the x-coordinate of the starting point
	 * @param y0
	 *            the y-coordinate of the starting point
	 * @param x1
	 *            the x-coordinate of the destination point
	 * @param y1
	 *            the y-coordinate of the destination point
	 */
	public static void line(double x0, double y0, double x1, double y1) {
		offscreen.draw(new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1),
				scaleY(y1)));
		// draw();
	}

	/**
	 * Draw one pixel at (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the pixel
	 * @param y
	 *            "Es hat " the y-coordinate of the pixel
	 */
	private static void pixel(double x, double y) {
		offscreen.fillRect((int) Math.round(scaleX(x)),
				(int) Math.round(scaleY(y)), 1, 1);
	}

	/**
	 * Draw a point at (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the point
	 * @param y
	 *            the y-coordinate of the point
	 */
	public static void point(double x, double y) {
		double xs = scaleX(x);
		double ys = scaleY(y);
		double r = penRadius;
		// double ws = factorX(2*r);
		// double hs = factorY(2*r);
		// if (ws <= 1 && hs <= 1) pixel(x, y);
		if (r <= 1)
			pixel(x, y);
		else
			offscreen.fill(new Ellipse2D.Double(xs - r / 2, ys - r / 2, r, r));
		// draw();
	}

	/**
	 * Draw a circle of radius r, centered on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the circle
	 * @param y
	 *            the y-coordinate of the center of the circle
	 * @param r
	 *            the radius of the circle
	 * @throws RuntimeException
	 *             if the radius of the circle is negative
	 */
	public static void circle(double x, double y, double r) {
		if (r < 0)
			throw new RuntimeException("circle radius can't be negative");
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * r);
		double hs = factorY(2 * r);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.draw(new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws,
					hs));
		// draw();
	}

	/**
	 * Draw filled circle of radius r, centered on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the circle
	 * @param y
	 *            the y-coordinate of the center of the circle
	 * @param r
	 *            the radius of the circle
	 * @throws RuntimeException
	 *             if the radius of the circle is negative
	 */
	public static void filledCircle(double x, double y, double r) {
		if (r < 0)
			throw new RuntimeException("circle radius can't be negative");
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * r);
		double hs = factorY(2 * r);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.fill(new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws,
					hs));
		// draw();
	}

	/**
	 * Draw an ellipse with given semimajor and semiminor axes, centered on (x,
	 * y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the ellipse
	 * @param y
	 *            the y-coordinate of the center of the ellipse
	 * @param semiMajorAxis
	 *            is the semimajor axis of the ellipse
	 * @param semiMinorAxis
	 *            is the semiminor axis of the ellipse
	 * @throws RuntimeException
	 *             if either of the axes are negative
	 */
	public static void ellipse(double x, double y, double semiMajorAxis,
			double semiMinorAxis) {
		if (semiMajorAxis < 0)
			throw new RuntimeException(
					"ellipse semimajor axis can't be negative");
		if (semiMinorAxis < 0)
			throw new RuntimeException(
					"ellipse semiminor axis can't be negative");
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * semiMajorAxis);
		double hs = factorY(2 * semiMinorAxis);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.draw(new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws,
					hs));
		// draw();
	}

	/**
	 * Draw an ellipse with given semimajor and semiminor axes, centered on (x,
	 * y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the ellipse
	 * @param y
	 *            the y-coordinate of the center of the ellipse
	 * @param semiMajorAxis
	 *            is the semimajor axis of the ellipse
	 * @param semiMinorAxis
	 *            is the semiminor axis of the ellipse
	 * @throws RuntimeException
	 *             if either of the axes are negative
	 */
	public static void filledEllipse(double x, double y, double semiMajorAxis,
			double semiMinorAxis) {
		if (semiMajorAxis < 0)
			throw new RuntimeException(
					"ellipse semimajor axis can't be negative");
		if (semiMinorAxis < 0)
			throw new RuntimeException(
					"ellipse semiminor axis can't be negative");
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * semiMajorAxis);
		double hs = factorY(2 * semiMinorAxis);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.fill(new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws,
					hs));
		// draw();
	}

	/**
	 * Draw an arc of radius r, centered on (x, y), from angle1 to angle2 (in
	 * degrees).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the circle
	 * @param y
	 *            the y-coordinate of the center of the circle
	 * @param r
	 *            the radius of the circle
	 * @param angle1
	 *            the starting angle. 0 would mean an arc beginning at 3
	 *            o'clock.
	 * @param angle2
	 *            the angle at the end of the arc. For example, if you want a 90
	 *            degree arc, then angle2 should be angle1 + 90.
	 * @throws RuntimeException
	 *             if the radius of the circle is negative
	 */
	public static void arc(double x, double y, double r, double angle1,
			double angle2) {
		if (r < 0)
			throw new RuntimeException("arc radius can't be negative");
		while (angle2 < angle1)
			angle2 += 360;
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * r);
		double hs = factorY(2 * r);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.draw(new Arc2D.Double(xs - ws / 2, ys - hs / 2, ws, hs,
					angle1, angle2 - angle1, Arc2D.OPEN));
		draw();
	}

	/**
	 * Draw a square of side length 2r, centered on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the square
	 * @param y
	 *            the y-coordinate of the center of the square
	 * @param r
	 *            radius is half the length of any side of the square
	 * @throws RuntimeException
	 *             if r is negative
	 */
	public static void square(double x, double y, double r) {
		if (r < 0)
			throw new RuntimeException("square side length can't be negative");
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * r);
		double hs = factorY(2 * r);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.draw(new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws,
					hs));
		// draw();
	}

	/**
	 * Draw a filled square of side length 2r, centered on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the square
	 * @param y
	 *            the y-coordinate of the center of the square
	 * @param r
	 *            radius is half the length of any side of the square
	 * @throws RuntimeException
	 *             if r is negative
	 */
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

	/**
	 * Draw a rectangle of given half width and half height, centered on (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the rectangle
	 * @param y
	 *            the y-coordinate of the center of the rectangle
	 * @param halfWidth
	 *            is half the width of the rectangle
	 * @param halfHeight
	 *            is half the height of the rectangle
	 * @throws RuntimeException
	 *             if halfWidth or halfHeight is negative
	 */
	public static void rectangle(double x, double y, double halfWidth,
			double halfHeight) {
		if (halfWidth < 0)
			throw new RuntimeException("half width can't be negative");
		if (halfHeight < 0)
			throw new RuntimeException("half height can't be negative");
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * halfWidth);
		double hs = factorY(2 * halfHeight);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.draw(new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws,
					hs));
		// draw();
	}

	/**
	 * Draw a filled rectangle of given half width and half height, centered on
	 * (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the center of the rectangle
	 * @param y
	 *            the y-coordinate of the center of the rectangle
	 * @param halfWidth
	 *            is half the width of the rectangle
	 * @param halfHeight
	 *            is half the height of the rectangle
	 * @throws RuntimeException
	 *             if halfWidth or halfHeight is negative
	 */
	public static void filledRectangle(double x, double y, double halfWidth,
			double halfHeight) {
		if (halfWidth < 0)
			throw new RuntimeException("half width can't be negative");
		if (halfHeight < 0)
			throw new RuntimeException("half height can't be negative");
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(2 * halfWidth);
		double hs = factorY(2 * halfHeight);
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else
			offscreen.fill(new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws,
					hs));
		// draw();
	}

	/**
	 * Draw a polygon with the given (x[i], y[i]) coordinates.
	 * 
	 * @param x
	 *            an array of all the x-coordindates of the polygon
	 * @param y
	 *            an array of all the y-coordindates of the polygon
	 */
	public static void polygon(double[] x, double[] y) {
		int N = x.length;
		GeneralPath path = new GeneralPath();
		path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));
		for (int i = 0; i < N; i++)
			path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
		path.closePath();
		offscreen.draw(path);
		// draw();
	}

	/**
	 * Draw a filled polygon with the given (x[i], y[i]) coordinates.
	 * 
	 * @param x
	 *            an array of all the x-coordindates of the polygon
	 * @param y
	 *            an array of all the y-coordindates of the polygon
	 */
	public static void filledPolygon(double[] x, double[] y) {
		int N = x.length;
		GeneralPath path = new GeneralPath();
		path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));
		for (int i = 0; i < N; i++)
			path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
		path.closePath();
		offscreen.fill(path);
		// draw();
	}

	/*************************************************************************
	 * Drawing images.
	 *************************************************************************/

	// get an image from the given filename
	private static Image getImage(String filename) {

		// to read from file
		ImageIcon icon = new ImageIcon(filename);

		// try to read from URL
		if ((icon == null)
				|| (icon.getImageLoadStatus() != MediaTracker.COMPLETE)) {
			try {
				URL url = new URL(filename);
				icon = new ImageIcon(url);
			} catch (Exception e) { /* not a url */
			}
		}

		// in case file is inside a .jar
		if ((icon == null)
				|| (icon.getImageLoadStatus() != MediaTracker.COMPLETE)) {
			URL url = StdDraw.class.getResource(filename);
			if (url == null)
				throw new RuntimeException("image " + filename + " not found");
			icon = new ImageIcon(url);
		}

		return icon.getImage();
	}

	/**
	 * Draw picture (gif, jpg, or png) centered on (x, y).
	 * 
	 * @param x
	 *            the center x-coordinate of the image
	 * @param y
	 *            the center y-coordinate of the image
	 * @param s
	 *            the name of the image/picture, e.g., "ball.gif"
	 * @throws RuntimeException
	 *             if the image is corrupt
	 */
	public static void picture(double x, double y, String s) {
		Image image = getImage(s);
		double xs = scaleX(x);
		double ys = scaleY(y);
		int ws = image.getWidth(null);
		int hs = image.getHeight(null);
		if (ws < 0 || hs < 0)
			throw new RuntimeException("image " + s + " is corrupt");

		offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0),
				(int) Math.round(ys - hs / 2.0), null);
		// draw();
	}

	/**
	 * Draw picture (gif, jpg, or png) centered on (x, y), rotated given number
	 * of degrees
	 * 
	 * @param x
	 *            the center x-coordinate of the image
	 * @param y
	 *            the center y-coordinate of the image
	 * @param s
	 *            the name of the image/picture, e.g., "ball.gif"
	 * @param degrees
	 *            is the number of degrees to rotate counterclockwise
	 * @throws RuntimeException
	 *             if the image is corrupt
	 */
	public static void picture(double x, double y, String s, double degrees) {
		Image image = getImage(s);
		double xs = scaleX(x);
		double ys = scaleY(y);
		int ws = image.getWidth(null);
		int hs = image.getHeight(null);
		if (ws < 0 || hs < 0)
			throw new RuntimeException("image " + s + " is corrupt");

		offscreen.rotate(Math.toRadians(-degrees), xs, ys);
		offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0),
				(int) Math.round(ys - hs / 2.0), null);
		offscreen.rotate(Math.toRadians(+degrees), xs, ys);

		// draw();
	}

	/**
	 * Draw picture (gif, jpg, or png) centered on (x, y), rescaled to w-by-h.
	 * 
	 * @param x
	 *            the center x coordinate of the image
	 * @param y
	 *            the center y coordinate of the image
	 * @param s
	 *            the name of the image/picture, e.g., "ball.gif"
	 * @param w
	 *            the width of the image
	 * @param h
	 *            the height of the image
	 * @throws RuntimeException
	 *             if the width height are negative
	 * @throws RuntimeException
	 *             if the image is corrupt
	 */
	public static void picture(double x, double y, String s, double w, double h) {
		Image image = getImage(s);
		double xs = scaleX(x);
		double ys = scaleY(y);
		if (w < 0)
			throw new RuntimeException("width is negative: " + w);
		if (h < 0)
			throw new RuntimeException("height is negative: " + h);
		double ws = factorX(w);
		double hs = factorY(h);
		if (ws < 0 || hs < 0)
			throw new RuntimeException("image " + s + " is corrupt");
		if (ws <= 1 && hs <= 1)
			pixel(x, y);
		else {
			offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0),
					(int) Math.round(ys - hs / 2.0), (int) Math.round(ws),
					(int) Math.round(hs), null);
		}
		// draw();
	}

	/**
	 * Draw picture (gif, jpg, or png) centered on (x, y), rotated given number
	 * of degrees, rescaled to w-by-h.
	 * 
	 * @param x
	 *            the center x-coordinate of the image
	 * @param y
	 *            the center y-coordinate of the image
	 * @param s
	 *            the name of the image/picture, e.g., "ball.gif"
	 * @param w
	 *            the width of the image
	 * @param h
	 *            the height of the image
	 * @param degrees
	 *            is the number of degrees to rotate counterclockwise
	 * @throws RuntimeException
	 *             if the image is corrupt
	 */
	public static void picture(double x, double y, String s, double w,
			double h, double degrees) {
		Image image = getImage(s);
		double xs = scaleX(x);
		double ys = scaleY(y);
		double ws = factorX(w);
		double hs = factorY(h);
		if (ws < 0 || hs < 0)
			throw new RuntimeException("image " + s + " is corrupt");
		if (ws <= 1 && hs <= 1)
			pixel(x, y);

		offscreen.rotate(Math.toRadians(-degrees), xs, ys);
		offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0),
				(int) Math.round(ys - hs / 2.0), (int) Math.round(ws),
				(int) Math.round(hs), null);
		offscreen.rotate(Math.toRadians(+degrees), xs, ys);

		// draw();
	}

	/*************************************************************************
	 * Drawing text.
	 *************************************************************************/

	/**
	 * Write the given text string in the current font, centered on (x, y).
	 * 
	 * @param x
	 *            the center x-coordinate of the text
	 * @param y
	 *            the center y-coordinate of the text
	 * @param s
	 *            the text
	 */
	public static void text(double x, double y, String s) {
		offscreen.setFont(font);
		FontMetrics metrics = offscreen.getFontMetrics();
		double xs = scaleX(x);
		double ys = scaleY(y);
		int ws = metrics.stringWidth(s);
		int hs = metrics.getDescent();
		offscreen.drawString(s, (float) (xs - ws / 2.0), (float) (ys + hs));
		// draw();
	}

	/**
	 * Write the given text string in the current font, centered on (x, y) and
	 * rotated by the specified number of degrees
	 * 
	 * @param x
	 *            the center x-coordinate of the text
	 * @param y
	 *            the center y-coordinate of the text
	 * @param s
	 *            the text
	 * @param degrees
	 *            is the number of degrees to rotate counterclockwise
	 */
	public static void text(double x, double y, String s, double degrees) {
		double xs = scaleX(x);
		double ys = scaleY(y);
		offscreen.rotate(Math.toRadians(-degrees), xs, ys);
		text(x, y, s);
		offscreen.rotate(Math.toRadians(+degrees), xs, ys);
	}

	/**
	 * Write the given text string in the current font, left-aligned at (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the text
	 * @param y
	 *            the y-coordinate of the text
	 * @param s
	 *            the text
	 */
	public static void textLeft(double x, double y, String s) {
		offscreen.setFont(font);
		FontMetrics metrics = offscreen.getFontMetrics();
		double xs = scaleX(x);
		double ys = scaleY(y);
		int hs = metrics.getDescent();
		offscreen.drawString(s, (float) (xs), (float) (ys + hs));
		// draw();
	}

	/**
	 * Write the given text string in the current font, right-aligned at (x, y).
	 * 
	 * @param x
	 *            the x-coordinate of the text
	 * @param y
	 *            the y-coordinate of the text
	 * @param s
	 *            the text
	 */
	public static void textRight(double x, double y, String s) {
		offscreen.setFont(font);
		FontMetrics metrics = offscreen.getFontMetrics();
		double xs = scaleX(x);
		double ys = scaleY(y);
		int ws = metrics.stringWidth(s);
		int hs = metrics.getDescent();
		offscreen.drawString(s, (float) (xs - ws), (float) (ys + hs));
		// draw();
	}

	/**
	 * Display on screen, pause for t milliseconds, and turn on
	 * <em>animation mode</em>: subsequent calls to drawing methods such as
	 * <tt>line()</tt>, <tt>circle()</tt>, and <tt>square()</tt> will not be
	 * displayed on screen until the next call to <tt>show()</tt>. This is
	 * useful for producing animations (clear the screen, draw a bunch of
	 * shapes, display on screen for a fixed amount of time, and repeat). It
	 * also speeds up drawing a huge number of shapes (call <tt>show(0)</tt> to
	 * defer drawing on screen, draw the shapes, and call <tt>show(0)</tt> to
	 * display them all on screen at once).
	 * 
	 * @param t
	 *            number of milliseconds
	 */
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
					new int[] { 0, 1, 2 });
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
					new int[] { 0, 1, 2 });
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
					new int[] { 0, 1, 2 });
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
		if (e.getActionCommand().equals("Starten")) {
			Main.setSpiel_start(true);
			Init.init();
		}
		// wenn der button "Beenden" aufgerufen wird, beende das Programm
		if (e.getActionCommand().equals("Beenden")) {
			System.exit(0);
		}

		if (e.getActionCommand().equals("500x500")) {
			width = 500;
			height = 500;
			init();
		}
		if (e.getActionCommand().equals("Men�")) {
			// menu.add(jpanel);
			// Init.init();
		}
		if (e.getActionCommand().equals("Zweispieler")) {
			Main.setSpiel_start(true);
			Main.setMehrspieler(true);
			Init.init();
			jpanel.setVisible(false);
			jpanel.removeKeyListener(std);
			frame.enableInputMethods(true);

			frame.toFront();
			frame.requestFocus();
			// System.exit(0); //Menu-Feld wird geschlossen, nachdem das Spiel
			// geoeffnet wurde

		}

		if (e.getActionCommand().equals("Einzelspieler")) {
			Main.setSpiel_start(true);
			Main.setMehrspieler(false);
			Init.init();
			jpanel.setVisible(false);
			jpanel.removeKeyListener(std);
			frame.enableInputMethods(true);

			frame.toFront();
			frame.requestFocus();
			// System.exit(0); //Menu-Feld wird geschlossen, nachdem das Spiel
			// geoeffnet wurde

		}
		
		if (e.getActionCommand().equals("Editor")){
			Editor ed = new Editor();
			ed.start();
			
		}

		if (e.getActionCommand().equals("Netzwerk-Server starten")) {
			try {
				Main.starteNetzwerk();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out
					.println("Befehl zum starten des Netzwerkspiels wurde erkannt!");
		}

		if (e.getActionCommand().equals("Netzwerk-Spiel beitreten")) {
			Main.startClient();

		}

		if (e.getActionCommand().equals("Tutorial")) {
			JFrame tutorial = new JFrame("Tutorial");
			tutorial.setSize(300, 600);
			tutorial.setVisible(true);

			tutorial.add(new JLabel(
					"<html><center><h1 font-size=30>Tutorial</h1><h2>ZIEL DES SPIELS</h2><p>Ziel des Spiel ist es, den Ausgang diagonal gegen�ber des<br>Startpunktes zu erreichen, indem die zerst�rbaren Mauern<br>mithilfe von Bomben zerst�rt werden und somit ein Weg<br>zum Ausgang freigemacht wird.<p/><br><h1>STEUERUNG</h1><p>&#8592; (Pfeil links):<img src='http://img849.imageshack.us/img849/8565/blackbmleft.gif'> nach links laufen<br>&#8594; (Pfeil rechts) <img src='http://img41.imageshack.us/img41/5863/blackbmright.gif'> nach rechts laufen<br>&#8593; (Pfeil oben): <img src='http://img837.imageshack.us/img837/8400/blackbmup.gif'> nach oben laufen<br>&#8595; (Pfeil unten) <img src='http://img535.imageshack.us/img535/8540/blackbmdown.gif'> nach unten laufen<br>Leertaste: <img width=50% height=50% src='http://img526.imageshack.us/img526/6987/boombe.png'> Bombe legen</p></center></html>"));
			tutorial.setFont(new Font("Arial", Font.PLAIN, 30));
			tutorial.setVisible(true);

		}

		if (e.getActionCommand().equals("Map laden")
				|| e.getActionCommand().equals("Level laden")) {
			FileDialog chooser = new FileDialog(StdDraw.frame,
					"W�hle eine Datei aus", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if (filename != null) {
				MapLoader map = new MapLoader();
				map.load(chooser.getDirectory() + File.separator + filename);
				System.out.println(chooser.getDirectory() + File.separator
						+ filename);

			}
		}
		if (e.getActionCommand().equals("Map speichern")
				|| e.getActionCommand().equals("Level speichern")) {
			FileDialog chooser = new FileDialog(StdDraw.frame,
					"Level speichern", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if (filename != null) {
				MapSaver map = new MapSaver();
				map.save(chooser.getDirectory() + File.separator + filename);
				System.out.println(chooser.getDirectory() + File.separator
						+ filename);

			}
		}
		if (e.getActionCommand().equals("Spieler")) {
			System.out.println("k4k");
		}
		if (e.getActionCommand().equals("Spiel beenden")) {
			Main.runserver = false;

			System.exit(0);
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
		synchronized (mouseLock) {
			mouseX = StdDraw.userX(e.getX());
			mouseY = StdDraw.userY(e.getY());
			mousePressed = true;
		}
	}

	/**
	 * This method cannot be called directly.
	 */
	public void mouseReleased(MouseEvent e) {
		synchronized (mouseLock) {
			mousePressed = false;
		}
	}

	/**
	 * This method cannot be called directly.
	 */
	public void mouseDragged(MouseEvent e) {
		synchronized (mouseLock) {
			mouseX = StdDraw.userX(e.getX());
			mouseY = StdDraw.userY(e.getY());
		}
	}

	/**
	 * This method cannot be called directly.
	 */
	public void mouseMoved(MouseEvent e) {
		synchronized (mouseLock) {
			mouseX = StdDraw.userX(e.getX());
			mouseY = StdDraw.userY(e.getY());
		}
	}

	/*************************************************************************
	 * Keyboard interactions.
	 *************************************************************************/

	/**
	 * Has the user typed a key?
	 * 
	 * @return true if the user has typed a key, false otherwise
	 */
	public static boolean hasNextKeyTyped() {
		synchronized (keyLock) {
			return !keysTyped.isEmpty();
		}
	}

	/**
	 * What is the next key that was typed by the user? This method returns a
	 * Unicode character corresponding to the key typed (such as 'a' or 'A'). It
	 * cannot identify action keys (such as F1 and arrow keys) or modifier keys
	 * (such as control).
	 * 
	 * @return the next Unicode key typed
	 */
	public static char nextKeyTyped() {
		synchronized (keyLock) {
			return keysTyped.removeLast();
		}
	}

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
		synchronized (keyLock) {
			keysTyped.addFirst(e.getKeyChar());
		}
	}

	/**
	 * This method cannot be called directly.
	 */
	public void keyPressed(KeyEvent e) {
		// System.out.println("keyPressed");
		if (Main.NetzwerkStatus() == false) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Main.setRight(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Main.setLeft(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				Main.setUp(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				Main.setDown(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				Main.setSpace(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				Main.setSpiel_start(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				Main.setRight2(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				Main.setLeft2(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				Main.setUp2(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				Main.setDown2(true);
			}
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				Main.setSpace2(true);
			}
		} else {
			if (Main.netzwerk_localPlayer_status() == true) {

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					try {
						//Client.establishConnection();
						Client.setRight(true);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					try {
						//Client.establishConnection();
						Client.setLeft(true);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					try {
						//Client.establishConnection();
						Client.setUp(true);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					try {
						//Client.establishConnection();
						Client.setDown(true);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					try {
						//Client.establishConnection();
						Client.setSpace(true);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(Main.is_client == true){
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					try {
						Client.setRight(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					try {
						Client.setLeft(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					try {
						Client.setUp(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					try {
						Client.setDown(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					try {
						Client.setSpace(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
			}
		}
		// keysDown.		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	}

	/**
	 * This method cannot be called directly.
	 */
	public void keyReleased(KeyEvent e) {
		// System.out.println("keyReleased");
		if (Main.NetzwerkStatus() == false) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Main.setRight(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Main.setLeft(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				Main.setUp(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				Main.setDown(false);
			}

			if (e.getKeyCode() == KeyEvent.VK_D) {
				Main.setRight2(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				Main.setLeft2(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				Main.setUp2(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				Main.setDown2(false);
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				jpanel.setVisible(true);
				jpanel.requestFocus();
				Main.setMenu_start(false);
			}

			keysDown.remove(e.getKeyCode());
			
			
		} else {
			if (Main.netzwerk_localPlayer_status() == true) {

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					try {
						//Client.establishConnection();
						Client.setRight(false);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					try {
						//Client.establishConnection();
						Client.setLeft(false);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					try {
						//Client.establishConnection();
						Client.setUp(false);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					try {
						//Client.establishConnection();
						Client.setDown(false);
						//Main.socketcli.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("Client Eingabe erkannt!");

			}
			
			if(Main.is_client == true){
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					try {
						Client.setRight(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					try {
						Client.setLeft(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					try {
						Client.setUp(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					try {
						Client.setDown(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		}
	}

}
