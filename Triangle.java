/**
 * A triangle defined by its three side lengths.
 * Validates positivity AND the triangle inequality.
 * Extends the abstract Shape class.
 */
public class Triangle extends Shape {

    private double sideA;
    private double sideB;
    private double sideC;

    /**
     * Creates a Triangle with default colour (white, not filled).
     *
     * @param a side a (must be > 0)
     * @param b side b (must be > 0)
     * @param c side c (must be > 0)
     * @throws InvalidShapeException if any side is non-positive or the
     *                               triangle inequality is violated
     */
    public Triangle(double a, double b, double c) {
        super();
        validateSides(a, b, c);
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
    }

    /**
     * Creates a Triangle with explicit colour and fill state.
     *
     * @param a      side a
     * @param b      side b
     * @param c      side c
     * @param color  the colour
     * @param filled true if filled
     * @throws InvalidShapeException if sides are invalid
     */
    public Triangle(double a, double b, double c, String color, boolean filled) {
        super(color, filled);
        validateSides(a, b, c);
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
    }

    // ── Validation ────────────────────────────────────────────────────────

    private static void validateSides(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new InvalidShapeException(
                    "All triangle sides must be positive. Got: " + a + ", " + b + ", " + c);
        }
        // Triangle inequality: each side must be less than the sum of the other two
        if (a >= b + c || b >= a + c || c >= a + b) {
            throw new InvalidShapeException(
                    "Triangle inequality violated for sides: " + a + ", " + b + ", " + c);
        }
    }

    // ── Getters ───────────────────────────────────────────────────────────

    /** @return side a */
    public double getSideA() { return sideA; }

    /** @return side b */
    public double getSideB() { return sideB; }

    /** @return side c */
    public double getSideC() { return sideC; }

    // ── Shape contract ────────────────────────────────────────────────────

    /** Uses Heron's formula. */
    @Override
    public double getArea() {
        double s = (sideA + sideB + sideC) / 2.0;   // semi-perimeter
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    /**
     * Scales all three sides by the given factor.
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
        sideA *= factor;
        sideB *= factor;
        sideC *= factor;
    }

    @Override
    public String toString() {
        return String.format(
                "Triangle[Color=%s, Filled=%b, Sides=(%.2f,%.2f,%.2f), Area=%.2f, Perimeter=%.2f]",
                color, filled, sideA, sideB, sideC, getArea(), getPerimeter());
    }
}
