package zad1;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;

class CountryTable extends JTable {
    private int flagIndex;
    private int dateOfModifyIndex;
    private int populationIndex;
    private ArrayList<ArrayList<Serializable>> countries = new ArrayList<>();
    private ArrayList<String> columnsName = new ArrayList<>();

    public CountryTable(String path) {
        try {
            FileReader fr = new FileReader(path);
            Scanner sc = new Scanner(fr);
            boolean first = true;
            while (sc.hasNextLine()) {
                if (first) {
                    String[] tmp = sc.nextLine().split("\t");
                    columnsName.addAll(Arrays.asList(tmp));
                    populationIndex = columnsName.indexOf("Population");
                    dateOfModifyIndex = columnsName.indexOf("Modification Date");
                    flagIndex = columnsName.indexOf("Flag");
                    first = false;
                } else {
                    String[] tmp = sc.nextLine().split("\t");
                    ArrayList<Serializable> columns = new ArrayList<>(columnsName.size());
                    for (int i = 0; i < tmp.length; i++) {
                        if (i == populationIndex) {
                            columns.add(Integer.parseInt(tmp[i]));
                        } else if (i == flagIndex) {
                            columns.add(new ImageIcon(tmp[i]));
                        } else {
                            columns.add(tmp[i]);
                        }
                    }
                    columns.add("");
                    columns.add("");
                    countries.add(columns);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    JTable create() {
        JTable jt = new JTable(new MyTableModel());
        jt.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setForeground(((Integer) value) > 20_000_000 ? Color.RED : Color.BLACK);
                return c;
            }
        });

        jt.setPreferredScrollableViewportSize(jt.getPreferredSize());

        return jt;
    }

    class MyTableModel extends AbstractTableModel {

        @Override
        public String getColumnName(int col) {
            return columnsName.get(col);
        }

        @Override
        public int getColumnCount() {
            return columnsName.size();
        }

        @Override
        public int getRowCount() {
            return countries.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(columnIndex < countries.get(rowIndex).size())
                return countries.get(rowIndex).get(columnIndex);
            else
                return null;
        }

        @Override
        public Class getColumnClass(int c) {
            if (c == populationIndex)
                return Integer.class;
            else if (c == flagIndex)
                return ImageIcon.class;
            else
                return String.class;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col == populationIndex;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            countries.get(row).set(col, (Serializable) value);
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            countries.get(row).set(dateOfModifyIndex, df.format(new Date()));
            fireTableCellUpdated(row, col);
        }
    }
}
