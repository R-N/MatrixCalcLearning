/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrices.tutorial.tutorials;

import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import matrices.main.form.MatrixOperation;
import matrices.main.form.MatrixForm;
import matrices.math.Matrix;
import matrices.tutorial.type.section.ImageSection;
import matrices.tutorial.type.Tutorial2;
import matrices.tutorial.type.TutorialSlide2;
import matrices.tutorial.type.button.*;
import matrices.tutorial.type.section.HorizontalLayoutSection;
import matrices.tutorial.type.TutorialResourceProvider;
import matrices.tutorial.type.section.MatrixSection2;
import matrices.tutorial.type.section.TextSection;
import matrices.tutorial.type.section.TutorialSection;

/**
 *
 * @author MojoMacW7
 */
public class Lesson {
    
    public static void init(){
        Tutorial2[] t = tutorials;
    }
    
    static Matrix b2x2_1_2_1 = new Matrix(
            new double[][]{
                new double[]{1, 2},
                new double[]{2, 1}
            },
            "B"
    );
    
    static Matrix m2x2_1_4 = new Matrix(
            new double[][]{
                new double[]{1, 2},
                new double[]{3, 4}
            },
            "M"
    );
    
    static Matrix a2x2_0_1_0 = new Matrix(
            new double[][]{
                new double[]{0, 1},
                new double[]{1, 0}
            },
            "A"
    );
    
    static Matrix b2x2_3_0_1 = new Matrix(
            new double[][]{
                new double[]{3, 0},
                new double[]{0, 1}
            },
            "B"
    );
    
    static Matrix cB2x2_3_0_1MulA2x2_0_1_0 = new Matrix(
            Matrix.multiply(b2x2_3_0_1, a2x2_0_1_0),
            "C"
    );
    
    static Matrix m1A2x2_0_1_0MulM2x2_1_4 = new Matrix(
            Matrix.multiply(a2x2_0_1_0, m2x2_1_4),
            "M1 {A*M}"
    );
    
    static Matrix m2B2x2_0_1_0MulM1 = new Matrix(
            Matrix.multiply(b2x2_3_0_1, m1A2x2_0_1_0MulM2x2_1_4),
            "M2 {B*M1 {A*M}}"
    );
    
    static Matrix m2CMulM2x2_1_4 = new Matrix(
            Matrix.multiply(cB2x2_3_0_1MulA2x2_0_1_0, m2x2_1_4),
            "M2 {C*M}"
    );

    
    static Matrix invM2x2_1_4 = m2x2_1_4.getInverseMatrix();
    
    static Matrix v3x1_1_3 = new Matrix(
            new double[][]{
                new double[]{1},
                new double[]{2},
                new double[]{3}
            },
            "V"
    );
    static Matrix a2x2_1_4 = new Matrix(
            new double[][]{
                new double[]{1, 2},
                new double[]{3, 4}
            },
            "A"
    );
    static Matrix invA2x2_1_4 = a2x2_1_4.getInverseMatrix();
    
    
    
    static Matrix cA2x2_1_4MulB2x2_1_2_1 = new Matrix(
            Matrix.multiply(a2x2_1_4, b2x2_1_2_1),
            "C"
    );
    
    static Matrix b3x2_1 = new Matrix(
        new double[][]{
            new double[]{1, 1},
            new double[]{1, 1},
            new double[]{1, 1}
        },
        "B"
    );
    
    static Matrix a1x3_1_3 = new Matrix(
        new double[][]{
            new double[]{1, 2, 3}
        },
        "A"
    );
    
    static Matrix b2x2_1 = new Matrix(
        new double[][]{
            new double[]{1, 1},
            new double[]{1, 1}
        },
        "B"
    );
    
    static Matrix m3x3_1_9 = new Matrix(
            new double[][]{
                new double[]{1, 2, 3},
                new double[]{4, 5, 6},
                new double[]{7, 8, 9}
            },
            "M"
    );
    
    static Matrix a2x2_1_4AddB2x2_1_2_1 = Matrix.add(a2x2_1_4, b2x2_1_2_1);
    static Matrix a2x2_1_4SubB2x2_1_2_1 = Matrix.subtract(a2x2_1_4, b2x2_1_2_1);
    static Matrix m2x2_1_0_1_1 = new Matrix(
            new double[][]{
                new double[]{1, 0},
                new double[]{1, 1}
            },
            "M"
    );
    static Matrix b2x2_1_2_1AddM_1_0_1_1 = Matrix.add(b2x2_1_2_1, m2x2_1_0_1_1);
    static Matrix b2x2_1_2_1SubM_1_0_1_1 = Matrix.subtract(b2x2_1_2_1, m2x2_1_0_1_1);
            
    
    static Matrix i3x3 = Matrix.getIdentityMatrix(3);
    
    static Matrix m2x3_1_6 = new Matrix(
            new double[][]{
                new double[]{1, 2, 3},
                new double[]{4, 5, 6}
            },
            "M"
    );
    
    static Matrix a2x2_1_4MulM2x2_1_0_1_1 = Matrix.multiply(a2x2_1_4, m2x2_1_0_1_1);
    static Matrix b2x2_1_2_1MulM2x2_1_0_1_1 = Matrix.multiply(b2x2_1_2_1, m2x2_1_0_1_1);
    static Matrix m2x2_1_0_1_1MulA2x2_1_4 = Matrix.multiply(m2x2_1_0_1_1, a2x2_1_4);
    static Matrix m2x2_1_0_1_1MulB2x2_1_2_1 = Matrix.multiply(m2x2_1_0_1_1, b2x2_1_2_1);
    
    static Matrix obe3x3Mul3R1 = new Matrix(
            new double[][]{
                new double[]{3, 0, 0},
                new double[]{0, 1, 0},
                new double[]{0, 0, 1}
            },
            "OBE3x3Mul3R1"
    );
    static Matrix obe3x3SwitchR1R2 = new Matrix(
            new double[][]{
                new double[]{0, 1, 0},
                new double[]{1, 0, 0},
                new double[]{0, 0, 1}
            },
            "OBE3x3SwitchR1R2"
    );
    static Matrix obe3x3Set2R1Add3R3 = new Matrix(
            new double[][]{
                new double[]{2, 0, 3},
                new double[]{0, 1, 0},
                new double[]{0, 0, 1}
            },
            "OBE3x3Set2R1Add3R3"
    );
    
    static Matrix zero2x2 = Matrix.getZeroMatrix(2, 2);
    static Matrix i2x2 = Matrix.getIdentityMatrix(2);
    
    static Matrix koef2x2 = new Matrix(
            new double[][]{
                new double[]{1, 2},
                new double[]{3, 4}
            },
            "Koefisien"
    );
    static Matrix invKoef2x2 = koef2x2.getInverseMatrix();
    static Matrix hasil2x1 = new Matrix(
            new double[][]{
                new double[]{1},
                new double[]{2}
            },
            "Hasil"
    );
    static Matrix var2x1 = Matrix.multiply(invKoef2x2, hasil2x1);
    
    static Matrix m3x3inv = new Matrix(
            new double[][]{
                new double[]{-2, 4, -5},
                new double[]{1, 3, -7},
                new double[]{0, 4, -8}
            },
            "M"
    );
    
