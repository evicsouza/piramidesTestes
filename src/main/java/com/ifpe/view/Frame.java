package com.ifpe.view;

import com.ifpe.conexaodb.Banco;
import com.ifpe.emprestimo.Emprestimo;
import com.ifpe.excecoes.SiapeInvalidoException;
import com.ifpe.excecoes.TelefoneInvalidoException;
import com.ifpe.facade.Facade;
import com.ifpe.item.Item;
import com.ifpe.professor.Professor;
import com.ifpe.utils.DbUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Frame extends JFrame{

	public JMenuBar menubar;
	static JTable tableProfessor;
	static JTable tableEmprestimo;
	static JTable tableItem;
	public JMenu menuCadastrar, menuRemover, menuEmprestimo;
	public JMenuItem menuItemCadastrarProfessor, menuItemCadastrarItem, menuItemRemoverProfessor, menuItemRemoverItem, menuItemCadastrarEmprestimo, menuItemRemoverEmprestimo;
	public JPanel panel1 = new JPanel();
	public JPanel panel2 = new JPanel();
	public JTextField txtNome = new JTextField(20);
	public JTextField txtTelefone = new JTextField(20);
	public JTextField txtSiape = new JTextField(20);
	public JTextField txtTipoItem = new JTextField(20);
	public JTextField txtCodigoItem = new JTextField(20);;
	public JButton btnCadastrarProf = new JButton("Cadastrar");
	public JButton btnCadastrarItem = new JButton("Cadastrar");
	public JButton btnRemoverProf = new JButton("Remover");
	public JButton btnRemoverItem = new JButton("Remover");
	static DbUtils db = new DbUtils();

	public Frame() throws MalformedURLException, IOException{
		initComponents(null, null);
	}

	public void initComponents(Frame frame, Facade fachada) throws MalformedURLException, IOException {
		//		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		//		fillPanelProf();
		//		fillPanelItem();
		//		initMenu();

		panel1.setBackground(new Color(231, 233, 236));
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		gbc.insets = new Insets(5, 5, 5, 5);
		//
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel1.add(new JLabel("Nome: "), gbc);

		gbc.gridx = 1;
		panel1.add(txtNome, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel1.add(new JLabel("Telefone: "), gbc);

		gbc.gridx = 1;
		panel1.add(txtTelefone, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel1.add(new JLabel("Siape: "), gbc);

		gbc.gridx = 1;
		panel1.add(txtSiape, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panel1.add(btnCadastrarProf, gbc);


		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.WEST;

		panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
				"Cadastro de Professores"));

		pack();


		panel2.setBackground(new Color(231, 233, 236));
		panel2.setLayout(new GridBagLayout());


		//		GridBagConstraints gbc = new GridBagConstraints();


		gbc.insets = new Insets(5, 5, 5, 5);
		//
		gbc.gridx = 0;
		gbc.gridy = 0;

		panel2.add(new JLabel("Tipo: "), gbc);

		gbc.gridx = 1;
		panel2.add(txtTipoItem, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel2.add(new JLabel("Código: "), gbc);

		gbc.gridx = 1;
		panel2.add(txtCodigoItem, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panel2.add(btnCadastrarItem, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.WEST;

		panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
				"Cadastro de Itens"));

		pack();

		menubar = new JMenuBar();
		menuCadastrar = new JMenu("Cadastrar");
		menuRemover = new JMenu("Remover");
		menuEmprestimo = new JMenu("Empréstimo");
		menuItemCadastrarProfessor = new JMenuItem("Professor");
		menuItemCadastrarItem = new JMenuItem("Item");
		menuItemRemoverProfessor = new JMenuItem("Professor");
		menuItemRemoverItem = new JMenuItem("Item");
		menuItemCadastrarEmprestimo = new JMenuItem("Efetuar Empréstimo");
		menuItemRemoverEmprestimo = new JMenuItem("Finalizar Empréstimo");
		menubar.add(menuCadastrar);
		menubar.add(menuRemover);
		menubar.add(menuEmprestimo);
		menuCadastrar.add(menuItemCadastrarProfessor);
		menuCadastrar.add(menuItemCadastrarItem);
		menuRemover.add(menuItemRemoverProfessor);
		menuRemover.add(menuItemRemoverItem);
		menuEmprestimo.add(menuItemCadastrarEmprestimo);
		menuEmprestimo.add(menuItemRemoverEmprestimo);
		setJMenuBar(menubar);
		menuItemCadastrarProfessor.addActionListener(new MenuAction(panel1));
		menuItemCadastrarItem.addActionListener(new MenuAction(panel2));



		//		frame.setBackground(Color.WHITE);
		//		JLabel t = new JLabel(new ImageIcon("images/empréstimo.png"));
		//		frame.add(t);
		//
		//		frame.btnCadastrarProf.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				try {
		//					String nome = frame.txtNome.getText();
		//					String telefone = frame.txtTelefone.getText();
		//					String siape = frame.txtSiape.getText();
		//					Professor p = new Professor(nome, telefone, siape);
		//					int res = fachada.inserirProfessor(p);
		//					if(res > 0) {
		//						GuiUtils.mostraMsgSucesso();
		//						frame.clearAllFieldsProfessor();
		//					} else {
		//						GuiUtils.mostraMsgFalhaSiapeIgual();
		//					}
		//				} catch (TelefoneInvalidoException ex) {
		//					GuiUtils.mostraMsgTelefoneInvalido();
		//					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
		//				} catch (SiapeInvalidoException ex) {
		//					GuiUtils.mostraMsgSiapeInvalido();
		//					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
		//				}
		//			}
		//		});
		//
		//		frame.btnCadastrarItem.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//
		//				int res = fachada.inserirItem(new Item(frame.txtCodigoItem.getText(), frame.txtTipoItem.getText()));
		//				if(res > 0) {
		//					GuiUtils.mostraMsgSucesso();
		//					frame.clearAllFieldsItem();
		//				} else {
		//					GuiUtils.mostraMsgFalhaCodigoIgual();
		//				}
		//			}
		//		});
		//
		//		frame.menuItemRemoverProfessor.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				String siapeRemover = GuiUtils.recebeSiapeParaRemoverProfessor();
		//				int res = fachada.removerProfessor(siapeRemover);
		//				if(res > 0) {
		//					GuiUtils.mostraMsgSucesso();
		//					frame.clearAllFieldsProfessor();
		//				} else {
		//					GuiUtils.mostraMsgFalhaAoRemoverProfessor();
		//				}
		//			}
		//		});
		//
		//		frame.menuItemRemoverItem.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				String codigo = GuiUtils.recebeCodigoParaRemoverItem();
		//				int res = fachada.removerItem(codigo);
		//				if(res > 0) {
		//					GuiUtils.mostraMsgSucesso();
		//					frame.clearAllFieldsItem();
		//				} else {
		//					GuiUtils.mostraMsgFalhaAoRemoverItem();
		//				}
		//			}
		//		});
		//
		//		frame.menuItemCadastrarEmprestimo.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//
		//				ResultSet rs = db.listar(DbUtils.selectProfessor());
		//				try {
		//					tableProfessor = new JTable(db.buildTableModel(rs));
		//					int ans = GuiUtils.mostraTabelaProfessor(tableProfessor);
		//
		//					if(ans == JOptionPane.YES_OPTION) {
		//						DefaultTableModel defaultTableModel = (DefaultTableModel) tableProfessor.getModel();
		//						Vector v = defaultTableModel.getDataVector().get(tableProfessor.getSelectedRow());
		//						String siapeProf = v.get(v.size()-1).toString();
		//						ResultSet rsItem = db.listar(DbUtils.selectItem());
		//						tableItem = new JTable(db.buildTableModel(rsItem));
		//						if(tableItem.getRowCount() == 0) {
		//							GuiUtils.mostraMsgTodosItensEmprestados();
		//						} else {
		//							int ansItem = GuiUtils.mostraTabelaItem(tableItem);
		//							if(ansItem == JOptionPane.YES_OPTION) {
		//								DefaultTableModel defaultTableModelItem = (DefaultTableModel) tableItem.getModel();
		//								Vector vItem = defaultTableModelItem.getDataVector().get(tableItem.getSelectedRow());
		//								String codItem = vItem.get(0).toString();
		//								int finalAns = GuiUtils.mostraConfirmacaoEmprestimo(siapeProf, codItem);
		//								if(finalAns == JOptionPane.YES_OPTION) {
		//									fachada.inserirEmprestimo(new Emprestimo(siapeProf, codItem, new Timestamp(System.currentTimeMillis()).toString()));
		//									GuiUtils.mostraMsgSucesso();
		//								}
		//							}
		//						}
		//					}
		//				} catch (SQLException ex) {
		//					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
		//				} catch (Exception ex) {
		//					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
		//				}
		//			}
		//		});
		//
		//		frame.menuItemRemoverEmprestimo.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				ResultSet rs = db.listar(DbUtils.selectEmprestimo());
		//				try {
		//					tableEmprestimo = new JTable(db.buildTableModel(rs));
		//					tableEmprestimo.setName("tableEmprestimo");
		//					int ans = GuiUtils.mostraTabelaEmprestimo(tableEmprestimo);
		//					DefaultTableModel defaultTableModelEmp = (DefaultTableModel) tableEmprestimo.getModel();
		//					Vector vEmp = defaultTableModelEmp.getDataVector().get(tableEmprestimo.getSelectedRow());
		//					//int codigoEmp = (int)tableEmprestimo.getValueAt(tableEmprestimo.getSelectedRow(), tableEmprestimo.getSelectedColumn());
		//					int codigoEmp = (int) vEmp.get(0);
		//					int confirmacao = GuiUtils.mostraConfirmacaoRemoverEmprestimo(codigoEmp);
		//					if(confirmacao == JOptionPane.YES_OPTION) {
		//						int resultadoRemocaoEmp = fachada.removerEmprestimo(codigoEmp);
		//						if(resultadoRemocaoEmp > 0) {
		//							GuiUtils.mostraMsgSucesso();
		//						}
		//					}
		//				} catch (SQLException ex) {
		//					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
		//				} catch (Exception ex) {
		//					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
		//				}
		//			}
		//		});
		//
		//		frame.setSize(690, 500);
		//		frame.getContentPane().setBackground(Color.WHITE);
		//		Image icon = Toolkit.getDefaultToolkit().getImage("images/icon.png");
		//		frame.setIconImage(icon);
		//		frame.setTitle("IFEmpréstimo");
		//		frame.setVisible(true);


	}


	public void clearAllFieldsProfessor() {
		txtNome.setText("");
		txtTelefone.setText("");
		txtSiape.setText("");
	}

	public void clearAllFieldsItem() {
		txtCodigoItem.setText("");
		txtTipoItem.setText("");

	}

	public void fillPanelProf() {
		//		panel1.setBackground(new Color(231, 233, 236));
		//		panel1.setLayout(new GridBagLayout());
		//		GridBagConstraints gbc = new GridBagConstraints();
		//
		//
		//		gbc.insets = new Insets(5, 5, 5, 5);
		//		//
		//		gbc.gridx = 0;
		//		gbc.gridy = 0;
		//		panel1.add(new JLabel("Nome: "), gbc);
		//
		//		gbc.gridx = 1;
		//		panel1.add(txtNome, gbc);
		//
		//		gbc.gridx = 0;
		//		gbc.gridy = 1;
		//		panel1.add(new JLabel("Telefone: "), gbc);
		//
		//		gbc.gridx = 1;
		//		panel1.add(txtTelefone, gbc);
		//
		//		gbc.gridx = 0;
		//		gbc.gridy = 2;
		//		panel1.add(new JLabel("Siape: "), gbc);
		//
		//		gbc.gridx = 1;
		//		panel1.add(txtSiape, gbc);
		//
		//		gbc.gridx = 1;
		//		gbc.gridy = 3;
		//		panel1.add(btnCadastrarProf, gbc);
		//
		//
		//		gbc.gridx = 0;
		//		gbc.gridy = 2;
		//		gbc.gridwidth = 2;
		//		gbc.anchor = GridBagConstraints.WEST;
		//
		//		panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
		//				"Cadastro de Professores"));
		//
		//		pack();


	}

	public void fillPanelItem() {


		//		panel2.setBackground(new Color(231, 233, 236));
		//		panel2.setLayout(new GridBagLayout());
		//
		//
		//		GridBagConstraints gbc = new GridBagConstraints();
		//
		//
		//		gbc.insets = new Insets(5, 5, 5, 5);
		//		//
		//		gbc.gridx = 0;
		//		gbc.gridy = 0;
		//
		//		panel2.add(new JLabel("Tipo: "), gbc);
		//
		//		gbc.gridx = 1;
		//		panel2.add(txtTipoItem, gbc);
		//
		//		gbc.gridx = 0;
		//		gbc.gridy = 1;
		//		panel2.add(new JLabel("Código: "), gbc);
		//
		//		gbc.gridx = 1;
		//		panel2.add(txtCodigoItem, gbc);
		//
		//		gbc.gridx = 1;
		//		gbc.gridy = 2;
		//		panel2.add(btnCadastrarItem, gbc);
		//
		//		gbc.gridx = 0;
		//		gbc.gridy = 2;
		//		gbc.gridwidth = 2;
		//		gbc.anchor = GridBagConstraints.WEST;
		//
		//		panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), 
		//				"Cadastro de Itens"));
		//
		//		pack();

	}


	public class MenuAction implements ActionListener {

		public JPanel panel;
		public MenuAction(JPanel pnl) {
			this.panel = pnl;

		}
		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(panel);

		}

	}


	private void initMenu() {
		//		menubar = new JMenuBar();
		//		menuCadastrar = new JMenu("Cadastrar");
		//		menuRemover = new JMenu("Remover");
		//		menuEmprestimo = new JMenu("Empréstimo");
		//		menuItemCadastrarProfessor = new JMenuItem("Professor");
		//		menuItemCadastrarItem = new JMenuItem("Item");
		//		menuItemRemoverProfessor = new JMenuItem("Professor");
		//		menuItemRemoverItem = new JMenuItem("Item");
		//		menuItemCadastrarEmprestimo = new JMenuItem("Efetuar Empréstimo");
		//		menuItemRemoverEmprestimo = new JMenuItem("Finalizar Empréstimo");
		//		menubar.add(menuCadastrar);
		//		menubar.add(menuRemover);
		//		menubar.add(menuEmprestimo);
		//		menuCadastrar.add(menuItemCadastrarProfessor);
		//		menuCadastrar.add(menuItemCadastrarItem);
		//		menuRemover.add(menuItemRemoverProfessor);
		//		menuRemover.add(menuItemRemoverItem);
		//		menuEmprestimo.add(menuItemCadastrarEmprestimo);
		//		menuEmprestimo.add(menuItemRemoverEmprestimo);
		//		setJMenuBar(menubar);
		//		menuItemCadastrarProfessor.addActionListener(new MenuAction(panel1));
		//		menuItemCadastrarItem.addActionListener(new MenuAction(panel2));


	}

	public void changePanel(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().revalidate();
		getContentPane().repaint();
	}



	public static void main(String[] args) throws MalformedURLException, IOException {

		Frame frame = new Frame();
		Facade fachada = new Facade();
		frame.setBackground(Color.WHITE);
		JLabel t = new JLabel(new ImageIcon("images/empréstimo.png"));
		frame.add(t);

		frame.btnCadastrarProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = frame.txtNome.getText();
					String telefone = frame.txtTelefone.getText();
					String siape = frame.txtSiape.getText();
					Professor p = new Professor(nome, telefone, siape);
					int res = fachada.inserirProfessor(p);
					if(res > 0) {
						GuiUtils.mostraMsgSucesso();
						frame.clearAllFieldsProfessor();
					} else {
						GuiUtils.mostraMsgFalhaSiapeIgual();
					}
				} catch (TelefoneInvalidoException ex) {
					GuiUtils.mostraMsgTelefoneInvalido();
					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
				} catch (SiapeInvalidoException ex) {
					GuiUtils.mostraMsgSiapeInvalido();
					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		frame.btnCadastrarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int res = fachada.inserirItem(new Item(frame.txtCodigoItem.getText(), frame.txtTipoItem.getText()));
				if(res > 0) {
					GuiUtils.mostraMsgSucesso();
					frame.clearAllFieldsItem();
				} else {
					GuiUtils.mostraMsgFalhaCodigoIgual();
				}
			}
		});

		frame.menuItemRemoverProfessor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String siapeRemover = GuiUtils.recebeSiapeParaRemoverProfessor();
				int res = fachada.removerProfessor(siapeRemover);
				if(res > 0) {
					GuiUtils.mostraMsgSucesso();
					frame.clearAllFieldsProfessor();
				} else {
					GuiUtils.mostraMsgFalhaAoRemoverProfessor();
				}
			}
		});

		frame.menuItemRemoverItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String codigo = GuiUtils.recebeCodigoParaRemoverItem();
				int res = fachada.removerItem(codigo);
				if(res > 0) {
					GuiUtils.mostraMsgSucesso();
					frame.clearAllFieldsItem();
				} else {
					GuiUtils.mostraMsgFalhaAoRemoverItem();
				}
			}
		});

		frame.menuItemCadastrarEmprestimo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ResultSet rs = db.listar(DbUtils.selectProfessor());
				try {
					tableProfessor = new JTable(db.buildTableModel(rs));
					int ans = GuiUtils.mostraTabelaProfessor(tableProfessor);

					if(ans == JOptionPane.YES_OPTION) {
						DefaultTableModel defaultTableModel = (DefaultTableModel) tableProfessor.getModel();
						Vector v = defaultTableModel.getDataVector().get(tableProfessor.getSelectedRow());
						String siapeProf = v.get(v.size()-1).toString();
						ResultSet rsItem = db.listar(DbUtils.selectItem());
						tableItem = new JTable(db.buildTableModel(rsItem));
						if(tableItem.getRowCount() == 0) {
							GuiUtils.mostraMsgTodosItensEmprestados();
						} else {
							int ansItem = GuiUtils.mostraTabelaItem(tableItem);
							if(ansItem == JOptionPane.YES_OPTION) {
								DefaultTableModel defaultTableModelItem = (DefaultTableModel) tableItem.getModel();
								Vector vItem = defaultTableModelItem.getDataVector().get(tableItem.getSelectedRow());
								String codItem = vItem.get(0).toString();
								int finalAns = GuiUtils.mostraConfirmacaoEmprestimo(siapeProf, codItem);
								if(finalAns == JOptionPane.YES_OPTION) {
									fachada.inserirEmprestimo(new Emprestimo(siapeProf, codItem, new Timestamp(System.currentTimeMillis()).toString()));
									GuiUtils.mostraMsgSucesso();
								}
							}
						}
					}
				} catch (SQLException ex) {
					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
				} catch (Exception ex) {
					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		frame.menuItemRemoverEmprestimo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.listar(DbUtils.selectEmprestimo());
				try {
					tableEmprestimo = new JTable(db.buildTableModel(rs));
					tableEmprestimo.setName("tableEmprestimo");
					int ans = GuiUtils.mostraTabelaEmprestimo(tableEmprestimo);
					DefaultTableModel defaultTableModelEmp = (DefaultTableModel) tableEmprestimo.getModel();
					Vector vEmp = defaultTableModelEmp.getDataVector().get(tableEmprestimo.getSelectedRow());
					//int codigoEmp = (int)tableEmprestimo.getValueAt(tableEmprestimo.getSelectedRow(), tableEmprestimo.getSelectedColumn());
					int codigoEmp = (int) vEmp.get(0);
					int confirmacao = GuiUtils.mostraConfirmacaoRemoverEmprestimo(codigoEmp);
					if(confirmacao == JOptionPane.YES_OPTION) {
						int resultadoRemocaoEmp = fachada.removerEmprestimo(codigoEmp);
						if(resultadoRemocaoEmp > 0) {
							GuiUtils.mostraMsgSucesso();
						}
					}
				} catch (SQLException ex) {
					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
				} catch (Exception ex) {
					Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		frame.setSize(690, 500);
		frame.getContentPane().setBackground(Color.WHITE);
		Image icon = Toolkit.getDefaultToolkit().getImage("images/icon.png");
		frame.setIconImage(icon);
		frame.setTitle("IFEmpréstimo");
		frame.setVisible(true);

		//
		//		EventQueue.invokeLater(() -> {
		//			try {
		//				new Frame().setVisible(true);
		//			} catch (IOException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//		});


	}
}