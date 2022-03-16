import java.util.ArrayList;

public class Bank
{
    private ArrayList<Account> accounts; // all accounts in this bank accounts has no null entries

    public Bank() {
        accounts = new ArrayList<>();
    }

    // postcondition: for each account in this bank, the monthly interest due
    // has been deposited into that account
    public void postMonthlyInterest() {
        for(int i = 0; i < accounts.size(); i++){
            accounts.get(i).deposit(accounts.get(i).monthlyInterest());
        }
    }

    public void showAllAccounts(){
        for(Account acc : accounts){
            System.out.println(acc);
        }
    }

    public void addAccount(Account a){
        accounts.add(a);
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount(new SavingsAccount(1, 100, 0.12));
        bank.addAccount(new CheckingAccount(2, 50, 0.5));
        bank.addAccount(new SpecialCheckingAccount(3, 75, 0.5, 0.06, 150));
        bank.addAccount(new SpecialCheckingAccount(4, 150, 0.5, 0.06, 50));
        System.out.println("BEFORE");
        bank.showAllAccounts();

        bank.postMonthlyInterest();
        System.out.println("AFTER");
        bank.showAllAccounts();

    }

}