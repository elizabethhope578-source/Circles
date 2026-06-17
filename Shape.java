/**
 * Abstract base class for all geometric shapes.
 * Provides common attributes (color, filled) and declares
 * the abstract contract that every concrete shape must fulfil.
 */
public abstract class Shape {

    protected String  color  = "white";
    protected boolean filled;

    /** No-arg constructor. Uses defaults: white, not filled. */
    public Shape() { }

    /**
     * Creates a Shape with the given colour and fill state.
     *
     * @param color  colour name (e.g. "red")
     * @param filled true if the shape is filled
     */
    public Shape(String color, boolean filled) {
        this.color  = color;
        this.filled = filled;
    }

    // ── Abstract methods (subclasses MUST override) ───────────────────────

    /**
     * Calculates the area of this shape.
     *
     * @return area in square units
     */
    public abstract double getArea();

    /**
     * Calculates the perimeter of this shape.
     *
     * @return perimeter in units
     */
    public abstract double getPerimeter();

    /**
     * Scales all linear dimensions of this shape by the given factor.
     *
     * @param factor the scale factor (must be > 0)
     * @throws InvalidShapeException if factor is non-positive
     */
    public abstract void resize(double factor);

    // ── Getters / setters ─────────────────────────────────────────────────

    /** @return the colour of this shape */
    public String getColor()  { return color; }

    /** @return true if the shape is filled */
    public boolean isFilled() { return filled; }

    /** @param color new colour */
    public void setColor(String color) { this.color = color; }

    /** @param filled new fill state */
    public void setFilled(boolean filled) { this.filled = filled; }

    // ── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format("Shape[Color=%s, Filled=%b, Area=%.2f, Perimeter=%.2f]",
                color, filled, getArea(), getPerimeter());
    }
}
