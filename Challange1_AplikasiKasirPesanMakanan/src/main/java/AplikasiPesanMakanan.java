import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String nama;
    int harga;
    int jumlah;


    public Item(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
        this.jumlah = 0;
    }

    public int getTotalHarga() {
        return harga * jumlah;
    }

}

public class AplikasiPesanMakanan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Item> daftarPesanan = new ArrayList<>();
        int totalHarga = 0;
        int totalQty = 0;

        Item nasiGoreng = new Item("Nasi Goreng\t\t\t", 15000);
        Item mieGoreng = new Item("Mie Goreng\t\t\t", 13000);
        Item nasiAyam = new Item("Nasi + Ayam\t\t\t", 18000);
        Item esTehManis = new Item("Es Teh Manis\t\t\t", 3000);
        Item esJeruk = new Item("Es Jeruk\t\t\t\t", 5000);

        Item[] menu = {nasiGoreng, mieGoreng, nasiAyam, esTehManis, esJeruk};

        while (true) {
            System.out.println("====================");
            System.out.println("Selamat Datang di BinarFud");
            System.out.println("====================");
            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + ". " + menu[i].nama + " | " + menu[i].harga);
            }
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("=> ");

            int pilihan = input.nextInt();

            if (pilihan == 0) {
                break;
            } else if (pilihan == 99) {
                if (daftarPesanan.isEmpty()) {
                    System.out.println("Daftar pesanan kosong.");
                    continue;
                }

                System.out.println("====================");
                System.out.println("Konfirmasi & Pembayaran");
                System.out.println("====================");
                for (Item item : daftarPesanan) {
                    System.out.println(item.nama + " " + item.jumlah + " " + item.getTotalHarga());
                    totalHarga += item.getTotalHarga();
                    totalQty += item.jumlah;


                }
                System.out.println("---------------------- +");
                System.out.println("Total       " + totalQty + "      " + totalHarga);

                System.out.println("1. Konfirmasi dan Bayar");
                System.out.println("2. Kembali ke Menu Utama");
                System.out.println("0. Keluar Aplikasi");
                System.out.print("=> ");

                int konfirmasi = input.nextInt();

                if (konfirmasi == 1) {
                    System.out.println("Metode pembayaran (1. Binar Cash): ");
                    System.out.print("Ketik 1 Jika ingin menggunakan Binar Cash : ");

                    String metodePembayaran = input.next();

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("struk_pembayaran.txt"));
                        writer.write("====================");
                        writer.newLine();
                        writer.write("BinarFud");
                        writer.newLine();
                        writer.write("====================");
                        writer.newLine();
                        writer.write("Terima kasih sudah memesan");
                        writer.newLine();
                        writer.write("di BinarFud");
                        writer.newLine();
                        writer.write("Dibawah ini adalah pesanan anda");
                        writer.newLine();

                        for (Item item : daftarPesanan) {
                            writer.write(item.nama + " \t\t\t\t | " + item.jumlah + " " + item.getTotalHarga());
                            writer.newLine();
                        }

                        writer.write("---------------------- +");
                        writer.newLine();
                        writer.write("Total       " + totalQty + "      " + totalHarga);
                        writer.newLine();
                        writer.write("Pembayaran : Binar Cash");
                        writer.newLine();
                        writer.write("===================");
                        writer.newLine();
                        writer.write("Simpan struk ini sebagai bukti pembayaran");
                        writer.newLine();
                        writer.write("===================");

                        writer.close();

                        System.out.println("Pesanan telah dibayar. Struk pembayaran telah disimpan dalam 'struk_pembayaran.txt'");
                        daftarPesanan.clear();
                        totalHarga = 0;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (konfirmasi == 2) {
                    daftarPesanan.clear();
                    totalHarga = 0;
                } else if (konfirmasi == 0) {
                    break;
                }
            } else if (pilihan >= 1 && pilihan <= menu.length) {
                System.out.println("====================");
                System.out.println("Berapa pesanan anda");
                System.out.println("====================");
                System.out.println("1. " + menu[pilihan - 1].nama + " | " + menu[pilihan - 1].harga);
                System.out.println("(input 0 untuk kembali)");
                System.out.print("qty => ");


                int qty = input.nextInt();

                if (qty == 0) {
                    continue;
                } else {
                    menu[pilihan - 1].jumlah += qty;
                    daftarPesanan.add(menu[pilihan - 1]);
                }
            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }
}