     static TutorialSlide2 minorSlide = new TutorialSlide2("Elemen Minor", 
            new TutorialSection[]{
                new TextSection(
                        "<div align=\"center\">Elemen Minor</div>"
                        + "<br>Elemen minor matriks A pada baris i kan kolom j didefiniskan sebagai determinan matriks A dengan mengecualikan baris i dan kolom j."
                        + "<br>Misal matriks A berordo nxn, maka elemen minor matriks A pada baris i dan kolom j adalah determinan matriks berordo (n-1)x(n-1) karena membuang baris i dan kolom j."
                        + "<br>Contoh, mencari minor<sub>2,1</sub> pada matriks 3x3"
                ),
                new ImageSection("det3x3lm1.png", 30),
                new ImageSection("det3x3lm2.png", 35),
                new ImageSection("det3x3lm3.png", 40),
                new TextSection(
                        "<br>Contoh:"
                ),
                new ImageSection("det3x3lm4.png", 35),
                new ImageSection("det3x3lm5.png", 40),
                new FormButton("Load",
                        m3x3_1_9,
                        MatrixForm.FORM_MINOR
                )
            }
    );
     static TutorialSlide2 cofactorSlide = new TutorialSlide2("Elemen Kofaktor", 
            new TutorialSection[]{
                new TextSection(
                        "<div align=\"center\">Elemen Kofaktor</div>"
                        + "<br>Elemen kofaktor matriks A pada baris i kan kolom j didefiniskan sebagai elemen minor A pada letak yang sama dikalikan dengan (-1)<sup>(i+j)</sup>."
                        + "<br>kofaktor<sub>i,j</sub> = (-1)<sup>(i+j)</sup> * minor<sub>i,j</sub>"
                        + "<br>Contoh, mencari kofaktor<sub>2,1</sub>"
                        + "<br>kofaktor<sub>2,1</sub> = (-1)<sup>(2+1)</sup> * minor<sub>2,1</sub>"
                        + "<br>kofaktor<sub>2,1</sub> = (-1)<sup>(3)</sup> * minor<sub>2,1</sub>"
                        + "<br>kofaktor<sub>2,1</sub> = (-1) * minor<sub>2,1</sub>"
                        + "<br>Contoh menggunakan minor pada matriks sebelumnya:"
                        + "<br>kofaktor<sub>2,1</sub> = (-1) * (-6)"
                        + "<br>kofaktor<sub>2,1</sub> = 6"
                ),
                new FormButton("Load",
                        m3x3_1_9,
                        MatrixForm.FORM_COFACTOR
                )
            }
    );
     
     static TutorialSlide2 identityMatrixSlide = new TutorialSlide2("Sifat Perkalian Matrix - Matriks Identitas", 
            new TutorialSection[]{
                new TextSection(
                        "Dalam perkalian matriks, matriks identitas berlaku seperti angka 1 pada perkalian biasa."
                        + "<br>Itu karena setiap matriks yang dikalikan dengan matriks identitas hasilnya adalah matriks itu sendiri."
                        + "<br>M*I = I*M = M"
                ),
                new ImageSection("multiplyid1.png", 60),
                new OperationButton("Load",
                        1,
                        i3x3,
                        MatrixOperation.OPERATOR_MULTIPLY,
                        1,
                        m3x3_1_9
                ),
                new ImageSection("multiplyid2.png", 60),
                new OperationButton("Load",
                        1,
                        m3x3_1_9,
                        MatrixOperation.OPERATOR_MULTIPLY,
                        1,
                        i3x3
                ),
            }
    );
     
     static TutorialSlide2 inverseMatrixSlide = new TutorialSlide2("Sifat Perkalian Matriks - Matriks Invers", 
            new TutorialSection[]{
                new TextSection(
                        "Invers berlaku seperti pembagi."
                        + "<br>Invers matriks M adalah kebalikan dari matriks M."
                        + "<br>M*M<sup>-1</sup> = M<sup>-1</sup>*M = I"
                ),
                new ImageSection("multiplyinv1.png", 50),
                new OperationButton("Load",
                        1,
                        m2x2_1_4,
                        MatrixOperation.OPERATOR_MULTIPLY,
                        1,
                        invM2x2_1_4
                ),
                new ImageSection("multiplyinv2.png", 50),
                new OperationButton("Load",
                        1,
                        invM2x2_1_4,
                        MatrixOperation.OPERATOR_MULTIPLY,
                        1,
                        m2x2_1_4
                ),
                new TextSection(
                        "Inverse juga berlaku seperti pembagi ketika \"menyebrangi tanda sama dengan\"."
                        + "<br>A * B = C  =>  B = A<sup>-1</sup> * C"
                        + "<br>Karena perkalian matriks tidak komutatif, letaknya sangat berpengaruh."
                        + "<br>Jika bingung, lakukan dengan menambahkan A<sup>-1</sup> di kiri setiap ekspresi."
                        + "<br>A * B = C"
                        + "<br>A<sup>-1</sup> * A * B = A<sup>-1</sup> * C"
                        + "<br>I * B = A<sup>-1</sup> * C"
                        + "<br>B = A<sup>-1</sup> * C"
                ),
                new ImageSection("multiplyinv3.png", 50),
                new OperationButton("Load",
                        1,
                        a2x2_1_4,
                        MatrixOperation.OPERATOR_MULTIPLY,
                        1,
                        b2x2_1_2_1
                ),
                new ImageSection("multiplyinv4.png", 50),
                new OperationButton("Load",
                        1,
                        invA2x2_1_4,
                        MatrixOperation.OPERATOR_MULTIPLY,
                        1,
                        cA2x2_1_4MulB2x2_1_2_1
                )
                
            }
    );
     
     static TutorialSlide2 obeSlide = new TutorialSlide2("Operasi Baris Elementer/Eliminasi Gauss-Jordan",
            new TutorialSection[]{
                new TextSection(
                        "<div align=\"center\">Operasi Baris Elementer/Eleminasi Gauss-Jordan</div>"
                        + "<br>Operasi baris elementer adalah operasi-operasi pada baris suatu matriks yang dapat direpresentasikan dengan matriks transformasi."
                        + "<br>Operasi baris elementer meliputi:"
                        + "<br>1. Perkalian baris dengan suatu koefisien tidak nol"
                        + "<br>2. Penukaran baris"
                        + "<br>3. Penjumlahan/pengurangan baris dengan baris lainnya"
                )
            }
    );
     
     static TutorialSlide2 obeMulSlide = new TutorialSlide2("OBE - Perkalian Baris",
             new TutorialSection[]{
                 new TextSection(
                        "<div align=\"center\">OBE - Perkalian Baris</div>"
                        + "<br>Perkalian suatu baris dengan suatu koefisien tidak nol"
                        + "<br>Contoh:"
                 ),
                 new ImageSection("obemul1.png", 70),
                 new TextSection("Dan sebagai matriks transformasi:"),
                 new ImageSection("obemul2.png", 80),
                 new OperationButton("Load",
                         1,
                         obe3x3Mul3R1,
                         MatrixOperation.OPERATOR_MULTIPLY,
                         1,
                         m3x3_1_9
                 )
             }
     );
     
