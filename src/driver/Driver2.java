import java.util.Scanner;

public class Driver2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input jumlah data
        System.out.print("Masukkan jumlah total data: ");
        int N = input.nextInt();

        int[] nilai = new int[N];
        String[] kode = new String[N];

        // Input nilai dan kode kelompok
        for (int i = 0; i < N; i++) {
            System.out.print("Nilai ke-" + (i + 1) + ": ");
            nilai[i] = input.nextInt();

            System.out.print("Kode Kelompok (ASPA/ASPI): ");
            kode[i] = input.next();
        }

        // Input kelompok yang ingin dihitung
        System.out.print("Masukkan kode kelompok yang ingin dihitung: ");
        String pilih = input.next();

        int total = 0;

        // Proses menghitung total hanya untuk kelompok yang diminta
        for (int i = 0; i < N; i++) {
            if (kode[i].equalsIgnoreCase(pilih)) {
                total += nilai[i];
            }
        }

        // Output hasil
        System.out.println("Total nilai kelompok " + pilih.toUpperCase() + " adalah: " + total);

        input.close();
    }
}