package makosear.datingsim.GameStructure;

public enum CharacterPosition {
    LEFT(200, 150, 200, 200),
    CENTER(300, 150, 200, 200),
    RIGHT(400, 150, 200, 200);

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    CharacterPosition(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width;}
    public int getHeight() { return height; }

}
