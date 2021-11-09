public class CoinPermutations {
    public static void getPermutations(int cents){
        int count = 0;
        for(int q=0; q*25<=cents; q++){
            for(int d=0; d*10+25*q<=cents; d++){
                for(int n=0; n*5+25*q+10*d<=cents; n++){
                    int p = cents-25*q-10*d-5*n;
                    System.out.println(cents + "¢ =" + " quarters: " + q + " dimes: " + d + " nickels: "+ n + " pennies: " + p);

                    count++;
                }
            }
        }
        System.out.println(count + " permutations");
    }
    public static void main(String[] args) {
        getPermutations(100);
//        overflows at 25235 cents
    }
}
