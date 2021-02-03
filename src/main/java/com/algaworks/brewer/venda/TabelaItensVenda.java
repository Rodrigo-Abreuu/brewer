package com.algaworks.brewer.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.ItemVenda;

public class TabelaItensVenda {

	private List<ItemVenda> itens = new ArrayList<>(); 
	
	public BigDecimal getValorTotal(){
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Cerveja cerveja, Integer quantidade){
		ItemVenda itenVenda = new ItemVenda();
		itenVenda.setCerveja(cerveja);
		itenVenda.setQuantidade(quantidade);
		itenVenda.setValorUnitario(cerveja.getValor());
		
		itens.add(itenVenda);
	}
	
}
