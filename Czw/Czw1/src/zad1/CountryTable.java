package zad1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.*;

class CountryTable extends JTable {
    private ArrayList<ArrayList<Serializable>> countries = new ArrayList<>();
    private ArrayList<String> columnsName = new ArrayList<>();
    private int populationIndex;

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
                    first = false;
                } else {
                    String[] tmp = sc.nextLine().split("\t");
                    ArrayList<Serializable> columns = new ArrayList<>(columnsName.size());
                    for(int i = 0; i < tmp.length; i++)
                        if (i == populationIndex) columns.add(Integer.parseInt(tmp[i]));
                        else columns.add(tmp[i]);
                    countries.add(columns);
                }
            }
            System.out.println(columnsName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    JTable create() {
        return new JTable(new MyTableModel());
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
            return countries.get(rowIndex).get(columnIndex);
        }

        @Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

        @Override
        public boolean isCellEditable(int row, int col) {
            return col == populationIndex;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            countries.get(row).set(col, (Serializable) value);
            fireTableCellUpdated(row, col);
        }

    }
}
