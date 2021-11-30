public class Priority {
    int priority;
    public Priority(int p) {
        if (p > 10) {
            this.priority = 10;
        }
        else if (p < 1) {
            this.priority = 0;
        }
        else {
            this.priority = p;
        }
    }

    public int getPriority() {
        return priority;
    }
}
