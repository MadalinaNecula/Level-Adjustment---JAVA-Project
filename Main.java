import java.io.File; // pentru lucrul cu fluxuri
import java.util.Scanner; // pentru citirea de la tastatura

public class Main {

    private static final String FOLDER = "C:\\Users\\Madalina Necula\\Desktop\\anul 3\\2019_Java_Labs\\Necula_Maria_Madalina\\Images"; // calea unde se afla pozele mele de scanat

    private File pozaDinFolderSelectata = null;
    private Effect efect = null;

    public static void main(String args[]) {
        Main main = new Main(); // creez  o instanta de clasa main
       /* String s1 = args[0];
        String s2 = args[1];
        System.out.println("s1" + " " + "s2");
        */
        main.doTest(); // apelez metoda doTest
    }

    private void doTest() {
        File directorPoze = new File(FOLDER); // creez o variabila folder din care voi citi pozele
        if (directorPoze.exists()) { // daca folderul exista
            File poze[] = directorPoze.listFiles(); // creez un vector nou in care pun pozele disponibile din folder
            Scanner scaner = new Scanner(System.in); // creez o instanta de tip scanner pentru citirea de la tastatura

            alegeImagine(poze, scaner); // aleg imaginea de la tastatura
            alegeEfect(poze, scaner); // aleg efectul de la tastatura

            scaner.close(); // inchid instanta de scanner

            efect.setPoza(pozaDinFolderSelectata); // setez fisierul de poza pe care voi lucra

            EffectExecutor<Effect> ee = new EffectExecutor<Effect>(efect); // creez o noua instanta de effect executor
            ee.aplicaEfect1(); //aplic pe ea efectul
        }
    }

    private void alegeImagine(File[] poze, Scanner scaner) { // aleg imaginea de la tastatura, primesc ca param vectorul de fisiere din folder si instanta scanner

        System.out.println("Imaginile disponibile sunt:"); // afisez "imaginile disponibile"
        for (int i = 0; i < poze.length; i++) {
            System.out.println((i + 1) + ". " + poze[i].getName()); // afisez din vectorul poze numele imaginile disponibile
        }
        System.out.println();

        System.out.print("Introduceti numele pozei pe care doriti sa aplicati efectul:"); // introduceti numele pozei

        do {		// citim numele fisierului

            String citit = scaner.nextLine().toLowerCase(); // citim o comanda data de la tastatura

            Integer indexPoza = null;		// verific daca ce s-a introdus este numeric
            try {
                indexPoza = Integer.parseInt(citit);  // daca e intreg, atunci iau indexul pozei
            } catch (NumberFormatException e) { // exceptie de tip unchecked
                //nu e nevoie sa tratez exceptia
            }

            if (indexPoza != null) {		// daca este numeric => verific indexul

                if (indexPoza > 0 && indexPoza <= poze.length) // daca nr este mai mare ca zero si mai mic decat nr de poze(lung vectorului poze)
                {
                    pozaDinFolderSelectata = poze[indexPoza - 1]; // poza selectata = poza de la indexul ala
                } else {
                    System.out.println(String.format("Ati introdus un index gresit (1 <= index <= %d). Reincercati!", poze.length)); // daca nu e ok indexul -> index gresit ->mesaj de eroare
                }
            } else {
                //daca ce am introdus de la tastatura este numele pozei
                for (int i = 0; i < poze.length; i++) {
                    String numePoza = poze[i].getName().toLowerCase(); // verific daca numele este in folder
                    if (numePoza.equals(citit) || numePoza.substring(0, numePoza.lastIndexOf('.')).equals(citit)) {
                        pozaDinFolderSelectata = poze[i]; //daca este, atribui var pozei selectate poza corespunzatoare
                        break;
                    }
                }

                if (pozaDinFolderSelectata == null) {
                    System.out.println("Numele pozei nu este in folder. Reincercati!"); // altfel numele pozei nu este in folder
                }
            }
        } while (pozaDinFolderSelectata == null); // am facut cu do while pentru a se executa citirea numelui pozei pana se introduce o valoare valida
        System.out.println();
    }

    private void alegeEfect(File[] poze, Scanner scaner) { // aleg efect este pe acelasi principiu ca functia de inainte

        System.out.println("Introduceti efectul dorit:(1 pentru BlackWhite/2 pentru Contrast)");
        do {
            String citit = scaner.nextLine().toLowerCase(); // citeesc comanda introdusa de la tastatura

            Integer indexEfect = null;		// verific daca daca ce s-a introdus este numeric
            try {
                indexEfect = Integer.parseInt(citit); // convertesc citit din string in int
            } catch (NumberFormatException e) {
                // nu e nevoie sa tratez exceptia
            }

            if (indexEfect != null) {	// daca este numeric => verific indexul

                if (indexEfect == 1) //	daca s-a introdus 1 atunci aplic efectul de blackWhite
                {
                    efect = new BlackWhite(); // se creeaza o instanta de clasa BlackWhite
                } else if (indexEfect == 2) {
                    efect = new Contrast(); // daca s-a introdus 2 aplic efectul de contrast
                } else {
                    System.out.println("Ati introdus un index gresit (1 <= index <= 2). Reincercati!"); // mesaj de eroare, indexul este gresit
                }
            } else {
                // acelasi lucru daca se introduce
                if (citit.equals("blackwhite")) {
                    efect = new BlackWhite();
                } else if (citit.equals("contrast")) {
                    efect = new Contrast();
                } else {
                    System.out.println("Numele pozei nu este in folder. Reincercati!");
                }
            }
        } while (efect == null);

//		System.out.println("S-a ales efectul: " + efect);
    }
}

