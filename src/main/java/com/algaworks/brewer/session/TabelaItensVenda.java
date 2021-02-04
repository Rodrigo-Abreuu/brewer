package com.algaworks.brewer.session;

import java.util.Optional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.ItemVenda;

@SessionScope
@Component
public class TabelaItensVenda {

	private List<ItemVenda> itens = new ArrayList<>(); 
	
	public BigDecimal getValorTotal(){
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Cerveja cerveja, Integer quantidade){
		Optional<ItemVenda> itemVendaOptional = itens.stream()
			.filter(i -> i.getCerveja().equals(cerveja))
			.findAny();
		
		ItemVenda itemVenda = null;
		if(itemVendaOptional.isPresent()){
			itemVenda = itemVendaOptional.get();
			itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
		} else {
			ItemVenda itenVenda = new ItemVenda();
			itenVenda.setCerveja(cerveja);
			itenVenda.setQuantidade(quantidade);
			itenVenda.setValorUnitario(cerveja.getValor());
			itens.add(0, itenVenda);
		}
	}
	
	public int total(){
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	
}
