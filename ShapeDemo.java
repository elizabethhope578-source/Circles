/**
 * ShapeDemo – driver class for Question 2.
 *
 * Demonstrates:
 *  - Creating Circle, Rectangle and Triangle objects
 *  - InvalidShapeException caught in a try-catch (invalid triangle)
 *  - printAreas() showing dynamic binding through a Shape[] array
 *  - largest() returning the shape with the greatest area
 *  - resize() in action
 */
public class ShapeDemo {

    // ── (d) printAreas ────────────────────────────────────────────────────

    /**
     * Prints the area of each shape via a superclass reference.
     * The JVM dispatches getArea() to the actual subclass at runtime
     * — this is dynamic binding / runtime polymorphism.
     *
     * @param shapes array of Shape references
     */
    public static void printAreas(Shape[] shapes) {
        System.out.println("\n--- printAreas (dynamic binding) ---");
        for (Shape s : shapes) {
            // getArea() is resolved at runtime to Circle/Rectangle/Triangle's version
            System.out.printf("  %-65s  Area = %8.2f%n", s, s.getArea());
        }
    }

    /**
     * Returns the shape with the largest area by comparing getArea() values.
     *
     * @param shapes array of Shape references (must not be empty)
     * @return the Shape with the greatest area
     */
    public static Shape largest(Shape[] shapes) {
        Shape champion = shapes[0];
        for (int i = 1; i < shapes.length; i++) {
            if (shapes[i].getArea() > champion.getArea()) {
                champion = shapes[i];
            }
        }
        return champion;
    }

    // ── main ──────────────────────────────────────────────────────────────

    public static void main(String[] args) {

        // ── 1. Create valid shapes ─────────────────────────────────────────
        System.out.println("=== Creating Shapes ===");

        Circle    c1 = new Circle(5.0, "red", true);
        Rectangle r1 = new Rectangle(4.0, 6.0, "blue", false);
        Triangle  t1 = new Triangle(3.0, 4.0, 5.0, "green", true);   // right triangle

        System.out.println("Created: " + c1);
        System.out.println("Created: " + r1);
        System.out.println("Created: " + t1);

        // ── 2. InvalidShapeException: bad triangle (triangle inequality) ───
        System.out.println("\n=== Testing InvalidShapeException ===");
        try {
            Triangle bad = new Triangle(1.0, 2.0, 10.0);   // 1 + 2 < 10 → invalid
            System.out.println("Should not reach here: " + bad);
        } catch (InvalidShapeException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("  (Exception type: " + e.getClass().getSimpleName()
                    + " — unchecked, extends RuntimeException)");
        }

        // Also demonstrate negative radius rejection
        try {
            Circle bad = new Circle(-3.0);
            System.out.println("Should not reach here: " + bad);
        } catch (InvalidShapeException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        // ── 3. printAreas via Shape[] (dynamic binding) ────────────────────
        Shape[] shapes = { c1, r1, t1 };
        printAreas(shapes);

        // ── 4. Find and print the largest ─────────────────────────────────
        Shape big = largest(shapes);
        System.out.println("\n--- Largest shape ---");
        System.out.println("  " + big);

        // ── 5. Demonstrate resize ─────────────────────────────────────────
        System.out.println("\n=== Resize Demo ===");
        System.out.printf("Circle before resize: radius implicit, Area=%.2f%n", c1.getArea());
        c1.resize(2.0);
        System.out.printf("Circle after resize(2): Area=%.2f  (quadruples because A=πr²)%n",
                c1.getArea());

        // Test invalid resize factor
        System.out.println("\n--- Resize with invalid factor ---");
        try {
            r1.resize(-1.5);
        } catch (InvalidShapeException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        // ── 6. Refresh printAreas after resize ────────────────────────────
        System.out.println("\n=== Updated areas after resize ===");
        printAreas(shapes);
        System.out.println("\nLargest after resize: " + largest(shapes));
    }
}
