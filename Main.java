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
            Scanner sc = null;

            try {
                File file = new File("Data_Files/" + months[i] + ".txt");   //dosyalar okunmaya basladı
                sc = new Scanner(file);// fileları oku
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split(","); //satırları 3 parçaya ayırdı
                    if (parts.length != 3) {
                        continue;  //sorun çıkmaması için satır uzunluğu 3 olmayanların atlanması için yazdım güvenlik kontrolü sağladı
                    }
                    String dayStr = parts[0].trim();
                    String commodityStr = parts[1].trim();
                    String profitStr = parts[2].trim(); //sayının baş ve sonundaki gereksiz boşluklar temizlendi

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
                if (sc != null) {
                    sc.close();//sistem çökmesin diye scanner olan readerı kapattım
                }
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
            return commodities[bestIndex] + " " + bestTotal;

        }

    public static int totalProfitOnDay(int month, int day) {
        int totalProfit = 0;
        int dayIndex = day - 1;
        if (day < 1 || day > 28) return -99999; //invalid month ve day için
        if (month < 0 || month > 11) return -99999;
        for (int i = 0; i < COMMS; i++) { //her comms için aylık kar hesapladı
            totalProfit += profit[month][dayIndex][i];
        public static int totalProfitOnDay ( int month, int day){
            int totalProfitof = 0;
            int dayIndex = day - 1;
            if (day < 1 || day > 28) return -99999; //invalid month ve day için
            if (month < 0 || month > 11) return -99999;
            for (int i = 0; i < COMMS; i++) { //her comms için aylık kar hesapladı
                totalProfitof += profit[month][dayIndex][i];
            }
            return totalProfitof;
        }
        return totalProfit;
        public static int commodityProfitInRange (String commodity,int fromDay, int toDay){
            int commodityIndex = commodityIndexOf(commodity);
            if (commodityIndex == -1) return -99999;
            if (fromDay > toDay || fromDay < 1 || fromDay > 28 || toDay < 1 || toDay > 28) return -99999;
            int fromDayIndex = fromDay - 1;
            int toDayIndex = toDay - 1;
            int totalRange = 0;
            for (int i = 0; i < MONTHS; i++) {     //tüm aylar  boyunca,toDay-fromDay arasındaki commoditylerin kar toplamı
                for (int j = fromDayIndex; j <= toDayIndex; j++) {
                    totalRange += profit[i][j][commodityIndex];//
                }
            }
            return totalRange;
        }
        return totalRange;

        public static int bestDayOfMonth ( int month){
            if (month < 0 || month > 11) return -1; // invalid değer değilse
            int bestTotal = 0;
            int bestDayIndex = 0;
            for (int i = 0; i < COMMS; i++) {
                bestTotal += profit[month][0][i];
            }
            for (int j = 1; j < DAYS; j++) { //kalan günlerde commoditylerde yapılan karlar hesaplandı
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
        public static String bestMonthForCommodity (String commodity){
            int commodityIndex = commodityIndexOf(commodity);
            if (commodityIndex == -1) return "INVALID_COMMODITY";
            int bestTotal = 0;
            int bestMonthIndex = 0;
                bestTotal += profit[0][j][commodityIndex]; //ocak toplamı
            }
            for (int i = 1; i < MONTHS; i++) { // diğer aylardaki tüm günlerdeki karları hesapladım
                int monthTotal = 0;
                for (int j = 0; j < DAYS; j++) {
                    monthTotal += profit[i][j][commodityIndex];
                }
                if (monthTotal > bestTotal) { //o ay içerisindeki karı önceki aylardaki karlardan fazla olanı bestTotal olarak isimlendirdim
                    bestTotal = monthTotal;
                    bestMonthIndex = i;
                }
            }
            return months[bestMonthIndex]; // month dizisindeki elemanlar arasından seçilen bestMonthu döndürdü
        }
        public static int consecutiveLossDays (String commodity){
            int commodityIndex = commodityIndexOf(commodity);
            if (commodityIndex == -1) return -1;
            int currentStreak = 0;
            int maxStreak = 0;
            for (int i = 0; i < MONTHS; i++) { //önce 1. forla bütün ayları gezdi en başta tanımlanan ay sayısı kadar
                for (int j = 0; j < DAYS; j++) { // buradaki forla da gezdiği aylar içindeki günlere sıra sıra baktı en başta verilen gün sayısına kadar
                    if (profit[i][j][commodityIndex] < 0) { //eğer bu kar değeri negatifse mevcut Streak arttı
                        currentStreak++;
                        if (currentStreak > maxStreak)
                            maxStreak = currentStreak;//mevcut streak hesaplanandan büyükse o zaman mevcutu hesaplanan olarak eşleştirdi
                    } else {  //negatif değer yoksa sıfırda kalması için
                        currentStreak = 0;
                    }
                }
            }
            return maxStreak;
        }
        public static int daysAboveThreshold (String commodity,int threshold){
            int commodityyIndex = commodityIndexOf(commodity);
            if (commodityyIndex == -1) return -1;
            int countof = 0;
            for (int i = 0; i < MONTHS; i++) { //tüm aylardaki ,tüm günlerdeki seçilen commodity icin kar miktarı thresolddan fazla mı baktı
                for (int j = 0; j < DAYS; j++) {
                    if (profit[i][j][commodityyIndex] > threshold) {
                        countof++; // eğer thresholddan büyükse 1 artırdı
                    }
                }
            }
            return countof;// bulunan count değerini döndürdü
        }
        public static int biggestDailySwing ( int month){
            if (month < 0 || month > 11) return -99999;
            int maxdiff = 0;
            int diff = 0;
            for (int i = 0; i < DAYS - 1; i++) {
                int totalProfitdayone = 0;
                int totalProfitdaytwo = 0;
                for (int j = 0; j < COMMS; j++) {
                    totalProfitdayone += profit[month][i][j];//i ve j değerine göre kar buldu
                    totalProfitdaytwo += profit[month][i + 1][j];// bir sonraki günün karını buldu
                }
                diff = totalProfitdaytwo - totalProfitdayone; //2 günün farkını buldu
                if (diff < 0) { //fark negatifse (-1) ile çarptı negatif cevap alınmamalı
                    diff = diff * (-1);
                }
                if (diff > maxdiff) { //fark o güne kadar elde edilen maksimum farktan büyükse max o olur
                    maxdiff = diff;

                }
            }
            return maxdiff;
        }

        return bestDayIndex + 1; //days array indexi 0'Dan basladıgı icin 1den başlatmak için 1 eklendi
    }


