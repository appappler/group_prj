package kr.co.sist.admin.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * 
 */
public class SubTabPanel extends JPanel {
	
	private static final long serialVersionUID = 13660220831488592L;
	private DefaultTableModel tableModel;
    private JTable table;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnSave;  // 추가


    /**
     * 공통 탭 패널 구성자
     * @param columnNames 테이블 컬럼명 배열
     * @param listener 이벤트 리스너 (EmpTabViewEvt 등)
     */
    public SubTabPanel(String[] columnNames, ActionListener listener) {
        setLayout(null);
        initTable(columnNames);
        initButtons(listener);
    }

    // 📌 테이블 초기화
    private void initTable(String[] columnNames) {
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        
        JTableHeader jthTable = table.getTableHeader();
        jthTable.setFont(new Font("Dialog", Font.BOLD, 16));
        jthTable.setForeground(Color.white);
        jthTable.setBackground(new Color(8, 60, 80));
        jthTable.setPreferredSize(new Dimension(jthTable.getWidth(), 30));
        jthTable.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 30, 500, 120);
        add(scrollPane);
        
        centerTableCells(table);

    }

    // 📌 버튼 초기화 및 이벤트 연결
    private void initButtons(ActionListener listener) {
        btnAdd = createButton("행 추가", 467, 5, listener);
        btnDelete = createButton("행 삭제", 567, 5, listener);
        btnSave = createButton("저장", 567, 195, listener);  // 저장 버튼 추가

    }

    // 📌 버튼 생성 공통 메서드
    private JButton createButton(String title, int x, int y, ActionListener listener) {
        JButton btn = new JButton(title);
        btn.setBounds(x, y, 81, 23);
        btn.addActionListener(listener);
        add(btn);
        return btn;
    }
    
    private void centerTableCells(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 컬럼 헤더도 가운데 정렬하고 싶다면:
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    // 📌 테이블 수정 가능 여부 설정
    public void setTableEditable(boolean editable) {
        table.setDefaultEditor(Object.class, editable ? new DefaultCellEditor(new JTextField()) : null);
    }

    // 📌 버튼 표시 여부 일괄 설정
    public void setButtonsVisible(boolean visible) {
        if (btnAdd != null) btnAdd.setVisible(visible);
        if (btnDelete != null) btnDelete.setVisible(visible);
        if (btnSave != null) btnSave.setVisible(visible);
    }
    
    public void resetTable() {
        tableModel.setRowCount(0);
    }

    // ✅ Getter 메서드들
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getTable() { return table; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnSave() { return btnSave; }

    
}//class