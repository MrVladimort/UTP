/**
 *
 *  @author Hravchenko Vladyslav S15567
 *
 */

package zad1;


public class Purchase {
    String id, customerName, towar;
    double cena, ilosc;

    Purchase(String id, String customerName, String towar, double cena, double ilosc){
        this.id = id;
        this.customerName = customerName;
        this.towar = towar;
        this.cena = cena;
        this.ilosc = ilosc;
    }
}
