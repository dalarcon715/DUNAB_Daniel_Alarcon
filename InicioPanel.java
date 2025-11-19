package dunabapp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel Inicio: muestra foto, nombre, DUNABs y barra de progreso.
 */
public class InicioPanel extends JPanel {

    private Estudiante estudiante;

    private JLabel lblNombre;
    private JLabel lblDunabsActuales;
    private JLabel lblDunabsFaltantes;
    private JProgressBar barra;

    public InicioPanel(Estudiante estudiante) {
        this.estudiante = estudiante;
        setBackground(LoginFrame.GRIS_FONDO);
        setLayout(new GridBagLayout());

        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new EmptyBorder(24, 40, 24, 40));
        tarjeta.setLayout(new BorderLayout(32, 0));

        // Panel izquierdo: foto
        JPanel panelFoto = new JPanel();
        panelFoto.setBackground(Color.WHITE);
        panelFoto.setLayout(new BoxLayout(panelFoto, BoxLayout.Y_AXIS));
        JLabel lblFoto = new JLabel("Foto");
        lblFoto.setFont(LoginFrame.getAppFont(Font.PLAIN, 12));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto.setVerticalAlignment(SwingConstants.CENTER);
        lblFoto.setPreferredSize(new Dimension(120, 120));
        lblFoto.setMaximumSize(new Dimension(120, 120));
        lblFoto.setBorder(BorderFactory.createLineBorder(new Color(0xDDDDDD)));
        lblFoto.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelFoto.add(lblFoto);

        // Panel derecho: datos
        JPanel panelDatos = new JPanel();
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));

        lblNombre = new JLabel();
        lblNombre.setFont(LoginFrame.getAppFont(Font.BOLD, 18));

        lblDunabsActuales = new JLabel();
        lblDunabsActuales.setFont(LoginFrame.getAppFont(Font.BOLD, 16));

        barra = new JProgressBar(0, estudiante.getDunabsMeta());
        barra.setPreferredSize(new Dimension(260, 20));
        barra.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        barra.setForeground(LoginFrame.VERDE_UNAB);
        barra.setBackground(new Color(0xEEEEEE));
        barra.setBorderPainted(false);

        lblDunabsFaltantes = new JLabel();
        lblDunabsFaltantes.setFont(LoginFrame.getAppFont(Font.PLAIN, 14));
        lblDunabsFaltantes.setForeground(LoginFrame.GRIS_TEXTO);

        panelDatos.add(lblNombre);
        panelDatos.add(Box.createVerticalStrut(12));
        panelDatos.add(lblDunabsActuales);
        panelDatos.add(Box.createVerticalStrut(12));
        panelDatos.add(barra);
        panelDatos.add(Box.createVerticalStrut(8));
        panelDatos.add(lblDunabsFaltantes);

        tarjeta.add(panelFoto, BorderLayout.WEST);
        tarjeta.add(panelDatos, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(tarjeta, gbc);

        actualizarDatos();
    }

    public void actualizarDatos() {
        lblNombre.setText(estudiante.getNombre());
        lblDunabsActuales.setText("Tienes " + estudiante.getDunabsActuales() + " DUNABs");
        barra.setValue(estudiante.getDunabsActuales());
        lblDunabsFaltantes.setText("Te faltan " + estudiante.getDunabsFaltantes() + " DUNABs");
    }
}
