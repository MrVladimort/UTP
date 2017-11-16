/**
 * @author Hravchenko Vladyslav S15567
 */

package zad1;

import java.io.*;
import java.util.*;

public class CustomersPurchaseSortFind {
    List<Purchase> purchases = new ArrayList<>();

    void readFile(String fname) {
        try {
            Scanner sc = new Scanner(new FileReader(fname));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] purchase = line.split( ";");
                if (purchase.length == 5)
                    if (purchase[0].matches("c\\d{5}+"))
                        try {
                            this.purchases.add(new Purchase(purchase[0], purchase[1], purchase[2],
                                    Double.parseDouble(purchase[3]), Double.parseDouble(purchase[4])));
                        } catch (NumberFormatException ignored){}
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void showSortedBy(String command) {
        Purchase[] sortedPurchases = this.purchases.toArray(new Purchase[this.purchases.size()]);
        System.out.println(command);
        switch (command) {
            case "Nazwiska":
                Arrays.sort(sortedPurchases, Purchase.klientComparator);
                for (Purchase purchase : sortedPurchases)
                    System.out.println(purchase);
                break;
            case "Koszty":
                Arrays.sort(sortedPurchases, Purchase.kosztComparator);
                for (Purchase purchase : sortedPurchases)
                    System.out.println(purchase + " (koszt: " + purchase.cena * purchase.ilosc + ")");
                break;
        }
        System.out.println();
    }

    void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        for (Purchase purchase : this.purchases)
            if (purchase.id.equals(id))
                System.out.println(purchase);
        System.out.println();
    }
}
