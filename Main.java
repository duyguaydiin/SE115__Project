import java.io.*;
import java.util.*;
public class Main{
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
            "July","August","September","October","November","December"};
    private static int[][][] profitData = null;
    private static int getCommodityIndex(String commodityName) {
        if (commodityName == null) return -1;
        for (int i = 0; i < COMMS; i++) {
            if (commodities[i].equals(commodityName)) {
                return i;
            }
        }
        return -1;
    }
    public static void loadData() {
        for (int i = 0; i < MONTHS; i++) {
            Scanner reader = null;

            try {
                File file = new File("Data_Files/" + months[i] + ".txt");   //dosyalar okunmaya basladı
                reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] parts = line.split(","); //satırları 3 parçaya ayırdı
                    if (parts.length != 3) {
                        continue;  //sorun çıkmaması için satır uzunluğu 3 olmayanların atlanması için yazdım güvenlik kontrolü sağladı
                    }
                    String dayStr = parts[0].trim();
                    String commodityStr = parts[1].trim();
                    String profitStr = parts[2].trim(); //boşluklar temizlendi

                    if (dayStr.equals("Day")) continue;
                    int dayIndex = Integer.parseInt(dayStr) - 1; //günü integere çevirdi
                    int profitValue = Integer.parseInt(profitStr); //karı double veri tipine çevirdi
                    if (dayIndex < 0 || dayIndex >= DAYS) continue;
                    int commodityIndex = commodityIndexOf(commodityStr);
                    if (commodityIndex == -1) continue;
                    profit[i][dayIndex][commodityIndex] = profitValue; //dosyada olan karı en başta oluşturduğumuz 3 boyutlu arrayin içine yerleştirdi
                }
            } catch (Exception ex) {
            } finally {
                if (reader != null) {
                    reader.close();//sistem çökmesin diye scanner olan readerı kapattım 
                }
            }
        }
    }


}

    public static String mostProfitableCommodityInMonth(int month) {
        return "DUMMY";
    }

    public static int totalProfitOnDay(int month, int day) {
        return 1234;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        return 1234;
    }

    public static int bestDayOfMonth(int month) {
        return 1234;
    }

    public static String bestMonthForCommodity(String comm) {
        return "DUMMY";
    }

    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}