     static TutorialSlide2 obeSwitchSlide = new TutorialSlide2("OBE - Penukaran Baris",
             new TutorialSection[]{
                 new TextSection(
                        "<div align=\"center\">OBE - Penukaran Baris</div>"
                        + "<br>Perkalian suatu baris dengan suatu koefisien tidak nol"
                        + "<br>Contoh:"
                 ),
                 new ImageSection("obeswitch1.png", 60),
                 new TextSection("Dan sebagai matriks transformasi:"),
                 new ImageSection("obeswitch2.png", 80),
                 new OperationButton("Load",
                         1,
                         obe3x3SwitchR1R2,
                         MatrixOperation.OPERATOR_MULTIPLY,
                         1,
                         m3x3_1_9
                 )
             }
     );
     
     static TutorialSlide2 obeSetSlide = new TutorialSlide2("OBE - Penjumlahan/Pengurangan Baris",
             new TutorialSection[]{
                 new TextSection(
                        "<div align=\"center\">OBE - Penjumlahan/Pengurangan Baris</div>"
                        + "<br>Penjumlahan/pengurangan baris dengan baris lainnya"
                        + "<br>Contoh:"
                 ),
                 new ImageSection("obeset1.png", 80),
                 new TextSection("Dan sebagai matriks transformasi:"),
                 new ImageSection("obeset2.png", 80),
                 new OperationButton("Load",
                         1,
                         obe3x3Set2R1Add3R3,
                         MatrixOperation.OPERATOR_MULTIPLY,
                         1,
                         m3x3_1_9
                 )
             }
     );
     
     static TutorialSlide2 compSlide = new TutorialSlide2("Matriks Komposisi",
             new TutorialSection[]{
                 new TextSection(
                        "<div align=\"center\">Matriks Komposisi</div>"
                        + "<br>Matriks transformasi digunakan dengan mengalikannya dengan matriks yang ditransformasi, dimana matris transformasi ada di sebelah kiri tanda kali."
                        + "<br>Misalkan matriks A dan B adalah matriks transformasi yang akan digunakan pada matriks M dengan urutan A lalu B"
                        + "<br>M1 adalah matriks M setelah ditransformasi oleh A --- M1 = A*M"
                        + "<br>M2 adalah matriks M setelah ditransformasi oleh A lalu B --- M2 = B*M1"
                        + "<br>Sehingga, M2 = B*(A*M) --- urutan penggunaan transformasi adalah dari kanan ke kiri"
                        + "<br>Perkalian matriks berlaku sifat asosiatif, yang berarti B*(A*M) = B*A*M = (B*A)*M"
                        + "<br>Dengan begitu, kita dapat menentukan <b>satu</b> matriks yang dapat langsung mentransformasikan M dengan A lalu B sekaligus --- C = B*A"
                        + "<br>C adalah matriks komposisi untuk transformasi A lalu B"
                        + "<br>Contoh:"
                 ),
                 new ImageSection("comp1.png", 80),
                 new HorizontalLayoutSection(
                         new TutorialSection[]{
                             new OperationButton("M1",
                                     1,
                                     a2x2_0_1_0,
                                     MatrixOperation.OPERATOR_MULTIPLY,
                                     1,
                                     m2x2_1_4
                             ),
                             new OperationButton("M2 (B*M1)",
                                     1,
                                     b2x2_3_0_1,
                                     MatrixOperation.OPERATOR_MULTIPLY,
                                     1,
                                     m1A2x2_0_1_0MulM2x2_1_4
                             ),
                             new OperationButton("C",
                                     1,
                                     b2x2_3_0_1,
                                     MatrixOperation.OPERATOR_MULTIPLY,
                                     1,
                                     a2x2_0_1_0
                             ),
                             new OperationButton("M2 (C*M)",
                                     1,
                                     cB2x2_3_0_1MulA2x2_0_1_0,
                                     MatrixOperation.OPERATOR_MULTIPLY,
                                     1,
                                     m2x2_1_4
                             ),  
                         }
                 )
             }
     );
     
     static TutorialSlide2 obeSlide2 = new TutorialSlide2("Operasi Baris Elementer (2)",
            new TutorialSection[]{
                new TextSection(
                "<div align=\"center\">Operasi Baris Elementer (2)</div>"
                        + "<br>OBE dilakukan dengan menggunakan <b>paling tidak 2 matriks dengan jumlah baris yang sama</b>,"
                        + "<br>dimana paling tidak <b>satu dari mereka adalah matriks persegi yang akan menjadi matriks utama</b>"
                        + "<br>OBE dilakukan dengan menggunakan ketiga operasi tersebut untuk <b>membuat matriks utama menjadi matriks identitas</b>"
                        + "<br>Pertama-tama dengan membuat elemen yang <b>bukan diagonal utama</b> menjadi 0"
                        + "<br>Dilanjutkan dengan membuat elemen <b>diagonal utama</b> menjadi 1"
                        + "<br>Jika elemen diagonal utama bernilai 0, berarti baris tersebut harus ditukar dengan baris lain"
                        + "<br> yang elemen pada kolom yang sama tidak nol, agar elemen diagonal utama tidak ada yang 0"
                        + "<br>Misalkan matriks utama adalah A dan matriks lainnya adalah B."
                        + "<br>Setiap operasi yang dilakukan pada A juga harus dilakukan pada B."
                        + "<br>Ketika A sudah menjadi matriks identitas, maka matriks komposisi dari setiap operasi yang dilakukan adalah A<sup>-1</sup>, karena A<sup>-1</sup> * A = I"
                        + "<br>Ini berarti dengan OBE, kita mengalikan A dengan A<sup>-1</sup>"
                        + "<br>Karena kita juga melakukan hal yang sama pada B, maka kita juga mengalikan A<sup>-1</sup> pada B"
                        + "<br>A<sup>-1</sup> * B ini lah hasil akhir dari OBE"
                )
            }
    );
    
