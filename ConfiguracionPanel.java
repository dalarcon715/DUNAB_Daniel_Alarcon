package dunabapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;

/**
 * Panel Configuración: permite actualizar datos básicos y foto.
 */
public class ConfiguracionPanel extends JPanel {

    private Estudiante estudiante;
    private DashboardFrame parent;

    private JTextField txtNombre;
    private JTextField txtCarrera;
    private JTextField txtSemestre;
    private JLabel lblFotoRuta;

    public ConfiguracionPanel(Estudiante estudiante, DashboardFrame parent) {
        this.estudiante = estudiante;
        this.parent = parent;

        setBackground(LoginFrame.GRIS_FONDO);
        setLayout(new BorderLayout(16, 16));
        setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new EmptyBorder(24, 24, 24, 24));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Configuración");
        lblTitulo.setFont(LoginFrame.getAppFont(Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtNombre = crearCampo("Nombre del estudiante", estudiante.getNombre());
        txtCarrera = crearCampo("Carrera", estudiante.getCarrera());
        txtSemestre = crearCampo("Semestre", estudiante.getSemestre());

        JButton btnFoto = new JButton("Seleccionar foto del estudiante");
        btnFoto.setFont(LoginFrame.getAppFont(Font.PLAIN, 13));
        btnFoto.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblFotoRuta = new JLabel("Sin foto seleccionada");
        lblFotoRuta.setFont(LoginFrame.getAppFont(Font.PLAIN, 11));
        lblFotoRuta.setForeground(LoginFrame.GRIS_TEXTO);
        lblFotoRuta.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(LoginFrame.getAppFont(Font.BOLD, 14));
        btnGuardar.setBackground(LoginFrame.NARANJA);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnGuardar.setFocusPainted(false);

        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setFont(LoginFrame.getAppFont(Font.BOLD, 13));
        btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
        btnCerrarSesion.setAlignmentX(Component.LEFT_ALIGNMENT);

        tarjeta.add(lblTitulo);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(txtNombre);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(txtCarrera);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(txtSemestre);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(btnFoto);
        tarjeta.add(Box.createVerticalStrut(4));
        tarjeta.add(lblFotoRuta);
        tarjeta.add(Box.createVerticalStrut(24));
        tarjeta.add(btnGuardar);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(btnCerrarSesion);

        add(tarjeta, BorderLayout.CENTER);

        btnFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarFoto();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    private JTextField crearCampo(String titulo, String valor) {
        JTextField txt = new JTextField(valor);
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txt.setFont(LoginFrame.getAppFont(Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0xDDDDDD)),
                titulo,
                0, 0,
                LoginFrame.getAppFont(Font.PLAIN, 11),
                LoginFrame.GRIS_TEXTO));
        return txt;
    }

    private void seleccionarFoto() {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            estudiante.setRutaFoto(f.getAbsolutePath());
            lblFotoRuta.setText(f.getName());
        }
    }

    private void guardarCambios() {
        estudiante.setNombre(txtNombre.getText());
        estudiante.setCarrera(txtCarrera.getText());
        estudiante.setSemestre(txtSemestre.getText());
        parent.actualizarDatosEstudiante();
        JOptionPane.showMessageDialog(this, "Datos actualizados", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cerrarSesion() {
        JFrame top = (JFrame) SwingUtilities.getWindowAncestor(this);
        top.dispose();
        new LoginFrame().setVisible(true);
    }
}
