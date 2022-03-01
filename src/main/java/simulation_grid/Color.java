package simulation_grid;

public record Color(int red, int green, int blue, int alpha) {
    public Color(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public Color(int red, int green, int blue, int alpha) {
        this.red = red % 256;
        this.green = green % 256;
        this.blue = blue % 256;
        this.alpha = alpha % 256;
    }
}
