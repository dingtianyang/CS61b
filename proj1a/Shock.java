public class Shock {
    public static int bang;
    public static Shock baby;

    public Shock() {
        bang = 100;
    }

    public Shock(int num) {
        bang = num;
        baby = starter();
        bang += num;
    }

    public static Shock starter() {
        Shock gear = new Shock();
        return gear;
    }

    public static void shrink(Shock statik) {
        bang -= 1;
    }

    public static void main(String[] args) {
        Shock gear = new Shock(200);
        System.out.println(bang);
        shrink(gear);
        shrink(starter());
        System.out.println(bang);
    }
}