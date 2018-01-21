package zad3;

import java.beans.*;

public class Account {
    private double balance;
    private int number;
    private static int count = 0;

    private PropertyChangeSupport pC = new PropertyChangeSupport(this);
    private VetoableChangeSupport v = new VetoableChangeSupport(this);

    public synchronized void addVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        v.addVetoableChangeListener(vetoableChangeListener);
    }

    Account() {
        this.balance = 0;
        this.number = ++count;
    }

    Account(double balance) {
        this.balance = balance;
        this.number = ++count;
    }

    public void deposit(double money) throws PropertyVetoException {
        setBalance(money);
    }

    public void withdraw(double money) throws PropertyVetoException {
        setBalance(balance - money);
    }

    public void transfer(Account account, double money) throws PropertyVetoException {
        setTransfer(account, money);
    }

    private synchronized void setTransfer(Account account, double balance) throws PropertyVetoException {
        setBalance(this.balance - balance);
        account.setBalance(account.balance + balance);
    }

    private synchronized void setBalance(double balance) throws PropertyVetoException {
        int oldValue = (int) this.balance;
        v.fireVetoableChange(number + ": ", new Integer(oldValue), new Integer((int) balance));
        this.balance = balance;
        pC.firePropertyChange(number + ": ", new Integer(oldValue), new Integer((int) balance));
    }

    public String toString() {
        return "Acc " + number + ": " + balance;
    }
}
