import java.util.ArrayList;

public class TradeLedger {

    private ArrayList<String> records = new ArrayList<>();

    public void addRecord(String record){
        records.add(record);
        tradeInfo();
    }

    public void tradeInfo(){
        System.out.println(records.get(records.size() - 1));
    }

    public void showAllLog(){
        for (String j: records) {
            System.out.println(j);
        }
    }

}
