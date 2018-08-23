package com.involves.selecao.service;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

public class GeradorAlertaRuptura extends GeradorAlertaAbstrato implements GeradorAlerta {
	
	public GeradorAlertaRuptura(AlertaGateway gateway, Alerta alerta, Pesquisa pesquisa, Resposta resposta) {
		super(gateway, alerta, pesquisa, resposta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gerarAlerta() {
		alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
	    alerta.setDescricao("Ruptura detectada!");
	    alerta.setProduto(pesquisa.getProduto());
	    alerta.setFlTipo(1);
	    gateway.salvar(alerta);
	}

}
