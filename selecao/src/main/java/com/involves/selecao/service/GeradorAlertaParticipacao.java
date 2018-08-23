package com.involves.selecao.service;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

public class GeradorAlertaParticipacao extends GeradorAlertaAbstrato implements GeradorAlerta{
	
	public GeradorAlertaParticipacao(AlertaGateway gateway, Alerta alerta, Pesquisa pesquisa, Resposta resposta) {
		super(gateway, alerta, pesquisa, resposta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gerarAlerta() {		
		int participacaoColetada = Integer.parseInt(resposta.getResposta());
		int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacao_estipulada());
		if(participacaoColetada > participacaoEstipulada){
		    int margem = participacaoEstipulada - Integer.parseInt(resposta.getResposta());
		    alerta.setMargem(margem);
		    alerta.setDescricao("Participação superior ao estipulado!");
		    alerta.setProduto(pesquisa.getCategoria());
		    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
		    alerta.setFlTipo(4);
		    gateway.salvar(alerta);
		} else if(participacaoColetada < participacaoEstipulada){
		    int margem = participacaoEstipulada - Integer.parseInt(resposta.getResposta());
		    alerta.setMargem(margem);
		    alerta.setDescricao("Participação inferior do estipulado!");
		    alerta.setProduto(pesquisa.getCategoria());
		    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
		    alerta.setFlTipo(5);
		    gateway.salvar(alerta);
		}		
	}

}
