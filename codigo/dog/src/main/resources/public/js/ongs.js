onload = () => {
    fetchJson();
}

async function fetchJson(url = 'http://52.167.125.62:4567/ongs', method = 'GET') {
    const response = await fetch(url, { method });
    const data = await response.json();

    var div = document.getElementById('JSarea');

    console.log(data);

    for (let i = 0; i < data.length; i++) {

        let aux = new cards(data[i].nome, data[i].cnpj, data[i].celular, data[i].sobrenome, data[i].email);

        div.appendChild(aux.construir());
    }
}

class cards {

    constructor(nome, cnpj, cel, cidade, email) {

        this.nome = nome;
        this.cnpj = cnpj;
        this.cel = cel;
        this.cidade = cidade;
        this.email = email;
    }

    construir() {

        let card = document.createElement("div");

        card.innerHTML = `
        <div class="col-12">
            <h2 class="nomeOng"><b>${this.nome}</b></h2>
                <p id="cnpj"><b>CNPJ:</b> ${this.cnpj}</p>
                <p id="telefone"><b>Telefone:</b> ${this.cel}</p>
                <p id="endereco"><b>Cidade:</b> ${this.cidade}</p>
                <p id="email"><b>Email:</b> ${this.email}</p>
        </div>
        `;

        return card;
    }
}