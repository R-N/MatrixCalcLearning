/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials;

import matrices.math.Matrix;
import static matrices.tutorial.tutorials.Lesson.tutorials;
import matrices.tutorial.type.Tutorial2;
import matrices.tutorial.type.TutorialSlide2;
import matrices.tutorial.type.section.ImageSection;
import matrices.tutorial.type.section.MatrixSection2;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class Guide {
    
     static String matrixEditGuide = "Pada window \"New Matrix\" yang ini, Anda dapat mengisi tiap elemen matriks, menambahkan atau menghapus baris atau kolom, dan mengganti nama matriks"
                                        + "<br>    a. Tekan tombol -/+ untuk menghapus atau menambahkan baris(row)/kolom(column)"
                                        + "<br>        &middot;Jika Anda memilih untuk menghapus baris/kolom ketika Anda sudah memilih suatu elemen, maka baris/kolom pada elemen itu lah yang akan dihapus"
                                        + "<br>        &middot;Menghapus baris/kolom tanpa memilih terlebih dahulu akan menghapus baris/kolom terakhir"
                                        + "<br>        &middot;Jika Anda memilih untuk menambah baris/kolom ketika Anda sudah memilih suatu elemen, maka akan disispkan baris/kolom baru <b>sebelum</b> baris/kolom yang dipilih."
                                        + "<br>        &middot;Menambahkan beris/kolom tanpa memilih terlebih dahulu akan menambahkan baris/kolom bari di belakang"
                                        + "<br>        &middot;Anda juga dapat langsung menentukan jumlah baris/kolom. Baris/kolom akan ditambahkan/dihapus dari belakang"
                                        + "<br>    b. Tekan tombol \"Deselect\" untuk menghilangkan pilihan elemen"
                                        + "<br>    c. Tekan tombol \"Reset\" untuk mengembalikan matriks ke kondisi awal";
    
     static TutorialSlide2 matrixElementHistorySlide = new TutorialSlide2("Riwayat Elemen Matriks",
             new TutorialSection[]{
                 new TextSection(
                         "<div align=\"center\">Riwayat Elemen Matriks</div>"
                         + "<br>Fitur untuk menampilkan riwayat elemen suatu matriks."
                         + "<br>Riwayat diurutkan dari yang paling awal, dimana yang paling awal berada di atas."
                         + "<br>Panel determinan hanya akan muncul jika diperlukan."
                         + "<br>Elemen pada panel determinan yang termasuk dalam baris/kolom yang digunakan untuk menghitung determinan juga dapat di-double-click untuk menampilkan riwayatnya."
                 ),
                 new ImageSection("history1.png", 90)
             }
     );
    
    public static void init(){
        Tutorial2[] t = tutorials;
    }
    
    public static Tutorial2[] tutorials = new Tutorial2[]{
        new Tutorial2("Intro", 
                new TutorialSlide2[]{
                    new TutorialSlide2("Selamat Datang",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Selamat Datang</div>"
                                        + "<br>Selamat Datang di program Matrices."
                                        + "<br>Program ini adalah alat bantu hitung matriks."
                                        + "<br>Dalam program ini, matriks direpresentasikan dalam tabel."
                                        + "<br>Contoh:"
                                ),
                                new MatrixSection2(
                                        new Matrix(
                                            new double[][]{
                                                new double[]{1, 2, 3},
                                                new double[]{4, 5, 6}
                                            },
                                            "M"
                                        ),
                                        false
                                ),
                                new TextSection(
                                        "Program ini dikembangkan oleh Muhammad Rizqi Nur, mahasiswa UIN Sunan Ampel Surabaya."
                                        + "<br>Tentunya pengembang program ini berharap program ini dapat berguna, terutama dalam membantu pembelajaran mengenai matriks."
                                )
                            }
                    ),
                    new TutorialSlide2("Window Utama",
                            new TutorialSection[]{
                                new TextSection("Window utama program ini dibagi menjadi panel kiri dan kanan yang akan dijelaskan nanti."),
                                new TextSection(
                                        "Program ini juga menyediakan materi dasar seputar matriks, tapi hanya sebagai pengingat."
                                        + "<br>Selain itu, program ini juga menyediakan latihan soal yang dibuat secara random."
                                        + "<br>Untuk panduan penggunaan, klik menu \"Guide\""
                                        + "<br>Untuk materi, klik menu \"Lesson\""
                                        + "<br>Untuk latihan soal, klik menu \"Exercise\""
                                ),
                                new ImageSection("main.png", 95),
                            }
                    )
                }
        ),
        new Tutorial2("Panel Kanan", 
                new TutorialSlide2[]{
                    new TutorialSlide2("Menambahkan Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menambahkan Matriks</div>"
                                        + "<br>Sebelum dapat memulai perhitungan matriks, Anda harus membuat matriksnya terlebih dahulu."
                                        + "<br>Berikut langkah-langkah membuat matriks:"
                                        + "<br>1. Klik tanda + pada panel kanan. Akan muncul window \"New Matrix\""
                                ),
                                new ImageSection("newmatrix0.png", 25),
                                new ImageSection("newmatrix1.png", 25),
                                new TextSection(
                                        "2. Pilih tipe matriks."
                                        + "<br>        &middot;Pilih \"Custom\" jika ingin mengisi matriks sendiri"
                                        + "<br>        &middot;Pilih \"Scalar\" Jika ingin membuat matriks skalar"
                                        + "<br>        &middot;Pilih \"Uniform\" Jika ingin membuat matriks yang setiap elemennya rata"
                                        + "<br>        &middot;Apapun yang dipilih, tiap elemen matris tetap bisa diubah nanti"
                                        + "<br>3. Isi semua kolom"
                                        + "<br>4. Klik \"Save As\", lalu akan muncul window \"New Matrix\" yang berbeda"
                                ),
                                new ImageSection("newmatrix2.png", 50),
                                new TextSection(
                                        "5. " + matrixEditGuide
                                ),
                                new ImageSection("newmatrix3.png", 50),
                                new TextSection(
                                        "6. Selesaikan"
                                        + "<br>        &middot;Tekan tombol \"Cancel\" untuk membatalkan pembuatan matriks"
                                        + "<br>        &middot;Tekan tombol \"Save As\" untuk menyimpan matriks"
                                )
                            }
                    ),
                    new TutorialSlide2("Menampilkan Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menampilkan Matriks</div>"
                                        + "<br>Untuk menampilkan matriks, cukup pilih matriks yang ingin ditampilkan pada daftar matriks di bagian bawah panel kanan, lalu matriks akan ditampilkan di atasnya."
                                ),
                                new ImageSection("showmatrix1.png", 30)
                            }
                    ),
                    new TutorialSlide2("Mengubah Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Mengubah Matriks</div>"
                                        + "<br>1. Pilih matriks yang ingin diubah"
                                        + "<br>2. Klik \"Edit\", lalu akan muncul window \"Edit Matrix\""
                                ),
                                new ImageSection("editmatrix1.png", 30),
                                new ImageSection("editmatrix2.png", 50),
                                new TextSection(
                                        "<br>3. " + matrixEditGuide
                                ),
                                new ImageSection("editmatrix3.png", 50),
                                new TextSection(
                                        "4. Selesaikan"
                                        + "<br>    a. Tekan tombol \"Cancel\" untuk membatalkan pengubahan matriks"
                                        + "<br>    b. Tekan tombol \"Save\" untuk menyimpan sebagai matriks yang tadi"
                                        + "<br>    c. Tekan tombol \"Save As\"untuk menyimpan sebagai matriks baru"
                                )
                                
                            }
                    ),
                    new TutorialSlide2("Menghapus Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menghapus Matriks</div>"
                                        + "<br>1. Pilih matriks yang ingin dihapus"
                                        + "<br>2. Tekan tombol \"-\" untuk menghapus matriks"
                                ),
                                new ImageSection("removematrix1.png", 30)
                            }
                    ),
                    new TutorialSlide2("Undo & Redo",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Undo & Redo</div>"
                                        + "<br>a. Tekan \"Undo\" untuk membatalkan perintah terakhir mengenai matriks-matriks di panel kanan"
                                        + "<br>b. Tekan \"Redo\" untuk melakukan kembali perintah yang dibatalkan"
                                ),
                                new ImageSection("undoredo1.png", 30)
                            }
                    )
                }
        ),
        new Tutorial2("Panel Kiri",
                new TutorialSlide2[]{
                    new TutorialSlide2("Overview",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Panel Kiri - Fitur Utama</div>"
                                        + "<br>Program ini memiliki 5 fitur utama yang terdapat di panel kiri"
                                        + "<br>Setiap fitur memiliki tab masing-masing"
                                        + "<br>Tab suatu fitur baru akan muncul jika fitur tersebut mungkin digunakan bergamtung pada matriks-matriks yang ada"
                                        + "<br>1. Operasi Baris Elementer/Eliminasi Gauss-Jordan"
                                        + "<br>        Syarat: Ada matriks"
                                        + "<br>2. Operasi Matriks"
                                        + "<br>        Syarat: Ada matriks"
                                        + "<br>3. Bentuk-Bentuk Matriks"
                                        + "<br>        Syarat: Ada matriks"
                                        + "<br>4. Determinan Matriks"
                                        + "<br>        Syarat: Ada matriks persegi (berordo nxn)"
                                        + "<br>5. Penyelesaian persamaan linear dengan matriks cara Cramer"
                                        + "<br>        Syarat: Ada matriks persegi berordo nxn dan ada matriks kolom pasangannya yang berordo nx1s"
                                )
                            }
                    )
                }
        ),
        new Tutorial2("Operasi Baris Elementer / Eliminasi Gauss-Jordan",
                new TutorialSlide2[]{
                    new TutorialSlide2("Overview",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Operasi Baris Elementer / Eliminasi Gauss-Jordan</div>"
                                        + "<br>Pada tab ini Anda dapat melakukan OBE pada banyak matriks sekaligus"
                                        + "<br>Operasi baris elementer akan dilakukan pada semua matriks yang terdaftar"
                                        + "<br>Perubahan pada matriks tidak akan mengubah matriks sumbernya."
                                        + "<br>OBE hanya dapat dilakukan pada matriks-matriks yang jumlah barisnya sama."
                                        + "<br>Karena itu, ketika memilih matriks utama, jika ada matriks yang jumlah barisnya tidak sama dengan yang Anda pilih,"
                                        + "<br>Anda harus memilih untuk menghapus matriks tersebut, atau membatalkan pemilihan matriks utama."
                                )
                            }
                    ),
                    new TutorialSlide2("Menambahkan Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menambahkan Matriks</div>"
                                        + "<br>Untuk melakukan OBE, Anda perlu menambahkan matriks untuk OBE terlebih dahulu."
                                        + "<br>Tentunya dianjurkan untuk menambahkan lebih dari 1 matriks dengan jumlah baris yang sama, karena OBE hanya dapat dilakukan pada matriks-matriks yang jumlah barisnya sama."
                                        + "<br>Langkah-langkah:"
                                        + "<br>1. Pilih salah satu matriks di panel kanan"
                                        + "<br>2. Tekan tombol \"+\" pada panel kanan tab \"OBE/GJE\""
                                        + "<br>Perubahan matriks pada matriks pada OBE tidak berdampak pada matriks sumbernya."
                                ),
                                new ImageSection("obeaddmatrix1.png", 50)
                            }
                    ),
                    new TutorialSlide2("Menampilkan Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menampilkan Matriks</div>"
                                        + "<br>Untuk menampilkan matriks, cukup pilih matriks pada panel di kanan bawah tab \"OBE/GJE\". Matriks akan ditampilkan pada panel di kanan atas tab."
                                ),
                                new ImageSection("obeshowmatrix1.png", 30)
                            }
                    ),
                    new TutorialSlide2("Menghapus matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menghapus Matriks</div>"
                                        + "<br>Langkah-langkah:"
                                        + "<br>1. Pilih salah satu matriks di panel kanan tab \"OBE/GJE\""
                                        + "<br>2. Tekan tombol \"-\" pada panel kanan tab \"OBE/GJE\""
                                ),
                                new ImageSection("oberemovematrix1.png", 30)
                            }
                    ),
                    new TutorialSlide2("Matriks Utama",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Matriks utama</div>"
                                        + "<br>Sebelum melakukan OBE, Anda harus memilih matriks utama terlebih dahulu. "
                                        + "<br>Matriks utama nantinya akan ditampilkan pada panel besar di sebelah kiri."
                                        + "<br>Langkah-langkah:"
                                        + "<br>1. Pilih salah satu matriks di panel kanan tab \"OBE/GJE\""
                                        + "<br>2. Tekan tombol \"Use\" pada panel kanan tab \"OBE/GJE\""
                                ),
                                new ImageSection("obemainmatrix1.png", 30),
                                new TextSection(
                                        "OBE hanya dapat dilakukan pada matriks-matriks yang jumlah barisnya sama."
                                        + "<br>Karena itu, ketika memilih matriks utama, jika ada matriks yang jumlah barisnya tidak sama dengan yang Anda pilih,"
                                        + "<br>Anda harus memilih untuk menghapus matriks tersebut, atau membatalkan pemilihan matriks utama."
                                ),
                                new ImageSection("obemainmatrix2.png", 60),
                                new TextSection(
                                        "Ketika Anda sudah memilih matriks utama, Anda tidak dapat menambah atau menghapus matriks OBE."
                                        + "<br>Anda dapat mengganti matriks utama dengan cara yang sama seperti bagaimana Anda memilih matriks utama sebelumnya"
                                        + "<br>Anda dapat menutup panel utama dengan menekan tombol \"Close\" sehingga Anda dapat menghapus atau menambah matriks OBE lagi."
                                ),
                                new ImageSection("obemainmatrix3.png", 80),
                            }
                    ),
                    new TutorialSlide2("Highlighting",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Highlighting</div>"
                                        + "<br>Program ini mendukung highlighting untuk membantu proses OBE"
                                        + "<br>a. Anda dapat menyalakan highlighting dengan menekan tombol \"Highlighting\" di bawah panel utama"
                                        + "<br>b. Anda dapat melihat keterangan arti warna-warna dengan menekan tombol \"Legend\""
                                ),
                                new ImageSection("obehighlighting1.png", 70),
                                new ImageSection("obehighlighting2.png", 30)
                            }
                    ),
                    new TutorialSlide2("Operasi Baris Elementer",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Operasi Baris Elementer</div>"
                                        + "<br>Ada 3 macam operais baris elementer; semuanya didukung oleh program ini."
                                ),
                                new ImageSection("obeobe1.png", 80),
                                new TextSection(
                                        "a. Mengalikan baris (multiply row)"
                                        + "<br>        1. Masukkan koefisien ke kolom yang kiri. Tidak boleh kosong, 0, atau 1."
                                        + "<br>        2. Masukkan nomor baris pada kolom yang kanan."
                                        + "<br>        3. Tekan tombol \"Multiply Row\""
                                ),
                                new ImageSection("obeobe2.png", 80),
                                new TextSection(
                                        "b. Menukar baris (switch rows)"
                                        + "<br>        1&2. Masukkan kedua nomor baris yang ingin ditukar. Nomor baris tidak boleh sama."
                                        + "<br>        3. Tekan tombol \"Switch Rows\""
                                ),
                                new ImageSection("obeobe3.png", 80),
                                new TextSection(
                                        "c. Menjumlahkan baris (set rows)"
                                        + "<br>        1. Masukkan koefisien baris pertama. Tidak boleh nol. Kosong berarti 1."
                                        + "<br>        2. Masukkan nomor baris pertama. Hasil dari penjumlahan akan disimpan pada baris pertama."
                                        + "<br>        3. Masukkan koefisien baris kedua. Tidak boleh nol. Kosong berarti 1."
                                        + "<br>        4. Masukkan nomor baris kedua. Tidak boleh sama dengan nomor baris pertama."
                                        + "<br>        5. Tekan tombol \"Set Row\""
                                ),
                            }
                    ),
                    new TutorialSlide2("Undo & Redo",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Undo & Redo</div>"
                                        + "<br>a. Tekan tombol \"Undo\" untuk membatalkan perubahan pada matriks-matriks OBE."
                                        + "<br>b. Tekan tombol \"Redo\" untuk melakukan kembali perubahan yang sudah dibatalkan."
                                ),
                                new ImageSection("obeundoredo1.png", 30)
                            }
                    ),
                    new TutorialSlide2("Menyimpan Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menyimpan Matrikx</div>"
                                        + "<br>Anda dapat menyimpan matriks hasil OBE ke daftar matriks utama dengan langkah-langkah sebagai berikut:"
                                        + "<br>1. Pilih matriks yang ingin disimpan di panel kanan bawah tab \"OBE/GJE\""
                                        + "<br>2. Tekan tombol \"Save\", lalu akan muncul window \"Save Matrix As\""
                                ),
                                new ImageSection("obesavematrix1.png", 30),
                                new ImageSection("obesavematrix2.png", 30),
                                new TextSection(
                                        "3. Tentukan nama matriks"
                                        + "<br>4. Selesaikan"
                                        + "<br>        &middot;Tekan tombol \"Cancel\" untuk membatalkan penyimpanan matriks"
                                        + "<br>        &middot;Tekan tombol \"Ok\" untuk menyimpan matriks"
                                )
                            }
                    ),
                }
        ),
        new Tutorial2("Operasi Matriks",
                new TutorialSlide2[]{
                    new TutorialSlide2("Operasi Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Operasi Matriks</div>"
                                        + "<br>Fitur operasi dasar matriks"
                                        + "<br>Mendukung operasi matriks-matriks (penjumlahan, pengurangan, dan perkalian), dan matriks-skalar (penjumlahan, pengurangan, perkalian, pembagian, modulus)"
                                        + "<br>dengan melakukan operasi kepada setiap elemen matriks."
                                ),
                                new ImageSection("op1.png", 80),
                                new TextSection(
                                        "Langkah-Langkah penggunaan:"
                                        + "<br>1. Tentukan koefisien matriks pertama (kiri)"
                                        + "<br>2. Pilih matriks pertama (kiri)"
                                        + "<br>3. Pilih operator/operasi."
                                        + "<br>4. Tentukan koefisien matriks kedua (kanan). Kolom ini akan digunakan sebagai besaran skalar ketika memilih \"Uniform single\" pada kolom matriks kedua (kanan)"
                                        + "<br>5. Pilih matriks kedua, atau pilih \"Uniform single\" jika ingin melakukan operasi uniform pada matriks."
                                        + "<br>Hanya matriks yang bisa dioperasikan dengan matriks pertama dengan operasi yang sudah dipilih yang bisa dipilih."
                                        + "<br>Hasil operasi akan ditampilkan pada panel utama"
                                ),
                                new ImageSection("op2.png", 80)
                            }
                    ),
                    new TutorialSlide2("Perhitungan",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Perhitungan</div>"
                                        + "<br>Program ini dapat menampilkan langkah-langkah perhitungan pada tiap elemen matriks hasil."
                                ),
                                new ImageSection("opcalc1.png", 80),
                                new TextSection(
                                        "Langkah-langkah:"
                                        + "<br>1. Tekan tombol \"Show Calculation\""
                                        + "<br>2. Pilih salah satu elemen pada matriks hasil"
                                ),
                                new ImageSection("opcalc2.png", 80)
                            }
                    ),
                    new TutorialSlide2("Menyimpan Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menyimpan Matriks</div>"
                                        + "<br>Matriks hasil operasi dapat disimpan dengan langkah-langkah berikut:"
                                        + "<br>1. Tekan tombol \"Save As\", lalu akan muncul window \"Save Matrix As\""
                                ),
                                new ImageSection("opsave1.png", 80),
                                new ImageSection("opsave2.png", 30),
                                new TextSection(
                                        "2. Selesaikan"
                                        + "<br>a. Tekan tombol \"Cancel\" untuk membatalkan penyimpanan matriks"
                                        + "<br>b. Tekan tombol \"OK\" untuk menyimpan matriks"
                                ),
                            }
                    )
                }
        ),
        new Tutorial2("Bentuk-Bentuk Matriks",
                new TutorialSlide2[]{
                    new TutorialSlide2("Bentuk-Bentuk Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Bentuk-Bentuk Matriks</div>"
                                        + "<br>Fitur untuk menampilkan bentuk lain dari suatu matriks."
                                ),
                                new ImageSection("form1.png", 80),
                                new TextSection(
                                        "Langkah-langkah:"
                                        + "<br>1. Pilih matriks"
                                        + "<br>2. Pilih bentuk matriks. Bentuk yang tersedia sesuai dengan bentuk yang mungkin dari matriks yang dipilih."
                                )
                            }
                    ),
                    new TutorialSlide2("Riwayat Elemen Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Riwayat Elemen Matriks</div>"
                                        + "<br>Anda dapat menampilkan riwayat elemen matriks dari matriks hasil dengan melakukan double click pada elemen yang diinginkan."
                                         + "<br>Urutan riwayat dari atas ke bawah, dimana yang paling atas adalah yang paling awal."
                                ),
                                new ImageSection("formhistory1.png", 80),
                                new ImageSection("formhistory2.png", 50)
                            }
                    ),
                    matrixElementHistorySlide,
                    new TutorialSlide2("Menyimpan Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Menyimpan Matriks</div>"
                                        + "<br>Anda dapat menyimpan matriks hasil dengan menekan tombol \"Save As\" di bawah."
                                ),
                                new ImageSection("formsave1.png", 80),
                                new ImageSection("formsave2.png", 30)
                            }
                    )
                }
        ),
        new Tutorial2("Determinan Matriks",
                new TutorialSlide2[]{
                    new TutorialSlide2("Determinan Matriks - Laplace/Kofaktor",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Determinan Matriks - Laplace/Kofaktor</div>"
                                        + "<br>Fitur untuk menghitung determinan suatu matriks dengan menggunakan cara Laplace/kofaktor."
                                ),
                                new ImageSection("det1.png", 80),
                                new TextSection(
                                        "Langkah-langkah:"
                                        + "<br>1. Pilih matriks. Matriks yang bisa dipilih hanya matriks persegi."
                                        + "<br>2. Pilih baris/kolom yang akan digunukan untuk menghitung determinan"
                                        + "<br>Determinan dan perhitungannya akan ditampilkan di bagian bawah."
                                ),
                                new ImageSection("det2.png", 80)
                            }
                    ),
                    new TutorialSlide2("Riwayat Elemen Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Riwayat Elemen Matriks</div>"
                                        + "<br>Anda dapat menampilkan riwayat perhitungan elemen matriks dari matriks hingga kofaktor dengan melakukan double click pada elemen yang diinginkan."
                                        + "<br>Elemen yang dapat ditampilkan riwayatnya adalah elemen yang termasuk dalam baris/kolom yang dipilih."
                                        + "<br>Urutan riwayat dari atas ke bawah, dimana yang paling atas adalah yang paling awal."
                                ),
                                new ImageSection("dethistory1.png", 80),
                                new ImageSection("dethistory2.png", 50)
                            }
                    ),
                    matrixElementHistorySlide
                }
        ),
        new Tutorial2("Cramer",
                new TutorialSlide2[]{
                    new TutorialSlide2("Cramer",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Penyelesaian Persamaan Linear dengan Matrix Cara Cramer</div>"
                                        + "<br>Fitur untuk menyelesaikan persamaan linear dengan matriks cara Cramer."
                                        + "<br>Pastikan Anda mempersiapkan matriksnya terlebih dahulu, atau tabnya tidak akan muncul."
                                        + "<br>Yaitu matriks persegi nxn dengan pasangannya matrikx nx1."
                                ),
                                new ImageSection("cramer1.png", 90),
                                new TextSection(
                                        "Langkah penggunaan:"
                                        + "<br>1. Pilih matriks utama (kiri). Matriks yang dapat dipilih hanyalah matriks persegi."
                                        + "<br>2. Pilih matriks sekunder (kanan). Matriks yang dapat dipilih hanyalah matriks kolom yang jumlah barisnya sama dengan matriks utama"
                                        + "<br>3. Pilih kolom dimana matriks sekunder akan disisipkan. 0 berarti tidak menyisipkan."
                                        + "<br>4. Pilih baris/kolom untuk menghitung determinan."
                                        + "<br>Hasil perhitungan akan ditampilkan di bawah"
                                ),
                                new ImageSection("cramer2.png", 80)
                            }
                    ),
                    new TutorialSlide2("Riwayat Elemen Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Riwayat Elemen Matriks</div>"
                                        + "<br>Anda dapat menampilkan riwayat perhitungan elemen matriks dari matriks hingga kofaktor seperti pada tab \"Determinan\" dengan melakukan double click pada elemen yang diinginkan."
                                        + "<br>Elemen yang dapat ditampilkan riwayatnya adalah elemen yang termasuk dalam baris/kolom yang dipilih untuk menghitung determinan."
                                        + "<br>Urutan riwayat dari atas ke bawah, dimana yang paling atas adalah yang paling awal."
                                ),
                                new ImageSection("cramerhistory1.png", 80),
                                new ImageSection("cramerhistory2.png", 50)
                            }
                    ),
                    //matrixElementHistorySlide
                }
        ),
        new Tutorial2("Riwayat Elemen Matriks",
                new TutorialSlide2[]{
                    matrixElementHistorySlide
                }
        )
    };
}