    public static Tutorial2[] tutorials = new Tutorial2[]{
        new Tutorial2("Introduction", 
                new TutorialSlide2[]{
                    new TutorialSlide2("Matriks", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Matriks</div>"
                                        + "<br>Matriks adalah susunan bilangan, simbol, atau ekspresi, yang disusun dalam baris dan kolom sehingga membentuk suatu bangun persegi."
                                        + "<br>Contoh matriks: "
                                ),
                                new ImageSection("intro1.png", 30),
                                new TextSection(
                                        "Dalam program ini, matriks direpresentasikan dalam tabel."
                                        + "<br>Contoh:"
                                ),
                                new MatrixSection2(m2x3_1_6)
                            }
                    ),
                    new TutorialSlide2("Ordo Matriks", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Ordo Matriks</div>"
                                        + "<br>Ordo matriks adalah jumlah baris dan kolom suatu matriks dengan format baris x kolom"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("intro2.png", 50)
                            }
                    ),
                    new TutorialSlide2("Elemen Matriks", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Elemen Matriks</div>"
                                        + "<br>Elemen matriks adalah anggota individual dalam matriks"
                                        + "<br>Elemen matriks M biasanya dilambangkan dengan notasi m<sub>i,j</sub> dimana m<sub>i,j</sub> adalah elemen matriks M pada baris ke-i dan kolom ke-j."
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("intro3.png", 45)
                            }
                    ),
                }
        ),
        new Tutorial2("Macam-Macam Matriks", 
                new TutorialSlide2[]{
                    new TutorialSlide2("Berdasarkan ordonya", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Macam-Macam Matriks Berdasarkan Ordonya</div>"
                                        + "<br>1. Matriks persegi"
                                        + "<br>   Matriks yang jumlah baris dan kolomnya sama"
                                        + "<br>   Berordo nxn"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kinda1.png", 20),
                                new TextSection(
                                        "2. Matriks baris"
                                        + "<br>   Matriks yang hanya terdiri dari 1 baris saja"
                                        + "<br>   Berordo 1xn dimana n > 1"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kinda2.png", 30),
                                new TextSection(
                                        "3. Matriks kolom"
                                        + "<br>   Matriks yang hanya terdiri dari 1 kolom saja"
                                        + "<br>   Berordo nx1 dimana n > 1"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kinda3.png", 10),
                                new TextSection(
                                        "4. Matriks mendatar"
                                        + "<br>   Matriks berordo mxn dimana m < n dan m > 1"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kinda4.png", 30),
                                new TextSection(
                                        "5. Matriks tegak"
                                        + "<br>   Matriks berordo mxn dimana m > n dan n > 1"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kinda5.png", 20)
                            }
                    ),
                    new TutorialSlide2("Berdasarkan Elemennya", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Macam-Macam Matriks Berdasarkan Elemennya</div>"
                                        + "<br>1. Matriks nol"
                                        + "<br>   Matriks yang semua elemennya nol"
                                        + "<br>   Berordo mxn"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kindb1.png", 30),
                                new TextSection(
                                        "2. Matriks segitiga (atas/bawah)"
                                        + "<br>   Matriks <b>persegi</b> yang semua elemen di atas atau di bawah diagonal utamanya terisi nol"
                                        + "<br>   Berordo nxn"
                                        + "<br>   Contoh:"
                                ),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new ImageSection("kindb2.png", 20),
                                            new ImageSection("kindb3.png", 30)
                                        }
                                ),
                                new TextSection(
                                        "3. Matriks simetris"
                                        + "<br>   Matriks <b>persegi</b> yang elemen di atas diagonal utamanya dicerminkan oleh diagonal utama ke elemen di bawah diagonal utama"
                                        + "<br>   Untuk setiap 1 <= i <= n, 1 <= j <= n, dan i =/= j, m<sub>i,j</sub> = m<sub>j,i</sub>"
                                        + "<br>   Berordo nxn"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kindb4.png", 30),
                                new TextSection(
                                        "4. Matriks diagonal"
                                        + "<br>   Matriks <b>persegi</b> yang hanya terisi pada diagonal utama, sedangkan elemen lainnya nol"
                                        + "<br>   Diagonal utama adalah diagonal dari pojok kiri atas hingga pojok kanan bawah"
                                        + "<br>   Berordo nxn"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kindb5.png", 20),
                                new TextSection(
                                        "5. Matriks skalar"
                                        + "<br>   Matriks <b>diagonal</b> dimana setiap elemen diagonal utamanya sama"
                                        + "<br>   Berordo nxn"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kindb6.png", 30),
                                new TextSection(
                                        "6. Matriks Identitas"
                                        + "<br>   Matriks <b>skalar</b> dimana setiap elemen diagonal utamanya bernilai 1"
                                        + "<br>   Berordo nxn"
                                        + "<br>   Suatu matriks persegi bila dikalikan matriks identitas maka hasilnya adalah matriks itu sendiri."
                                        + "<br>   M * I = I * M = M"
                                        + "<br>   Contoh:"
                                ),
                                new ImageSection("kindb7.png", 30)
                            }
                    )    
                }
        ),
        new Tutorial2("Operasi", 
                new TutorialSlide2[]{
                    new TutorialSlide2("Dengan Skalar", 
                            new TutorialSection[]{
                                new TextSection("<div align=\"center\">Operasi Matriks dengan Skalar</div>"
                                        + "<br>Operasi matriks dengan skalar secara resmi hanya perkalian."
                                        + "<br>Perkalian matriks-skalar dilakukan dengan mengalikan tiap elemen matriks dengan besaran skalar tersebut"
                                        + "<br>Program ini menyediakan operasi matriks-skalar selain perkalian hanya untuk memudahkan perhitungan jika diperlukan"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("scalar1.png", 100),
                                new OperationButton(
                                        "Load",
                                        1,
                                        a2x2_1_4,
                                        MatrixOperation.OPERATOR_MULTIPLY,
                                        2,
                                        null
                                )
                            }
                    ),
                    new TutorialSlide2("Dengan Matriks", 
                            new TutorialSection[]{
                                new TextSection("<div align=\"center\">Operasi Matriks dengan Matriks</div>"
                                        + "<br>Operasi matriks dengan matriks ada tiga, yaitu penjumlahan, pengurangan, dan perkalian."
                                        + "<br>Materi ini akan dibagi menjadi 2 bagian, yaitu bagian penjumlahan dan pengurangan, dan bagian perkalian."
                                ),
                            }
                    ),
                    new TutorialSlide2("Dengan Matriks - Penjumlahan dan Pengurangan", 
                            new TutorialSection[]{
                                new TextSection("<div align=\"center\">Operasi Matriks dengan Matriks - Penjumlahan dan Pengurangan</div>"
                                        + "<br>Penjumlahan dan pengurangan antar matriks dilakukan dengan melakukannya pada tiap elemen pada posisi yang sama."
                                        + "<br>Karena itu, penjumlahan dan pengurangan antar matriks hanya dapat dilakukan jika kedua matriks <b>berordo sama</b>."
                                        + "<br>A<sub>mxn</sub> &plusmn; B<sub>mxn</sub> = C<sub>mxn</sub>"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("add1.png", 80),
                                new OperationButton(
                                        "Load",
                                        1,
                                        a2x2_1_4,
                                        MatrixOperation.OPERATOR_ADD,
                                        1,
                                        b2x2_1
                                ),
                                new ImageSection("subtract1.png", 80),
                                new OperationButton(
                                        "Load",
                                        1,
                                        a2x2_1_4,
                                        MatrixOperation.OPERATOR_SUBTRACT,
                                        1,
                                        b2x2_1
                                )
                            }
                    ),
                    new TutorialSlide2("Dengan Matriks - Perkalian", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Operasi Matriks dengan Matriks</div>"
                                        + "<br>Operasi perkalian antar matriks hanya dapat dilakukan jika <b>jumlah kolom matriks pertama sama dengan jumlah baris matriks kedua</b>."
                                        + "<br>A<sub>mxn</sub> * B<sub>nxo</sub> = C<sub>mxo</sub>"
                                        + "<br>Dimana tiap elemen C sebagai hasilnya adalah:"
                                        + "<br>C<sub>i,j</sub> = &sum;<sup>n</sup><sub>k=1</sub>(A<sub>i,k</sub> * B<sub>k,j</sub>)"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("multiply1.png", 60),
                                new ImageSection("multiply2.png", 60),
                                new ImageSection("multiply3.png", 60),
                                new ImageSection("multiply4.png", 60),
                                new OperationButton(
                                        "Load",
                                        1,
                                        a1x3_1_3,
                                        MatrixOperation.OPERATOR_MULTIPLY,
                                        1,
                                        b3x2_1
                                )
                            }
                    ),
                }
        ),
        new Tutorial2("Sifat Operasi Matriks",
                new TutorialSlide2[]{
                    new TutorialSlide2("Sifat Penjumlahan dan Pengurangan", 
                            new TutorialSection[]{
                                new TextSection("<div align=\"center\">Sifat Penjumlahan dan Pengurangan antar Matriks</div>"
                                        + "<br>Karena penjumlahan dan pengurangan antarmatriks hanya menjumlahkan dan mengurangkan seperti biasa pada tiap elemen,"
                                        + "<br>mereka bersifat sama dengan penjumlahan dan pengurangan biasa."
                                        + "<br>Penjumlahan bersifat asosiatif dan komutatif, sedangkan pengurangan bersifat tidak bersifat asosiatif maupun komutatif"
                                        + "<br>Asosiatif: A&plusmn;B&plusmn;C = (A&plusmn;B)&plusmn;C = A&plusmn;(B&plusmn;C)"
                                        + "<br>Komutatif: A+B=B+A"
                                ),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new TextSection("Penjumlahan Komutatif: "),
                                            new OperationButton("A+B",
                                                    1,
                                                    a2x2_1_4,
                                                    MatrixOperation.OPERATOR_ADD,
                                                    1,
                                                    b2x2_1_2_1
                                            ),
                                            new OperationButton("B+A",
                                                    1,
                                                    b2x2_1_2_1,
                                                    MatrixOperation.OPERATOR_ADD,
                                                    1,
                                                    a2x2_1_4
                                            )
                                        },
                                        GridBagConstraints.WEST
                                ),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new TextSection("Pengurangan Tidak Komutatif: "),
                                            new OperationButton("A-B",
                                                    1,
                                                    a2x2_1_4,
                                                    MatrixOperation.OPERATOR_SUBTRACT,
                                                    1,
                                                    b2x2_1_2_1
                                            ),
                                            new OperationButton("B-A",
                                                    1,
                                                    b2x2_1_2_1,
                                                    MatrixOperation.OPERATOR_SUBTRACT,
                                                    1,
                                                    a2x2_1_4
                                            )
                                        },
                                        GridBagConstraints.WEST
                                ),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new TextSection("Penjumlahan Asosiatif: "),
                                            new OperationButton("(A+B)+M",
                                                    1,
                                                    a2x2_1_4AddB2x2_1_2_1,
                                                    MatrixOperation.OPERATOR_ADD,
                                                    1,
                                                    m2x2_1_0_1_1
                                            ),
                                            new OperationButton("A+(B+M)",
                                                    1,
                                                    a2x2_1_4,
                                                    MatrixOperation.OPERATOR_ADD,
                                                    1,
                                                    b2x2_1_2_1AddM_1_0_1_1
                                            )
                                        },
                                        GridBagConstraints.WEST
                                ),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new TextSection("Pengurangan Tidak Asosiatif: "),
                                            new OperationButton("(A-B)-M",
                                                    1,
                                                    a2x2_1_4SubB2x2_1_2_1,
                                                    MatrixOperation.OPERATOR_SUBTRACT,
                                                    1,
                                                    m2x2_1_0_1_1
                                            ),
                                            new OperationButton("A-(B-M)",
                                                    1,
                                                    a2x2_1_4,
                                                    MatrixOperation.OPERATOR_SUBTRACT,
                                                    1,
                                                    b2x2_1_2_1SubM_1_0_1_1
                                            )
                                        },
                                        GridBagConstraints.WEST
                                ),
                            }
                    ),
                    new TutorialSlide2("Sifat Perkalian Matriks", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Sifat Perkalian Matriks-Matriks</div>"
                                        + "<br>Perkalian antar matriks <b>tidak</b> berlaku komutatif:"
                                        + "<br>A*B <b>tidak</b> pasti sama dengan B*A."
                                ),
                                new ImageSection("multiplycom1.png", 50),
                                new OperationButton("Load",
                                        1,
                                        a2x2_1_4,
                                        MatrixOperation.OPERATOR_MULTIPLY,
                                        1,
                                        b2x2_1_2_1
                                ),
                                new ImageSection("multiplycom2.png", 50),
                                new OperationButton("Load",
                                        1,
                                        b2x2_1_2_1,
                                        MatrixOperation.OPERATOR_MULTIPLY,
                                        1,
                                        a2x2_1_4
                                ),
                                new TextSection(
                                        "Selain itu, perkalian antar matriks bersifat distributif: "
                                        + "<br>(A&plusmn;B)*M = AM &plusmn; BM"
                                        + "<br>M*(A&plusmn;B) = MA &plusmn; MB"
                                        + "<br>Contoh 1:"
                                ),
                                new ImageSection("multiplydis1.png", 70),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new OperationButton("A+B",
                                                    1,
                                                    a2x2_1_4,
                                                    MatrixOperation.OPERATOR_ADD,
                                                    1,
                                                    b2x2_1_2_1
                                            ),
                                            new OperationButton("(A+B)*M",
                                                    1,
                                                    a2x2_1_4AddB2x2_1_2_1,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    m2x2_1_0_1_1
                                            )
                                        }
                                ),
                                new ImageSection("multiplydis2.png", 70),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new OperationButton("A*M",
                                                    1,
                                                    a2x2_1_4,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    m2x2_1_0_1_1
                                            ),
                                            new OperationButton("B*M",
                                                    1,
                                                    b2x2_1_2_1,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    m2x2_1_0_1_1
                                            ),
                                            new OperationButton("(A*M)+(B*M)",
                                                    1,
                                                    a2x2_1_4MulM2x2_1_0_1_1,
                                                    MatrixOperation.OPERATOR_ADD,
                                                    1,
                                                    b2x2_1_2_1MulM2x2_1_0_1_1
                                            )
                                        }
                                ),
                                new TextSection("Contoh 2:"),
                                new ImageSection("multiplydis3.png", 70),
                                new ImageSection("multiplydis1.png", 70),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new OperationButton("A+B",
                                                    1,
                                                    a2x2_1_4,
                                                    MatrixOperation.OPERATOR_ADD,
                                                    1,
                                                    b2x2_1_2_1
                                            ),
                                            new OperationButton("M*(A+B)",
                                                    1,
                                                    m2x2_1_0_1_1,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    a2x2_1_4AddB2x2_1_2_1
                                            ),
                                            new OperationButton("(A+B)*M",
                                                    1,
                                                    a2x2_1_4AddB2x2_1_2_1,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    m2x2_1_0_1_1
                                            )
                                        }
                                ),
                                new ImageSection("multiplydis4.png", 70),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new OperationButton("M*A",
                                                    1,
                                                    m2x2_1_0_1_1,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    a2x2_1_4
                                            ),
                                            new OperationButton("M*B",
                                                    1,
                                                    m2x2_1_0_1_1,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    b2x2_1_2_1
                                            ),
                                            new OperationButton("(M*A)+(M*B)",
                                                    1,
                                                    m2x2_1_0_1_1MulA2x2_1_4,
                                                    MatrixOperation.OPERATOR_ADD,
                                                    1,
                                                    m2x2_1_0_1_1MulB2x2_1_2_1
                                            )
                                        }
                                ),
                            }
                    ),
                    identityMatrixSlide,
                    inverseMatrixSlide,
                    new TutorialSlide2("Sifat Operasi Matrix - Matriks Nol", 
                            new TutorialSection[]{
                                new TextSection(
                                        "Tentunya matriks nol berlaku seperti angka nol dalam setiap operasi matriks."
                                        + "<br>M*0 = 0*M = 0"
                                        + "<br>M+0 = 0+M = M"
                                        + "<br>M-0 = M"
                                        + "<br>0-M = -M"
                                ),
                                new ImageSection("multiplyzero1.png", 50),
                                new OperationButton("Load",
                                        1,
                                        a2x2_1_4,
                                        MatrixOperation.OPERATOR_MULTIPLY,
                                        1,
                                        zero2x2
                                ),
                                new ImageSection("multiplyzero2.png", 50),
                                new OperationButton("Load",
                                        1,
                                        zero2x2,
                                        MatrixOperation.OPERATOR_MULTIPLY,
                                        1,
                                        a2x2_1_4
                                ),
                                new ImageSection("addzero1.png", 50),
                                new OperationButton("Load",
                                        1,
                                        a2x2_1_4,
                                        MatrixOperation.OPERATOR_ADD,
                                        1,
                                        zero2x2
                                ),
                                new ImageSection("subtractzero1.png", 55),
                                new OperationButton("Load",
                                        1,
                                        a2x2_1_4,
                                        MatrixOperation.OPERATOR_SUBTRACT,
                                        1,
                                        zero2x2
                                ),
                                new ImageSection("subtractzero2.png", 55),
                                new OperationButton("Load",
                                        1,
                                        zero2x2,
                                        MatrixOperation.OPERATOR_SUBTRACT,
                                        1,
                                        a2x2_1_4
                                ),
                            }
                    )
                }
        ),
        new Tutorial2("Determinan", 
                new TutorialSlide2[]{
                    new TutorialSlide2("Determinan", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Determinan</div>"
                                        + "<br>Determinan adalah nilai yang dapat dihitung dari suatu <b>matriks persegi</b>. (wikipedia)"
                                        + "<br>Jadi, syarat agar suatu matriks memiliki determinan adalah matriks itu harus merupakan matriks persegi, yaitu matriks berordo nxn"
                                        + "<br>Matriks yang determinannya 0 disebut juga matriks singular, yaitu matriks yang tidak memiliki invers."
                                        + "<br>Determinan matriks A dilambangkan dengan det(A) atau |A|"
                                        + "<br>Determinan suatu matriks juga dapat dilambangkan dengan matriks itu sendiri dengan mengganti tanda kurungnya menjadi garis tegak"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("det2x21.png", 20.0)
                            }
                    ),
                    new TutorialSlide2("Determinan Matriks 1x1", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Determinan Matriks 1x1</div>"
                                        + "<br>Determinan matriks 1x1 adalah nilai pada matriks itu sendiri"
                                ),
                                new DeterminantButton("Contoh",
                                        new Matrix(
                                                new double[][]{new double[]{2}},
                                                "2"
                                        ),
                                        true,
                                        0
                                ) 
                            }
                    ),
                    new TutorialSlide2("Determinan Matriks 2x2", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Determinan Matriks 2x2</div>"
                                        + "<br>Misal matriks A:"
                                ),
                                new ImageSection("det2x21.png", 20.0),
                                new TextSection(
                                        "Maka det(A) = ad-bc"
                                ),
                                new ImageSection("det2x22.png", 20.0),
                                new TextSection(
                                        "Contoh:"
                                ),
                                new ImageSection("det2x23.png", 50.0),
                                new DeterminantButton("Load",
                                        a2x2_1_4,
                                        true,
                                        0
                                )
                            }
                    ),
                    new TutorialSlide2("Determinan Matriks 3x3 - Sarrus", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Determinan Matriks 3x3 - Sarrus</div>"
                                        + "<br>Misal matriks A:"
                                ),
                                new ImageSection("det3x3s1.png", 30),
                                new TextSection(
                                        "Salin/copy kolom pertama dan kedua ke kanan"
                                ),
                                new ImageSection("det3x3s2.png", 50),
                                new TextSection(
                                        "det(A) = (jumlah hasil kali elemen diagonal kiri atas ke kanan bawah) - (jumlah hasil kali elemen diagonal kiri bawah ke kanan atas)"
                                ),
                                new ImageSection("det3x3s3.png", 60),
                                new TextSection(
                                        "<div align=\"center\">det(A) = <font color=\"orange\">(aei+bfg+cdh)</font> – <font color=\"green\">(ceg+afh+bdi)</font></div>"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("det3x3s4.png", 30),
                                new ImageSection("det3x3s5.png", 50),
                                new ImageSection("det3x3s6.png", 60),
                                new TextSection(
                                        "det(A) = <font color=\"orange\">(1*5*9 + 2*6*7 + 3*4*8)</font> – <font color=\"green\">(3*5*7 + 1*6*8 + 2*4*9)</font>"
                                        + "<br>det(A) = <font color=\"orange\">(45+84+96)</font> – <font color=\"green\">(105+48+72)</font>"
                                        + "<br>det(A) = <font color=\"orange\">225</font> – <font color=\"green\">225</font>"
                                        + "<br>det(A) = 0"
                                        + "<br>Matriks yang determinannya 0 disebut juga matriks singular, yaitu matriks yang tidak memiliki invers."
                                ),
                                new DeterminantButton("Load",
                                        m3x3_1_9,
                                        true,
                                        0
                                )
                            }
                    ),
                    new TutorialSlide2("Determinan Matriks 3x3 - Laplace/Kofaktor (Pengantar)", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Elemen Minor dan Kofaktor</div>"
                                        + "<br>Sebelum dapat menghitung dengan cara laplace/kofaktor, kita perlu mengetahui dulu apa itu elemen minor dan kofaktor dan bagaimana mencarinya"
                                        + "<br>Selain nilai elemen matriks itu sendiri, elemen matriks juga memiliki minor dan kofaktor."
                                        + "<br>Elemen minor dan kofaktor hanya dimiliki oleh matriks persegi yang jumlah kolom/barisnya lebih besar dari 1"
                                ),
                            }
                    ),
                    minorSlide,
                    cofactorSlide,
                    new TutorialSlide2("Determinan Matriks 3x3 - Laplace/Kofaktor", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Determinan Matriks 3x3 - Laplace/Kofaktor</div>"
                                        + "<br>Setelah mengetahui apa itu elemen minor dan kofaktor, kini kita dapat menghitung determinan matriks 3x3 dengan cara Laplace/kofaktor."
                                        + "<br>1. Pilihlah salah satu baris atau kolom"
                                        + "<br>2. Carilah <b>kofaktor</b> dari tiap elemen pada baris atau kolom yang dipilih"
                                        + "<br>3. Kalikan elemen pada baris/kolom yang dipilih dengan kofaktornya, lalu jumlahkan hasilnya. Hasil penjumlahan inilah determinan dari matriks A"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("det3x3l1.png", 30),
                                new TextSection(
                                        "<br>det = m<sub>1,1</sub> * kof<sub>1,1</sub> + m<sub>1,2</sub> + kof<sub>1,2</sub> + m<sub>1,3</sub> * kof<sub>1,3</sub>"
                                        + "<br>det = a * (-1)<sup>(1+1)</sup> * minor<sub>1,2</sub> + b * (-1)<sup>(1+2)</sup> * minor<sub>1,2</sub> + c * (-1)<sup>(1+3)</sup> * minor<sub>1,3</sub>"
                                ),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new TextSection("det = a * 1 * "),
                                            new ImageSection("det3x3l2.png", 10),
                                            new TextSection("+ b * (-1) * "),
                                            new ImageSection("det3x3l3.png", 10),
                                            new TextSection("+ c * 1 * "),
                                            new ImageSection("det3x3l4.png", 10)
                                        },
                                        GridBagConstraints.WEST
                                ),
                                new TextSection(
                                        "Contoh:"
                                ),
                                new ImageSection("det3x3l5.png", 30),
                                new TextSection(
                                        "<br>det = m<sub>1,1</sub> * kof<sub>1,1</sub> + m<sub>1,2</sub> + kof<sub>1,2</sub> + m<sub>1,3</sub> * kof<sub>1,3</sub>"
                                        + "<br>det = 1 * (-1)<sup>(1+1)</sup> * minor<sub>1,2</sub> + 2 * (-1)<sup>(1+2)</sup> * minor<sub>1,2</sub> + 3 * (-1)<sup>(1+3)</sup> * minor<sub>1,3</sub>"
                                ),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new TextSection("det = 1 * 1 * "),
                                            new ImageSection("det3x3l6.png", 10),
                                            new TextSection("+ 2 * (-1) * "),
                                            new ImageSection("det3x3l7.png", 10),
                                            new TextSection("+ 3 * 1 * "),
                                            new ImageSection("det3x3l8.png", 10)
                                        },
                                        GridBagConstraints.WEST
                                ),
                                new TextSection(
                                        "det = 1 * (5*9-6*8) - 2 * (4*9-6*7) + 3 * (4*8-5*7)"
                                        + "<br>det = 1 * (45-48) - 2 * (36-42) + 3 * (32-35)"
                                        + "<br>det = 1 * (-3) - 2 * (-6) + 3 * (-3)"
                                        + "<br>det = -3 + 12 - 9"
                                        + "<br>det = 0"
                                ),
                                new DeterminantButton("Load",
                                        m3x3_1_9,
                                        true, 0
                                )
                            }
                    ),
                    new TutorialSlide2("Determinan Matriks nxn - Laplace/Kofaktor", 
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Determinan Matriks nxn - Laplace/Kofaktor</div>"
                                        + "<br>Cara Laplace/Kofaktor juga dapat digunakan pada matriks persegi lebih dari 3x3 tanpa mengubah rumus."
                                        + "<br>Perhitungan dilakukan secara rekursif, dimana determinan matriks nxn akan menghitung determinan submatriksnya"
                                        + "<br>yang berordo (n-1)x(n-1) untuk menghitung elemen minornya."
                                        + "<br>Rekursi akan terus berlangsung hingga mendapatkan determinan matriks 1x1 atau 2x2 yang mudah dihitung."
                                        + "<br>Setelah itu, barulah proses hitung tiap tingkat dilanjutkan hingga kembali ke tingkat nxn."
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("detnxn1.png", 80),
                                new TextSection("<hr>"),
                                new ImageSection("detnxn2.png", 80),
                                new TextSection("<hr>"),
                                new ImageSection("detnxn3.png", 80),
                                new TextSection("<hr>"),
                                new ImageSection("detnxn4.png", 80),
                                new TextSection("<hr>"),
                                new ImageSection("detnxn5.png", 80),
                                new DeterminantButton("Load",
                                        new Matrix(
                                                new double[][]{
                                                    new double[]{1, 2, 3, 4},
                                                    new double[]{5, 6, 7, 8},
                                                    new double[]{8, 7, 6, 5},
                                                    new double[]{4, 3, 2, 1}
                                                },
                                                "M"
                                        ),
                                        true,
                                        0
                                )
                            }
                    )
                }
        ),
        new Tutorial2("Bentuk-Bentuk Matriks",
                new TutorialSlide2[]{
                    new TutorialSlide2("Transpose",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Matriks Transpose</div>"
                                        + "<br>Matriks transpose adalah matriks yang ditukar baris dan kolomnya."
                                        + "<br>Secara matematis, transpose dari matriks A dilambangkan dengan A<sup>t</sup>"
                                        + "<br>Dimana:"
                                        + "<br>(A<sup>t</sup>)<sub>mxn</sub> = (A<sub>nxm</sub>)<sup>t</sup>"
                                        + "<br>A<sup>t</sup><sub>i,j</sub> = A<sub>j,i</sub>"
                                ),
                                new ImageSection("formt1.png", 50),
                                new FormButton("Contoh",
                                        m2x3_1_6,
                                        MatrixForm.FORM_TRANSPOSE
                                )
                            }
                    ),
                    new TutorialSlide2("Magnitude",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Magnitude</div>"
                                        + "<br>Sebenarnya ini bukan salah satu bentuk matriks."
                                        + "<br>Magnitude yang dimaksud di sini adalah panjang suatu vektor, yang direpresentasikan dalam matriks kolom"
                                        + "<br>Panjang vektor v dilambangkan dengan |v|"
                                ),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new TextSection("Misalnya untuk vektor 3D v ="),
                                            new ImageSection("formmag1.png", 7.5)
                                        },
                                        GridBagConstraints.WEST
                                ),
                                new TextSection(
                                        "|v| = &radic;(v<sub>1</sub><sup>2</sup>+v<sub>2</sub><sup>2</sup>+v<sub>3</sub><sup>2</sup>)"
                                ),
                                new FormButton("Contoh",
                                        v3x1_1_3,
                                        MatrixForm.FORM_MAGNITUDE
                                )
                            }
                    ),
                    new TutorialSlide2("Minor",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Matriks Minor</div>"
                                        + "<br>Matriks minor dari matriks M adalah matriks yang setiap elemennya adalah elemen minor matriks M pada letak yang sesuai."
                                        + "<br>Karena matriks yang memiliki elemen minor hanya matriks persegi, maka matriks yang mempunyai bentuk minor pun hanya matriks persegi."
                                ),
                            },
                            minorSlide
                    ),
                    new TutorialSlide2("Kofaktor",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Matriks Kofaktor</div>"
                                        + "<br>Matriks kofaktor dari matriks M adalah matriks yang setiap elemennya adalah elemen kofaktor matriks M pada letak yang sesuai."
                                        + "<br>Karena kofaktor berasal dari minor, dan matriks yang memiliki elemen minor hanya matriks persegi,"
                                        + "<br>maka matriks yang mempunyai bentuk minor pun hanya matriks persegi."
                                ),
                            },
                            cofactorSlide
                    ),
                    new TutorialSlide2("Adjoint",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Matriks Adjoin</div>"
                                        + "<br>Matriks adjoin dari matriks M adalah transpose dari matriks kofaktor dari matriks M."
                                        + "<br>Karena berasal dari matriks kofaktor, kofaktor berasal dari minor, dan matriks yang memiliki elemen minor hanya matriks persegi,"
                                        + "<br>maka matriks yang mempunyai bentuk minor pun hanya matriks persegi."
                                ),
                                new FormButton("Contoh",
                                        m3x3_1_9,
                                        MatrixForm.FORM_ADJOINT
                                )
                            }
                    )
                }
        ),
        new Tutorial2("Invers Matriks",
                new TutorialSlide2[]{
                    new TutorialSlide2("Inverse dengan Adjoin",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Inverse dengan Adjoin</div>"
                                        + "<br>Inverse suatu matriks dapat dihitung dengan menggunakan determinan dan adjoin matriks dengan rumus:"
                                        + "<br>M<sup>-1</sup> = (1/|M|) * adj(M)"
                                        + "<br>Karena itu pula, syarat agar suatu matriks memiliki inverse adalah matriks tersebut <b>harus memiliki determinan tidak nol</b>."
                                        + "<br>Penjelasan mengenai adjoin, kofaktor, minor, dan determinan ada pada slide-slide sebelumnya."
                                ),
                                new FormButton("Contoh",
                                        m3x3inv,
                                        MatrixForm.FORM_INVERSE
                                )
                            }
                    ),
                    inverseMatrixSlide,
                }
        ),
        new Tutorial2("Operasi Baris Elementer/Eliminasi Gauss-Jordan",
                new TutorialSlide2[]{
                    obeSlide,
                    obeMulSlide,
                    obeSwitchSlide,
                    obeSetSlide,
                    compSlide,
                    obeSlide2,
                    new TutorialSlide2("Invers dengan OBE",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Invers dengan OBE</div>"
                                        + "<br>Penentuan invers suatu matriks dengan OBE menggunakan prinsip OBE dan matriks identitas, dimana A*I=I*A=A"
                                        + "<br>Ini juga berarti bahwa A<sup>-1</sup>*I = I*A<sup>-1</sup> = A<sup>-1</sup>"
                                        + "<br>Dengan menggunakan OBE pada A dan I dengan A sebagai matriks utama, maka pada akhir OBE ketika A sudah menjadi I,"
                                        + "<br>I akan menjadi A<sup>-1</sup>, karena keduanya dikalikan A<sup>-1</sup>"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("obeinverse1.png", 90),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new OBEButton("Coba",
                                                    new Matrix[]{
                                                        a2x2_1_4,
                                                        i2x2
                                                    },
                                                    0
                                            ),
                                            new FormButton("Cek",
                                                    a2x2_1_4,
                                                    MatrixForm.FORM_INVERSE
                                            )
                                        }
                                )
                            }
                    )
                }
        ),
        new Tutorial2("Persamaan Linear",
                new TutorialSlide2[]{
                    new TutorialSlide2("Persamaan Linear dengan Matriks",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Persamaan Linear dengan Matriks</div>"
                                        + "<br>Suatu persamaan linear n variabel dapat diselesaikan jika memiliki n persamaan."
                                        + "<br>Persamaan tersebut pun dapat diselesaikan dengan menggunakan matriks"
                                        + "<br>Misalnya untuk persamaan linear 2 variabel:"
                                        + "<br>ax + by = c"
                                        + "<br>px + qy = r"
                                        + "<br>Dapat dijadikan matriks seperti di bawah ini:"
                                ),
                                new ImageSection("lineareq1.png", 50),
                            }
                    ),
                    new TutorialSlide2("Persamaan Linear dengan Cara Invers",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Persamaan Linear dengan Cara Invers</div>"
                                        + "<br>Persamaan linear matriks dapat diselesaikan dengan invers, sebagaimana salah satu sifat perkalian matriks."
                                        + "<br>A * B = C  =>  B = A<sup>-1</sup> * C"
                                        + "<br>Karena invers mengharuskan determinan tidak nol, ini berarti persamaan tidak memiliki penyelesaian bila |A|=0."
                                        + "<br>Misalnya untuk persamaan sebelumnya:"
                                ),
                                new ImageSection("lineareqinv1.png", 50),
                                new TextSection("Contoh:"),
                                new ImageSection("lineareqinv2.png", 60),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new FormButton("Invers(Koefisien)",
                                                    koef2x2,
                                                    MatrixForm.FORM_INVERSE
                                            ),
                                            new OperationButton("Variabel",
                                                    1,
                                                    invKoef2x2,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    hasil2x1
                                            ),
                                            new OperationButton("Cek",
                                                    1,
                                                    koef2x2,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    var2x1
                                            )
                                        }
                                )
                            }
                    ),
                    new TutorialSlide2("Persamaan Linear dengan Cara OBE",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Persamaan Linear dengan Cara OBE</div>"
                                        + "<br>Persamaan linear matriks dapat diselesaikan dengan menggunakan OBE tanpa harus mencari inversnya terlebih dahulu."
                                        + "<br>Gunakan matriks koefisien sebagai matriks utama dan matriks hasil sebagai matriks sekunder."
                                        + "<br>Ketika matriks koefisien sudah menjadi matriks identitas, maka matriks sekunder sudah menjadi matriks variabel"
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("lineareqobe1.png", 70),
                                new HorizontalLayoutSection(
                                        new TutorialSection[]{
                                            new OBEButton("Coba",
                                                    new Matrix[]{
                                                        koef2x2,
                                                        hasil2x1
                                                    },
                                                    0
                                            ),
                                            new OperationButton("Cek",
                                                    1,
                                                    invKoef2x2,
                                                    MatrixOperation.OPERATOR_MULTIPLY,
                                                    1,
                                                    hasil2x1
                                            )
                                        }
                                )
                            }
                    ),
                    new TutorialSlide2("Persamaan Linear dengan Cara Cramer",
                            new TutorialSection[]{
                                new TextSection(
                                        "<div align=\"center\">Persamaan Linear dengan Cara Cramer</div>"
                                        + "<br>Persamaan linear matriks dapat diselesaikan dengan hanya menggunakan determinan dengan cara Cramer"
                                        + "<br>Misal matriks koefisien A dan matriks hasil C:"
                                        + "<br>A<sub>i</sub> = Matriks A dimana kolom i diganti dengan matriks C yang merupakan matriks kolom"
                                        + "<br>Kemudian variabel ke-i dapat dihitung dengan rumus:"
                                        + "<br>v<sub>i</sub> = |A<sub>i</sub>|/|A|"
                                        + "<br>Karena menggunakan determinan A sebagai pembagi, berarti persamaan tidak memiliki penyelesaian bila |A|=0."
                                        + "<br>Contoh:"
                                ),
                                new ImageSection("lineareqcramer1.png", 60),
                                new CramerButton("Load",
                                        koef2x2,
                                        true,
                                        0,
                                        hasil2x1,
                                        1
                                )
                            }
                    ),
                }
        ),
    };
}
