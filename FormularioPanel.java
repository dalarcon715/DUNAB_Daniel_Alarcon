package dunabapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class FormularioPanel extends JPanel {

    private DefaultListModel<String> modeloEventos;
    private JList<String> lista;

    public FormularioPanel() {
        setBackground(LoginFrame.GRIS_FONDO);
        setLayout(new BorderLayout(16, 16));
        setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel tarjeta = new JPanel(new BorderLayout(16, 16));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new EmptyBorder(24, 24, 24, 24));

        JLabel lblTitulo = new JLabel("Formulario");
        lblTitulo.setFont(LoginFrame.getAppFont(Font.BOLD, 20));

        JLabel lblSub = new JLabel("Eventos inscritos");
        lblSub.setFont(LoginFrame.getAppFont(Font.PLAIN, 14));
        lblSub.setForeground(LoginFrame.GRIS_TEXTO);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.add(lblTitulo, BorderLayout.NORTH);
        header.add(lblSub, BorderLayout.SOUTH);

        tarjeta.add(header, BorderLayout.NORTH);

        modeloEventos = new DefaultListModel<>();
        modeloEventos.addElement("8:00 am - Semana de ingeniería 2025");
        modeloEventos.addElement("12:00 pm - Bibliocine");
        modeloEventos.addElement("3:00 pm - Taller virtual inducción servicios de biblioteca");

        lista = new JList<>(modeloEventos);
        lista.setFont(LoginFrame.getAppFont(Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(lista);

        tarjeta.add(scroll, BorderLayout.CENTER);

        JButton btnBaja = new JButton("Darse de baja");
        btnBaja.setFont(LoginFrame.getAppFont(Font.BOLD, 13));
        btnBaja.setBackground(LoginFrame.NARANJA);
        btnBaja.setForeground(Color.WHITE);
        btnBaja.setFocusPainted(false);
        btnBaja.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = lista.getSelectedIndex();
                if (idx >= 0) {
                    modeloEventos.remove(idx);
                }
            }
        });

        tarjeta.add(btnBaja, BorderLayout.SOUTH);

        add(tarjeta, BorderLayout.CENTER);
    }

    public void refrescarEventos() {
        // En un futuro se podría enlazar con los datos reales.
    }
}
