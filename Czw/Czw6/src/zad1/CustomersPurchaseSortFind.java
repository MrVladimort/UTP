/**
 *
 *  @author Hravchenko Vladyslav S15567
 *
 */

package zad1;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomersPurchaseSortFind {
    ArrayList<Purchase> purchases;

    public void readFile(String fname) {
        try {
            Scanner sc = new Scanner(new FileReader(fname));
            while (sc.hasNextLine()){
                String [] purchase = sc.nextLine().split(";");
                this.purchases.add(new Purchase(purchase[0], purchase[1], purchase[2],
                        Double.parseDouble(purchase[3]), Double.parseDouble(purchase[4])));
            }
            System.out.println(this.purchases);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String nazwiska) {

    }

    public void showPurchaseFor(String id) {

    }
}
