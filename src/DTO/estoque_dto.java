package DTO;

public class estoque_dto {
    private String codigo_estoque;
    private int quantidade_estoque;
    private String descricao_estoque;
    private float valor_estoque;

    public String getCodigo_estoque() {
        return codigo_estoque;
    }

    public void setCodigo_estoque(String codigo_estoque) {
        this.codigo_estoque = codigo_estoque;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public String getDescricao_estoque() {
        return descricao_estoque;
    }

    public void setDescricao_estoque(String descricao_estoque) {
        this.descricao_estoque = descricao_estoque;
    }

    public float getValor_estoque() {
        return valor_estoque;
    }

    public void setValor_estoque(float valor_estoque) {
        this.valor_estoque = valor_estoque;
    }
}
