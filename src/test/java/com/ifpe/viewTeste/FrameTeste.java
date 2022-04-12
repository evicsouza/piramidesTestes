package com.ifpe.viewTeste;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JMenuItemFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ifpe.emprestimo.Emprestimo;
import com.ifpe.excecoes.SiapeInvalidoException;
import com.ifpe.excecoes.TelefoneInvalidoException;
import com.ifpe.facade.Facade;
import com.ifpe.item.Item;
import com.ifpe.professor.Professor;
import com.ifpe.utils.DbUtils;
import com.ifpe.view.Frame;
import com.ifpe.view.GuiUtils;

public class FrameTeste {

	private FrameFixture janela;
	private JPanelFixture panel;
	Frame frame;
	Facade fachada;
	DbUtils db = new DbUtils();
	JTable tableProfessor;
	JTable tableEmprestimo;
	JTable tableItem;
	JMenuBar menubar;



	//	@BeforeClass
	//	public static void setUpOnce() {
	//		FailOnThreadViolationRepaintManager.install();
	//	}

	@Before
	public void onSetUp() throws MalformedURLException, IOException {
		frame = GuiActionRunner.execute(() -> new Frame());
		frame = new Frame();
		fachada = new Facade();
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

		janela = new FrameFixture(frame);
	}

	@Test
	public void verificaJanelaAbrir() {
		frame.isVisible();
	}

	@Test
	public void verificaTitulo() {
		janela.requireTitle("IFEmpréstimo");
	}

	@Test
	public void clickMenuCadastrarProfTeste() {
		JMenuItemFixture menuItem = janela.menuItemWithPath("Cadastrar", "Professor");
		menuItem.click();
	}

	@Test
	public void clickMenuCadastrarItemTeste() {
		JMenuItemFixture menuItem = janela.menuItemWithPath("Cadastrar", "Item");
		menuItem.click();
	}

	@Test
	public void clickMenuEfetuarEmprestimoTeste() {
		JMenuItemFixture menuItem = janela.menuItemWithPath("Empréstimo", "Efetuar Empréstimo");
		menuItem.click();
	}

	//
	//	@Test
	//	public void clickMenuRemoverEmprestimoTeste() {
	//		JMenuItemFixture menuItem = janela.menuItemWithPath("Empréstimo", "Finalizar Empréstimo");
	//		menuItem.click();
	//	}

	//	@Test
	//	public void efetuarEmprestimoTeste() {
	//		janela.menuItemWithPath("Empréstimo", "Efetuar Empréstimo").click();
	//		JTableFixture tableProf = janela.table();
	//		tableProf.requireVisible();
	//		//		tableProf.cell("1234567").row();
	//		//		tableProf.click();



	//	@Test
	//	public void finalizarEmprestimo() {
	//		JMenuItemFixture menuItem = janela.menuItemWithPath("Empréstimo", "Finalizar Empréstimo");
	//		menuItem.click();
	//		JPanelFixture panel = janela.panel();
	//		//		JButtonFixture button = panel.button(new GenericTypeMatcher<JButton>(JButton.class) {
	//		//
	//		//			@Override
	//		//			protected boolean isMatching(JButton component) {
	//		//				// TODO Auto-generated method stub
	//		//				return "Finalizar Empréstimo".equals(component.getText());
	//		//			}
	//		//
	//		//		});
	//		JTableFixture tabela = panel.table("tableEmprestimo");	
	//		JTableFixture cell = panel.table("tableEmprestimo").selectRows(2);
	//
	//	}



	@After
	public void onTearDown() {
		janela.cleanUp();
	}

}
