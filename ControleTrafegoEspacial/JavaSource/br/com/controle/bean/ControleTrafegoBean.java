package br.com.controle.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DualListModel;

import br.com.controle.model.People;
import br.com.controle.model.Planet;
import br.com.controle.model.PlanoVoo;
import br.com.controle.model.SWModelList;
import br.com.controle.model.Vehicle;
import br.com.controle.util.TrafegoUtil;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;


@RequestScoped
@ManagedBean (name = "trafego")
public class ControleTrafegoBean {
	
	private PlanoVoo planoVoo;
	
	public List<PlanoVoo> listaPlanoVoo = new ArrayList<PlanoVoo>();
	public List<Planet> listaPlanetas = new ArrayList<Planet>();
	public List<Vehicle> listaVeiculos = new ArrayList<Vehicle>();
	public List<People> listaPessoas = new ArrayList<People>();
	
	private DualListModel<People> selectPassagens;
	private DualListModel<Planet> selectPlanetas;
	
	@PostConstruct
	public void init(){
		
		if(listaVeiculos.isEmpty()){
			carregarDadosAeronave();
		}
		
		if(listaPessoas.isEmpty()){
			carregarDadosPessoas();
		}
		
		if(listaPlanetas.isEmpty()){
			carregarDadosPlanetas();
		}
		
        //Pessoas
        List<People> peopleSource = listaPessoas;
        List<People> peopleTarget = new ArrayList<People>();
        selectPassagens = new DualListModel<People>(peopleSource, peopleTarget);
        
        //Pessoas
        List<Planet> planetSource = listaPlanetas;
        List<Planet> planetTarget = new ArrayList<Planet>();
        selectPlanetas = new DualListModel<Planet>(planetSource, planetTarget);
	}
	
	@SuppressWarnings("unchecked")
	public void carregarDadosAeronave(){
		listaVeiculos.clear();
		Gson jsonParser = new Gson();
		SWModelList<Vehicle> veiculo = null;
		Vehicle item = null;
		String url = null;
		try {
			do {
				
				if(veiculo == null){
					url = TrafegoUtil.URL_VEICULO;
				}else{
					if(veiculo.hasMore()){
						url = veiculo.getNext();
					}else{
						url = "";
					}
				}
				String json = TrafegoUtil.readUrl(url);
				veiculo = jsonParser.fromJson(json, SWModelList.class);
				for (Object p : veiculo.getResults()) {
					item = new Vehicle();
					item.setModel(((LinkedTreeMap<String, String>)p).get("model"));
					item.setName(((LinkedTreeMap<String, String>)p).get("name"));
					try {
						item.setPassengers(Integer.parseInt(((LinkedTreeMap<String, String>)p).get("passengers")));
					} catch (Exception e) {
						item.setPassengers(0);
						e.printStackTrace();
					}
					listaVeiculos.add(item);
				}
			} while (veiculo.hasMore());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void carregarDadosPessoas(){
		listaPessoas.clear();
		Gson jsonParser = new Gson();
		SWModelList<People> pessoas = null;
		People item = null;
		String url = null;
		int indice = 0;
		try {
			do {
				
				if(pessoas == null){
					url = TrafegoUtil.URL_PESSOAS;
				}else{
					if(pessoas.hasMore()){
						url = pessoas.getNext();
					}else{
						url = "";
					}
				}
				
				String json = TrafegoUtil.readUrl(url);
				pessoas = jsonParser.fromJson(json, SWModelList.class);
				for (Object p : pessoas.getResults()) {
					item = new People();
					item.setName(((LinkedTreeMap<String, String>)p).get("name"));
					item.setGender(((LinkedTreeMap<String, String>)p).get("gender"));
					item.setId(indice++);
					listaPessoas.add(item);
				}
			} while (pessoas.hasMore());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void carregarDadosPlanetas(){
		listaPlanetas.clear();
		Gson jsonParser = new Gson();
		SWModelList<Planet> planeta = null;
		Planet item = null;
		String url = null;
		int indice = 0;
		try {
			do {
				if(planeta == null){
					url = TrafegoUtil.URL_PLANETAS;
				}else{
					if(planeta.hasMore()){
						url = planeta.getNext();
					}else{
						url = "";
					}
				}
				String json = TrafegoUtil.readUrl(url);
				planeta = jsonParser.fromJson(json, SWModelList.class);
				
				for (Object p : planeta.getResults()) {
					item = new Planet();
					item.setClimate(((LinkedTreeMap<String, String>)p).get("climate"));
					item.setDiameter(((LinkedTreeMap<String, String>)p).get("diameter"));
					item.setName(((LinkedTreeMap<String, String>)p).get("name"));
					item.setPopulation(((LinkedTreeMap<String, String>)p).get("population"));
					item.setTerrain(((LinkedTreeMap<String, String>)p).get("terrain"));
					item.setId(indice++);
					listaPlanetas.add(item);
				}
			} while (planeta.hasMore());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gerarPlanoVoo(){
		Collections.reverse(listaVeiculos);
		
		for (Planet itemPlaneta : selectPlanetas.getTarget()) {
			planoVoo = new PlanoVoo();
			planoVoo.setPlanetaDestino(itemPlaneta);
			
			int quantidadeTotalPassageiros = selectPassagens.getTarget().size();
			
			for (Vehicle veiculos : listaVeiculos) {
				if(veiculos.getPassengers() > quantidadeTotalPassageiros && !veiculos.isUtilizada()){
					veiculos.setUtilizada(true);
					planoVoo.setAeronave(veiculos);
					break;
				}
			}
			planoVoo.setPessoa(selectPassagens.getTarget());
			listaPlanoVoo.add(planoVoo);
		}
		selectPlanetas.getTarget().clear();
		
	}


	
	//GETTERS AND SETTERS
	
	public PlanoVoo getPlanoVoo() {
		return planoVoo;
	}


	public void setPlanoVoo(PlanoVoo planoVoo) {
		this.planoVoo = planoVoo;
	}

	public List<PlanoVoo> getListaPlanoVoo() {
		return listaPlanoVoo;
	}

	public void setListaPlanoVoo(List<PlanoVoo> listaPlanoVoo) {
		this.listaPlanoVoo = listaPlanoVoo;
	}

	public DualListModel<People> getSelectPassagens() {
		return selectPassagens;
	}

	public void setSelectPassagens(DualListModel<People> selectPassagens) {
		this.selectPassagens = selectPassagens;
	}

	public DualListModel<Planet> getSelectPlanetas() {
		return selectPlanetas;
	}

	public void setSelectPlanetas(DualListModel<Planet> selectPlanetas) {
		this.selectPlanetas = selectPlanetas;
	}
	
	
}
