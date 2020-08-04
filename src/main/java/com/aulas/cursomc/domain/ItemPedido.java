package com.aulas.cursomc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();
    @Getter
    @Setter
    private Double desconto;
    @Getter
    @Setter
    private Integer quantidade;
    @Getter
    @Setter
    private Double preco;

    public Pedido getPedido(){
        return id.getPedido();
    }

    public Produto getProduto(){
        return id.getProduto();
    }
    public ItemPedido(){

    }

    public ItemPedido(Pedido pedido, Produto produto,Double desconto, Integer quantidade, Double preco) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
