public class CheckingAccount extends Account {
    private double checkCharge;
    public CheckingAccount(int idNum, double startBal, double chkCharge) {
        super(idNum, startBal);
        checkCharge = chkCharge;
    }
    public void clearCheck(double amount) {
        decreaseBalance(amount + checkCharge);
    }
    public double monthlyInterest() {
        return 0;
    }
}
