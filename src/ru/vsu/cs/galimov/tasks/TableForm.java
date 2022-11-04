package ru.vsu.cs.galimov.tasks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TableForm extends JFrame implements ActionListener {
    private JLabel ownersLabel;
    private JButton solveButton;
    private JTextField size;
    private JButton changeSize;
    private JLabel sizeLabel;
    private JLabel sortedLabel;
    private JTable tableMatrix;
    private JTable sortedTable;
    private JScrollPane scrollPaneMatrix;
    private JScrollPane scrollPaneSorted;

    public TableForm() {
        this.setTitle("Таблица");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container formElementsContainer = getContentPane();
        formElementsContainer.setLayout(null);

        tableMatrix = new JTable(3,5);
        scrollPaneMatrix = new JScrollPane(tableMatrix);
        scrollPaneMatrix.setSize(500, 300);
        scrollPaneMatrix.setLocation(0, 60);
        tableMatrix.getTableHeader().setVisible(false);
        formElementsContainer.add(scrollPaneMatrix);

        ownersLabel = new JLabel("queue of owners");
        ownersLabel.setSize(100, 10);
        ownersLabel.setLocation(250, 25);
        add(ownersLabel);

        size = new JTextField(5);
        size.setSize(50, 50);
        size.setLocation(70, 0);
        formElementsContainer.add(size);

        sizeLabel = new JLabel("Enter size: ");
        sizeLabel.setLabelFor(size);
        sizeLabel.setSize(80, 10);
        sizeLabel.setLocation(0, 25);
        add(sizeLabel);

        solveButton = new JButton("Solve");
        solveButton.setSize(100, 50);
        solveButton.setLocation(0, 350);
        solveButton.addActionListener(this);
        formElementsContainer.add(solveButton);

        changeSize = new JButton("Change size");
        changeSize.setSize(150, 50);
        changeSize.setLocation(240, 350);
        changeSize.addActionListener(this);
        formElementsContainer.add(changeSize);

        this.repaint();
        this.setSize(500, 500);
        this.setVisible(true);
    }

    // обработка событий
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solveButton) { // при нажатии на кнопку посчитать запускается функция handleSolveButtonClick()
            handleSolveButtonClick();
        }
        else if (e.getSource() == changeSize) {
            setTableSize();
        }
    }

    private void setTableSize() {
        if(sortedTable != null){
            remove(sortedTable);
            remove(scrollPaneSorted);
            remove(sortedLabel);
            this.repaint();
        }
        try {
            int length = Integer.parseInt(size.getText());
            if (length < 1) throw new NumberFormatException(); // если данные некорректные кидаем исключение

            remove(scrollPaneMatrix); // удаляем старую таблицу
            tableMatrix = new JTable(3, length); // создаем новую
            scrollPaneMatrix = new JScrollPane(tableMatrix);
            scrollPaneMatrix.setSize(500, 300);
            scrollPaneMatrix.setLocation(0, 60);
            tableMatrix.getTableHeader().setVisible(false);
            add(scrollPaneMatrix);// добавляем новую таблицу в окошко нашего приложения

            this.repaint();

        } catch (NumberFormatException nfe) {
            displayError("enter data correctly");
        }
    }

    private void convertArrayToTable(int[] result) {
        sortedLabel = new JLabel("time of leave");
        sortedLabel.setSize(80, 10);
        sortedLabel.setLocation(580, 25);
        add(sortedLabel);

        this.repaint();

        sortedTable = new JTable(1,result.length);
        scrollPaneSorted = new JScrollPane(sortedTable);
        scrollPaneSorted.setSize(200, 50);
        scrollPaneSorted.setLocation(520, 60);
        sortedTable.getTableHeader().setVisible(false);
        add(scrollPaneSorted);

        this.repaint();
        for (int i = 0; i < result.length; i++) {
            sortedTable.getModel().setValueAt(result[i], 0, i); // заполняем таблицу данными
        }
    }

    private void handleSolveButtonClick() {
        try {
            int[][] source = convertTableToArray(tableMatrix);
            LinkedQueue queue = LinkedQueue.createList(source);
            queue.sort();
            int[] result = queue.countTimeOfDeal();
            convertArrayToTable(result);

            displayMessage("Выполнено успешно");
        } catch (NumberFormatException exception) {
            displayError("Таблица должна быть заполнена числами");
        }
    }

    private int[][] convertTableToArray(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel(); // берем модель таблицы

        int[][] array = new int[tableModel.getRowCount()][table.getColumnCount()]; // создаем массив

        for (int r = 0; r < tableModel.getRowCount(); r++) {
            for (int c = 0; c < tableModel.getColumnCount(); c++) {
                array[r][c] = Integer.parseInt(String.valueOf(tableModel.getValueAt(r, c))); // заполняем массив данными из модели таблицы
            }
        }

        return array;
    }

    private void displayMessage(String messageText) {
        JOptionPane.showMessageDialog(this, messageText,
                "Сообщение", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayError(String errorText) {
        JOptionPane.showMessageDialog(this, errorText,
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
