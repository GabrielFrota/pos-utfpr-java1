package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Leitura {
    private final String invalidOP = "Opção inválida";
    private final String invalid = "Inserção inválida";
    private final Scanner sc = new Scanner(System.in);

    public String entDados(String rotulo) {
        for (;;) {
            System.out.print(rotulo);
            var in = sc.nextLine();
            if (in.length() > 0)
                return in;
            else
                System.out.println(invalid);
        }
    }

    public int readOpt(int lower, int upper) {
        for (;;) {
            try {
                var op = sc.nextInt();
                while (op < lower || op > upper) {
                    System.out.println(invalidOP);
                    op = sc.nextInt();
                }
                sc.nextLine();
                return op;
            } catch (InputMismatchException ime) {
                sc.nextLine();
                System.out.println(invalidOP);
            }
        }
    }

    public String readOpt(Set<String> possibleVals) {
        var op = sc.nextLine();
        while (!possibleVals.contains(op)) {
            System.out.println(invalidOP);
            op = sc.nextLine();
        }
        return op;
    }

    public float readFloat(String label) {
        System.out.print(label);
        for (;;) {
            try {
                var op = sc.nextFloat();
                sc.nextLine();
                return op;
            } catch (InputMismatchException ime) {
                sc.nextLine();
                System.out.println(invalid);
                System.out.print(label);
            }
        }
    }

    public int readInt(String label) {
        System.out.print(label);
        for (;;) {
            try {
                var op = sc.nextInt();
                sc.nextLine();
                return op;
            } catch (InputMismatchException ime) {
                sc.nextLine();
                System.out.println(invalid);
                System.out.print(label);
            }
        }
    }
}