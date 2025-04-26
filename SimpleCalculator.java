import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator {
    private Frame frame;
    private TextField display;
    private Panel panel;
    private Button[] numberButtons = new Button[10];
    private Button addButton, subButton, mulButton, divButton, eqButton, clrButton;

    public SimpleCalculator() {
        frame = new Frame("Calculator");
        display = new TextField();
        panel = new Panel();
        frame.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            panel.add(numberButtons[i]);
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        eqButton = new Button("=");
        clrButton = new Button("C");

        // Arrange operator buttons properly in the grid
        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(eqButton);
        panel.add(clrButton);

        addButton.addActionListener(new OperatorButtonListener());
        subButton.addActionListener(new OperatorButtonListener());
        mulButton.addActionListener(new OperatorButtonListener());
        divButton.addActionListener(new OperatorButtonListener());
        eqButton.addActionListener(new EqualButtonListener());
        clrButton.addActionListener(new ClearButtonListener());

        frame.add(display, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 500);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Button source = (Button) e.getSource();
            display.setText(display.getText() + source.getLabel());
        }
    }

    class OperatorButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Button source = (Button) e.getSource();
            display.setText(display.getText() + " " + source.getLabel() + " ");
        }
    }

    class EqualButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String expression = display.getText();
                String[] tokens = expression.split(" ");
                double num1 = Double.parseDouble(tokens[0]);
                String operator = tokens[1];
                double num2 = Double.parseDouble(tokens[2]);

                double result = 0;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            display.setText("Error: Div by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();  
    }
}
