package view;

import model.Duvida;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DuvidaTableModel extends AbstractTableModel {

   private final String[] colunas = {
    "ID", "Aluno(ID)", "Prof(ID)", "Prioridade", "Status", "Título", "Resposta", "Criada em", "Resolvida em"
};


    private List<Duvida> dados = new ArrayList<>();

    public void setDados(List<Duvida> lista) {
        this.dados = (lista == null) ? new ArrayList<>() : lista;
        fireTableDataChanged();
    }

    public Duvida getDuvidaAt(int row) {
        if (row < 0 || row >= dados.size()) return null;
        return dados.get(row);
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
   
    public Object getValueAt(int rowIndex, int columnIndex) {
    Duvida d = dados.get(rowIndex);

    return switch (columnIndex) {
        case 0 -> d.getIdDuvida();
        case 1 -> d.getIdAluno();
        case 2 -> d.getIdProfessor();
        case 3 -> d.getPrioridade();
        case 4 -> d.getStatusAtendimento();
        case 5 -> d.getTitulo();
        case 6 -> d.getResposta();         // ✅ NOVO
        case 7 -> d.getDataCriacao();
        case 8 -> d.getDataResolucao();
        default -> "";
    };
}

}
