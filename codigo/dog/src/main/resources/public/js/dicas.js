onload = () => {
    fetchJson();
}

async function fetchJson(url = 'http://52.167.125.62:4567/dicas', method = 'GET') {
    const response = await fetch(url, { method });
    const data = await response.json();

    var div = document.getElementById("tela");

    console.log(data);

    for (let i = 0; i < data.length; i++) {

        let aux = new cards(data[i].id_dica, data[i].titulo, data[i].descricao, data[i].user_dica, data[i].img_dica);

        div.appendChild(aux.construir());
    }
}

class cards {

    constructor(id_dica, titulo, descricao, user_dica, img) {
        this.id_dica = id_dica;
        this.titulo = titulo;
        this.descricao = descricao;
        this.user_dica = user_dica;
        this.img = img;
    }

    construir() {

        let card = document.createElement("div");
        card.innerHTML = `
        <div class="card card-dica">
            <img src="http://52.167.125.62:4567/${this.img}" class="card-img-top" alt="">
            <div class="card-body">
                <div class="card-text">
                    <h5 class="card-title"> ${this.titulo}</h5>
                    <p> ${this.descricao}</p>
                </div>
            </div>   
        </div>
        `;

        return card;
    }
}