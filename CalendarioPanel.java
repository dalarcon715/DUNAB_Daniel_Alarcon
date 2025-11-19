package dunabapp;

import java.awt.*;
import java.time.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel Calendario simplificado. Muestra un mes y, al lado derecho, una lista
 * de eventos con un botón "Inscribirse a evento".
 */
public class CalendarioPanel extends JPanel {

    private JLabel lblMesAno;
    private JPanel gridDias;
    private DefaultListModel<String> modeloEventos;
    private JList<String> listaEventos;

    public CalendarioPanel() {
        setBackground(LoginFrame.GRIS_FONDO);
        setLayout(new BorderLayout(16, 16));
        setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel tarjeta = new JPanel(new BorderLayout(16, 16));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new EmptyBorder(24, 24, 24, 24));

        JPanel panelCalendario = new JPanel(new BorderLayout(8, 8));
        panelCalendario.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Calendario");
        lblTitulo.setFont(LoginFrame.getAppFont(Font.BOLD, 18));

        lblMesAno = new JLabel();
        lblMesAno.setFont(LoginFrame.getAppFont(Font.PLAIN, 14));
        lblMesAno.setForeground(LoginFrame.GRIS_TEXTO);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.add(lblTitulo, BorderLayout.NORTH);
        header.add(lblMesAno, BorderLayout.SOUTH);

        panelCalendario.add(header, BorderLayout.NORTH);

        gridDias = new JPanel(new GridLayout(0, 7, 4, 4));
        gridDias.setBackground(Color.WHITE);

        panelCalendario.add(gridDias, BorderLayout.CENTER);

        tarjeta.add(panelCalendario, BorderLayout.WEST);

        // Panel de eventos
        JPanel panelEventos = new JPanel();
        panelEventos.setBackground(Color.WHITE);
        panelEventos.setLayout(new BorderLayout(8, 8));

        JLabel lblEventos = new JLabel("Eventos UNAB");
        lblEventos.setFont(LoginFrame.getAppFont(Font.BOLD, 16));

        modeloEventos = new DefaultListModel<>();
        modeloEventos.addElement("Semana de Ingeniería 2025 - 8:00 am");
        modeloEventos.addElement("Bibliocine - 12:00 pm");
        modeloEventos.addElement("Taller virtual de inducción a servicios de biblioteca - 3:00 pm");

        listaEventos = new JList<>(modeloEventos);
        listaEventos.setFont(LoginFrame.getAppFont(Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(listaEventos);

        JButton btnInscribir = new JButton("Inscribirse a evento");
        btnInscribir.setFont(LoginFrame.getAppFont(Font.BOLD, 13));
        btnInscribir.setBackground(LoginFrame.NARANJA);
        btnInscribir.setForeground(Color.WHITE);
        btnInscribir.setFocusPainted(false);
        btnInscribir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelEventos.add(lblEventos, BorderLayout.NORTH);
        panelEventos.add(scroll, BorderLayout.CENTER);
        panelEventos.add(btnInscribir, BorderLayout.SOUTH);

        tarjeta.add(panelEventos, BorderLayout.CENTER);

        add(tarjeta, BorderLayout.CENTER);

        construirCalendario(LocalDate.now());
    }

    private void construirCalendario(LocalDate fecha) {
        gridDias.removeAll();

        YearMonth ym = YearMonth.of(fecha.getYear(), fecha.getMonth());
        LocalDate primerDia = ym.atDay(1);
        int primerDiaSemana = primerDia.getDayOfWeek().getValue(); // 1=Lunes

        lblMesAno.setText(fecha.getMonth().toString() + " " + fecha.getYear());

        String[] nombres = {"L", "M", "M", "J", "V", "S", "D"};
        for (String n : nombres) {
            JLabel lbl = new JLabel(n, SwingConstants.CENTER);
            lbl.setFont(LoginFrame.getAppFont(Font.BOLD, 12));
            lbl.setForeground(LoginFrame.GRIS_TEXTO);
            gridDias.add(lbl);
        }

        for (int i = 1; i < primerDiaSemana; i++) {
            gridDias.add(new JLabel(""));
        }

        for (int d = 1; d <= ym.lengthOfMonth(); d++) {
            JLabel lbl = new JLabel(String.valueOf(d), SwingConstants.CENTER);
            lbl.setFont(LoginFrame.getAppFont(Font.PLAIN, 12));
            if (d == fecha.getDayOfMonth()) {
                lbl.setOpaque(true);
                lbl.setBackground(LoginFrame.VERDE_UNAB);
                lbl.setForeground(Color.WHITE);
                lbl.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            }
            gridDias.add(lbl);
        }

        gridDias.revalidate();
        gridDias.repaint();
    }
}
