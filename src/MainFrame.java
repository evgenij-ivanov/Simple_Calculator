import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JButton btn8;

	private boolean CheckSign(String text, char sign)
	{
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == sign) return true;
		}
		return false;
	}
	
	public double Calculate(double a, double b, char c)
	{
		double result = 0;
		if(c == '+')
		{
			result = a + b;
		}
		if(c == '-')
		{
			result = a - b;
		}
		if(c == '*')
		{
			result = a * b;
		}
		if(c == '/')
		{
			result = a / b;
		}
		return result;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public double resultValue = 0, secondValue = 0, firstValue = 0;
	public char sign = 'N';
	public boolean flag = false;
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 393);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				char currentChar = arg0.getKeyChar();
				if(!(Character.isDigit(currentChar) || (currentChar == KeyEvent.VK_BACK_SPACE) ))
				{
					if(((currentChar != '.') || CheckSign(formattedTextField.getText(), '.')) && ((currentChar != '-') || (formattedTextField.getText().length() > 0)))
					{
						arg0.consume();
					}
				}
			}
		});
		formattedTextField.setForeground(Color.GRAY);
		formattedTextField.setBackground(Color.WHITE);
		formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField.setFont(new Font("Dialog", Font.PLAIN, 28));
		formattedTextField.setBounds(12, 12, 226, 44);
		contentPane.add(formattedTextField);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formattedTextField.setText("");
				firstValue = 0;
				secondValue = 0;
				resultValue = 0;
			}
		});
		btnC.setToolTipText("Clear field");
		btnC.setBackground(new Color(255, 51, 51));
		btnC.setFont(new Font("Dialog", Font.BOLD, 18));
		btnC.setBounds(12, 66, 50, 50);
		contentPane.add(btnC);
		
		JButton btnDel = new JButton("D");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formattedTextField.setText(formattedTextField.getText().substring(0,formattedTextField.getText().length() - 1));
			}
		});
		btnDel.setToolTipText("Delete the last character");
		btnDel.setBackground(new Color(255, 51, 51));
		btnDel.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDel.setBounds(70, 66, 50, 50);
		contentPane.add(btnDel);
		
		JButton btnABS = new JButton("|x|");
		btnABS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double temp = Math.abs(Double.parseDouble(formattedTextField.getText()));
				if(temp == (int)temp)
				{
					formattedTextField.setText(Integer.toString((int)temp));
				}
				else
				{
					formattedTextField.setText(Double.toString(temp));
				}
			}
		});
		btnABS.setToolTipText("Absolution");
		btnABS.setBackground(new Color(0, 204, 255));
		btnABS.setFont(new Font("Dialog", Font.BOLD, 15));
		btnABS.setBounds(128, 66, 50, 50);
		contentPane.add(btnABS);
		
		JButton btnDiv = new JButton("/");
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sign == 'N') {
					firstValue = Double.parseDouble(formattedTextField.getText());
					formattedTextField.setText("");
					sign = '/';
					flag = false;
				}
				else {
					if(!((secondValue == 0) && (sign == '/'))) {
						secondValue = Double.parseDouble(formattedTextField.getText());
						resultValue = Calculate(firstValue, secondValue, sign);
						firstValue = resultValue;
						if(resultValue == (int)resultValue)
						{
							formattedTextField.setText(Integer.toString((int)resultValue));
						}
						else
						{
							formattedTextField.setText(Double.toString(resultValue));
						}
						flag = true;
						sign = '/';
					}
					else {
						formattedTextField.setText("ERROR");
						flag = false;
						firstValue = 0;
						secondValue = 0;
						resultValue = 0;
						sign = 'N';
					}
			}
		}
		});
		btnDiv.setBackground(new Color(0, 204, 255));
		btnDiv.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDiv.setBounds(186, 66, 50, 50);
		contentPane.add(btnDiv);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "7");
			}
		});
		btn7.setBackground(UIManager.getColor("Button.background"));
		btn7.setFont(new Font("Dialog", Font.BOLD, 18));
		btn7.setBounds(12, 124, 50, 50);
		contentPane.add(btn7);
		
		btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "8");
			}
		});
		btn8.setBackground(UIManager.getColor("Button.background"));
		btn8.setFont(new Font("Dialog", Font.BOLD, 18));
		btn8.setBounds(70, 124, 50, 50);
		contentPane.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "9");
			}
		});
		btn9.setBackground(UIManager.getColor("Button.background"));
		btn9.setFont(new Font("Dialog", Font.BOLD, 18));
		btn9.setBounds(128, 124, 50, 50);
		contentPane.add(btn9);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sign == 'N') {
					firstValue = Double.parseDouble(formattedTextField.getText());
					formattedTextField.setText("");
					sign = '+';
					flag = false;
				}
				else {
					if(!((secondValue == 0) && (sign == '/'))) {
						secondValue = Double.parseDouble(formattedTextField.getText());
						resultValue = Calculate(firstValue, secondValue, sign);
						firstValue = resultValue;
						if(resultValue == (int)resultValue)
						{
							formattedTextField.setText(Integer.toString((int)resultValue));
						}
						else
						{
							formattedTextField.setText(Double.toString(resultValue));
						}
						flag = true;
						sign = '+';
					}
					else {
						formattedTextField.setText("ERROR");
						firstValue = 0;
						flag = false;
						secondValue = 0;
						resultValue = 0;
						sign = 'N';
					}
				}
			}
		});
		btnPlus.setBackground(new Color(0, 204, 255));
		btnPlus.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPlus.setBounds(186, 124, 50, 50);
		contentPane.add(btnPlus);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "4");
			}
		});
		btn4.setBackground(UIManager.getColor("Button.background"));
		btn4.setFont(new Font("Dialog", Font.BOLD, 18));
		btn4.setBounds(12, 182, 50, 50);
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "5");
			}
		});
		btn5.setBackground(UIManager.getColor("Button.background"));
		btn5.setFont(new Font("Dialog", Font.BOLD, 18));
		btn5.setBounds(70, 182, 50, 50);
		contentPane.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "6");
			}
		});
		btn6.setBackground(UIManager.getColor("Button.background"));
		btn6.setFont(new Font("Dialog", Font.BOLD, 18));
		btn6.setBounds(128, 182, 50, 50);
		contentPane.add(btn6);
		
		JButton btnSub = new JButton("-");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sign == 'N') {
					firstValue = Double.parseDouble(formattedTextField.getText());
					formattedTextField.setText("");
					sign = '-';
					flag = false;
				}
				else {
					if(!((secondValue == 0) && (sign == '/'))) {
						secondValue = Double.parseDouble(formattedTextField.getText());
						resultValue = Calculate(firstValue, secondValue, sign);
						firstValue = resultValue;
						if(resultValue == (int)resultValue)
						{
							formattedTextField.setText(Integer.toString((int)resultValue));
						}
						else
						{
							formattedTextField.setText(Double.toString(resultValue));
						}
						flag = true;
						sign = '-';
					}
					else {
						formattedTextField.setText("ERROR");
						firstValue = 0;
						flag = false;
						secondValue = 0;
						resultValue = 0;
						sign = 'N';
					}
				}
			}
		});
		btnSub.setBackground(new Color(0, 204, 255));
		btnSub.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSub.setBounds(186, 182, 50, 50);
		contentPane.add(btnSub);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "1");
			}
		});
		btn1.setBackground(UIManager.getColor("Button.background"));
		btn1.setFont(new Font("Dialog", Font.BOLD, 18));
		btn1.setBounds(12, 240, 50, 50);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "2");
			}
		});
		btn2.setBackground(UIManager.getColor("Button.background"));
		btn2.setFont(new Font("Dialog", Font.BOLD, 18));
		btn2.setBounds(70, 240, 50, 50);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "3");
			}
		});
		btn3.setBackground(UIManager.getColor("Button.background"));
		btn3.setFont(new Font("Dialog", Font.BOLD, 18));
		btn3.setBounds(128, 240, 50, 50);
		contentPane.add(btn3);
		
		JButton btnMult = new JButton("*");
		btnMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sign == 'N') {
					firstValue = Double.parseDouble(formattedTextField.getText());
					formattedTextField.setText("");
					sign = '*';
					flag = false;
				}
				else {
					if(!((secondValue == 0) && (sign == '/'))) {
						secondValue = Double.parseDouble(formattedTextField.getText());
						resultValue = Calculate(firstValue, secondValue, sign);
						firstValue = resultValue;
						if(resultValue == (int)resultValue)
						{
							formattedTextField.setText(Integer.toString((int)resultValue));
						}
						else
						{
							formattedTextField.setText(Double.toString(resultValue));
						}
						flag = true;
						sign = '*';
					}
					else {
						formattedTextField.setText("ERROR");
						firstValue = 0;
						flag = false;
						secondValue = 0;
						resultValue = 0;
						sign = 'N';
					}
				}
			}
		});
		btnMult.setBackground(new Color(0, 204, 255));
		btnMult.setFont(new Font("Dialog", Font.BOLD, 18));
		btnMult.setBounds(186, 240, 50, 50);
		contentPane.add(btnMult);
		
		JButton btnDot = new JButton(".");
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				if (!CheckSign(formattedTextField.getText(), '.')) formattedTextField.setText(formattedTextField.getText() + ".");
			}
		});
		btnDot.setBackground(new Color(0, 204, 255));
		btnDot.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDot.setBounds(12, 298, 50, 50);
		contentPane.add(btnDot);

		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag) {
					formattedTextField.setText("");
					flag = false;
				}
				formattedTextField.setText(formattedTextField.getText() + "0");
			}
		});
		btn0.setBackground(UIManager.getColor("Button.background"));
		btn0.setFont(new Font("Dialog", Font.BOLD, 18));
		btn0.setBounds(70, 298, 50, 50);
		contentPane.add(btn0);
		
		JButton btnPerc = new JButton("%");
		btnPerc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((sign == 'N') || (sign == '*') || (sign == '/')) {
					firstValue = 0;
					secondValue = 0;
					resultValue = 0;
					formattedTextField.setText("ERROR");
				}
				else {
					secondValue = Double.parseDouble(formattedTextField.getText());
					resultValue = Calculate(firstValue, firstValue * (secondValue / 100), sign);
					if(resultValue == (int)resultValue)
					{
						formattedTextField.setText(Integer.toString((int)resultValue));
					}
					else
					{
						formattedTextField.setText(Double.toString(resultValue));
					}
					firstValue = resultValue;
					flag = true;
					sign = 'N';
				}
			}
		});
		btnPerc.setBackground(new Color(0, 204, 255));
		btnPerc.setFont(new Font("Dialog", Font.BOLD, 17));
		btnPerc.setBounds(128, 298, 50, 50);
		contentPane.add(btnPerc);
		
		JButton btnCalculate = new JButton("=");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sign != 'N') {
					String temp = formattedTextField.getText();
					if(temp == "") secondValue = 0;
					else secondValue = Double.parseDouble(temp);
					resultValue = Calculate(firstValue, secondValue, sign);
					flag = true;
					if(resultValue == (int)resultValue)
					{
						formattedTextField.setText(Integer.toString((int)resultValue));
					}
					else
					{
						formattedTextField.setText(Double.toString(resultValue));
					}
					firstValue = resultValue;
					secondValue = 0;
					sign = 'N';
					resultValue = 0;
				}
			}
		});
		btnCalculate.setBackground(SystemColor.info);
		btnCalculate.setFont(new Font("Dialog", Font.BOLD, 18));
		btnCalculate.setBounds(186, 298, 50, 50);
		contentPane.add(btnCalculate);
	}
}
