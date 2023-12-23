import java.util.Scanner;

    public class mineSweeper {
        int satirSayisi;
        int sutunSayisi;
        int girilenSayi;
        String[][] secmeTablo;
        String[][] tablo;
        int dogruSecim = 0;
        int satir;
        int sutun;
        int satMaxLimit;
        int satMinLimit;
        int sutunMaxLimit;
        int sutunMinLimit;
        int mineCount=0;

        mineSweeper(int satirSayisi, int sutunSayisi) {
            this.satirSayisi = satirSayisi;
            this.sutunSayisi = sutunSayisi;
            this.girilenSayi = satirSayisi * sutunSayisi / 4;
            this.tablo = new String[satirSayisi][sutunSayisi];
            this.secmeTablo = new String[satirSayisi][sutunSayisi];
        }

        void fillArr() {
            for (int i = 0; i < this.satirSayisi; i++) {
                for (int j = 0; j < this.sutunSayisi; j++) {
                    this.secmeTablo[i][j] = "-";
                    this.tablo[i][j] = "-";
                }
            }

        }

        void placeMines() {
            for (int i = 0; i < this.girilenSayi; i++) {
                int row = (int) (Math.random() * this.satirSayisi);
                int column = (int) (Math.random() * this.sutunSayisi);
                if (this.secmeTablo[row][column].equals("*")) {
                    i--;
                }
                this.secmeTablo[row][column] = "*";
            }
        }

        void showBoard() {
            for (int i = 0; i < this.satirSayisi; i++) {
                for (int j = 0; j < this.sutunSayisi; j++) {
                    System.out.print(this.tablo[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }

        void showAnswerBoard() {
            System.out.println("Mayınların Konumu");
            for (int i = 0; i < this.satirSayisi; i++) {
                for (int j = 0; j < this.sutunSayisi; j++) {
                    System.out.print(this.secmeTablo[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }

        void findMines() {
            this.satMaxLimit = this.satir + 1;
            this.satMinLimit = this.satir - 1;
            this.sutunMinLimit = this.sutun - 1;
            this.sutunMaxLimit = this.sutun + 1;

            if (this.satMinLimit < 0) {
                this.satMinLimit++;
            }
            if (this.satMaxLimit > this.secmeTablo[0].length - 1) {
                this.satMaxLimit--;
            }
            if (this.sutunMinLimit < 0) {
                this.sutunMinLimit++;
            }
            if (this.sutunMaxLimit > this.secmeTablo.length - 1) {
                this.sutunMaxLimit--;
            }
            for (int i = this.satMinLimit; i <= this.satMaxLimit; i++) {
                for (int j = this.sutunMinLimit; j <= this.sutunMaxLimit; j++) {
                    if (this.secmeTablo[i][j].equals("*")) {
                        this.mineCount++;
                    }
                }
            }
            this.tablo[this.satir][this.sutun] = Integer.toString(this.mineCount);
            this.mineCount = 0;
        }

        void run() {
            Scanner input = new Scanner(System.in);
            this.fillArr();
            this.placeMines();
            this.showAnswerBoard();

            while (this.dogruSecim < this.satirSayisi * this.sutunSayisi - this.girilenSayi) {
                System.out.println("=========================");
                this.showBoard();
                System.out.print("Satır sayısı gir: ");
                this.satir = input.nextInt()-1;
                System.out.print("Sütun sayısı gir: ");
                this.sutun = input.nextInt()-1;
                if (this.satir >= 0 && this.satir < this.tablo[0].length) {
                    if (this.sutun >= 0 && this.sutun < this.tablo.length) {
                        this.findMines();
                        dogruSecim++;
                        if (this.dogruSecim == this.satirSayisi * this.sutunSayisi - this.girilenSayi) {
                            System.out.println("Son tablo");
                            this.showBoard();
                            System.out.println("====================");
                            System.out.println("Tebrikler. Kazandınız.");

                        }

                        if (this.secmeTablo[this.satir][this.sutun].equals("*")) {
                            this.showAnswerBoard();
                            System.out.println("Patladınız.");
                            System.out.println("Oyunu Kaybettiniz.");
                            return;
                        }
                    } else {
                        System.out.println("==Geçerli aralık giriniz.==");
                    }
                } else {
                    System.out.println("==Geçerli aralık giriniz.==");
                }
            }
        }
    }

