import java.util.ArrayList;
import java.util.Scanner;

class Pesanan {
    String namaMenu;
    int porsi;
    int harga;
    int total;

    Pesanan(String namaMenu, int porsi, int harga, int total) {
        this.namaMenu = namaMenu;
        this.porsi = porsi;
        this.harga = harga;
        this.total = total;
    }
}

public class Driver1 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Pesanan> daftarPesanan = new ArrayList<>();

        int totalPembayaran = 0;

        while (true) {

            System.out.print("Masukkan Kode Menu (atau END untuk selesai): ");
            String kode = input.nextLine().toUpperCase();

            if (kode.equals("END")) {
                break;
            }

            System.out.print("Masukkan Porsi Butet: ");
            int porsiButet = Integer.parseInt(input.nextLine());

            int porsiUcok = 2 * porsiButet;
            int totalPorsi = porsiButet + porsiUcok;

            String namaMenu = "";
            int harga = 0;

            switch (kode) {
                case "NGS":
                    namaMenu = "Nasi Goreng Spesial";
                    harga = 15000;
                    break;
                case "AP":
                    namaMenu = "Ayam Penyet";
                    harga = 20000;
                    break;
                case "SA":
                    namaMenu = "Sate Ayam (10 Tusuk)";
                    harga = 25000;
                    break;
                case "BU":
                    namaMenu = "Bakso Urat";
                    harga = 18000;
                    break;
                case "MAP":
                    namaMenu = "Mie Ayam Pangsit";
                    harga = 15000;
                    break;
                case "GG":
                    namaMenu = "Gado-Gado";
                    harga = 15000;
                    break;
                case "SAA":
                    namaMenu = "Soto Ayam";
                    harga = 17000;
                    break;
                case "RD":
                    namaMenu = "Rendang Daging";
                    harga = 25000;
                    break;
                case "IB":
                    namaMenu = "Ikan Bakar";
                    harga = 35000;
                    break;
                case "NUK":
                    namaMenu = "Nasi Uduk Komplit";
                    harga = 20000;
                    break;
                default:
                    System.out.println("Kode menu tidak ditemukan!");
                    continue;
            }

            int subtotal = harga * totalPorsi;
            totalPembayaran += subtotal;

            daftarPesanan.add(new Pesanan(namaMenu, totalPorsi, harga, subtotal));
        }

        System.out.println("\n==============================================================");
        System.out.printf("%-25s %-10s %-10s %-10s\n", "Menu", "Porsi", "Harga", "Total");
        System.out.println("--------------------------------------------------------------");

        for (Pesanan p : daftarPesanan) {
            System.out.printf("%-25s %-10d %-10d %-10d\n",
                    p.namaMenu, p.porsi, p.harga, p.total);
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("Total Pembayaran : " + totalPembayaran);
        System.out.println("==============================================================");

        input.close();
    }
}