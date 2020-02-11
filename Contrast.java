import java.awt.image.RescaleOp; // clasa cu care se face contrastul

public class Contrast extends Effect {

    protected void executaEfect()
    {
        RescaleOp rescaleOp = new RescaleOp(1.5f, 0	, null);
        // declar o variabila de tip rescaleop cu parametrii 1.5f (cat de mare sa fie contrastul, 0
        rescaleOp.filter(getImagine(), getImagine());
        // getImage sunt metode din effect, imaginea care se foloseste si imaginea in care pune rezultatul
        //rezultatul mi-l pune direct in getImagine
    }
}

