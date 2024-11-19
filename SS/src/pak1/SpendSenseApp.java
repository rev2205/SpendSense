package pak1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Map;

public class SpendSenseApp {
    private JFrame frame;
    private Repository repo = Repository.getRepository();
    private ReportService reportService = new ReportService();
    private Color primaryColor = new Color(41, 128, 185);
    private Color secondaryColor = new Color(52, 152, 219);
    private Color accentColor = new Color(231, 76, 60);
    private Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private Font buttonFont = new Font("Segoe UI", Font.PLAIN, 16);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
                SpendSenseApp window = new SpendSenseApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SpendSenseApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("SpendSense");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        JPanel mainPanel = createMainPanel();
        JPanel menuPanel = createMenuPanel();

        frame.getContentPane().add(mainPanel, "main");
        frame.getContentPane().add(menuPanel, "menu");
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome to SpendSense");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setForeground(primaryColor);
        welcomeLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JTextArea instructionsArea = new JTextArea(
            "Track your expenses with ease!\n\n" +
            "1. Add categories for your expenses\n" +
            "2. Enter your expenses\n" +
            "3. View reports and analyze your spending"
        );
        instructionsArea.setEditable(false);
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        instructionsArea.setBackground(Color.WHITE);
        instructionsArea.setBorder(new EmptyBorder(20, 40, 20, 40));
        mainPanel.add(instructionsArea, BorderLayout.CENTER);

        JButton btnContinue = createStyledButton("Get Started", e -> showPanel("menu"));
        btnContinue.setPreferredSize(new Dimension(200, 50));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(btnContinue);
        buttonPanel.setBorder(new EmptyBorder(0, 0, 40, 0));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBackground(Color.WHITE);

        JLabel menuLabel = new JLabel("SpendSense Menu");
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuLabel.setFont(titleFont);
        menuLabel.setForeground(primaryColor);
        menuLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        menuPanel.add(menuLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        buttonPanel.add(createMenuButton("Add Category", e -> onAddCategory()));
        buttonPanel.add(createMenuButton("Category List", e -> onCategoryList()));
        buttonPanel.add(createMenuButton("Expense Entry", e -> onExpenseEntry()));
        buttonPanel.add(createMenuButton("Expense List", e -> onExpenseList()));
        buttonPanel.add(createMenuButton("Monthly Expenses", e -> onMonthlyExpenseList()));
        buttonPanel.add(createMenuButton("Yearly Expenses", e -> onYearlyExpenseList()));
        buttonPanel.add(createMenuButton("Categorized Expenses", e -> onCategoryExpenseList()));
        
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        JButton btnBack = createStyledButton("Back to Main", e -> showPanel("main"));
        btnBack.setPreferredSize(new Dimension(200, 50));
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButtonPanel.setBackground(Color.WHITE);
        backButtonPanel.add(btnBack);
        backButtonPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        menuPanel.add(backButtonPanel, BorderLayout.SOUTH);

        return menuPanel;
    }

