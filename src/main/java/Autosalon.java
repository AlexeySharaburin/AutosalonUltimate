import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Autosalon {

    static int maxQuantity = 3;
    static List<Auto> autos = new ArrayList<>(maxQuantity);
    Customer customer;


    public synchronized Auto comeCustomer() {

        String nameCustomer = Thread.currentThread().getName();
        customer = new Customer(nameCustomer);


        Auto auto = null;

        try {
            System.out.printf("%s зашёл в автосалон\n", nameCustomer);

            while (getAuto().size() == 0) {
                System.out.println("Нет автомобилей в наличии");
                wait();
            }
            auto = getAuto().remove(0);

            System.out.printf("%s уехал домой на %s\n", nameCustomer, auto.getNameAuto());

        } catch (
                Exception e) {
            e.printStackTrace();
        }

        return auto;

    }


    public synchronized void sellAuto() {

        String autosalon = Thread.currentThread().getName();
        int i = 0;

        try {
            System.out.printf("%s открыл продажи автомобилей!\n", autosalon);

            while (maxQuantity != 0) {
                i++;
                Thread.sleep(5000);
                Auto auto = new Auto("Авто " + i);
                getAuto().add(auto);
                System.out.printf("%s подготовил %s к продаже\n", autosalon, auto.getNameAuto());
                maxQuantity--;
                notify();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Auto> getAuto() {
        return autos;
    }

}
