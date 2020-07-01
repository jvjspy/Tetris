package config;

import java.awt.Color;

public class Config {
	public static final int WINDOW_WIDTH=600;
	public static final int WINDOW_HEIGHT=650;
	public static final int ROWS=15;
	public static final int COLUMNS=10;
	public static final int BRICK_SIZE=40;
	public static final boolean[][][] BRICK_SHAPES= {{{true, true, true, true}},{{true, true}, {true, true}},{{false, true, false}, {true, true, true}},{{true, false}, {true, true}, {false, true}},{{true, false}, {true, false}, {true, true}}};
	public static final Color[] COLORS= {Color.RED,Color.BLUE,Color.MAGENTA,Color.CYAN,Color.YELLOW,Color.ORANGE,Color.GREEN};
}
