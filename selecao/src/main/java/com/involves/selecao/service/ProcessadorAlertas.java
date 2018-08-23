package com.involves.selecao.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class ProcessadorAlertas {

	@Autowired
	private AlertaGateway gateway;
	
	public void processa() throws IOException {
		URL url = new URL("https://selecao-involves.agilepromoter.com/pesquisas");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		  new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		Pesquisa[] ps =  gson.fromJson(content.toString(), Pesquisa[].class);
		
		for (int i = 0; i < ps.length; i++){
			Pesquisa pesquisa = ps[i];			
			pesquisa.getRespostas().forEach(resposta -> processarResposta(resposta, pesquisa)); 
		}
	}
	
	private void processarResposta(Resposta resposta, Pesquisa pesquisa) {
		if (resposta.getPergunta().equals("Qual a situação do produto?")) {
			if(resposta.getResposta().equals("Produto ausente na gondola")){
				
			    GeradorAlerta geradorAlertaRuptura = new GeradorAlertaRuptura(gateway, new Alerta(), pesquisa, resposta);  
			    geradorAlertaRuptura.gerarAlerta();
			}
		} else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
								    
		    GeradorAlerta geradorAlertaPreco = new GeradorAlertaPreco(gateway, new Alerta(), pesquisa, resposta);		
		    geradorAlertaPreco.gerarAlerta();
			
		} else if(resposta.getPergunta().equals("%Share")) {
								    
		    GeradorAlerta geradorAlertaParticipacao = new GeradorAlertaParticipacao(gateway, new Alerta(), pesquisa, resposta);		
		    geradorAlertaParticipacao.gerarAlerta();
		} else {
			System.out.println("Alerta ainda não implementado!");
		}
	}
}

