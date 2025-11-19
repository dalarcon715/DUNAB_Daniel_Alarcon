package dunabapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Dashboard principal con panel lateral.
 * Desde aquí se navega a Inicio (resumen), Calendario, Formulario y Configuración.
 */
public class DashboardFrame extends JFrame {

    private Estudiante estudiante;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    private InicioPanel inicioPanel;
    private CalendarioPanel calendarioPanel;
    private FormularioPanel formularioPanel;
    private ConfiguracionPanel configuracionPanel;

    public DashboardFrame(Estudiante estudiante) {
        this.estudiante = estudiante;
        setTitle("DUNAB - Panel principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(LoginFrame.GRIS_FONDO);
        setLayout(new BorderLayout());

        JPanel side = crearSideBar();
        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);
        contentPanel.setBackground(LoginFrame.GRIS_FONDO);

        inicioPanel = new InicioPanel(estudiante);
        calendarioPanel = new CalendarioPanel();
        formularioPanel = new FormularioPanel();
        configuracionPanel = new ConfiguracionPanel(estudiante, this);

        contentPanel.add(inicioPanel, "INICIO");
        contentPanel.add(calendarioPanel, "CALENDARIO");
        contentPanel.add(formularioPanel, "FORMULARIO");
        contentPanel.add(configuracionPanel, "CONFIGURACION");

        add(side, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        mostrar("INICIO");
    }

    private JPanel crearSideBar() {
        JPanel side = new JPanel();
        side.setBackground(Color.WHITE);
        side.setPreferredSize(new Dimension(180, 600));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setBorder(BorderFactory.createEmptyBorder(20, 16, 20, 16));

        JLabel lblLogo = new JLabel("Facultad de Ingeniería");
        lblLogo.setFont(LoginFrame.getAppFont(Font.BOLD, 14));
        lblLogo.setForeground(LoginFrame.VERDE_UNAB);
        lblLogo.setAlignmentX(Component.LEFT_ALIGNMENT);

        side.add(lblLogo);
        side.add(Box.createVerticalStrut(24));

        side.add(crearBotonMenu("Inicio", "INICIO"));
        side.add(crearBotonMenu("Formulario", "FORMULARIO"));
        side.add(crearBotonMenu("Calendario", "CALENDARIO"));
        side.add(crearBotonMenu("Configuración", "CONFIGURACION"));

        return side;
    }

    private JButton crearBotonMenu(String texto, final String tarjeta) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setFont(LoginFrame.getAppFont(Font.PLAIN, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrar(tarjeta);
            }
        });
        return btn;
    }

    public void mostrar(String tarjeta) {
        cardLayout.show(contentPanel, tarjeta);
    }

    public void actualizarDatosEstudiante() {
        inicioPanel.actualizarDatos();
        formularioPanel.refrescarEventos();
    }
}
