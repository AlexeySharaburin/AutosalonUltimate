import java.util.ArrayList;
import java.util.List;

public class Main {

    static int customers = 3;

    public static void main(String[] args) throws InterruptedException {

        final Autosalon autosalon = new Autosalon();

        List<Thread> threadsListCustomers = new ArrayList<>();

        new Thread(null, autosalon::sellAuto, "Автосалон").start();

        for (int i = 1; i < (customers + 1); i++) {
            Thread.sleep(3000);
            Thread customer = new Thread(null, autosalon::comeCustomer, "Покупатель " + i);
            customer.start();
            threadsListCustomers.add(customer);
        }


        for (Thread thread : threadsListCustomers) {
            thread.join();
        }

        System.out.println("Автомобили закончились и клиенты разъехались");
    }
}
