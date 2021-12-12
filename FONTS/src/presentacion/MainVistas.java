package presentacion;

public class MainVistas {
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        ControladorPresentacion ctrpres = new ControladorPresentacion();
                        ctrpres.inicialitzarPresentacion();
                    }
                }
        );

    }
}
