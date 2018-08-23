package com.involves.selecao.service;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

public class GeradorAlertaPreco extends GeradorAlertaAbstrato implements GeradorAlerta {	

	public GeradorAlertaPreco(AlertaGateway gateway, Alerta alerta, Pesquisa pesquisa, Resposta resposta) {
		super(gateway, alerta, pesquisa, resposta);
	}

	@Override
	public void gerarAlerta() {
		int precoColetado = Integer.parseInt(resposta.getResposta());
		int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
		if(precoColetado > precoEstipulado){
		    int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
		    alerta.setMargem(margem);
		    alerta.setDescricao("Preço acima do estipulado!");
		    alerta.setProduto(pesquisa.getProduto());
		    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
		    alerta.setFlTipo(2);
		    gateway.salvar(alerta);
		} else if(precoColetado < precoEstipulado){
		    int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
		    alerta.setMargem(margem);
		    alerta.setDescricao("Preço abaixo do estipulado!");
		    alerta.setProduto(pesquisa.getProduto());
		    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
		    alerta.setFlTipo(3);
		    gateway.salvar(alerta);
		}		
	}

}
