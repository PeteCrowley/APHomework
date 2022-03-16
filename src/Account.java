import java.text.DecimalFormat;

public abstract class Account {
    private int idNumber; // identity number for this account
    private double balance; // current balance for this account
    /** precondition: startBal >= 0.0
     * postcondition: An Account with identity number idNum and current balance
     * of startBal has been created*/
    public Account(int idNum, double startBal) {
        idNumber = idNum;
        balance = startBal;
    }
    /** @return the identity number for this account */
    public int idNumber()           {return idNumber;}
    /** @return the current balance for this account */
    public double currentBalance()  {return balance;}
    /** precondition: amount >= 0.0
     * postcondition: the current balance of this account has been increased
     * by amount */
    public void deposit(double amount) { balance += amount; }
    /** precondition: 0.0 <= amount <= balance
     * postcondition: the current balance of this account has been decreased
     * by amount and return true; return false if amount > balance */
    public boolean decreaseBalance(double amount) {
        if(balance > amount)
            return false;
        balance -= amount;
        return true;
    }
    /** @return the monthly interest due for this account */
    public abstract double monthlyInterest();

    public String toString(){
        DecimalFormat df = new DecimalFormat("$.00");
        return getClass().toString().substring(6) + " " + idNumber() + ": " + df.format(currentBalance());
    }
}