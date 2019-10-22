package br.com.movimento_estoque.magica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.movimento_estoque.connection.Conexao;
import br.com.movimento_estoque.model.Produto;
import br.com.movimento_estoque.service.Service;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class MainScreen extends JFrame {

	private JPanel contentPane;
	private String[] dias = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String[] meses = {"01","02","03","04","05","06","07","08","09","10","11","12"};
	private String[] anos = {"2018","2019"};
	private JTextField txtBanco;
	private static String banco; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		
		super("MENEW CORRIGE SALDO");

		ImageIcon iconL = new ImageIcon(MainScreen.class.getResource("/br/com/movimento_estoque/img/logo.png"));
		Image imaL = iconL.getImage();
		Image imagemL = imaL.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		
		setIconImage(imagemL);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 160, 122));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(188, 11, 100, 98);
		ImageIcon icon = new ImageIcon(MainScreen.class.getResource("/br/com/movimento_estoque/img/logo.png"));
		Image ima = icon.getImage();
		Image imagem = ima.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT);
		Icon ico = new ImageIcon(imagem);
		lblLogo.setIcon(ico);
		contentPane.add(lblLogo);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(199, 271, 77, 62);
		ImageIcon iconS = new ImageIcon(MainScreen.class.getResource("/br/com/movimento_estoque/img/seta.png"));
		Image imaS = iconS.getImage();
		Image imagemS = imaS.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT);
		Icon icoS = new ImageIcon(imagemS);
		lblNewLabel.setIcon(icoS);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);
		
		JLabel lblDia = new JLabel("dia");
		lblDia.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblDia.setBounds(148, 208, 36, 14);
		lblDia.setVisible(false);
		contentPane.add(lblDia);
		
		JLabel lblMes = new JLabel("mes");
		lblMes.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblMes.setBounds(211, 208, 46, 14);
		lblMes.setVisible(false);
		contentPane.add(lblMes);
		
		JLabel lblAno = new JLabel("ano");
		lblAno.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblAno.setBounds(274, 208, 46, 14);
		lblAno.setVisible(false);
		contentPane.add(lblAno);
		
		JComboBox cbDia = new JComboBox(dias);
		cbDia.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		cbDia.setBounds(148, 234, 51, 26);
		cbDia.setVisible(false);
		contentPane.add(cbDia);
		
		JComboBox cbMes = new JComboBox(meses);
		cbMes.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		cbMes.setBounds(211, 234, 51, 26);
		cbMes.setVisible(false);
		contentPane.add(cbMes);
		
		JComboBox cbAno = new JComboBox(anos);
		cbAno.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		cbAno.setBounds(274, 234, 69, 26);
		cbAno.setVisible(false);
		contentPane.add(cbAno);
		
		JButton btnNewButton = new JButton("Onde a m\u00E1gica ocorre..");
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(MainScreen.this, "Tem certeza que deseja prosseguir? É um caminho sem volta..") == 0) {
				
					if(cbDia.getSelectedItem().toString() == "" || cbDia.getSelectedItem().toString() == null || cbMes.getSelectedItem().toString() == "" || 
							cbMes.getSelectedItem().toString() == null || cbAno.getSelectedItem().toString() == "" || cbAno.getSelectedItem().toString() == null) {
						
						JOptionPane.showMessageDialog(null, "Preencha todos os campos de data de De e até por favor pessoa!!");
						
					}else {
						Service service;
						
						try {
							service = new Service();
						
							String de = cbDia.getSelectedItem().toString() +"." +cbMes.getSelectedItem().toString() +"." +cbAno.getSelectedItem().toString();
							
							System.out.println(de);
						
							service.alterarDetParaZero(banco, de);
							
							System.out.println("Pegou pra Zero!!");
							
							ArrayList<Produto> produtos = new ArrayList<Produto>();
							
							produtos = service.buscarProdsComanda(banco);
							
							for(int i = 0; i < produtos.size(); i++) {
								
								Produto produtoCerto = new Produto();
								
								produtoCerto.setCancela(produtos.get(i).getCancela());
								produtoCerto.setCodigo(produtos.get(i).getCodigo());
								produtoCerto.setNquant(produtos.get(i).getNquant());
								produtoCerto.setNum_cotrole(produtos.get(i).getNum_cotrole());
								produtoCerto.setOperacao(produtos.get(i).getOperacao());
								produtoCerto.setPreco(produtos.get(i).getPreco());
								
								service.alterarParaConcertar(banco, produtoCerto);
								
							}
							
							JOptionPane.showMessageDialog(null, "Agora é so conferir e ver se deu certo!!");
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		btnNewButton.setBounds(118, 345, 249, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblMenewcorrigebaixaestoque = new JLabel("MENEW CORRIGE BAIXA ESTOQUE");
		lblMenewcorrigebaixaestoque.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblMenewcorrigebaixaestoque.setBounds(73, 120, 368, 23);
		contentPane.add(lblMenewcorrigebaixaestoque);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);
			}
		});
		lblX.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
		lblX.setBounds(434, 11, 29, 26);
		contentPane.add(lblX);
		
		JLabel lblCorrigirDesdeQuando = new JLabel("Corrigir desde quando?");
		lblCorrigirDesdeQuando.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		lblCorrigirDesdeQuando.setBounds(148, 161, 179, 16);
		lblCorrigirDesdeQuando.setVisible(false);
		contentPane.add(lblCorrigirDesdeQuando);
		
		txtBanco = new JTextField();
		txtBanco.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtBanco.setBounds(6, 271, 459, 36);
		contentPane.add(txtBanco);
		txtBanco.setColumns(10);
		
		JLabel lblInformeAbaixoO = new JLabel("Informe abaixo o caminho do netuno");
		lblInformeAbaixoO.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblInformeAbaixoO.setBounds(12, 234, 457, 26);
		contentPane.add(lblInformeAbaixoO);
		
		ImageIcon iconC = new ImageIcon(MainScreen.class.getResource("/br/com/movimento_estoque/img/config.png"));
		Image imaC = iconC.getImage();
		Image imagemC = imaC.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		Icon icoC = new ImageIcon(imagemC);
		JButton btnconfig = new JButton(icoC);
		btnconfig.setVisible(false);
		
		JButton btnConfirmar = new JButton("confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtBanco.getText() == "") {
					
					JOptionPane.showMessageDialog(null, "Preencha o caminho do banco jovem, o sistema não é advinho!!");
					
				}else {
				
					banco = txtBanco.getText();
					
					//abaixo os que aparecem
					
					lblAno.setVisible(true);
					lblDia.setVisible(true);
					lblMes.setVisible(true);
					lblCorrigirDesdeQuando.setVisible(true);
					lblNewLabel.setVisible(true);
					
					cbAno.setVisible(true);
					cbMes.setVisible(true);
					cbDia.setVisible(true);
					
					btnNewButton.setVisible(true);
					btnconfig.setVisible(true);
					
					//abaixo os que somem
					
					lblInformeAbaixoO.setVisible(false);
					
					txtBanco.setVisible(false);
					
					btnConfirmar.setVisible(false);
				}
			}
		});
		btnConfirmar.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnConfirmar.setBounds(6, 319, 112, 28);
		contentPane.add(btnConfirmar);
		
		//Ação do Botão config
		btnconfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//abaixo os que somem
				
				lblAno.setVisible(false);
				lblDia.setVisible(false);
				lblMes.setVisible(false);
				lblCorrigirDesdeQuando.setVisible(false);
				lblNewLabel.setVisible(false);
				
				cbAno.setVisible(false);
				cbMes.setVisible(false);
				cbDia.setVisible(false);
				
				btnNewButton.setVisible(false);
				btnconfig.setVisible(false);
				
				//abaixo os que aparecem
				
				lblInformeAbaixoO.setVisible(true);
				
				txtBanco.setVisible(true);
				
				btnConfirmar.setVisible(true);
			}
		});
		btnconfig.setBounds(6, 11, 46, 30);
		contentPane.add(btnconfig);
		
		
		
		
	}
}
