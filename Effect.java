import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Effect implements Interface{

    private File poza; // fisierul poza pe care il iau din calculator,il selectez de la tastatura
    private BufferedImage imagine; //  imaginea in sine care rezulta dupa efect

    protected abstract void executaEfect(); // metoda executaEfect aplica efectul propriu-zis - e scris in clasele ce mostenesc effect

    public void aplicaEfect() { //daca imaginea nu e null, executa efectul. Daca setez imaginea(daca imaginea e setata in clasa asta, daca e pusa cu setImage, atunci pot sa execut metoda asa)
        if (getImagine() != null) {
            executaEfect();
        }
    }

    public void aplicaEfect(File poza) { //daca primesc ca parametru fisierul poza, atunci setez poza
        setPoza(poza);

        if (getImagine() != null) {
            executaEfect();
        }
    }

    private void citestePoza() {  // citeste poza imi seteaza imaginea de la calea respectiva. De la path-ul ala el o citeste si o baga in memorie
        try {
            setImagine(ImageIO.read(getPoza()));
        } catch (IOException e) { // exceptie checked - se verica la compilare
            setImagine(null);
            e.printStackTrace();
        }
    }

    public String salveazaPoza() {

        long a = System.currentTimeMillis(); // imi salvez timpul curent in millisec

        File fisierDeSalvat = null; // numele fisierului in care salvam poza
        int index = 0; // indexul noii poze
        do {
            String numeNou = poza.getName().substring(0, poza.getName().lastIndexOf(".")); //ia numele pozei fara extensia bmp
            String numeFisierSalvare = "C:\\Users\\Madalina Necula\\Desktop\\anul 3\\2019_Java_Labs\\Necula_Maria_Madalina\\NewImages" ;
            numeNou = numeFisierSalvare + File.separator + numeNou + "_" + index + ".bmp"; // creez un nume nou de poza, in baza numelui anterior
            //daca doream salvarea noii imagini in acelasi fisier cu cea veche, foloseam poza.getParent(), in loc de numeFisierSalvare,care imi da calea completa a folderului in care se afla poza
            //.separator imi returneaza "/"
            fisierDeSalvat = new File(numeNou); // se creeaza o noua instanta de fisier cu numele asta
            index++;
        } while (fisierDeSalvat.exists());

        try {
            ImageIO.write(imagine, "BMP", fisierDeSalvat); // salveaza poza in fisierul cu numele nou
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("timp executie: " + (System.currentTimeMillis() - a)); // timpul de salvare
        return ( fisierDeSalvat != null ? fisierDeSalvat.getName() : null); // if fisier diferit de null, atunci returneaza fisierDeSalvat.getName() else retuneaza null; cand salvez o poza imi returneaza numele noii poze
    }

    public File getPoza() {
        return poza;
    }

    public void setPoza(File poza) {
        if (poza == null)// daca var poza e null (nu i s-a alocat nici macar spatiu in memorie)
        {
            return;		// sau arunca o exceptie
        }
        if (this.getPoza() == null || !poza.getAbsolutePath().equals(this.getPoza().getAbsolutePath())) { // setez poza si o bag in memorie doar daca poza pe care o trimit eu ca parametru este alta decat cea pe care o aveam pana acum.
            this.poza = poza; // getAbsolutePath imi returneaza toata calea pozei
            citestePoza();
        }
    }

    public BufferedImage getImagine() { // returneaza doar bufferedImage-ul
        return imagine;
    }

    protected void setImagine(BufferedImage imagine) { //seteaza BufferedImage-ul cu imaginea pe care se aplica efectul
        this.imagine = imagine;
    }

    @Override
    public String toString() {		// polimorfism
        return getClass().getSimpleName(); // Metoda toString descrie o instanta in forma umana(citibila)
        //getSimpleName zice doar numele clasei fara sa zica pachetul din care face parte. Am nevoie de clasa pentru a face polimorfism, dar nu o folosesc la nimic
    }
    public static void exemplu(int...a){
        System.out.println("Aceasta e metoda cu numar variabil de argumente");
        int i;
        System.out.println("Introduceti pozitia de pe care doriti sa luati argumentul");
        System.out.println(a);
    }
}

