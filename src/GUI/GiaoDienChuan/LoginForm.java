/**/
package GUI.GiaoDienChuan;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.border.*;

import BUS.*;
import DEV.*;

public class LoginForm extends JFrame {

        private JLabel lab_username;
        private JLabel lab_pass;

        // Creates new form LoginForm2
        public LoginForm() {

                initComponents();

                this.setTitle("Đăng nhập");
                ImageIcon logo = new ImageIcon(getClass().getResource("/images/icon8_trasua_3.png"));
                setIconImage(logo.getImage());

                this.setLocationRelativeTo(null);

                // add event Enter
                KeyAdapter ka = new KeyAdapter() {
                        public void keyPressed(KeyEvent ke) {
                                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                                        button_login.doClick();
                                }
                        }
                };
                tx_username.addKeyListener(ka);
                tx_pass.addKeyListener(ka);

                // add auto select text on focus

                FocusListener fl = new FocusListener() {
                        public void focusGained(FocusEvent fe) {
                                if (fe.getSource() instanceof JTextField) {
                                        JTextField tx = (JTextField) fe.getSource();
                                        tx.select(0, tx.getText().length());

                                } else if (fe.getSource() instanceof JPasswordField) {
                                        JPasswordField tx = (JPasswordField) fe.getSource();
                                        tx.select(0, new String(tx.getPassword()).length());
                                }
                        }

                        public void focusLost(FocusEvent fe) {
                                if (fe.getSource() instanceof JTextField) {
                                        JTextField tx = (JTextField) fe.getSource();
                                        tx.select(0, 0);

                                } else if (fe.getSource() instanceof JPasswordField) {
                                        JPasswordField tx = (JPasswordField) fe.getSource();
                                        tx.select(0, 0);
                                }
                        }
                };
                tx_username.addFocusListener(fl);
                tx_pass.addFocusListener(fl);

