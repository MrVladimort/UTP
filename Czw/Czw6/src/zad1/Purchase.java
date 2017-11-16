/**
 *
 *  @author Hravchenko Vladyslav S15567
 *
 */

package zad1;


import java.util.Comparator;

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

    @Override
    public String toString() {
        return this.id+";"+this.customerName+";"+this.towar+";"+this.cena+";"+this.ilosc;
    }

    static Comparator<Purchase> klientComparator = (arg1, arg2) -> {
        if (arg1.customerName.equals(arg2.customerName))
            return arg1.id.compareTo(arg2.id);
        else
            return arg1.customerName.compareTo(arg2.customerName);
    };

    static Comparator<Purchase> kosztComparator = (arg1, arg2) -> {
        double koszt1 = arg1.cena*arg1.ilosc;
        double koszt2 = arg2.cena*arg2.ilosc;

        if (koszt1 == koszt2)
            return  arg1.id.compareToIgnoreCase(arg2.id);
        else
            return  Double.compare(koszt1, koszt2) * -1;
    };
}
