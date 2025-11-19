package dunabapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Pantalla de inicio de sesión.
 * Colores aproximados según el diseño:
 *  - Verde UNAB: #39B54A
 *  - Naranja:    #FF7F2A
 *  - Gris fondo: #F4F4F4
 * Tipografía: se intenta usar "Poppins". Si no está instalada, usa SansSerif.
 */
public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnOlvido;

    public static final Color VERDE_UNAB = new Color(0x39B54A);
    public static final Color NARANJA = new Color(0xFF7F2A);
    public static final Color GRIS_FONDO = new Color(0xF4F4F4);
    public static final Color GRIS_TEXTO = new Color(0x555555);

    public LoginFrame() {
        setTitle("DUNAB - Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 480);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(GRIS_FONDO);
        setLayout(new GridBagLayout());

        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        JLabel lblLogo = new JLabel("Facultad de Ingeniería");
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLogo.setForeground(VERDE_UNAB);
        lblLogo.setFont(getAppFont(Font.BOLD, 20));

        JLabel lblTitulo = new JLabel("Iniciar sesión");
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(getAppFont(Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(24, 0, 16, 0));

        txtUsuario = new JTextField();
        txtUsuario.setFont(getAppFont(Font.PLAIN, 14));
        txtUsuario.setPreferredSize(new Dimension(260, 40));
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtUsuario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0xDDDDDD)),
                "Usuario",
                0, 0,
                getAppFont(Font.PLAIN, 11),
                new Color(0x999999)));

        txtPassword = new JPasswordField();
        txtPassword.setFont(getAppFont(Font.PLAIN, 14));
        txtPassword.setPreferredSize(new Dimension(260, 40));
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPassword.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0xDDDDDD)),
                "Contraseña",
                0, 0,
                getAppFont(Font.PLAIN, 11),
                new Color(0x999999)));

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setFont(getAppFont(Font.BOLD, 14));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(VERDE_UNAB);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnOlvido = new JButton("¿Olvidaste tu contraseña?");
        btnOlvido.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOlvido.setFont(getAppFont(Font.PLAIN, 12));
        btnOlvido.setBorderPainted(false);
        btnOlvido.setContentAreaFilled(false);
        btnOlvido.setForeground(NARANJA);
        btnOlvido.setCursor(new Cursor(Cursor.HAND_CURSOR));

        tarjeta.add(lblLogo);
        tarjeta.add(lblTitulo);
        tarjeta.add(txtUsuario);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(txtPassword);
        tarjeta.add(Box.createVerticalStrut(24));
        tarjeta.add(btnLogin);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(btnOlvido);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(tarjeta, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDashboard();
            }
        });
    }

    private void abrirDashboard() {
        // Datos de ejemplo
        Estudiante est = new Estudiante("Pedro Pérez", "Ingeniería de Sistemas", "6", 65, 80);
        DashboardFrame dash = new DashboardFrame(est);
        dash.setVisible(true);
        dispose();
    }

    public static Font getAppFont(int style, float size) {
        // Intenta usar Poppins, si no existe usa SansSerif
        String[] preferred = {"Poppins", "Montserrat", "SansSerif"};
        for (String name : preferred) {
            Font f = new Font(name, style, (int) size);
            if (f.getFamily().equals(name) || name.equals("SansSerif")) {
                return f.deriveFont(style, size);
            }
        }
        return new Font("SansSerif", style, (int) size);
    }
}
