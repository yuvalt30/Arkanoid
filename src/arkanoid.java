// ID 311127120

/**
 * contains main method to run Game.
 */
public class Ass6Game {
    /**
     * main.
     * @param args unused
     */
    public static  void main(String[] args) {
        GameFlow gf = new GameFlow();
        gf.runLevels(gf.argsToLevels(args));
    }
}
