
public class LizardApplication {
    public static void main(String[] args) {
        try {
            new LizardMenu(args).start();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
