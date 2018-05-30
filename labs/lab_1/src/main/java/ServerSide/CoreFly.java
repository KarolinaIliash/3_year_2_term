package ServerSide;

public class CoreFly {
    // init info
    double v0;
    double angle;
    double startX;
    double startY;
    double gravity;
    // calc info
    double x;
    double y;

    CoreFly(double v0, double angle, double startX, double startY, double gravity)
    {
        this.v0 = v0;
        this.angle = angle;
        this.startX = startX;
        this.startY = startY;
        this.gravity = gravity;
    }

    void calcNext(double t) {
        x = v0 * t * Math.cos(angle) + startX;
        y = Math.max(v0 * t * Math.sin(angle) - gravity * t * t / 2. + startY, 0.);
    }
}
