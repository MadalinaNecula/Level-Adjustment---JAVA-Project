import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class BlackWhite extends Effect {

    protected void executaEfect() {
        float[] scales = {2f, 2f, 2f};
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);

        final BufferedImage scaledImage = new BufferedImage( //clasa care imi reprezinta imaginea, in asta tin bmp-ul, o creez de la zero cu inaltime, latime si matrice de culoare
                getImagine().getWidth(),
                getImagine().getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(getImagine(), rop, 0, 0);

        final BufferedImage grayImage = new BufferedImage( // creeaza o imagine noua cu matricea de culoare alb-negru
                getImagine().getWidth(),
                getImagine().getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        g = grayImage.createGraphics();
        g.drawImage(getImagine(), 0, 0, null);

        setImagine(grayImage); // salveaza imaginea

    }
}