                // auto focus to tenDangNhap
                tx_username.requestFocus();

        }

        private void initComponents() {
                panelForm = new JPanel();
                panel_header = new JPanel();
                lab_img_pass = new JLabel();
                lab_img_user = new JLabel();
                lab_username = new JLabel("username");
                lab_pass = new JLabel("password");
                lbAva = new JLabel();
                lab_head = new JLabel();
                lab_input = new JLabel();

                tx_username = new JTextField();
                tx_pass = new JPasswordField();
                button_login = new RoundedButton();

                // panel Form
                setDefaultCloseOperation(3);
                setSize(1000, 600);
                setResizable(false);
                panelForm = new JPanel() {
                        protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                ImageIcon image = new ImageIcon("images\\background-tra-sua.png");
                                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
                        }
                };
                panelForm.setLayout(new FlowLayout());
                panelForm.setBorder(new EmptyBorder(55, 400, 50, 50));

                lab_head.setText("QUẢN LÝ TRÀ SỮA");
                lab_head.setOpaque(false);
                lab_head.setFont(new Font("Arial", 1, 32));
                lab_head.setForeground(Color.decode("#B71375"));

                panel_header.setLayout(new GridLayout(2, 1));
                panel_header.add(lab_head);
                panel_header.add(lbAva);
                panel_header.setOpaque(false);
                panel_header.setBorder(new EmptyBorder(0, 200, 0, 200));

                lab_input.setPreferredSize(new Dimension(500, 200));
                lab_input.setLayout(new GridBagLayout());
                lab_input.setOpaque(false);

                GridBagConstraints c = new GridBagConstraints();
                c.gridy = 0;
                c.gridx = 1;
                c.anchor = GridBagConstraints.WEST;
                c.ipadx = 30;
                lab_input.add(lab_username, c);
                c.gridy = 1;
                c.gridx = 0;
                lab_input.add(lab_img_user, c);
                c.gridx = 1;
                lab_input.add(tx_username, c);
                c.gridy = 2;
                c.gridx = 1;
                lab_input.add(lab_pass, c);
                c.gridy = 3;
                c.gridx = 0;
                lab_input.add(lab_img_pass, c);
                c.gridx = 1;
                lab_input.add(tx_pass, c);

                // button login
                button_login.setText("LOGIN NOW");
                button_login.setPreferredSize(new Dimension(230, 65));
                button_login.setBackground(new Color(0, 204, 102));
                button_login.setFont(new Font("Comic Sans MS", 1, 32));
                button_login.setForeground(new Color(10, 10, 10));
                button_login.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                button_loginActionPerformed(evt);
                        }
                });

                tx_username.setFont(new Font("Segoe UI", 0, 18));
                tx_username.setColumns(15);
                tx_username.setPreferredSize(new Dimension(0, 40));

                lab_img_pass.setIcon(new ImageIcon(getClass().getResource("/images/icons8_password_40px.png")));
                lab_img_user.setIcon(
                                new ImageIcon(getClass().getResource(
                                                "/images/icons8_circled_user_male_skin_type_1_2_40px.png")));

                tx_pass.setFont(new Font("Segoe UI", 0, 18));
                tx_pass.setColumns(15);
                tx_pass.setPreferredSize(new Dimension(0, 40));

                lbAva.setHorizontalAlignment(SwingConstants.CENTER);
                lbAva.setIcon(new ImageIcon(
                                getClass().getResource("/images/password.png")));

                panelForm.add(panel_header);
                panelForm.add(lab_input);
                panelForm.add(button_login);
                add(panelForm);

        }

        private void button_loginActionPerformed(ActionEvent evt) {
                String tentk = tx_username.getText();
                String mk = new String(tx_pass.getPassword());
                QuanLyTaiKhoanBUS qltk = new QuanLyTaiKhoanBUS();
                TaiKhoan tk = qltk.getTaiKhoan(tentk);

                if (tk != null) {
                        // check xem nhân viên của tài khoản này có bị khóa (Ẩn) hay không
                        NhanVien nv = new QuanLyNhanVienBUS().getNhanVien(tk.getMaNV());
                        if (nv.getTrangThai() == 1) {
                                JOptionPane.showMessageDialog(this,
                                                "Tài khoản này đã bị khóa, do chủ nhân tài khoản này đã bị ẨN khỏi hệ thống!");
                                return;
                        }

                        // check password
                        if (tk.getPassword().equals(mk)) {
                                taiKhoanLogin = tk;
                                nhanVienLogin = nv;
                                quyenLogin = new QuanLyQuyenBUS().getQuyen(taiKhoanLogin.getMaQuyen());

                                new GiaoDienChuan().setVisible(true);
                                this.dispose();

                        } else {
                                JOptionPane.showMessageDialog(this, "Sai mật khẩu!");
                                tx_pass.requestFocus();
                        }

                } else {
                        JOptionPane.showMessageDialog(this, "Sai tên đăng nhập!");
                        tx_username.requestFocus();
                }
        }

        public class RoundedButton extends JButton {
                private static final int ARC_WIDTH = 16;
                private static final int ARC_HEIGHT = 16;

                public RoundedButton() {
                        setOpaque(false);
                        setBorder(null);
                }

                @Override
                protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setPaint(getBackground());
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
                        g2.setPaint(getForeground());
                        g2.drawString(getText(), getWidth() / 2 - g2.getFontMetrics().stringWidth(getText()) / 2,
                                        getHeight() / 2 + g2.getFontMetrics().getAscent() / 2
                                                        - g2.getFontMetrics().getDescent() / 2);
                        g2.dispose();
                }

                @Override
                protected void paintBorder(Graphics g) {
                        super.paintBorder(g);
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setPaint(getForeground());
                        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
                        g2.dispose();
                }

                @Override
                public boolean contains(int x, int y) {
                        if (shape == null || !shape.getBounds().equals(getBounds())) {
                                shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), ARC_WIDTH,
                                                ARC_HEIGHT);
                        }
                        return shape.contains(x, y);
                }

                private Shape shape;
        }

        public static Quyen quyenLogin;
        public static NhanVien nhanVienLogin;
        public static TaiKhoan taiKhoanLogin;

        private RoundedButton button_login;

        private JLabel lbAva;
        private JLabel lab_img_pass;
        private JLabel lab_img_user;
        private JLabel lab_input;

        private JPanel panelForm;
        private JPanel panel_header;

        private JPasswordField tx_pass;
        private JTextField tx_username;
        private JLabel lab_head;
}
