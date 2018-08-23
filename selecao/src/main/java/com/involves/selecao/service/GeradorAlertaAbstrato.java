package com.involves.selecao.service;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

public abstract class GeradorAlertaAbstrato {
	protected AlertaGateway gateway;
	protected Alerta alerta;
	protected Pesquisa pesquisa;
	protected Resposta resposta;
	
	public GeradorAlertaAbstrato(AlertaGateway gateway, Alerta alerta, Pesquisa pesquisa, Resposta resposta) {
		super();
		this.gateway = gateway;
		this.alerta = alerta;
		this.pesquisa = pesquisa;
		this.resposta = resposta;
	}
}
