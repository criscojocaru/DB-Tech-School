package Lab03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static List<Transaction> addTransactions(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        return transactions;
    }

    public static void main(String[] args){

        List<Transaction> transactions = addTransactions();

        // 1. Gasiti toate tranzactiile din anul 2011 si sortati-le dupa valoare.
        List<Transaction> sortedTransactions = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        sortedTransactions.stream().forEach(System.out::println);

        // 2. Care sunt toate orasele (unice)?
        List<String> uniqueCities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        uniqueCities.forEach(System.out::println);

        // 3. Gasiti toti traderii din Cambridge si sortati-i dupa nume.
        List<Trader> cambridgeTraders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        cambridgeTraders.forEach(System.out::println);

        // 4. Un string cu toate numele traderilor ordonati alfabetic
        String concatenatedNames = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing((Trader::getName)))
                .map(Trader::getName)
                .reduce("", String::concat);

        System.out.println(concatenatedNames);

        // 5. Sunt traderi din Milan?
        boolean milanTraders = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        System.out.println(milanTraders);

        // 6. Printati valoarea tuturor tranzactiilor trader-ilor care traiesc in Cambridge.
        List<Integer> transactionsValue = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());

        transactionsValue.forEach(System.out::println);

        // 7. Care e tranzactia cu cea mai mare valoare?
        Transaction greatestTransaction = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue)).get();

        System.out.println(greatestTransaction);

        // 8. Dar cea mai mica?
        Transaction smallestTransaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue)).get();

        System.out.println(smallestTransaction);
    }
}
