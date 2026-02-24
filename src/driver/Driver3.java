import java.util.*;  // import harus paling atas

public class Driver3 {

    static Scanner input = new Scanner(System.in);

    static List<Customer> customers = new ArrayList<>();
    static List<Transaksi> transaksiList = new ArrayList<>();
    static List<Keluhan> keluhanList = new ArrayList<>();
    static List<Rating> ratingList = new ArrayList<>();

    static int customerId = 1;
    static int transaksiId = 1;

    public static void main(String[] args) {
        int pilih;

        do {
            menu();
            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
                case 1: tambahCustomer(); break;
                case 2: tambahTransaksi(); break;
                case 3: tambahKeluhan(); break;
                case 4: tambahRating(); break;
                case 5: tampilData(); break;
                case 6: System.out.println("Terima kasih."); break;
                default: System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 6);
    }

    static void menu() {
        System.out.println("\n=== SISTEM LAUNDRY DEL ===");
        System.out.println("1. Tambah Customer");
        System.out.println("2. Tambah Transaksi");
        System.out.println("3. Tambah Keluhan (Opsional)");
        System.out.println("4. Tambah Rating (1-5)");
        System.out.println("5. Lihat Semua Data");
        System.out.println("6. Keluar");
        System.out.print("Pilih: ");
    }

    static void tambahCustomer() {
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("No HP: ");
        String hp = input.nextLine();

        customers.add(new Customer(customerId++, nama, hp));
        System.out.println("Customer berhasil ditambahkan.");
    }

    static void tambahTransaksi() {
        System.out.print("ID Customer: ");
        int cid = input.nextInt(); input.nextLine();

        if (cariCustomer(cid) == null) {
            System.out.println("Customer tidak ditemukan.");
            return;
        }

        System.out.print("Jenis Pakaian: ");
        String jenis = input.nextLine();
        System.out.print("Berat (kg): ");
        double berat = input.nextDouble();

        transaksiList.add(new Transaksi(transaksiId++, cid, jenis, berat));
        System.out.println("Transaksi berhasil ditambahkan.");
    }

    static void tambahKeluhan() {
        System.out.print("ID Transaksi: ");
        int tid = input.nextInt(); input.nextLine();

        if (cariTransaksi(tid) == null) {
            System.out.println("Transaksi tidak ditemukan.");
            return;
        }

        if (cariKeluhan(tid) != null) {
            System.out.println("Keluhan sudah ada.");
            return;
        }

        System.out.print("Deskripsi Keluhan: ");
        String desk = input.nextLine();
        keluhanList.add(new Keluhan(tid, desk));

        System.out.println("Keluhan berhasil ditambahkan.");
    }

    static void tambahRating() {
        System.out.print("ID Transaksi: ");
        int tid = input.nextInt();

        if (cariTransaksi(tid) == null) {
            System.out.println("Transaksi tidak ditemukan.");
            return;
        }

        if (cariRating(tid) != null) {
            System.out.println("Rating sudah ada.");
            return;
        }

        System.out.print("Nilai (1-5): ");
        int nilai = input.nextInt();

        if (nilai < 1 || nilai > 5) {
            System.out.println("Rating harus 1-5.");
            return;
        }

        ratingList.add(new Rating(tid, nilai));
        System.out.println("Rating berhasil ditambahkan.");
    }

    static void tampilData() {
        System.out.println("\n--- CUSTOMER ---");
        customers.forEach(Customer::display);

        System.out.println("\n--- TRANSAKSI ---");
        transaksiList.forEach(Transaksi::display);

        System.out.println("\n--- KELUHAN ---");
        if (keluhanList.isEmpty()) System.out.println("Tidak ada keluhan.");
        else keluhanList.forEach(Keluhan::display);

        System.out.println("\n--- RATING ---");
        if (ratingList.isEmpty()) System.out.println("Tidak ada rating.");
        else {
            ratingList.forEach(Rating::display);
            System.out.println("Rata-rata Kepuasan: " + rataRating());
        }
    }

    // HELPER METHODS
    static Customer cariCustomer(int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElse(null);
    }

    static Transaksi cariTransaksi(int id) {
        return transaksiList.stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);
    }

    static Keluhan cariKeluhan(int transaksiId) {
        return keluhanList.stream()
                .filter(k -> k.getTransaksiId() == transaksiId)
                .findFirst().orElse(null);
    }

    static Rating cariRating(int transaksiId) {
        return ratingList.stream()
                .filter(r -> r.getTransaksiId() == transaksiId)
                .findFirst().orElse(null);
    }

    static double rataRating() {
        return ratingList.stream()
                .mapToInt(Rating::getNilai)
                .average()
                .orElse(0.0);
    }
}

// CUSTOMER
class Customer {
    private int id;
    private String nama;
    private String noHp;

    public Customer(int id, String nama, String noHp) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
    }

    public int getId() { return id; }

    public void display() {
        System.out.println("ID: " + id + " | Nama: " + nama + " | HP: " + noHp);
    }
}

// TRANSAKSI
class Transaksi {
    private int id;
    private int customerId;
    private String jenis;
    private double berat;

    public Transaksi(int id, int customerId, String jenis, double berat) {
        this.id = id;
        this.customerId = customerId;
        this.jenis = jenis;
        this.berat = berat;
    }

    public int getId() { return id; }
    public int getCustomerId() { return customerId; }

    public void display() {
        System.out.println("ID: " + id +
                " | Customer ID: " + customerId +
                " | Jenis: " + jenis +
                " | Berat: " + berat + " kg");
    }
}

// KELUHAN
class Keluhan {
    private int transaksiId;
    private String deskripsi;

    public Keluhan(int transaksiId, String deskripsi) {
        this.transaksiId = transaksiId;
        this.deskripsi = deskripsi;
    }

    public int getTransaksiId() { return transaksiId; }

    public void display() {
        System.out.println("Transaksi ID: " + transaksiId +
                " | Keluhan: " + deskripsi);
    }
}

// RATING
class Rating {
    private int transaksiId;
    private int nilai;

    public Rating(int transaksiId, int nilai) {
        this.transaksiId = transaksiId;
        this.nilai = nilai;
    }

    public int getTransaksiId() { return transaksiId; }
    public int getNilai() { return nilai; }

    public String getKeterangan() {
        switch (nilai) {
            case 1: return "Sangat Tidak Puas";
            case 2: return "Tidak Puas";
            case 3: return "Cukup";
            case 4: return "Puas";
            case 5: return "Sangat Puas";
            default: return "-";
        }
    }

    public void display() {
        System.out.println("Transaksi ID: " + transaksiId +
                " | Rating: " + nilai +
                " (" + getKeterangan() + ")");
    }
}