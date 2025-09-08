import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI-based Temperature Converter using Java Swing
public class TemperatureConverterGUI extends JFrame implements ActionListener {
    // GUI components
    private JTextField inputField;
    private JComboBox<String> fromScale, toScale;
    private JLabel resultLabel;

    // Constructor: Sets up the GUI window
    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");             // Window title
        setSize(400, 200);                             // Window size (width x height)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the app when the window is closed
        setLayout(new GridLayout(4, 2, 10, 10));       // Grid layout with 4 rows, 2 columns, and spacing

        // Temperature scales for dropdown menus
        String[] scales = {"Celsius", "Fahrenheit", "Kelvin"};

        // Add components to the window

        // Input label and text field
        add(new JLabel("Enter Temperature:"));
        inputField = new JTextField();
        add(inputField);

        // "From" scale dropdown
        add(new JLabel("From:"));
        fromScale = new JComboBox<>(scales);
        add(fromScale);

        // "To" scale dropdown
        add(new JLabel("To:"));
        toScale = new JComboBox<>(scales);
        add(toScale);

        // Convert button
        JButton convertBtn = new JButton("Convert");
        convertBtn.addActionListener(this); // Attach event listener
        add(convertBtn);

        // Label to display the result
        resultLabel = new JLabel("Result: ");
        add(resultLabel);

        // Make the window visible
        setVisible(true);
    }

    // Event handler: Called when the Convert button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Parse the temperature value entered by the user
            double temp = Double.parseDouble(inputField.getText());

            // Get the selected units
            String from = (String) fromScale.getSelectedItem();
            String to = (String) toScale.getSelectedItem();

            // Perform conversion
            double result = convert(temp, from, to);

            // Show result in the label
            resultLabel.setText("Result: " + result + " " + to);
        } catch (NumberFormatException ex) {
            // Show error message if input is not a valid number
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }

    // Conversion logic: converts value from one temperature scale to another
    private double convert(double value, String from, String to) {
        if (from.equals(to)) return value; // No conversion needed if scales are the same
        double celsius;

        // Step 1: Convert from original scale to Celsius
        switch (from) {
            case "Fahrenheit":
                celsius = (value - 32) * 5 / 9;
                break;
            case "Kelvin":
                celsius = value - 273.15;
                break;
            default: // If already in Celsius
                celsius = value;
                break;
        }

        // Step 2: Convert from Celsius to target scale
        switch (to) {
            case "Fahrenheit":
                return (celsius * 9 / 5) + 32;
            case "Kelvin":
                return celsius + 273.15;
            default: // Target is Celsius
                return celsius;
        }
    }

    // Main method: entry point of the program
    public static void main(String[] args) {
        new TemperatureConverterGUI(); // Create and display the GUI
    }
}
