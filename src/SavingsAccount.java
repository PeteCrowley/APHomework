public class SavingsAccount extends Account{
    private double yearlyInterestRate;

    public SavingsAccount(int idNum, double startBalance, double interestRate){
        super(idNum, startBalance);
        yearlyInterestRate = interestRate;
    }

    public double monthlyInterest() {
        return currentBalance() * (yearlyInterestRate / 12);
    }
}
