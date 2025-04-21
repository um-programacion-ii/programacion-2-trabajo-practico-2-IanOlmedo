package consola;
import java.util.Scanner;

public class Consola {
    private static Scanner scanner = new Scanner(System.in);

    public static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(scanner.nextLine());
    }
}
