package Q12.client;

import Q12.Carnet;
import Q12.CarnetService;

public class Init {

    public static void main(String[] args) throws Exception {
        Carnet carnet = new Carnet();
        carnet.addContact("toto", "1234567890");
        carnet.addContact("titi", "0987654321");
        carnet.addContact("tutu", "6789054321");

        System.out.println("Sauvegarde du carnet...");
        System.out.println(carnet);
        CarnetService service = new CarnetService();
        service.saveCarnet(carnet);

        // plus tard...
        Carnet loadedCarnet = service.loadCarnet();
        System.out.println(loadedCarnet);
    }
}
