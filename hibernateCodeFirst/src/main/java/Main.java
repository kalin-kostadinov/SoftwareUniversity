import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static final String GRINGOTTS_PU = "gringotts";
    public static final String SALES_PU = "sales";
    public static final String UNIVERSITY_PU = "university";
    public static final String HOSPITAL_PU = "hospital";
    public static final String BILLS_PAYMENT_PU = "bills_payment";

    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        printOut();
    }

    private static void printOut() {
        System.out.println("Greetings fellow coder!");
        System.out.println("Before you start: ");
        System.out.println("\tDrop all your databases and chance database name in your persistence file");
        System.out.println("\tAnd change you password and username");
        System.out.println();
        System.out.println("For Gringotts database press 1");
        System.out.println("For Sales database press 2");
        System.out.println("For University system press 3");
        System.out.println("For Hospital database press 4");
        System.out.println("For Bill Payment system press 5");
        String input = sc.nextLine();
        switch (input) {
            case "1":
                entityManagerFactory = Persistence.createEntityManagerFactory(GRINGOTTS_PU);
                entityManagerFactory.createEntityManager();
                break;
            case "2":
                entityManagerFactory = Persistence.createEntityManagerFactory(SALES_PU);
                entityManagerFactory.createEntityManager();
                break;
            case "3":
                entityManagerFactory = Persistence.createEntityManagerFactory(UNIVERSITY_PU);
                entityManagerFactory.createEntityManager();
                break;
            case "4":
                entityManagerFactory = Persistence.createEntityManagerFactory(HOSPITAL_PU);
                entityManagerFactory.createEntityManager();
                break;
            case "5":
                entityManagerFactory = Persistence.createEntityManagerFactory(BILLS_PAYMENT_PU);
                entityManagerFactory.createEntityManager();
                break;

        }
    }
}

