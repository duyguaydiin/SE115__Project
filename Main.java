import java.io.*;
import java.util.*;
public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    static int[][][] profit = new int[MONTHS][DAYS][COMMS]; //... ayında, ... gününde,... commoditynin karı ne

    public static int commodityIndexOf(String commodity) { //commodityler için case-sensitive taraması
        if (commodity == null) return -1;
        commodity = commodity.trim();
        for (int i = 0; i < COMMS; i++) {
            if (commodities[i].equals(commodity)) {
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
                    int profitValue = Integer.parseInt(profitStr);
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
        public static String mostProfitableCommodityInMonth ( int month){
            if (month < 0 || month > 11) return "INVALID_MONTH"; //month icin geçerli olmayan değerler
            int bestTotal = 0;
            int bestIndex = 0;
            for (int i = 0; i < DAYS; i++) { //ilk commodity için toplamı hesapladı negatif çıkmadığından emin olmak için
                bestTotal += profit[month][i][0];
            }
            for (int j = 1; j < COMMS; j++) { //kalan commodityleri seçilen günlerde karşılaştırdım
                int total = 0;
                for (int k = 0; k < DAYS; k++) {
                    total += profit[month][k][j];
                }
                if (total > bestTotal) { // herhangi bir commodity için en yüksek kar için sıra sıra 2li karşılaştırılarak
                    bestTotal = total;
                    bestIndex = j; //en çok kar yapılan commodity
                }
            }
            return commodities[bestIndex] + " " +bestTotal;

        }

    public static int totalProfitOnDay(int month, int day) {
        int totalProfit = 0;
        int dayIndex = day - 1;
        if (day < 1 || day > 28) return -99999; //invalid month ve day için
        if (month < 0 || month > 11) return -99999;
        for (int i = 0; i < COMMS; i++) { //her comms için aylık kar hesapladı
            totalProfit += profit[month][dayIndex][i];
        }
        return totalProfit;
    }
        public static int commodityProfitInRange(String commodity, int fromDay, int toDay) {
        int commodityIndex = commodityIndexOf(commodity);
        if (commodityIndex == -1) return -99999;
        if (fromDay > toDay || fromDay < 1 || fromDay > 28 || toDay < 1 || toDay > 28) return -99999;
        int fromDayIndex = fromDay - 1;
        int toDayIndex = toDay - 1;
        int totalRange = 0;
        for (int i = 0; i < MONTHS; i++) {     //tüm aylar  boyunca,belirtilen gün aralığında(toDay-fromDay) istenilen commoditynin karını topladı
            for (int j = fromDayIndex; j <= toDayIndex; j++) {
                totalRange += profit[i][j][commodityIndex];//
            }
        }
        return totalRange;
    }

    public static int bestDayOfMonth(int month) {
        if (month < 0 || month > 11) return -1;
        int bestTotal = 0;
        int bestDayIndex = 0;
        for (int i = 0; i < COMMS; i++) { //1.gün toplamı
            bestTotal += profit[month][0][i];
        }
        for (int j = 1; j < DAYS; j++) { //kalan günlerde commoditylerden yapılan karları hesapladım.
            int dayTotal = 0;
            for (int i = 0; i < COMMS; i++) {
                dayTotal += profit[month][j][i];
            }
            if (dayTotal > bestTotal) { // o gün yapılan kar o güne kadar yapılan karlardan fazlaysa bestTotal o olur
                bestTotal = dayTotal;
                bestDayIndex = j;
            }
        }

        return bestDayIndex + 1; //days array indexi 0'Dan basladıgı icin 1den başlatmak için 1 eklendi
    }
}


