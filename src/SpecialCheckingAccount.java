public class SpecialCheckingAccount extends CheckingAccount{
    private double minBalance;
    private double yearlyInterestRate;

    public SpecialCheckingAccount(int idNum, double startBal, double chkCharge, double interestRate, double minBal) {
        super(idNum, startBal, chkCharge);
        yearlyInterestRate = interestRate;
        minBalance = minBal;
    }

    public void clearCheck(double amount) {

        if(currentBalance() <= minBalance)
            super.clearCheck(amount);
        decreaseBalance(amount);

    }

    public double monthlyInterest(){
        if(currentBalance() > minBalance)
            return currentBalance() * (yearlyInterestRate / 12);
        return super.monthlyInterest();
    }
}