    private JButton createStyledButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(buttonFont);
        button.setForeground(Color.WHITE);
        button.setBackground(primaryColor);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(secondaryColor);
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(primaryColor);
            }
        });
        return button;
    }

    private JButton createMenuButton(String text, ActionListener listener) {
        JButton button = createStyledButton(text, listener);
        button.setPreferredSize(new Dimension(200, 60));
        return button;
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();
        cl.show(frame.getContentPane(), panelName);
    }

    private void onAddCategory() {
        String catName = JOptionPane.showInputDialog(frame, "Enter category name:", "Add Category", JOptionPane.PLAIN_MESSAGE);
        if (catName != null && !catName.trim().isEmpty()) {
            Category cat = new Category(catName);
            repo.catList.add(cat);
            JOptionPane.showMessageDialog(frame, "Category added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void onCategoryList() {
        String[] columnNames = {"Category Name", "Category ID"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Category c : repo.catList) {
            model.addRow(new Object[]{c.getName(), c.getCategoryID()});
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(frame, scrollPane, "Category List", JOptionPane.PLAIN_MESSAGE);
    }

    private void onExpenseEntry() {
        if (repo.catList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No categories available. Please add a category first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] categories = repo.catList.stream().map(Category::getName).toArray(String[]::new);
        String selectedCategory = (String) JOptionPane.showInputDialog(frame, "Choose Category:", "Expense Entry", JOptionPane.QUESTION_MESSAGE, null, categories, categories[0]);
        
        if (selectedCategory != null) {
            Category selectedCat = repo.catList.stream().filter(cat -> cat.getName().equals(selectedCategory)).findFirst().orElse(null);
            if (selectedCat != null) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter Amount:", "Expense Entry", JOptionPane.PLAIN_MESSAGE);
                String remark = JOptionPane.showInputDialog(frame, "Enter Remark:", "Expense Entry", JOptionPane.PLAIN_MESSAGE);
                String dateAsString = JOptionPane.showInputDialog(frame, "Enter date (DD/MM/YYYY):", "Expense Entry", JOptionPane.PLAIN_MESSAGE);

                if (amountStr != null && remark != null && dateAsString != null) {
                    try {
                        float amount = Float.parseFloat(amountStr);
                        Date date = DateUtil.stringToDate(dateAsString);

                        Expense exp = new Expense();
                        exp.setCategoryID(selectedCat.getCategoryID());
                        exp.setAmount(amount);
                        exp.setRemark(remark);
                        exp.setDate(date);
                        repo.expList.add(exp);

                        JOptionPane.showMessageDialog(frame, "Expense added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format. Please use DD/MM/YYYY.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void onExpenseList() {
        String[] columnNames = {"Category Name", "Amount", "Remark", "Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Expense exp : repo.expList) {
            String catName = reportService.getCategoryNameById(exp.getCategoryID());
            String dateString = DateUtil.dateToString(exp.getDate());
            model.addRow(new Object[]{catName, exp.getAmount(), exp.getRemark(), dateString});
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        JOptionPane.showMessageDialog(frame, scrollPane, "Expense List", JOptionPane.PLAIN_MESSAGE);
    }

    private void onMonthlyExpenseList() {
        Map<String, Float> resultMap = reportService.MonthlyTotal();
        StringBuilder result = new StringBuilder("Monthly List:\n\n");
        for (Map.Entry<String, Float> entry : resultMap.entrySet()) {
            String[] arr = entry.getKey().split(",");
            String year = arr[0];
            Integer monthNo = Integer.valueOf(arr[1]);
            String monthName = DateUtil.getMonthName(monthNo);
            result.append(String.format("%s, %s : %.2f\n", year, monthName, entry.getValue()));
        }
        JTextArea textArea = new JTextArea(result.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(frame, scrollPane, "Monthly Expense List", JOptionPane.PLAIN_MESSAGE);
    }

    private void onYearlyExpenseList() {
        Map<Integer, Float> resultMap = reportService.YearlyTotal();
        StringBuilder result = new StringBuilder("Yearly Total:\n\n");
        Float total = 0.0f;
        for (Map.Entry<Integer, Float> entry : resultMap.entrySet()) {
            Float exp = entry.getValue();
            total += exp;
            result.append(String.format("%d : %.2f\n", entry.getKey(), exp));
        }
        result.append("----------------------\n");
        result.append(String.format("Total Amount spent: %.2f", total));
        JTextArea textArea = new JTextArea(result.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(frame, scrollPane, "Yearly Expense List", JOptionPane.PLAIN_MESSAGE);
    }

    private void onCategoryExpenseList() {
        Map<String, Float> resultMap = reportService.CategoryTotal();
        StringBuilder result = new StringBuilder("Category Total:\n\n");
        Float total = 0.0f;
        for (Map.Entry<String, Float> entry : resultMap.entrySet()) {
            Float catTotal = entry.getValue();
            total += catTotal;
            result.append(String.format("%s : %.2f\n", entry.getKey(), catTotal));
        }
        result.append("----------------------------\n");
        result.append(String.format("Total Amount spent: %.2f", total));
        JTextArea textArea = new JTextArea(result.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(frame, scrollPane, "Categorized Expense List", JOptionPane.PLAIN_MESSAGE);
    }
}