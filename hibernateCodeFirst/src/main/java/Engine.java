import entities.*;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {

    }
}
