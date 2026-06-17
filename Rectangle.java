/**
 * A rectangle defined by width and height.
 * Extends the abstract Shape class.
 */
public class Rectangle extends Shape {

    private double width;
    private double height;

    /**
     * Creates a Rectangle with default colour (white, not filled).
     *
     * @param width  the rectangle width (must be > 0)
     * @param height the rectangle height (must be > 0)
     * @throws InvalidShapeException if either dimension is non-positive
     */
    public Rectangle(double width, double height) {
        super();
        validateDimensions(width, height);
        this.width  = width;
        this.height = height;
    }

    /**
     * Creates a Rectangle with explicit colour and fill state.
     *
     * @param width  the rectangle width (must be > 0)
     * @param height the rectangle height (must be > 0)
     * @param color  the colour
     * @param filled true if filled
     * @throws InvalidShapeException if either dimension is non-positive
     */
    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        validateDimensions(width, height);
        this.width  = width;
        this.height = height;
    }

    // ── Validation ────────────────────────────────────────────────────────

    private static void validateDimensions(double w, double h) {
        if (w <= 0) throw new InvalidShapeException("Width must be positive, got: " + w);
        if (h <= 0) throw new InvalidShapeException("Height must be positive, got: " + h);
    }

    // ── Getters / setters ─────────────────────────────────────────────────

    /** @return width */
    public double getWidth()  { return width; }

    /** @return height */
    public double getHeight() { return height; }

    /** @param width new width (> 0) */
    public void setWidth(double width) {
        if (width <= 0) throw new InvalidShapeException("Width must be positive.");
        this.width = width;
    }

    /** @param height new height (> 0) */
    public void setHeight(double height) {
        if (height <= 0) throw new InvalidShapeException("Height must be positive.");
        this.height = height;
    }

    // ── Shape contract ────────────────────────────────────────────────────

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    /**
     * Scales both width and height by the given factor.
     *
     * @param factor must be > 0
     * @throws InvalidShapeException if factor is non-positive
     */
    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException(
                    "Resize factor must be positive, got: " + factor);
        }
        width  *= factor;
        height *= factor;
    }

    @Override
    public String toString() {
        return String.format(
                "Rectangle[Color=%s, Filled=%b, Width=%.2f, Height=%.2f, Area=%.2f, Perimeter=%.2f]",
                color, filled, width, height, getArea(), getPerimeter());
    }
}
