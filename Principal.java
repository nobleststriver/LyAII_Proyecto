import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Principal extends JFrame {
    
    private JTextArea areaCodigo;
    private JButton btnCargar, btnAnalizar;
    private JLabel lblEstado;
    
    public Principal() {
        configurarVentana();
        initComponentes();
    }
    
    private void configurarVentana() {
        setTitle("Analizador de Código");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout(10, 10));
        setResizable(false);
    }
    
    private void initComponentes() {
        
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(70, 130, 180));
        panelSuperior.setPreferredSize(new Dimension(800, 50));
        
        JLabel titulo = new JLabel("ANALIZADOR DE CÓDIGO");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panelSuperior.add(titulo);
        
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        areaCodigo = new JTextArea();
        areaCodigo.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaCodigo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        JScrollPane scroll = new JScrollPane(areaCodigo);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        panelCentral.add(scroll, BorderLayout.CENTER);
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelInferior.setBackground(new Color(230, 230, 230));
        
        btnCargar = new JButton("Cargar Archivo");
        btnAnalizar = new JButton("Analizar Código");
        
        // Configurar botones según el sistema operativo
        configurarBotones();
        
        lblEstado = new JLabel("Esperando archivo...");
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        panelInferior.add(btnCargar);
        panelInferior.add(btnAnalizar);
        panelInferior.add(lblEstado);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void configurarBotones() {
        String os = System.getProperty("os.name").toLowerCase();
        boolean esMac = os.contains("mac");
        
        // Configuración común
        btnCargar.setPreferredSize(new Dimension(150, 35));
        btnCargar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCargar.addActionListener(this::cargarArchivo);
        
        btnAnalizar.setPreferredSize(new Dimension(150, 35));
        btnAnalizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAnalizar.addActionListener(this::analizarCodigo);
        
        if (esMac) {
            // Configuración para macOS
            btnCargar.setForeground(new Color(50, 150, 250));
            btnCargar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 150, 250), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            btnCargar.setOpaque(false);
            
            btnAnalizar.setForeground(new Color(80, 180, 80));
            btnAnalizar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 180, 80), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            btnAnalizar.setOpaque(false);
            
            // Efectos hover para Mac
            btnCargar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnCargar.setBackground(new Color(50, 150, 250, 50));
                    btnCargar.setOpaque(true);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnCargar.setOpaque(false);
                }
            });
            
            btnAnalizar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnAnalizar.setBackground(new Color(80, 180, 80, 50));
                    btnAnalizar.setOpaque(true);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnAnalizar.setOpaque(false);
                }
            });
            
        } else {
            // Configuración para Windows/Linux (original)
            btnCargar.setBackground(new Color(50, 150, 250));
            btnCargar.setForeground(Color.WHITE);
            btnCargar.setFocusPainted(false);
            btnCargar.setOpaque(true);
            
            btnAnalizar.setBackground(new Color(80, 180, 80));
            btnAnalizar.setForeground(Color.WHITE);
            btnAnalizar.setFocusPainted(false);
            btnAnalizar.setOpaque(true);
        }
    }
    
    private void cargarArchivo(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de código");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (.txt)", "txt"));
        
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                String contenido = new String(Files.readAllBytes(Paths.get(path)));
                areaCodigo.setText(contenido);
                lblEstado.setText("Archivo cargado: " + fileChooser.getSelectedFile().getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al leer el archivo", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void analizarCodigo(ActionEvent e) {
        String codigo = areaCodigo.getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay código para analizar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            new Parser(codigo);
            lblEstado.setText("Análisis completado con éxito");
        } catch (Exception ex) {
            lblEstado.setText("Error en el análisis");
            JOptionPane.showMessageDialog(this, 
                "Error al analizar el código: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Principal app = new Principal();
            app.setVisible(true);
        });
    }
}

