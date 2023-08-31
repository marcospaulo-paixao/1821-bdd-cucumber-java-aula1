package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import br.com.alura.leilao.e2e.pages.NovoLeilaoPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

public class LeilaoSteps {

    private LoginPage loginPage;
    private LeiloesPage leilaoPage;
    private NovoLeilaoPage novoLeilaoPage;
    private Browser browser;

    @Dado("um usuario logado")
    public void um_usuario_logado() {
        browser = new Browser();
        browser.seed();
        loginPage = browser.getLoginPage();
        leilaoPage = loginPage.realizaLoginComoFulano();
    }

    @Quando("acessa a pagina de novo leilao")
    public void acessa_a_pagina_de_novo_leilao() {
        novoLeilaoPage = this.leilaoPage.visitaPaginaParaCriarUmNovoLeilao();
    }

    @Quando("prenche o formulario com dados validos")
    public void prenche_o_formulario_com_dados_validos() {
        this.leilaoPage = this.novoLeilaoPage.preencheForm("PC Novo", "1500", "01/11/2020");
    }

    @Entao("volta para a pagina de leiloes")
    public void volta_para_a_pagina_de_leiloes() {
        Assert.assertTrue(this.leilaoPage.estaNaPaginaDeLeiloes());
    }

    @Entao("o novo leilao aparece na tabela")
    public void o_novo_leilao_aparece_na_tabela() {
        this.leilaoPage.existe("PC Novo", "1500", "01/11/2020", "fulano");
        this.browser.clean();
    }
}
